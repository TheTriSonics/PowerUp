/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

import org.usfirst.frc.team4003.robot.commands.FastDriveCommand;
import org.usfirst.frc.team4003.robot.commands.SetClimbMode;
import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.commands.ShiftCommand;
import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.commands.ToggleClamp;
import org.usfirst.frc.team4003.robot.commands.ToggleFlippers;
import org.usfirst.frc.team4003.robot.commands.ToggleIntake;
import org.usfirst.frc.team4003.robot.commands.TogglePusher;
import org.usfirst.frc.team4003.robot.state.CubeStateHomeCommand;
import org.usfirst.frc.team4003.robot.state.PlaceWithoutPusher;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;
import org.usfirst.frc.team4003.robot.triggers.XboxTrigger;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */ 
public class OI {

	public XboxController driver = new XboxController(0);
	public XboxController operator = new XboxController(1);
    
    public OI() {
    	XboxTrigger shiftHigh = new XboxTrigger(driver, XboxTrigger.B);
    	XboxTrigger shiftLow = new XboxTrigger(driver, XboxTrigger.X);
    	XboxTrigger switchDirection = new XboxTrigger(driver, XboxTrigger.A);
    	
    	shiftHigh.whenActive(new ShiftCommand(true));
    	shiftLow.whenActive(new ShiftCommand(false));
    	switchDirection.whenActive(new SwitchDirection());
    	
    	XboxTrigger slowDrive = new XboxTrigger(driver, XboxTrigger.DPADLEFT);
    	XboxTrigger fastDrive = new XboxTrigger(driver, XboxTrigger.DPADRIGHT);
    	
    	slowDrive.whenActive(new FastDriveCommand(false));
    	fastDrive.whenActive(new FastDriveCommand(true));
    	
    	
    	XboxTrigger toggleIntake = new XboxTrigger(operator, XboxTrigger.A);
    	XboxTrigger togglePusher = new XboxTrigger(operator, XboxTrigger.B);
    	XboxTrigger toggleClamp = new XboxTrigger(operator, XboxTrigger.X);
    	XboxTrigger toggleFlippers = new XboxTrigger(operator, XboxTrigger.Y);
    	XboxTrigger advanceState = new XboxTrigger(operator, XboxTrigger.RB);
    	XboxTrigger backState = new XboxTrigger(operator, XboxTrigger.LB);
    	XboxTrigger homeState = new XboxTrigger(operator,XboxTrigger.DPADDOWN);
    	XboxTrigger raiseLift = new XboxTrigger(operator,XboxTrigger.DPADUP);
    	//XboxTrigger placeCubeWithoutPusher = new XboxTrigger(operator,XboxTrigger.RT);
    	XboxTrigger liftState = new XboxTrigger(operator,XboxTrigger.DPADLEFT);
    	XboxTrigger climbState = new XboxTrigger(operator,XboxTrigger.DPADRIGHT);
    	
    	toggleIntake.whenActive(new ToggleIntake());
    	togglePusher.whenActive(new TogglePusher());
    	toggleClamp.whenActive(new ToggleClamp());
    	toggleFlippers.whenActive(new ToggleFlippers());
    	homeState.whenActive(new CubeStateHomeCommand());
    	advanceState.whenActive(new StateCommand(true));
    	backState.whenActive(new StateCommand(false));
    	raiseLift.whenActive(new SetLiftHeight(LiftMotors.SCALE_HIGH));
    	//placeCubeWithoutPusher.whenActive(new PlaceWithoutPusher());
    	liftState.whenActive(new SetClimbMode(false));
    	climbState.whenActive(new SetClimbMode(true));
    }

}
