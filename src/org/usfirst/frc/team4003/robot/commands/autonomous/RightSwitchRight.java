package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.LowerLift;
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
public class RightSwitchRight extends CommandGroup {

    public RightSwitchRight() {
    	PrepareCube prepareCube = new PrepareCube(2000, LiftMotors.SWITCH);
    	LowerLift lowerLift = new LowerLift();
    	
    	
    	addSequential(new CubeInit());
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/r-switch-right.profile.csv");
    	addParallel(prepareCube);
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(1000));
    	addSequential(new StateCommand(true));
    	profile = new DriveTrainProfile("/home/lvuser/profiles/r-switch-backup.profile.csv");
    	addParallel(lowerLift);
    	addSequential(new SwitchDirection());
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new SwitchDirection());
    	addSequential(new DriveToPoint(198, 30, 0.35));
    }
}
