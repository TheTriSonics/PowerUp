package org.usfirst.frc.team4003.robot.commands;

import java.util.logging.Level;

import org.usfirst.frc.team4003.robot.CSVReader;
import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.logging.FRCLogger;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PlaybackProfile extends Command implements Runnable {
	
    private final double kP = 0.025;
    private final double kAngle = 0.0125;
	
	private CSVReader reader;
	
	private double[][] profile;
	private double vmax;
	private int currentPoint = 0;
	private long lastTime;
	private Notifier notifier;
	private boolean isNotifierRunning = false;
	private long minElapsedTime = 10000;
	private long maxElapsedTime = 0;
	
    public PlaybackProfile(String filename) {
        requires(Robot.drive);
    	this.reader = new CSVReader(filename);
    	//this.vmax = reader.getVmax();
    	this.vmax = 2.88;
    	this.profile = reader.parseCSV();
    	notifier = new Notifier(this);
    }

    protected void initialize() {
    	Robot.sensors.resetDriveEncoder();
    	lastTime = System.currentTimeMillis(); 
    	isNotifierRunning = true;
    	notifier.startPeriodic(0.01);
    	System.out.println("In Init");
    }
    protected void execute() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    public void run() {
    	//System.out.println("In Run " + currentPoint + " " + profile.length);
    	if(currentPoint == profile.length) return;
    	double[] profilePoint = profile[currentPoint];
    	double leftPos = profilePoint[0];
    	double leftVel = profilePoint[1];
    	double rightPos = profilePoint[2];
    	double rightVel = profilePoint[3];
    	double heading = profilePoint[4];
    	double leftError = leftPos-Robot.sensors.getLeftPosition();
    	double rightError = rightPos-Robot.sensors.getRightPosition();
    	double correction = kAngle * (heading - Robot.sensors.getHeading());
    	
    	
    	currentPoint++;
    	
    	double leftPower = leftVel / vmax + (kP * (leftError)) - correction;
    	double rightPower = rightVel / vmax + (kP * (rightError)) + correction;
    	
    	leftPower = leftPower > 1 ? 1 : leftPower;
    	rightPower = rightPower > 1 ? 1 : rightPower;
    	
    	leftPower = leftPower < -1 ? -1 : leftPower;
    	rightPower = rightPower < -1 ? -1 : rightPower;
    	
    	Robot.drive.setPower(leftPower, rightPower);
    	
    	long currentTime = System.currentTimeMillis();
    	long elapsedTime = currentTime - lastTime;
    	if(elapsedTime < minElapsedTime) minElapsedTime = elapsedTime;
    	if(elapsedTime > maxElapsedTime) maxElapsedTime = elapsedTime;
    	lastTime = currentTime;
    	if(currentPoint == profile.length) {
    		stopNotifier();
    	}
    }
    
    protected void stopNotifier() {
    	isNotifierRunning = false;
    }
    
    protected boolean isNotifierRunning() {
    	return isNotifierRunning;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !isNotifierRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("In End " + minElapsedTime + " " + maxElapsedTime);
    	Robot.drive.setPower(0,0);
    	notifier.stop();
    	//Robot.drive.setLeftOutput(ControlMode.PercentOutput, 0);
    	//Robot.drive.setRightOutput(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
