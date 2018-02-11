package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchRight extends CommandGroup {

    public RightSwitchRight() {
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/r-switch-right.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new WaitForTime(1000));
    	profile = new DriveTrainProfile("/home/lvuser/profiles/r-switch-backup.profile.csv");
    	addSequential(new SwitchDirection());
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(198, 30, 0.35));
    }
}
