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
public class Center extends CommandGroup {

    public Center(String gameData) {
    	String profile1, profile2, profile3;
    	if(gameData.charAt(0) == 'L') {
    		profile1 = "/home/lvuser/profiles/c-switch-left.profile.csv";
    		profile2 = "/home/lvuser/profiles/c-switch-left-backwards.profile.csv";
    	} else {
    		profile1 = "/home/lvuser/profiles/c-switch-right.profile.csv";
    		profile2 = "/home/lvuser/profiles/c-switch-right-backwards.profile.csv";
    	}
    	
    	//if(gameData.charAt(1) == 'L') profile3 = "/home/lvuser/profiles/c-scale-left.profile.csv";
    	//else profile3 = "/home/lvuser/profiles/c-scale-right.profile.csv";
    	
    	if(gameData.charAt(1) == 'L') profile3 = "/home/lvuser/profiles/c-switch-left-back-2.profile.csv";
    	else profile3 = "/home/lvuser/profiles/c-switch-right-back-2.profile.csv";
    	
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(100, LiftMotors.SWITCH));
    	DriveTrainProfile profile = new DriveTrainProfile(profile1);
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	// place cube
    	addSequential(new StateCommand(true));
    	addSequential(new SwitchDirection());
    	
    	addParallel(new WaitAndGoHome(1000));
    	
    	DriveTrainProfile backProfile2 = new DriveTrainProfile(profile2);
    	addSequential(new ExecuteDriveProfile(backProfile2));
    	addSequential(new SwitchDirection());
    	
    	addSequential(new StateCommand(true));
    	addSequential(new DriveToPoint(67, 0, 0.45));
    	addSequential(new GrabCube());
    	addSequential(new SwitchDirection());
    	addSequential(new WaitForTime(200));
    	addSequential(new SetLiftHeight(LiftMotors.GROUND_LEVEL));
    	
    	DriveTrainProfile scaleProfile = new DriveTrainProfile(profile3);
    	addSequential(new ExecuteDriveProfile(scaleProfile));
    	
    	
    	/*
    	addSequential(new DriveToPoint(5, 0, 0.45));
    	addSequential(new SwitchDirection());
    	
    	
    	// Commented out on 3/7 -- test it
    	//addSequential(new StateCommand(true));
    	addSequential(new SetLiftHeight(LiftMotors.GROUND_LEVEL));
    	
    	CommandGroup group = new CommandGroup();
    	group.addSequential(new WaitForTime(1000));
    	//group.addSequential(new SetLiftHeight(LiftMotors.SWITCH));
    	group.addSequential(new WaitForTime(1000));;
    	group.addSequential(new StateCommand(true));
    	
    	//addParallel(new PrepareCube(100, 1000, LiftMotors.SWITCH));
    	DriveTrainProfile scaleProfile = new DriveTrainProfile(profile3);
    	addSequential(new ExecuteDriveProfile(scaleProfile));
    	/*
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	//addSequential(new StateCommand(true));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.4));
    	*/
    }
}
