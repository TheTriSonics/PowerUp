package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.profiling.Waypoint;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ExecuteDriveProfile extends Command implements Runnable {

	private double kP = 0.025;
    private final double kAngle = 0.0125;
    private final double vMax = 1.008; // 2.88;
	
	private DriveTrainProfile profile;
	private int currentPoint = 0;
	private long lastTime;
	private Notifier notifier;
	private boolean isNotifierRunning = false;
	private long minElapsedTime = 10000;
	private long maxElapsedTime = 0;
	
    public ExecuteDriveProfile(DriveTrainProfile profile) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	this.profile = profile;
    	notifier = new Notifier(this);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensors.resetDriveEncoder();
    	lastTime = System.currentTimeMillis(); 
    	isNotifierRunning = true;
    	notifier.startPeriodic(0.01);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected void stopNotifier() {
    	isNotifierRunning = false;
    }
    
    protected boolean isNotifierRunning() {
    	return isNotifierRunning;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !isNotifierRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("In End " + minElapsedTime + " " + maxElapsedTime);
    	Robot.drive.setPower(0,0);
    	notifier.stop();
    	//Robot.drive.setLeftOutput(ControlMode.PercentOutput, 0);
    	//Robot.drive.setRightOutput(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("In Run " + currentPoint + " " + profile.length);
    	if(currentPoint == profile.getLeftWaypoints().size()) return;
    	String command = profile.getCommand(currentPoint ).trim();
    	if (command.length() > 0)Robot.startCommand(command);
    	Waypoint leftPoint = (Waypoint) profile.getLeftWaypoints().get(currentPoint);
    	Waypoint rightPoint = (Waypoint) profile.getRightWaypoints().get(currentPoint);
    	double leftPos = leftPoint.position;
    	double leftVel = leftPoint.velocity;
    	double rightPos = rightPoint.position;
    	double rightVel = rightPoint.velocity;
    	double heading = rightPoint.angle;
    	
    	double currentLeftPos = Robot.sensors.getLeftPosition();
    	double currentRightPos = Robot.sensors.getRightPosition();
    	
    	if(Robot.drive.isSwitched()) {
    		double temp = currentLeftPos;
    		currentLeftPos = -currentRightPos;
    		currentRightPos = -temp;
    	}
    	
    	double leftError = leftPos-currentLeftPos;   	
    	double rightError = rightPos-currentRightPos;
    	double angleError = Robot.drive.normalizeAngle(heading - Robot.drive.getHeading(), 180);
    	double correction = kAngle * angleError;
    	if (profile.getLeftWaypoints().size() - currentPoint < 20) correction = 0;
    	if (profile.getLeftWaypoints().size() - currentPoint == 20) kP /= 2.0;
    	SmartDashboard.putNumber("heading", Robot.drive.getHeading());
    	//System.out.println(leftError + " " + rightError + " " + angleError);
    	
    	currentPoint++;
    	
    	double leftPower = leftVel / vMax + (kP * (leftError)) - correction;
    	double rightPower = rightVel / vMax + (kP * (rightError)) + correction;
    	
    	leftPower = leftPower > 1 ? 1 : leftPower;
    	rightPower = rightPower > 1 ? 1 : rightPower;
    	
    	leftPower = leftPower < -1 ? -1 : leftPower;
    	rightPower = rightPower < -1 ? -1 : rightPower;
    	
    	Robot.drive.setPower(leftPower, rightPower);
    	
    	long currentTime = System.currentTimeMillis();
    	long elapsedTime = currentTime - lastTime;
    	if(elapsedTime < minElapsedTime) minElapsedTime = elapsedTime;
    	if(elapsedTime > maxElapsedTime) maxElapsedTime = elapsedTime;
    	lastTime = currentTime;
    	if(currentPoint == profile.getLeftWaypoints().size()) {
    		stopNotifier();
    	}
		
	}
	
}
