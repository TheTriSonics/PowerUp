package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCommand extends Command {

    public ClimberCommand() {
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.getClimbState()) {
    		Robot.climber.setPower(0);
    		return;
    	}
    	double right = -Robot.oi.operator.getY(Hand.kRight);
    	double left = -Robot.oi.operator.getY(Hand.kLeft);
    	if (Math.abs(left) > 0.5) {
    		Robot.climber.setPower(left);
    	} else {
    		Robot.climber.setPower(0.5 * right);
    	}
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
