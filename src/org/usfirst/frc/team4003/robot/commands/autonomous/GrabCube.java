package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabCube extends CommandGroup {

    public GrabCube() {
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(500));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	//addSequential(new SetLiftHeight(LiftMotors.DRIVING));
    	//addSequential(new StateCommand(true));

    }
}
