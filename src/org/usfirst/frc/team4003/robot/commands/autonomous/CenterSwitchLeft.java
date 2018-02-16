package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchLeft extends CommandGroup {

    public CenterSwitchLeft() {
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/c-switch-left.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new WaitForTime(1000));
    	DriveTrainProfile profile2 = new DriveTrainProfile("/home/lvuser/profiles/c-switch-left-backwards.profile.csv");
    	addSequential(new SwitchDirection());
    	addSequential(new ExecuteDriveProfile(profile2));
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(85, 0, 0.4));
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(5, 0, 0.4));
    	addSequential(new SwitchDirection());
    	addSequential(new ExecuteDriveProfile(profile));
    }
}
