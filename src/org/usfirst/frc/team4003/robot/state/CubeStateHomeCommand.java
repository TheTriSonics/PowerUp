package org.usfirst.frc.team4003.robot.state;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeStateHomeCommand extends Command {

    public CubeStateHomeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatics.setState(Pneumatics.PUSHER, false);
    	Robot.pneumatics.setState(Pneumatics.CLAMP, false);
    	Robot.pneumatics.setState(Pneumatics.FLIPPERS, false);
    	Robot.pneumatics.setState(Pneumatics.INTAKE, false);
    	Robot.intake.setState(IntakeMotors.OFF);
    	Robot.cubeState.setState(CubeState.DRIVE);
    	Robot.lift.setState(LiftMotors.GROUND_LEVEL);
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
