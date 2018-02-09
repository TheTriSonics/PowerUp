package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class SwitchableDriveTrain extends Subsystem {
	boolean switched = false;
	double xLimit = 1;
	double yLimit = .8;
	double maxSpeed = 1;
	
	public abstract void setPower(double left, double right);
	
	public void switchDrive() {
		switched = !switched;
	}
	
	public void setMaxSpeed(double speed){
		maxSpeed = speed;
	}
	
	public double getHeading() {
		double offset = 0;
		if (switched) offset = 180;
		return Robot.sensors.getHeading() + offset;
	}
	
	public double normalizeAngle(double angle, double cutPoint) {
		while(angle > cutPoint) angle -= 360;
		while(angle < (cutPoint - 360)) angle += 360;
		return angle;
	}
	
	public boolean isSwitched() {
		return switched;
	}
	
	public void setLimit(double xValue, double yValue){
		yLimit = yValue;
		xLimit = xValue;
	}
	
	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {

	    double leftMotorSpeed;
	    double rightMotorSpeed;
	    moveValue *= yLimit;
	    rotateValue *= xLimit;

	    if (squaredInputs) {
	      // square the inputs (while preserving the sign) to increase fine control
	      // while permitting full power
	      if (moveValue >= 0.0) {
	        moveValue = moveValue * moveValue;
	      } else {
	        moveValue = -(moveValue * moveValue);
	      }
	      if (rotateValue >= 0.0) {
	        rotateValue = rotateValue * rotateValue;
	      } else {
	        rotateValue = -(rotateValue * rotateValue);
	      }
	    }

	    if (moveValue > 0.0) {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = Math.max(moveValue, rotateValue);
	      } else {
	        leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      }
	    } else {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      } else {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	      }
	    }
	    this.setPower(leftMotorSpeed, rightMotorSpeed);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

