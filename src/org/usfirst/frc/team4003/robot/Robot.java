/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

import java.io.IOException;
import java.util.logging.Level;

import org.usfirst.frc.team4003.robot.commands.PlaybackProfile;
import org.usfirst.frc.team4003.robot.commands.autonomous.MotionProfileTester;
import org.usfirst.frc.team4003.logging.FRCLogger;
import org.usfirst.frc.team4003.robot.profiling.AutonProfile;
import org.usfirst.frc.team4003.robot.subsystems.Pneumatics;
import org.usfirst.frc.team4003.robot.subsystems.TalonDriveTrain;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    //public static final DriveSubsystem drive = new DriveSubsystem();
	public static final TalonDriveTrain drive = new TalonDriveTrain();
	public static final Pneumatics pneumatics = new Pneumatics();
    public static OI oi;
    public static Sensors sensors;
    
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

        oi = new OI();
        chooser.addDefault("Motion Profile Tester", new MotionProfileTester(new AutonProfile()));
        // chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);

        fmsAttached = false;
    }

    @Override
    public void disabledInit() {
        
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();

        if (!fmsAttached && DriverStation.getInstance().isFMSAttached()) {
            FRCLogger.log(Level.INFO, "FMS Attached.");

            fmsAttached = true;
        }
    }

    @Override
    public void autonomousInit() {
    	String gamedata = DriverStation.getInstance().getGameSpecificMessage();
    	FRCLogger.log(Level.INFO, gamedata);
    	SmartDashboard.putString("Game Specific Message:", gamedata);
    	sensors.resetDriveEncoder();
    	sensors.resetGyro();
    	sensors.resetPosition();
    	if (autonomousCommand != null) {
    		autonomousCommand.cancel();
    		autonomousCommand = null;
    	}
        autonomousCommand = new PlaybackProfile("/home/lvuser/profiles/test.csv");
        System.out.println(autonomousCommand);
        
        if (autonomousCommand != null) {
            FRCLogger.log(Level.INFO, String.format("%s autonomous command has started.", autonomousCommand.getName()));
            autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
    	sensors.updatePosition();
    	//System.out.println(sensors.getLeftPosition() + " " + sensors.getRightPosition());
        Scheduler.getInstance().run();
        double[] position = sensors.getPosition();
        SmartDashboard.putNumber("leftEncoder", sensors.getLeftEncoder());
    	SmartDashboard.putNumber("rightEncoder", sensors.getRightEncoder());
    	SmartDashboard.putNumber("RobotX", position[0]);
    	SmartDashboard.putNumber("RobotY", position[1]);
    	SmartDashboard.putNumber("Heading", sensors.getHeading());
    }

    @Override
    public void teleopInit() {
    	sensors.resetDriveEncoder();
    	sensors.resetGyro();
    	sensors.resetPosition();
        if (autonomousCommand != null) {
            FRCLogger.log(Level.INFO, String.format("%s autonomous command was canceled. CAUSE: teleopInit()", autonomousCommand.getName()));
            autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
    	sensors.updatePosition();
    	double[] position = sensors.getPosition();
    	SmartDashboard.putNumber("leftEncoder", sensors.getLeftEncoder());
    	SmartDashboard.putNumber("rightEncoder", sensors.getRightEncoder());
    	SmartDashboard.putNumber("RobotX", position[0]);
    	SmartDashboard.putNumber("RobotY", position[1]);
    	SmartDashboard.putNumber("Heading", sensors.getHeading());
    	System.out.println(sensors.getLeftPosition() + " " + sensors.getRightPosition());
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
        
    }
}
