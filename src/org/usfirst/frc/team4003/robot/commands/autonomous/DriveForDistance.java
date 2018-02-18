package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {
	double distance;
	double speed;
	double encoderTarget;
	boolean back = false;
	long timeout;
    public DriveForDistance(double d, double s) {
    	distance = d;
    	speed = s;
    	back = distance < 0;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	encoderTarget = Robot.sensors.getLeftPosition() + distance;
    	timeout = System.currentTimeMillis() + 1500;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = speed;
    	if (back) power *= -1;
    	Robot.drive.setPower(power, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() > timeout) return true;
    	double position = Robot.sensors.getLeftPosition();
        return (back && position < encoderTarget) || (!back && position > encoderTarget);
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
