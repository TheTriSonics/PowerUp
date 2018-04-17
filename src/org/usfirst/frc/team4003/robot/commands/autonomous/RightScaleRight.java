package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.PrepareCube;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRight extends CommandGroup {

    public RightScaleRight() {
    	addSequential(new CubeInit());
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/r-scale-right.profile.csv");
    	addParallel(new ExecuteDriveProfile(profile));
    	addSequential(new PrepareCube(4000, 2500, LiftMotors.SCALE_HIGH));
    	
    	//addParallel(new PrepareCube(4000, LiftMotors.SCALE_HIGH));
    	//addSequential(new ExecuteDriveProfile(profile));
    	
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.4));
    	addSequential(new WaitForTime(1000));
    	addSequential(new RotateToPoint(4000, 4000, 0.65));
    	
    }
}
