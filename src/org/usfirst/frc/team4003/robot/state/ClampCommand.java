package org.usfirst.frc.team4003.robot.state;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClampCommand extends Command {
	Timer timer = new Timer();
	boolean isFinished = false;

    public ClampCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.cubeState.getState() != CubeState.PULLINGIN) {
    		isFinished = true;
    		return;
    	}
    	Robot.intake.setState(IntakeMotors.OFF);
    	Robot.pneumatics.setState(Pneumatics.CLAMP, false);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get() >= 0.2) {
    		timer.stop();
    		Robot.pneumatics.setState(Pneumatics.FLIPPERS, true);
    		Robot.pneumatics.setState(Pneumatics.INTAKE, false);
    		Robot.cubeState.setState(CubeState.CLAMPCUBE);
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
