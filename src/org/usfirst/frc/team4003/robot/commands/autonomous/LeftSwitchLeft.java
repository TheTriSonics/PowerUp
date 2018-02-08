package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchLeft extends CommandGroup {

    public LeftSwitchLeft() {
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new WaitForTime(1000));
    	profile = new DriveTrainProfile("/home/lvuser/profiles/l-switch-backup.profile.csv");
    	addSequential(new SwitchDirection());
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(198, -37, 0.4));
    }
}
