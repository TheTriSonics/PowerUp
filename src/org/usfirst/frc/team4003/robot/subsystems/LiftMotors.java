package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.logging.FRCLogger;
import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.LiftCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.logging.Level;

/**
 *
 */
public class LiftMotors extends Subsystem {
	
	public static final int GROUND_LEVEL = 0;
	public static final int DRIVING = 1;
	public static final int SWITCH = 2;
	public static final int SWITCH_HIGH = 3;
	public static final int SCALE = 4;
	public static final int SCALE_HIGH = 5;
	public static final int SCALE_SUPER_HIGH = 6;
	
	
	int[] stops = new int[7];
	
	boolean manual = false;
	int state = GROUND_LEVEL;
	int encoderTarget = 0;
	int encoderOffset = 0;
	int holdPosition = 0;
	int RAISEDLIMIT = 30000;
	public void setState(int s) {
		state = s;
		manual = false;
		setHoldPosition(stops[state]);
    FRCLogger.log(Level.INFO, String.format("Lift set to state %d", state));
	}
	
	public void setHoldPosition(int p) {
		holdPosition = p;
	}
	
	public int getHoldPosition() {
		return holdPosition;
	}
	
	public void setManual(boolean m) {
		if(manual && !m) {
			holdPosition = getPosition();
		}
		manual = m;
	}
	
	public boolean isManual() {
		return manual;
	}
	
	 public boolean isLiftRaised() {
	    	return getPosition() > RAISEDLIMIT;
	 }
	

	TalonSRX master = new TalonSRX(RobotMap.LEFT_LIFT);
	TalonSRX slave = new TalonSRX(RobotMap.RIGHT_LIFT);
	
	public LiftMotors() {
		master.setInverted(true);
		master.setSensorPhase(true);
		//slave.follow(master);
		//slave.setInverted(true);
		master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
				0, 0);
		stops[GROUND_LEVEL] = 200;
		stops[DRIVING] = 6000;
		stops[SWITCH] = 13000;
		stops[SWITCH_HIGH] = 21600;
		stops[SCALE] = 40000;
		stops[SCALE_HIGH] = 43000;
		stops[SCALE_SUPER_HIGH] = 46000;
	}
	
	public void setPower(double power) {
		if (Math.abs(power) < 0.05) power = 0;
		master.set(ControlMode.PercentOutput, power);
		slave.set(ControlMode.PercentOutput, power);
	}
	
	public int getPosition() {
		return master.getSelectedSensorPosition(0) - encoderOffset;
	}
	
	public void resetEncoder() {
		encoderOffset = master.getSelectedSensorPosition(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LiftCommand());
    }
}

