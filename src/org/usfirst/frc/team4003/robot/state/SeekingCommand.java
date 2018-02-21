package org.usfirst.frc.team4003.robot.state;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SeekingCommand extends Command {
	Timer timer;
	boolean isFinished = false;
    public SeekingCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.cubeState.getState() != CubeState.DRIVE) {
    		//System.out.println("Initialized improperly");
    		isFinished = true;
    		return;
    	}
    	//System.out.println("Initialized controls");
    	Robot.intake.setState(IntakeMotors.ON);
    	Robot.pneumatics.setState(Pneumatics.INTAKE, true);
    	Robot.pneumatics.setState(Pneumatics.FLIPPERS, true);
    	timer.start();
    	Robot.cubeState.setState(CubeState.SEEKING);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get() < 0.6) return;
    	isFinished = true;
    	Robot.pneumatics.setState(Pneumatics.CLAMP, true);
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
