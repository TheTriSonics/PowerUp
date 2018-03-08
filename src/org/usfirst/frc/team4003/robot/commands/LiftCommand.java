package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftCommand extends Command {
	int LOWERLIMIT = 2000;
	int UPPERLIMIT = 46000;
	double COUNTSPERINCH = 579.3;

    public LiftCommand() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    int zerocount = 0;
    double lastError = 0;
    double kp = 1/6000.0;
    double kd = 1/60000.0;
    protected void execute() {
    	if (Robot.getClimbState()) {
    		Robot.lift.setPower(0);
    		return;
    	}
    	double power = -Robot.oi.operator.getY(Hand.kRight);
    	if(Math.abs(power) > 0.1) {
    		Robot.lift.setManual(true);
    	} else {
    		Robot.lift.setManual(false);
    		int position = Robot.lift.getPosition();
    		if (position == 0) zerocount++;
    		else zerocount = 0;
    		double error = Robot.lift.getHoldPosition() - position;
    		double changeError = error - lastError;
    		//SmartDashboard.putNumber("error", error);
    		power = kp * error + kd * changeError;
    		if (Math.abs(power) > 1) {
    			if (power > 1) power = 1;
    			else power = -1;
    		}
    		lastError = error;
    		//if (zerocount > 20) power = 0;
    	}
    	int liftPosition = Robot.lift.getPosition();
    	if (power < 0 && liftPosition < 10000) power *= 0.3;
    	
    	if (liftPosition < LOWERLIMIT && power < 0) power = 0;
    	if (liftPosition > UPPERLIMIT && power > 0) power = 0;
    	Robot.lift.setPower(power);
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
