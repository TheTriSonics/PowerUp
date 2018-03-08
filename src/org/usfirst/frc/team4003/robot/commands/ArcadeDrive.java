package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    double alpha = 0.5; //0.74;
    double turnAlpha = .7;
    double lastTurn = 0;
    double turnAlpham1 = 1-turnAlpha;
    double alpham1 = 1-alpha;
    double lastThrottle = 0;
    double lastSteering = 0;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = -Robot.oi.driver.getY(Hand.kLeft);
    	double steering = -0.8*Robot.oi.driver.getX(Hand.kRight);
    	double power = (alpha * throttle) + (alpham1 * lastThrottle);
    	double turn = (turnAlpha * steering) + turnAlpham1 * lastSteering;
    	
    	if (Robot.oi.driver.getTriggerAxis(Hand.kLeft) > 0.5) {
    		Robot.drive.setMaxSpeed(0.6);
    	} else {
    		Robot.drive.setMaxSpeed(1.0);
    	}
    	
    	Robot.drive.arcadeDrive(power, turn, true);
    	lastThrottle = throttle;
    	lastSteering = steering;
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
