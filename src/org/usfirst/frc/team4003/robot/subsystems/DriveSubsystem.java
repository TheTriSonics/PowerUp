/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.DriveCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

    private TalonSRX left1;
    private TalonSRX left2;
    private TalonSRX left3;
    private TalonSRX right1;
    private TalonSRX right2;
    private TalonSRX right3;
	double yLimit, xLimit;
	double maxSpeed = 1;
    public DriveSubsystem() {
        left1 = new TalonSRX(RobotMap.LEFT_ONE);
        left2 = new TalonSRX(RobotMap.LEFT_TWO);
        left3 = new TalonSRX(RobotMap.LEFT_THREE);
        right1 = new TalonSRX(RobotMap.RIGHT_ONE);
        right2 = new TalonSRX(RobotMap.RIGHT_TWO);
        right3 = new TalonSRX(RobotMap.RIGHT_THREE);
        right1.setInverted(true);
        right2.setInverted(true);
        right3.setInverted(true);
        //left2.follow(left1);
        //left3.follow(left1);
        //right2.follow(right1);
        //right3.follow(right1);
		yLimit = 1;
		xLimit = .8;
		left1.setNeutralMode(NeutralMode.Brake);
		left2.setNeutralMode(NeutralMode.Brake);
		left3.setNeutralMode(NeutralMode.Brake);
		right1.setNeutralMode(NeutralMode.Brake);
		right2.setNeutralMode(NeutralMode.Brake);
		right3.setNeutralMode(NeutralMode.Brake);
		/*
		left1.setSensorPhase(true);
		right1.setSensorPhase(true);
		
		left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		left1.configMotionProfileTrajectoryPeriod(10, 0);
		right1.configMotionProfileTrajectoryPeriod(10, 0);
		
		left1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
		right1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
		*/
       // motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
       // motor.setSensorPhase(true); /* keep sensor and motor in phase */
       // motor.configNeutralDeadband(0.01, 0); 

        // TODO - Set these as our own values.
//        motor.config_kF(0, 0.076, 0);
//        motor.config_kP(0, 2.000, 0);
//        motor.config_kI(0, 0.0, 0);
//        motor.config_kD(0,20.0, 0);
//
//        motor.configMotionProfileTrajectoryPeriod(10, 0); //Our profile uses 10 ms timing
//        motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {

	    double leftMotorSpeed;
	    double rightMotorSpeed;
	    moveValue *= yLimit;
	    rotateValue *= xLimit;

	    if (squaredInputs) {
	      // square the inputs (while preserving the sign) to increase fine control
	      // while permitting full power
	      if (moveValue >= 0.0) {
	        moveValue = moveValue * moveValue;
	      } else {
	        moveValue = -(moveValue * moveValue);
	      }
	      if (rotateValue >= 0.0) {
	        rotateValue = rotateValue * rotateValue;
	      } else {
	        rotateValue = -(rotateValue * rotateValue);
	      }
	    }

	    if (moveValue > 0.0) {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = Math.max(moveValue, rotateValue);
	      } else {
	        leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      }
	    } else {
	      if (rotateValue > 0.0) {
	        leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	        rightMotorSpeed = moveValue + rotateValue;
	      } else {
	        leftMotorSpeed = moveValue - rotateValue;
	        rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	      }
	    }
	    
	    left1.set(ControlMode.PercentOutput,leftMotorSpeed);
	    left2.set(ControlMode.PercentOutput,leftMotorSpeed);
	    left3.set(ControlMode.PercentOutput,leftMotorSpeed);
	    right1.set(ControlMode.PercentOutput,rightMotorSpeed);
	    right2.set(ControlMode.PercentOutput,rightMotorSpeed);
	    right3.set(ControlMode.PercentOutput,rightMotorSpeed);
	}
    public void setLeftOutput(ControlMode mode, double value) {
       left1.set(mode, value);
       left2.set(mode, value);
       left3.set(mode, value);
    }

    public TalonSRX getLeftSide() {
//        NetworkTable table = NetworkTableInstance.getDefault().getTable("RobotData");
        return this.left1;
    }
    
    public void setRightOutput(ControlMode mode, double value) {
        right1.set(mode, value);
        right2.set(mode, value);
        right3.set(mode, value);
     }

     public TalonSRX getRightSide() {
//         NetworkTable table = NetworkTableInstance.getDefault().getTable("RobotData");
         return this.right1;
     }

}
