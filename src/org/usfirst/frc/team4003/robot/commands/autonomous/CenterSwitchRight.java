package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.PrepareCube;
import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchRight extends CommandGroup {

    public CenterSwitchRight() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(100, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/c-switch-right.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	// place cube
    	addSequential(new StateCommand(true));
    	addSequential(new SwitchDirection());
    	
    	addParallel(new WaitAndGoHome(1000));
    	
    	DriveTrainProfile profile2 = new DriveTrainProfile("/home/lvuser/profiles/c-switch-right-backwards.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile2));
    	addSequential(new SwitchDirection());
    	
    	addSequential(new StateCommand(true));
    	addSequential(new DriveToPoint(67, -7, 0.45));
    	addSequential(new GrabCube());
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(5, 0, 0.45));
    	addSequential(new SwitchDirection());
    	
    	addSequential(new StateCommand(true));
    	CommandGroup group = new CommandGroup();
    	group.addSequential(new WaitForTime(1000));
    	group.addSequential(new SetLiftHeight(LiftMotors.SWITCH));
    	group.addSequential(new WaitForTime(1000));;
    	group.addSequential(new StateCommand(true));
    	
    	addParallel(new PrepareCube(100, 1000, LiftMotors.SWITCH));
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	//addSequential(new StateCommand(true));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.4));
    	
    	
    	/*
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(1000, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/c-switch-right.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	// place cube
    	addSequential(new StateCommand(true));
    	addSequential(new SwitchDirection());
    	
    	addParallel(new WaitAndGoHome(1000));
    	
    	DriveTrainProfile profile2 = new DriveTrainProfile("/home/lvuser/profiles/c-switch-right-backwards.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile2));
    	addSequential(new SwitchDirection());
    	
    	addSequential(new StateCommand(true));
    	addSequential(new DriveToPoint(67, 0, 0.45));
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
    	*/
    }
}