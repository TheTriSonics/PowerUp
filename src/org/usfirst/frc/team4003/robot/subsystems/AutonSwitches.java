package org.usfirst.frc.team4003.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonSwitches extends Subsystem {
	int numSwitches = 10;
	DigitalInput[] switches = new DigitalInput[numSwitches];
	int[] channels = new int[] {10, 11, 12, 13, 18, 19, 20, 21, 22, 23};
	
	public AutonSwitches() {
		for (int i = 0; i < switches.length; i++) {
			switches[i] = new DigitalInput(channels[i]);
		}
	}
	
	public void printState() {
		String s = "";
		for (int i = 0; i < numSwitches; i++) {
			s = s + !switches[i].get() + " ";
		}
		System.out.println(s);
				
	}
	
	public boolean[] getSwitchValues() {
		boolean[] values = new boolean[numSwitches];
		for (int i = 0; i < values.length; i++) {
			values[i] = !switches[i].get();
		}
		return values;
	}
	
	public boolean getSwitchValue(Integer switchNum) {
		return !switches[switchNum.intValue()].get();
	}
	
	public int getInt(int startPosition, int length) {
		int result = 0;
		for (int i = 0; i < length; i++) {
			result *= 2;
			if (!switches[startPosition + i].get()) result++;
		}
		return result;
	}
	
	public void displayState() {
		for (int i = 0; i < numSwitches; i++) {
			SmartDashboard.putBoolean("Switch " + (i+1), !(switches[i].get()));
		}
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

