package org.usfirst.frc.team4003.robot.state;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;

public class CubeState {
	public static final int DRIVE = 0;
	public static final int SEEKING = 1;
	public static final int PULLINGIN = 2;
	public static final int CLAMPCUBE = 3;
	public static final int TRANSPORT = 4;
	public static final int PREPAREPLACEMENT = 5;
	public static final int PLACECUBE = 6;
	public static final int RETRACTPUSHER = 7;
	int state = DRIVE;
	
	public int getState() {
		return state;
	}
	
	public void setState(int s) {
		state = s;
	}
	
	public void startSeeking() { 
		(new SeekingCommand()).start();
	}
	
	public void stopSeeking() {
		if (state != SEEKING) return;
		Robot.pneumatics.setState(Pneumatics.CLAMP, false);
		Robot.pneumatics.setState(Pneumatics.INTAKE, false);
		Robot.pneumatics.setState(Pneumatics.FLIPPERS, false);
		Robot.intake.setState(IntakeMotors.OFF);
		state = DRIVE;
	}
	
	public void startPullingIn() {
		if (state != SEEKING) return;
		Robot.pneumatics.setState(Pneumatics.FLIPPERS, false);
		state = PULLINGIN;
	}
	
	public void stopPullingIn() {
		if (state != PULLINGIN) return;
		Robot.pneumatics.setState(Pneumatics.FLIPPERS, true);
		state = SEEKING;
	}
	
	public void startTransport() {
		if (state != CLAMPCUBE) return;
		Robot.lift.setState(LiftMotors.DRIVING);
		state = TRANSPORT;
	}
	
	public void stopTransport() {
		if (state != TRANSPORT) return;
		Robot.lift.setState(LiftMotors.GROUND_LEVEL);
		Robot.pneumatics.setState(Pneumatics.CLAMP, true);
		Robot.pneumatics.setState(Pneumatics.INTAKE, true);
		Robot.pneumatics.setState(Pneumatics.FLIPPERS, true);
		Robot.pneumatics.setState(Pneumatics.INTAKE, true);
		Robot.intake.setState(IntakeMotors.ON);
		state = SEEKING;
	}
	
	public void stopClamping() {
		System.out.println("Stop  clamping!");
		if (state != CLAMPCUBE) return;
		Robot.lift.setState(LiftMotors.GROUND_LEVEL);
		Robot.pneumatics.setState(Pneumatics.CLAMP, true);
		Robot.pneumatics.setState(Pneumatics.INTAKE, true);
		Robot.pneumatics.setState(Pneumatics.FLIPPERS, true);
		Robot.intake.setState(IntakeMotors.ON);
		state = SEEKING;
	}
	
	public void startPreparation() {
		if (state != TRANSPORT) return;
		Robot.pneumatics.setState(Pneumatics.PUSHER, true);
		state = PREPAREPLACEMENT;
	}
	
	public void startPlaceCube() {
		if (state != PREPAREPLACEMENT) return;
		Robot.pneumatics.setState(Pneumatics.CLAMP, true);
		state = PLACECUBE;
	}
	
	public void startRetractPusher() {
		if (state != PLACECUBE) return;
		Robot.pneumatics.setState(Pneumatics.CLAMP, false);
		Robot.pneumatics.setState(Pneumatics.PUSHER, false);
		state = RETRACTPUSHER;
	}
	
	public void returnToDrive() {
		if (state != RETRACTPUSHER) return;
		Robot.pneumatics.setState(Pneumatics.FLIPPERS, false);
		Robot.pneumatics.setState(Pneumatics.INTAKE, false);
		Robot.lift.setState(LiftMotors.GROUND_LEVEL);
		state = DRIVE;
	}
	
	public void advance() {
		System.out.println("In advance: " + Robot.cubeState.getState());
    	switch (Robot.cubeState.getState()) {
	    	case DRIVE:
	    		startSeeking();
	    		return;
	    	case SEEKING:
	    		startPullingIn();
	    		return;
	    	case PULLINGIN:
	    		(new ClampCommand()).start();
	    		return;
	    	case CLAMPCUBE:
	    		startTransport();
	    		return;
	    	case TRANSPORT:
	    		startPreparation();
	    		return;
	    	case PREPAREPLACEMENT:
	    		startPlaceCube();
	    		return;
	    	case PLACECUBE:
	    		startRetractPusher();
	    		return;
	    	case RETRACTPUSHER:
	    		returnToDrive();
	    		return;
    	}
    }
	
	public void back() {
		switch (Robot.cubeState.getState()) {
		case SEEKING:
			stopSeeking();
			return;
		case PULLINGIN:
			stopPullingIn();
			return;
		case CLAMPCUBE:
			stopClamping();
			return;
		case TRANSPORT:
			stopTransport();
			return;
		}
	}

}
