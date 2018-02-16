package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeMotors extends Subsystem {

	Victor left = new Victor(RobotMap.LEFT_INTAKE);
	Victor right = new Victor(RobotMap.RIGHT_INTAKE);
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
		right.set(power);
		left.set(power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeCommand());
    }
}

