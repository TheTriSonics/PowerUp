package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.*;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchLeft extends CommandGroup {

    public CenterSwitchLeft() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(1000, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/c-switch-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	// place cube
    	addSequential(new StateCommand(true));
    	addSequential(new SwitchDirection());
    	
    	addParallel(new WaitAndGoHome(1000));
    	
    	DriveTrainProfile profile2 = new DriveTrainProfile("/home/lvuser/profiles/c-switch-left-backwards.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile2));
    	addSequential(new SwitchDirection());
    	
    	addSequential(new StateCommand(true));
    	addSequential(new DriveToPoint(80, 0, 0.4));
    	addSequential(new GrabCube());
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(5, 0, 0.4));
    	addSequential(new SwitchDirection());
    	
    	addParallel(new PrepareCube(1000, LiftMotors.SWITCH));
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new StateCommand(true));
    	//addSequential(new WaitForTime(200));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.4));
    	
    }
}
