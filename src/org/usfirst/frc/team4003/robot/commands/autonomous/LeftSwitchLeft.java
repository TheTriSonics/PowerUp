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
    	addSequential(new PrepareCube(2000, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	addSequential(new StateCommand(true));
    	
    	addParallel(new SwitchDirection());
    	addSequential(new WaitForTime(200));
    	
    	addParallel(new WaitAndGoHome(1000));
    	profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-backup.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new SwitchDirection());
    	
    	addSequential(new StateCommand(true));
    	addSequential(new DriveToPoint(205, -37, 0.4));
    	addSequential(new GrabCube());
    	addParallel(new PrepareCube(0, LiftMotors.SWITCH));
    	addSequential(new DriveForDistance(15, 0.4));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.4));
    	
    }
}
