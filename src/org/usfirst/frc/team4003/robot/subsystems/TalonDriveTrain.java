package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.OI;
import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.commands.DriveCommand;
//import com.allendalerobotics.frc.motionprofiling.commands.ArcadeDrive;
//import com.allendalerobotics.frc.motionprofiling.commands.TankDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;

/**
 *
 */
public class TalonDriveTrain extends Subsystem {
	TalonSRX left1, left2, left3, right1, right2, right3;
	
	int switchCount = 0;
	double yLimit, xLimit;
	double maxSpeed = 1;
	
	public TalonDriveTrain() {
		left1 = new TalonSRX(3);
		left2 = new TalonSRX(4);
		left3 = new TalonSRX(5);
		right1 = new TalonSRX(6);
		right1.setInverted(true);
		right2 = new TalonSRX(1);
		right2.setInverted(true);
		right3 = new TalonSRX(2);
		right3.setInverted(true);
		
		yLimit = 1;
		xLimit = .8;
	}
	
	public void setMaxSpeed(double speed){
		maxSpeed = speed;
	}
	
	public void setPower(double left, double right) {
		/*
		if(Math.abs(Robot.oi.driver.getTriggerAxis(Hand.kLeft)) > .5 || Robot.inAuton){
			left *= maxSpeed;
			right *= maxSpeed;
		}
		*/
		//System.out.println("left power: " + left);
		left1.set(ControlMode.PercentOutput, left);
		left2.set(ControlMode.PercentOutput, left);
		left3.set(ControlMode.PercentOutput, left);
		right1.set(ControlMode.PercentOutput, right);
		right2.set(ControlMode.PercentOutput, right);
		right3.set(ControlMode.PercentOutput, right);
		
		
		
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
	    /*
	    if(Math.abs(Robot.oi.driver.getTriggerAxis(Hand.kLeft)) > .5){
			leftMotorSpeed *= maxSpeed;
			rightMotorSpeed *= maxSpeed;
		}
	    */
	    
	    
	    left1.set(ControlMode.PercentOutput, leftMotorSpeed);
	    left2.set(ControlMode.PercentOutput, leftMotorSpeed);
	    left3.set(ControlMode.PercentOutput, leftMotorSpeed);
	    right1.set(ControlMode.PercentOutput, rightMotorSpeed);
	    right2.set(ControlMode.PercentOutput, rightMotorSpeed);
	    right3.set(ControlMode.PercentOutput, rightMotorSpeed);
	}
	
	public void switchDirection(){
		TalonSRX temp = left1;
		left1 = right1;
		right1 = temp;
		temp = left2;
		left2 = right2;
		right2 = temp;
		temp = left3;
		left3 = right3;
		right3 = temp;
		left1.setInverted(!left1.getInverted());
		left2.setInverted(!left2.getInverted());
		left3.setInverted(!left3.getInverted());
		right1.setInverted(!right1.getInverted());
		right2.setInverted(!right2.getInverted());
		right3.setInverted(!right3.getInverted());
		switchCount = 1-switchCount;
	}
	
	public int getSwitchCount() {
		return switchCount;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    	
    }
}

