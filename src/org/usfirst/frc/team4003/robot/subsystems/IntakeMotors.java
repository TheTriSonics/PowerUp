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
	int state = OFF;
	
	public void setState(int s) {
		state = s;
	}
	
	public int getState() {
		return state;
	}
	
	public IntakeMotors() {
		right.setInverted(true);
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
		}
		if (Math.abs(power) < 0.05) power = 0;
		right.set(ControlMode.PercentOutput,power);
		left.set(ControlMode.PercentOutput,power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeCommand());
    }
}

