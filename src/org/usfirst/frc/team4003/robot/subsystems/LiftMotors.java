package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.LiftCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftMotors extends Subsystem {

	TalonSRX master = new TalonSRX(RobotMap.LEFT_LIFT);
	TalonSRX slave = new TalonSRX(RobotMap.RIGHT_LIFT);
	
	public LiftMotors() {
		slave.setInverted(true);
		slave.follow(master);
		master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	}
	
	public void setPower(double power) {
		master.set(ControlMode.PercentOutput, power);
	}
	
	public int getPosition() {
		return master.getSelectedSensorPosition(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LiftCommand());
    }
}

