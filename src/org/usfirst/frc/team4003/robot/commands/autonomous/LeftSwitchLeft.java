package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.*;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.*;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchLeft extends CommandGroup {

    public LeftSwitchLeft() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(2000, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	addSequential(new StateCommand(true));
    	
    	addParallel(new SwitchDirection());
    	addSequential(new WaitForTime(200));
    	
    	addParallel(new WaitAndGoHome(1000));
    	profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-backup.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new SwitchDirection());
    	
    	double x = 195;
    	double y = -30;
    	
    	addSequential(new RotateToPoint(x,y,0.5));
    	addSequential(new StateCommand(true));
    	
    	addSequential(new DriveToPoint(x, y, 0.4));
    	addSequential(new GrabCube());
    	addSequential(new PrepareCube(0, LiftMotors.SWITCH));
    	addSequential(new WaitForTime(500));
    	addSequential(new DriveForDistance(15, 0.3));
    	addSequential(new TogglePusher());
    	//addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(500));
    	addSequential(new ToggleClamp());
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.3));
    	
    }
}
