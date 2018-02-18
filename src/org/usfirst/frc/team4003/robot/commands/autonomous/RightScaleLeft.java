package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.*;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleLeft extends CommandGroup {

    public RightScaleLeft() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(10000, LiftMotors.SCALE));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/r-scale-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new StateCommand(true));
    	
    	
    }
}
