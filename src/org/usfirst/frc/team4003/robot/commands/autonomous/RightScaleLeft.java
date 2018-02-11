package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleLeft extends CommandGroup {

    public RightScaleLeft() {
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/r-scale-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new WaitForTime(1000));
    }
}
