package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.PrepareCube;
import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.commands.SwitchDirection;
import org.usfirst.frc.team4003.robot.commands.ToggleClamp;
import org.usfirst.frc.team4003.robot.commands.TogglePusher;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleLeftSide extends CommandGroup {

    public LeftScaleLeftSide() {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(1000, 2000, LiftMotors.SCALE_HIGH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/l-scale-l-side.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-18, 0.4));
    	
    	//addParallel(new SwitchDirection());
    	addSequential(new WaitForTime(1000));
    	
    	double x = 203;
    	double y = -44;
    	
    	addSequential(new RotateToPoint(x,y,0.55));
    	addSequential(new StateCommand(true));
    	
    	addSequential(new DriveToPoint(x, y, 0.4));
    	addSequential(new GrabCube(1200));
    	//addSequential(new PrepareCube(0, LiftMotors.SWITCH));
    	addSequential(new SetLiftHeight(LiftMotors.SWITCH_HIGH));
    	addSequential(new WaitForTime(1000));
    	addSequential(new TogglePusher());
    	addSequential(new DriveForDistance(8, 0.3));
    	
    	//addSequential(new StateCommand(true));
    	//addSequential(new WaitForTime(500));
    	addSequential(new ToggleClamp());
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.3));
    	
       
    }
}
