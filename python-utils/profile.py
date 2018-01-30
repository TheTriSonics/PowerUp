from math import *
import sys

## state variables named assuming positive acceleration

POSJERK = 0
NOJERK = 1
NEGJERK = 2
END = 3

def vdiff(u, v):
    r = []
    for i in range(len(u)):
        r.append(u[i] - v[i])
    return r
def vsum(u, v):
    r = []
    for i in range(len(u)):
        r.append(u[i] + v[i])
    return r
def smult(s, u):
    r = []
    for i in range(len(u)):
        r.append(s*u[i])
    return r

def accelprofile(v0, v1):
    if v0 == v1:
        return [[v0, 0, 0]]
    accel = 1
    if v1 < v0:
        accel = -1

    ## Do we have time to reach amax?

    deltav = abs(v1-v0)
    T = round(sqrt(deltav/jmax))
    A = jmax*T
    if A < amax:
        t1 = int(T)
        t2 = t1
        t3 = int(2*T)

    else:
        t1 = int( ceil(amax/jmax)) 
        t2 = int( t1 + abs(v1-v0)/amax - amax/jmax) 
        t3 = t2 + t1

    t = 0
    v = v0
    a = 0
    j = 0

    trajectory = [[v,a,j]]
    jm = jmax


    state = POSJERK

    while state != END:
        t += 1
        if state == POSJERK:
            if t > t1:
                if t1 == t2:
                    state = NEGJERK
                    T = abs(a/float(jm))
                    jm = abs(a/ceil(T))

                    j = -accel * jm
                    a += j
                    v += a
                    print T, jm, jmax
                else:
                    state = NOJERK
                    j = 0
                    a = accel * amax
                    v += a
            else:
                j = accel * jm
                a += j
                if abs(a) > amax:
                    a = accel * amax
                v += a
        elif state == NOJERK:
            if t > t2:
                state = NEGJERK
                j = -accel * jm
                a += j
                v += a
            else:
                j = 0
                a += j
                v += a
        elif state == NEGJERK:
            j = -accel*jm
            a += j
            v += a
            if accel * a < 0 or accel * (abs(v)- abs(v1)) > 0:
                j = 0
                a = 0
                v = v1
                state = END

        trajectory.append([v,a,j])
    return trajectory

def evaluateBezier(P, t):
    term = smult((1-t)**3, P[0])
    term = vsum(term, smult(3*t*(1-t)**2, P[1]))
    term = vsum(term, smult(3*t*t*(1-t), P[2]))
    return vsum(term, smult(t**3, P[3]))

def evaluateBezierPrime(P, t):
    term = smult(-3*(1-t)**2, P[0])
    term = vsum(term, smult(9*t*t-12*t+3, P[1]))
    term = vsum(term, smult(6*t-9*t*t, P[2]))
    return vsum(term, smult(3*t*t, P[3]))

def length(v):
    r = 0
    for p in v:
        r += p*p
    return sqrt(r)

def theta(v):
    return atan2(v[1], v[0])

def speed(P, t):
    return length(evaluateBezierPrime(P,t))

def angle(P, t):
    v = evaluateBezierPrime(P,t)
    return theta(v)

def multiplyrow(m, j, s):
    for k in range(len(m[j])):
        m[j][k] *= s
        
def rowreplace(m, j, k):
    mult = -m[k][j]
    for i in range(j, len(m[k])):
        m[k][i] += mult*m[j][i]

def findP1(K, comp):
    N = len(K)-1
    matrix = []
    for i in range(N):
        matrix.append([0.0]*N)
    matrix[0][0] = 2.0
    matrix[0][1] = 1.0
    matrix[0].append(K[0][comp] + 2.0*K[1][comp])

    for i in range(1, N-1):
        matrix[i][i-1] = 1.0
        matrix[i][i] = 4.0
        matrix[i][i+1] = 1.0
        matrix[i].append(4.0*K[i][comp] + 2.0*K[i+1][comp])

    matrix[N-1][N-2] = 2.0
    matrix[N-1][N-1] = 7.0
    matrix[N-1].append(8.0*K[N-1][comp] + K[N][comp])

    ## forward elimination
    for i in range(N):
        multiplyrow(matrix, i, 1/float(matrix[i][i]))
        if i < N-1: rowreplace(matrix, i, i+1)

    for i in range(N-1, 0, -1):
        rowreplace(matrix, i, i-1)

    P1 = []
    for i in range(N):
        P1.append(matrix[i][N])
    return P1

def findP2(K, P1, comp):
    N = len(K) -1
    P2 = []
    for i in range(N-1):
        P2.append(2.0*K[i+1][comp] - P1[i+1])
    P2.append((K[N][comp] + P1[N-1])/2.0)
    return P2
    

def buildtrajectory(K):
    P1x = findP1(K, 0)
    P1y = findP1(K, 1)
    P2x = findP2(K, P1x, 0)
    P2y = findP2(K, P1y, 1)

    beziers = []
    for i in range(len(K)-1):
        curve = [K[i], [P1x[i], P1y[i]], [P2x[i], P2y[i]], K[i+1]]
        beziers.append(curve)
    return beziers

def findDecelPoint(bezier, velocity):
    b = bezier[:]
    b.reverse()

    accel = accelprofile(velocity[2], velocity[1])
    x = 0

    for i in range(len(accel)-1):
        v0 = accel[i][0]
        v1 = accel[i+1][0]
        distance = (v0+v1)/2.0

        speedx = speed(b,x)
        deltax = distance/speedx
        x1 = x + deltax
        x += 2 * distance/ (speedx + speed(b, x1))

    return 1-x

def takestep(b, x, v0, v1, radius, left, right, heading):
    distance = (v0+v1)/2.0

    speedx = speed(b, x)
    deltax = distance/speedx
    xp = x+deltax
    x1 = x + 2 * distance / (speedx + speed(b, xp))

    dangle = angle(b, x1) - angle(b, x)
    if dangle > pi:
        dangle -= 2*pi
    if dangle < -pi:
        dangle += 2*pi

    rtheta = radius*dangle

    leftdistance = distance - rtheta
    rightdistance = distance + rtheta
    left.append([left[-1][0] + leftdistance,
                 2*leftdistance - left[-1][1]])
    right.append([right[-1][0] + rightdistance,
                  2*rightdistance - right[-1][1]])
    heading.append(angle(b, x1)*180/pi)
    
    return x1

def backonestep(left, right, heading):
    left.pop(-1)
    right.pop(-1)
    heading.pop(-1)

def buildprofile(beziers, velocities, wheelbase):
    radius = wheelbase/2.0

    ## position, velocity
    
    left = [[0,0]]
    right = [[0,0]]
    heading = [0]

    N = len(beziers)
    leftover = 0
    for j in range(N):
        bezier = beziers[j]
        velocity = velocities[j]
        xdecel = findDecelPoint(bezier, velocity)

#        x = leftover/speed(bezier, 0)
        x = leftover

        accel = accelprofile(velocity[0], velocity[1])
        for i in range(len(accel)-1):
            v0 = accel[i][0]
            v1 = accel[i+1][0]
            lastx = x
            x = takestep(bezier, x, v0, v1, radius, left, right, heading)

        if x > xdecel:
            print "Not enough time to implement change in speed in curve", j
            sys.exit(1)
                                 
        while x < xdecel:
            lastx = x
            x = takestep(bezier, x, velocity[1], velocity[1], radius,
                         left, right, heading)

        if (lastx+x)/2.0 > xdecel:
            x = lastx
            backonestep(left, right, heading)

        accel = accelprofile(velocity[1], velocity[2])
        for i in range(len(accel)-1):
            v0 = accel[i][0]
            v1 = accel[i+1][0]
            lastx = x
            x = takestep(bezier, x, v0, v1, radius, left, right, heading)

        if (lastx + x)/2.0 > 1:
            leftover = 1-lastx
            backonestep(left, right, heading)
        else:
            leftover = x - 1


    return [left, right, heading]

def outputprofile(filename, left, right, heading):
    out = open(filename, "w")
    out.write("%5.4f\n" % vmax)
    while len(left) > 0:
        l = left.pop(0)
        r = right.pop(0)
        h = heading.pop(0)
        s = "%5.4f,%5.4f,%5.4f,%5.4f,%5.2f\n" % ( l[0], l[1], r[0], r[1], h )
        out.write(s)
        

## physical units on the field

#fps = 8.0         ## feet/sec (stronghold)
fps = 12.0         ## steamworks
vmax = fps * 12    ## inches/sec
amax = vmax * 1.0  ## inches/sec/sec (reaches vmax in 1/1th seconds)
jmax = amax * 10.0 ## inches/sec/sec (reaches amax in 1/10th seconds)

## units per cycle
##   time measured in cycles from this point
deltat = 0.01   ## 10 ms/cycle
vmax *= deltat
amax *= deltat**2
jmax *= deltat**3

d = 144.0
speedfactor = 0.4

K = [[0,0], [d/2,0], [d, d/2], [d,d], [d, 3*d/2], [3*d/2, 2*d], [2*d,2*d]]

K = [[0,0], [d/4,0], [d/2,0], [7*d/8, d/8], [d,d/2], [d,3*d/4], [d,d]]

velocities = [[0, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, 0]]

#K = [[0,0], [d/2,0], [d,0]]
#velocities = [[0, speedfactor*vmax, speedfactor*vmax],
#              [speedfactor*vmax, speedfactor*vmax, 0]]

K = [[15.0, 30.0], 
[55.125937843434606, 30.22586520809004],
[114.03502828396766, 60.982002063560756],
[155.64970685205066, 113.4795460065194],
[229.6914076809775, 107.11620734676683],
[225.90825508387906, 29.695586986443992]]

defaultVelocities = True #Do you want to use the default velocity list
if defaultVelocities:
    velocities = [[0,speedfactor*vmax,speedfactor*vmax]]
    for x in range(0,int(len(K))-3):
        velocities.append([speedfactor*vmax, speedfactor*vmax, speedfactor*vmax])
    velocities.append([speedfactor*vmax, speedfactor*vmax, 0])
    
'''velocities = [[0, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, speedfactor*vmax],
              [speedfactor*vmax, speedfactor*vmax, 0]]'''

beziers = buildtrajectory(K)
left, right, heading = buildprofile(beziers, velocities, 25)

left[-1][1] = 0.0
right[-1][1] = 0.0

print "steps =", len(left)
filename = "test.csv"

outputprofile(filename, left, right, heading)

