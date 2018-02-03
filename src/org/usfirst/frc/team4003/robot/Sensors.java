package org.usfirst.frc.team4003.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors {
	
	private Encoder rightEncoder;
	private Encoder leftEncoder;
    
	private int leftOffset;
    private int rightOffset;
    private double gyroOffset;
    private AHRS navX;
    private double x, y;
    private int lastLeftEncoder, lastRightEncoder;
    private Object lock = new Object();
    
    public Sensors() {
    	rightEncoder = new Encoder(0, 1);
    	rightEncoder.setReverseDirection(true);
    	leftEncoder = new Encoder(2, 3);
    	leftOffset = 0;
    	rightOffset = 0;
    	gyroOffset = 0;
    	navX = new AHRS(SerialPort.Port.kMXP);
    }
    
    private double readGyro() {
    	return -navX.getYaw();
    }
    
    public AHRS getGyro() {
    	return navX;
    }
    
    public double getHeading() {
    	synchronized(lock) {
    		return readGyro() -gyroOffset;
    	}
    }
    
    public void resetGyro() {
    	gyroOffset = readGyro();
    }
    
	public int getLeftEncoder() {
		return leftEncoder.get() - leftOffset;
	}
	
	public int getRightEncoder() {
		return (rightEncoder.get()) - rightOffset;
	}
	
	public void resetDriveEncoder() {
		leftOffset = leftEncoder.get();
		rightOffset = (rightEncoder.get());
		lastLeftEncoder = 0;
		lastRightEncoder = 0;
	}
	
	public void resetPosition() {
		x = 0;
		y = 0;
	}
	
	public void updatePosition() {
		int leftEncoder = getLeftEncoder();
		int rightEncoder = getRightEncoder();
		int changeLeftEncoder = leftEncoder - lastLeftEncoder;
		int changeRightEncoder = rightEncoder - lastRightEncoder;
		double averageChange = (changeLeftEncoder + changeRightEncoder) / 2.0;
		double heading = Math.toRadians(getHeading());
		x += averageChange * Math.cos(heading);
		y += averageChange * Math.sin(heading);
		lastLeftEncoder = leftEncoder;
		lastRightEncoder = rightEncoder;
	}
	
	public double[] getPosition() {
		return new double[] {
				x / RobotMap.ENCODER_TICKS_PER_INCH,
				y / RobotMap.ENCODER_TICKS_PER_INCH
		};
	}
	
	public double getLeftPosition() {
		return getLeftEncoder() / RobotMap.ENCODER_TICKS_PER_INCH;
	}
	
	public double getRightPosition() {
		return getRightEncoder() / RobotMap.ENCODER_TICKS_PER_INCH;
	}
	
}
