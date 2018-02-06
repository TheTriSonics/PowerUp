package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPoint extends Command {
	double speed;
	double targetX, targetY;
	double kAngle = 0.04;
	double distance;
	
    public DriveToPoint(double x, double y, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        targetX = x;
        targetY = y;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double [] position = Robot.sensors.getPosition();
    	double deltaX = targetX - position[0];
    	double deltaY = targetY - position[1];
    	distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    	double theta = Math.atan2(deltaY, deltaX);
    	double heading = Robot.drive.getHeading();
    	double correction = kAngle * (theta - heading);
    	double leftPower = speed - correction;
    	double rightPower = speed + correction;
    	Robot.drive.setPower(leftPower, rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return distance < 4;
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
