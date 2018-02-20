package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToPoint extends Command {
	double speed;
	double targetX, targetY;
	double kAngle = 0.02;
	long timeout;
	double angleError;	

    public RotateToPoint(double x, double y, double speed) {
    	targetX = x;
    	targetY = y;
    	this.speed = speed;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeout = System.currentTimeMillis() + 2000;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double [] position = Robot.sensors.getPosition();
    	double deltaX = targetX - position[0];
    	double deltaY = targetY - position[1];
    	
    	double theta = Math.toDegrees(Math.atan2(deltaY, deltaX));
    	double heading = Robot.drive.getHeading();
    	angleError = Robot.drive.normalizeAngle(theta - heading, 180);
    	double correction = kAngle * (angleError);
    	if (Math.abs(correction) > 1) {
    		if (correction > 1) correction = 1;
    		else correction = -1;
    	}
    	Robot.drive.setPower(-correction*speed, correction*speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(angleError) < 5) || System.currentTimeMillis() > timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
