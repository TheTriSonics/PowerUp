package org.usfirst.frc.team4003.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonSwitch extends Subsystem {
	DigitalInput[] switches = new DigitalInput[10];
	int[] channels = new int[] {10, 11, 12, 13, 18, 19, 20, 21, 22, 23};
	
	public AutonSwitch() {
		for (int i = 0; i < 10; i++) {
			switches[i] = new DigitalInput(channels[i]);
		}
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

