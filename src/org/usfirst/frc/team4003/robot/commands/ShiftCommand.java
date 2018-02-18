package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftCommand extends Command {

	boolean high;
	
    public ShiftCommand(boolean high) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.high = high;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatics.setState(Pneumatics.SHIFTER, high);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
