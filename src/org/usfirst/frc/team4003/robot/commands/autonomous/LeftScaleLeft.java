package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleLeft extends CommandGroup {

    public LeftScaleLeft() {
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-scale-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new WaitForTime(1000));
    }
}
