package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = -Robot.oi.operator.getY(Hand.kLeft);
    	boolean reverse = Robot.oi.operator.getTriggerAxis(Hand.kLeft) > 0.5;
    	if (Math.abs(power) > 0.1) Robot.intake.setState(IntakeMotors.MANUAL);
    	
    	if (reverse) {
    		Robot.intake.setPower(1, -1);
    	} else {
    		Robot.intake.setPower(power);	
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
