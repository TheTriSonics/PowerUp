/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import org.usfirst.frc.team4003.logging.FRCLogger;
import org.usfirst.frc.team4003.robot.commands.autonomous.*;
import org.usfirst.frc.team4003.robot.profiling.AutonProfile;
import org.usfirst.frc.team4003.robot.state.CubeState;
import org.usfirst.frc.team4003.robot.subsystems.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    //public static final DriveSubsystem drive = new DriveSubsystem();
	//public static final TalonDriveTrain drive = new TalonDriveTrain();
	
	
	
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final LiftMotors lift = new LiftMotors();
	//public static final ClimberMotor climber = new ClimberMotor();
	public static final IntakeMotors intake = new IntakeMotors();
	public static final PowerUpDriveTrain drive = new PowerUpDriveTrain();
	public static final CubeState cubeState = new CubeState();
	public static AutonSwitches switches = new AutonSwitches();
	HashMap<String, Integer> switchHash = new HashMap<String, Integer>();
	HashMap<String, Command> commandHash = new HashMap<String, Command>();

    public static OI oi;
    public static Sensors sensors;
    String gameData;
    boolean center;
    
    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    private boolean fmsAttached;

    @Override
    public void robotInit() {
    	sensors = new Sensors();
    	Compressor c = new Compressor(0);
		c.setClosedLoopControl(true);
		c.start();
    	
        // Trying to instantiate our logger class for debugging.
        try {
            FRCLogger.init();
            FRCLogger.setLevel(RobotMap.DEBUG ? Level.INFO : Level.WARNING);
            FRCLogger.log(Level.INFO, "***NEW ROBOT SESSION HAS STARTED***");
            FRCLogger.log(Level.INFO, "Careful now, robots are dangerous!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        lift.resetEncoder();
        
        switchHash.put("Pos", new Integer(1));
		switchHash.put("LL", new Integer(2));
		switchHash.put("LR", new Integer(3));
		switchHash.put("RL", new Integer(4));
		switchHash.put("RR", new Integer(5));
		
		commandHash.put("LeftSwitchLeft", new LeftSwitchLeft());
		commandHash.put("LeftSwitchRight", new LeftSwitchRight());
		commandHash.put("LeftScaleLeftNoCube", new LeftScaleLeftSide(false));
		commandHash.put("LeftScaleLeftCube", new LeftScaleLeftSide(true));
		commandHash.put("LeftScaleRight", new LeftScaleRight2());
		commandHash.put("RightSwitchLeft", new RightSwitchLeft());
		commandHash.put("RightSwitchRight", new RightSwitchRight());
		commandHash.put("RightScaleLeft", new RightScaleLeft2());
		commandHash.put("RightScaleRightNoCube", new RightScaleRightSide(false));
		commandHash.put("RightScaleRightCube", new RightScaleRightSide(true));
		commandHash.put("CenterSwitchLeft", new CenterSwitchLeft());
		commandHash.put("CenterSwitchRight", new CenterSwitchRight());
		
        oi = new OI();
        chooser.addDefault("Motion Profile Tester", new MotionProfileTester(new AutonProfile()));
        chooser.addObject("Normal Motion Profile", null);
        // chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);

        fmsAttached = false;
        //pneumatics.setState(Pneumatics.SHIFTER, true);
    }

    @Override
    public void disabledInit() {
        
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();

        if (!fmsAttached && DriverStation.getInstance().isFMSAttached()) {
            FRCLogger.setCompetitionMode(true);
            FRCLogger.log(Level.INFO, "FMS Attached.");
            fmsAttached = true;
        }
    }
    
    public String getAutonCommand() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData == null && gameData.length() < 3) return null;
		gameData = gameData.substring(0, 2);
		System.out.println(gameData);
		boolean[] switchValues = switches.getSwitchValues();
		System.out.println(switchValues[0]);
		center = switchValues[0];
		if (center) {
			if (gameData.charAt(0) == 'R') {
				return "CenterSwitchRight";
			} else {
				return "CenterSwitchLeft";
			}
		}
		boolean left = switches.getSwitchValue(switchHash.get("Pos"));
		boolean scale = switches.getSwitchValue(switchHash.get(gameData));
		switch(gameData) {
		case "LL":{
			if(left) {
				if(scale) return "LeftScaleLeftCube";
				else return "LeftSwitchLeft";
			} else {
				if(scale) return "RightScaleLeft";
				else return "RightSwitchLeft";
			}
		}
		
		case "LR":{
			if(left) {
				if(scale) return "LeftScaleRight";
				else return "LeftSwitchLeft";
			} else {
				if(scale) return "RightScaleRightNoCube";
				else return "RightSwitchLeft";
			}
		}
		
		case "RL":{
			if(left) {
				if(scale) return "LeftScaleLeftNoCube";
				else return "LeftSwitchRight";
			} else {
				if(scale) return "RightScaleLeft";
				else return "RightSwitchRight";
			}
		}
		
		default:
		case "RR":{
			if(left) {
				if(scale) return "LeftScaleRight";
				else return "LeftSwitchRight";
			} else {
				if(scale) return "RightScaleRightCube";
				else return "RightSwitchRight";
			}
		}
		
		}
		
    }

    @Override
    public void autonomousInit() {
    	gameData = DriverStation.getInstance().getGameSpecificMessage();
    	FRCLogger.log(Level.INFO, gameData);

    	if (DriverStation.getInstance().getGameSpecificMessage().length() <= 0) {
    	  FRCLogger.log(Level.SEVERE, "GAME DATA MESSAGE IS EMPTY. FMS DID NOT SEND IN AUTONOMOUS_INIT OR " +
            "IT IS NOT SET IN THE DRIVER STATION.");
        } else {
          FRCLogger.log(Level.INFO, gameData);
        }

    	SmartDashboard.putString("Game Specific Message:", gameData);
    	sensors.resetDriveEncoder();
    	sensors.resetGyro();
    	sensors.resetPosition();
    	lift.resetEncoder();
    	//pneumatics.setState(Pneumatics.SHIFTER, true);
    	/*
    	if (autonomousCommand != null) {
    		autonomousCommand.cancel();
    		autonomousCommand = null;
    	}
    	*/
    	startAuton();
    	
    	
    }
    
    public void startAuton() {
    	String autonString = getAutonCommand();
    	if(autonString == null) return;
    	if(center) autonomousCommand = new Center(gameData);
    	else autonomousCommand = commandHash.get(autonString);
    	
    	autonomousCommand = new RightSwitchRight();
        System.out.println(autonomousCommand);
        SmartDashboard.putString("Auton String", autonString);
        
        if (autonomousCommand != null) {
            FRCLogger.log(Level.INFO, String.format("%s autonomous command has started.", autonomousCommand.getName()));
            autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
    	if(autonomousCommand == null) {
    		startAuton();
            return;
    	}
    	sensors.updatePosition();
    	//System.out.println(sensors.getLeftPosition() + " " + sensors.getRightPosition());
        Scheduler.getInstance().run();
        double[] position = sensors.getPosition();
        SmartDashboard.putNumber("leftEncoder", sensors.getLeftEncoder());
    	SmartDashboard.putNumber("rightEncoder", sensors.getRightEncoder());
    	SmartDashboard.putNumber("RobotX", position[0]);
    	SmartDashboard.putNumber("RobotY", position[1]);
    	SmartDashboard.putData(sensors.getGyro());
    	//SmartDashboard.putData(new PowerDistributionPanel());
    	Command nextCommand = getNextCommand();
    	if(nextCommand != null) nextCommand.start();
    }
    
    static String nextCommand = "";
    static Object lock = new Object();
    public static void startCommand(String command) {
    	synchronized(lock) {
    		nextCommand = command;
    	}
    }
    
    public static Command getNextCommand() {
    	synchronized(lock) {
    		if(nextCommand.length() == 0) return null;
    		//if(nextCommand.equals("raiseLift")) return new TriggerGearRelease(true);
    		return null;
    	}
    }

    @Override
    public void teleopInit() {
    	/*
    	sensors.resetDriveEncoder();
    	sensors.resetGyro();
    	sensors.resetPosition();
    	*/
    	//pneumatics.setState(Pneumatics.SHIFTER, true);
        if (autonomousCommand != null) {
            FRCLogger.log(Level.INFO, String.format("%s autonomous command was canceled. CAUSE: teleopInit()", autonomousCommand.getName()));
            autonomousCommand.cancel();
        }
    }

    long lastTime = 0;
    int lastLeftEncoder, lastRightEncoder;
    @Override
    public void teleopPeriodic() {
    	sensors.updatePosition();
    	double[] position = sensors.getPosition();
    	SmartDashboard.putNumber("leftEncoder", sensors.getLeftEncoder());
    	SmartDashboard.putNumber("rightEncoder", sensors.getRightEncoder());
    	SmartDashboard.putNumber("RobotX", position[0]);
    	SmartDashboard.putNumber("RobotY", position[1]);
    	SmartDashboard.putNumber("Heading", drive.getHeading());
    	SmartDashboard.putNumber("Lift Encoder", lift.getPosition());
    	SmartDashboard.putNumber("Robot State", cubeState.getState());
    	//System.out.println(sensors.getLeftPosition() + " " + sensors.getRightPosition());
        Scheduler.getInstance().run();
        
        int cState = cubeState.getState();
        for (int i = 0; i < 8; i++) {
        	if (i == cState) SmartDashboard.putBoolean("State " + i, true);
        	else SmartDashboard.putBoolean("State " + i, false);
        }
        
        
        /*
        long currentTime = System.currentTimeMillis();
        int leftEncoder = sensors.getLeftEncoder();
        int rightEncoder = sensors.getRightEncoder();
        
        if (lastTime != 0) {
        	long elapsedTime = currentTime - lastTime;
        	int changeLeftEncoder = leftEncoder - lastLeftEncoder;
        	int changeRightEncoder = rightEncoder - lastRightEncoder;
        	double distance = (changeLeftEncoder + changeRightEncoder)/2.0;
        	double velocity = distance / elapsedTime;
        	SmartDashboard.putNumber("Velocity", velocity);
        }
        lastTime = currentTime;
        lastLeftEncoder = leftEncoder;
        lastRightEncoder = rightEncoder;
        */
    }

    @Override
    public void testPeriodic() {
        
    }
}
