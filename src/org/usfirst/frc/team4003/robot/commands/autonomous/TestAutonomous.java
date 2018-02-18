package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutonomous extends CommandGroup {

	public TestAutonomous(DriveTrainProfile profile) {
		addParallel(new ExecuteDriveProfile(profile));
		//addSequential(new WaitForTime(2000));
		//addSequential(new TriggerGearRelease(true));
		
		
	}
}
