package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.PrepareCube;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleRight2 extends CommandGroup {

    public LeftScaleRight2() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(6000, LiftMotors.SCALE_HIGH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-scale-right-2.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-30, 0.4));
    	double x = 204;
    	double y = -195;
    	addSequential(new RotateToPoint(x, y, 0.45));
    	addSequential(new StateCommand(true));
    	addSequential(new DriveToPoint(x, y, 0.4));
    	addSequential(new GrabCube(600,IntakeMotors.LEFT));
    }
}
