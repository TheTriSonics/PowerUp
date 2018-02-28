package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.IntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeMotors extends Subsystem {

	VictorSPX left = new VictorSPX(RobotMap.LEFT_INTAKE);
	VictorSPX right = new VictorSPX(RobotMap.RIGHT_INTAKE);
	public static final int ON = 0;
	public static final int OFF = 1;
	public static final int MANUAL = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	int state = OFF;
	
	public void setState(int s) {
		state = s;
	}
	
	public int getState() {
		return state;
	}
	
	public IntakeMotors() {
		left.setInverted(true);
	}
	
	public void setPower(double power) {
		switch(state) {
		case(ON): {
			power = 1;
			break;
		}
		case(OFF): {
			power = 0;
			break;
		}
		case(LEFT): {
			setPower(1,-1);
			return;
		}
		case(RIGHT): {
			setPower(-1,1);
			return;
		}
		}
		if (Math.abs(power) < 0.05) power = 0;
		power *= maxPower;
		right.set(ControlMode.PercentOutput,power);
		left.set(ControlMode.PercentOutput,power);
	}
	
	double maxPower = 0.75;
	public void setPower(double leftPower, double rightPower) {
		switch(state) {
		case(ON): {
			break;
		}
		case(OFF): {
			rightPower = 0;
			leftPower = 0;
			break;
		}
		}
		if (Math.abs(rightPower) < 0.05) rightPower = 0;
		if (Math.abs(leftPower) < 0.05) leftPower = 0;
		leftPower *= maxPower;
		rightPower *= maxPower;
		right.set(ControlMode.PercentOutput,rightPower);
		left.set(ControlMode.PercentOutput,leftPower);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeCommand());
    }
}

