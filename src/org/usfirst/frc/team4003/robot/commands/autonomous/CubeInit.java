package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.state.CubeState;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeInit extends Command {
	
	Timer timer;
	boolean isFinished = false;
	
    public CubeInit() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatics.setState(Pneumatics.CLAMP, false);
    	Robot.pneumatics.setState(Pneumatics.FLIPPERS, true);
    	timer = new Timer();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() < 0.2) {
    		return;
    	}
    	//timer.stop();
    	Robot.lift.setState(LiftMotors.DRIVING);
    	isFinished = true;
    	Robot.cubeState.setState(CubeState.TRANSPORT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer.get() > 0.4) {
    		timer.stop();
    		return true;
    	}
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
