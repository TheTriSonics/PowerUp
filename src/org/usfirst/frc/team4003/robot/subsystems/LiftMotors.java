package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
	}
	
	public void setPower(double power) {
		master.set(ControlMode.PercentOutput, power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

