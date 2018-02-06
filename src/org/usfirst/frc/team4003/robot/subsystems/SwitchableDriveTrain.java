package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class SwitchableDriveTrain extends Subsystem {
	boolean switched = false;
	public void switched() {
		switched = !switched;
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

