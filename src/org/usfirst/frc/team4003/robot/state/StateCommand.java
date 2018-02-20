package org.usfirst.frc.team4003.robot.state;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StateCommand extends Command {
	
	boolean advance;
    public StateCommand(boolean advance) {
    	this.advance = advance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//  System.out.print("I am here trying to advance the cube state... or go backwards.");
    	if (advance) Robot.cubeState.advance();
    	else Robot.cubeState.back();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("executing");
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
