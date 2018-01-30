/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

//import org.usfirst.frc.team4003.commands.TriggerGearRelease;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */ 
public class OI {

    public XboxController driver = new XboxController(RobotMap.DRIVER_CONTROLLER);;
    
    JoystickButton gearRelease = new JoystickButton(driver,3);
    
    public OI() {
    	//gearRelease.whileHeld(new TriggerGearRelease());
    	//gearRelease.toggleWhenPressed(new TriggerGearRelease());
    }

}
