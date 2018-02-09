package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeMotors extends Subsystem {

	Victor left = new Victor(RobotMap.LEFT_INTAKE);
	Victor right = new Victor(RobotMap.RIGHT_INTAKE);
	
	public IntakeMotors() {
		right.setInverted(true);
	}
	
	public void setPower(double power) {
		right.set(power);
		left.set(power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

