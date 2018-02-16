/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

import org.usfirst.frc.team4003.robot.commands.ShiftCommand;
import org.usfirst.frc.team4003.robot.commands.ToggleClamp;
import org.usfirst.frc.team4003.robot.commands.ToggleFlippers;
import org.usfirst.frc.team4003.robot.commands.ToggleIntake;
import org.usfirst.frc.team4003.robot.commands.TogglePusher;
import org.usfirst.frc.team4003.robot.state.StateCommand;
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
    	
    	shiftHigh.whenActive(new ShiftCommand(true));
    	shiftLow.whenActive(new ShiftCommand(false));
    	
    	XboxTrigger toggleIntake = new XboxTrigger(operator, XboxTrigger.A);
    	XboxTrigger togglePusher = new XboxTrigger(operator, XboxTrigger.B);
    	XboxTrigger toggleClamp = new XboxTrigger(operator, XboxTrigger.X);
    	XboxTrigger toggleFlippers = new XboxTrigger(operator, XboxTrigger.Y);
    	XboxTrigger advanceState = new XboxTrigger(operator, XboxTrigger.RB);
    	XboxTrigger backState = new XboxTrigger(operator, XboxTrigger.LB);
    	
    	toggleIntake.whenActive(new ToggleIntake());
    	togglePusher.whenActive(new TogglePusher());
    	toggleClamp.whenActive(new ToggleClamp());
    	toggleFlippers.whenActive(new ToggleFlippers());
    	//advanceState.whenActive(new StateCommand(true));
    	//backState.whenActive(new StateCommand(false));

    	
    }

}
