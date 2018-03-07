package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.*;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.*;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchLeft extends CommandGroup {

    public LeftSwitchLeft() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(1000, 1500, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	addSequential(new StateCommand(true));
    	
    	addParallel(new SwitchDirection());
    	addSequential(new WaitForTime(200));
    	
    	addParallel(new WaitAndGoHome(1000));
    	profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-backup.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new SwitchDirection());
    	
    	double x = 197.5;
    	double y = -50;
    	
    	addSequential(new RotateToPoint(x,y,0.55));
    	addSequential(new StateCommand(true));
    	
    	addSequential(new DriveToPoint(x, y, 0.4));
    	addParallel(new DriveForDistance(-4, 0.3));
    	addSequential(new GrabCube(400, IntakeMotors.LEFT));
    	//addSequential(new PrepareCube(0, LiftMotors.SWITCH));
    	addSequential(new SetLiftHeight(LiftMotors.GROUND_LEVEL));
    	
    	
    	/*
    	addSequential(new WaitForTime(1000));
    	addSequential(new TogglePusher());
    	addSequential(new DriveForDistance(8, 0.3));
    	
    	//addSequential(new StateCommand(true));
    	//addSequential(new WaitForTime(500));
    	addSequential(new ToggleClamp());
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.3));
    	*/
    }
}
