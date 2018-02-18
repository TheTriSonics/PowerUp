package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.commands.autonomous.WaitForTime;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepareCube extends CommandGroup {

    public PrepareCube(int delay, int height) {
    	addSequential(new WaitForTime(delay));
    	addSequential(new SetLiftHeight(height));
    	addSequential(new WaitForTime(2000));
    	addSequential(new StateCommand(true));
    }
}
