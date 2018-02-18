package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.PneumaticsCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {

    public static final int SHIFTER = 0;
    public static final int CLAMP = 1;
    public static final int PUSHER = 2;
    public static final int INTAKE = 3;
    public static final int FLIPPERS = 4;
    String[] labels = new String[] {
    		"Shifter","Clamp","Pusher","Intake","Flippers"
    };
    
	Solenoid[] solenoids = new Solenoid[5];
	boolean[] states = new boolean[5];
	
	public Pneumatics() {
		solenoids[SHIFTER] = new Solenoid(RobotMap.SHIFTER_PORT);
		solenoids[CLAMP] = new Solenoid(RobotMap.CLAMP_PORT);
		solenoids[PUSHER] = new Solenoid(RobotMap.PUSHER_PORT);
		solenoids[INTAKE] = new Solenoid(RobotMap.INTAKE_PORT);
		solenoids[FLIPPERS] = new Solenoid(RobotMap.FLIPPERS_PORT);
		
		for(int i = 0; i < 5; i++) states[i] = false;
		
	}
	
	public void setState(int valve, boolean state) {
		states[valve] = state;
	}
	
	public void toggleState(int valve) {
		states[valve] = !states[valve];
	}
	
	public void update() {
		solenoids[SHIFTER].set(states[SHIFTER]);
		solenoids[CLAMP].set(states[CLAMP]);
		solenoids[PUSHER].set(states[PUSHER]);
		solenoids[INTAKE].set(states[INTAKE]);
		solenoids[FLIPPERS].set(states[FLIPPERS]);
		for(int i = 0; i < 5; i++) {
			SmartDashboard.putBoolean(labels[i], solenoids[i].get());
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PneumaticsCommand());
    }
}

