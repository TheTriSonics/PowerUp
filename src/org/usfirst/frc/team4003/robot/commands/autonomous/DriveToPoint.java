package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPoint extends Command {
	double speed;
	double targetX, targetY;
	double kAngle = 0.01;
	double distance;
	double angleTolerance = 5;
	long timeout;
	double lastDistance = 10000;
	boolean finished = false;
	double currentSpeed = 0.2;
	double slowDistance = 15;
	
    public DriveToPoint(double x, double y, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        targetX = x;
        targetY = y;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensors.resetDriveEncoder();
    	timeout = System.currentTimeMillis() + 3000;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double [] position = Robot.sensors.getPosition();
    	double deltaX = targetX - position[0];
    	double deltaY = targetY - position[1];
    	distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    	double theta = Math.toDegrees(Math.atan2(deltaY, deltaX));
    	double heading = Robot.drive.getHeading();
    	double angleError = Robot.drive.normalizeAngle(theta - heading, 180);
    	double correction = kAngle * (angleError);
    	
    	currentSpeed += 0.02;
    	if (currentSpeed > speed) currentSpeed = speed;
    	
    	double ramp = distance/slowDistance;
    	if (ramp > 1) ramp = 1;
    	if (ramp < 0.5) correction = 0;
    	
    	double leftPower = currentSpeed*ramp - correction;
    	double rightPower = currentSpeed*ramp + correction;
    	double oneside = 0.8;
    	if (angleError > angleTolerance) {
    		leftPower = 0;
    		rightPower = oneside*speed;
    	} else if (angleError < -angleTolerance) {
    		rightPower = 0;
    		leftPower = oneside*speed;
    	}
    	Robot.drive.setPower(leftPower, rightPower);
    	
    	if (lastDistance < distance && distance < 20) finished = true;
    	lastDistance = distance;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() > timeout) return true;
    	return finished || distance < 4;
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
