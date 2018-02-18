package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.ArcadeDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 *
 */
public class PowerUpDriveTrain extends SwitchableDriveTrain {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private TalonSRX leftMaster, leftSlave, rightMaster, rightSlave;
	
	public PowerUpDriveTrain() {
		leftMaster = new TalonSRX(RobotMap.LEFT_MASTER);
		leftSlave = new TalonSRX(RobotMap.LEFT_SLAVE);
		rightMaster = new TalonSRX(RobotMap.RIGHT_MASTER);
		rightSlave = new TalonSRX(RobotMap.RIGHT_SLAVE);

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);
		
		rightMaster.setInverted(true);
		rightSlave.setInverted(true);
		
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		leftMaster.setSensorPhase(true);
		rightMaster.setSensorPhase(true);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArcadeDrive());
    }

	@Override
	public void setPower(double left, double right) {
		// TODO Auto-generated method stub
		if (switched) {
			double temp = left;
			left = -right;
			right = -temp;
		}
		if (Math.abs(left) < 0.05) left = 0;
		if (Math.abs(right) < 0.05) right = 0;
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
	}
	
	public int getLeftPosition() {
		return leftMaster.getSelectedSensorPosition(0);
	}
	
	public int getRightPosition() {
		return rightMaster.getSelectedSensorPosition(0);
	}
}

