package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.commands.*;

//import com.allendalerobotics.frc.motionprofiling.commands.ArcadeDrive;
//import com.allendalerobotics.frc.motionprofiling.commands.TankDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class TalonDriveTrain extends SwitchableDriveTrain {
	TalonSRX left1, left2, left3, right1, right2, right3;
		
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
	
	public void setPower(double left, double right) {
		/*
		if(Math.abs(Robot.oi.driver.getTriggerAxis(Hand.kLeft)) > .5 || Robot.inAuton){
			left *= maxSpeed;
			right *= maxSpeed;
		}
		*/
		//System.out.println("left power: " + left);
		left *= maxSpeed;
		right *= maxSpeed;
		if (switched) {
			double temp = left;
			left = -right;
			right = -temp;
		}
		left1.set(ControlMode.PercentOutput, left);
		left2.set(ControlMode.PercentOutput, left);
		left3.set(ControlMode.PercentOutput, left);
		right1.set(ControlMode.PercentOutput, right);
		right2.set(ControlMode.PercentOutput, right);
		right3.set(ControlMode.PercentOutput, right);
		
		
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDrive());
    	
    }
}

