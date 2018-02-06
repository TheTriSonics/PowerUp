/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.commands.TriggerGearRelease;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */ 
public class OI {

    public XboxController driver = new XboxController(RobotMap.DRIVER_CONTROLLER);;
    
    JoystickButton gearOpen = new JoystickButton(driver,3);
    JoystickButton gearClose = new JoystickButton(driver,4);
    JoystickButton switchDirection = new JoystickButton(driver,2);
    
    public OI() {
    	//gearRelease.whileHeld(new TriggerGearRelease());
    	//gearRelease.toggleWhenPressed(new TriggerGearRelease());
    	gearOpen.whenPressed(new TriggerGearRelease(true));
    	gearClose.whenPressed(new TriggerGearRelease(false));
    	switchDirection.whenPressed(new SwitchDirection());
    }

}
