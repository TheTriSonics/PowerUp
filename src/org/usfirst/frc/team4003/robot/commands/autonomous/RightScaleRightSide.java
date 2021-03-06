package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.ExecuteDriveProfile;
import org.usfirst.frc.team4003.robot.commands.PrepareCube;
import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.commands.ToggleClamp;
import org.usfirst.frc.team4003.robot.commands.TogglePusher;
import org.usfirst.frc.team4003.robot.profiling.DriveTrainProfile;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRightSide extends CommandGroup {

    public RightScaleRightSide(boolean placeCube) {
    	addSequential(new CubeInit());
    	addParallel(new PrepareCube(2000, 2000, LiftMotors.SCALE_HIGH));
    	DriveTrainProfile profile = new DriveTrainProfile("/home/lvuser/profiles/r-scale-r-side.profile.csv");
    	addSequential(new ExecuteDriveProfile(profile));
    	
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-15, 0.4));
    	
    	//addParallel(new SwitchDirection());
    	addSequential(new WaitForTime(1000));
    	
    	double x = 204.5;
    	double y = 46; //44;
    	
    	addSequential(new RotateToPoint(x,y,0.55));
    	addSequential(new StateCommand(true));
    	//Starting Seeking
    	
    	addSequential(new DriveToPoint(x, y, 0.45)); // was 0.4
    	addParallel(new DriveForDistance(-4, 0.3));
    	//addSequential(new GrabCube(300, IntakeMotors.RIGHT));
    	addSequential(new GrabCube(300, IntakeMotors.RIGHT, 3));
    	if(!placeCube) {
    		addSequential(new SetLiftHeight(LiftMotors.GROUND_LEVEL));
    		x += 55;
    		y -= 2; //7;
    		addSequential(new RotateToPoint(x, y, 0.65)); // was 0.65
    		addParallel(new DriveToPoint(x, y, 0.45)); // was 0.4
    		//addSequential(new WaitForTime(100));
    		addSequential(new SetLiftHeight(LiftMotors.SCALE_HIGH));
    		addSequential(new WaitForTime(1200)); // 1300
    		addSequential(new TogglePusher());
    		addSequential(new WaitForTime(800));
    		addSequential(new ToggleClamp());
    		addSequential(new WaitForTime(500));
    		addSequential(new TogglePusher());
    		addParallel(new WaitAndGoHome(500));
        	addSequential(new DriveForDistance(-24, 0.4));
    		return;
    	}
    	//addSequential(new PrepareCube(0, LiftMotors.SWITCH));
    	addSequential(new SetLiftHeight(LiftMotors.SWITCH_HIGH));
    	addSequential(new WaitForTime(800));
    	addSequential(new TogglePusher());
    	addSequential(new DriveForDistance(8, 0.3));
    	
    	//addSequential(new StateCommand(true));
    	//addSequential(new WaitForTime(500));
    	addSequential(new ToggleClamp());
    	addParallel(new WaitAndGoHome(1000));
    	addSequential(new DriveForDistance(-24, 0.3));
    }
}
