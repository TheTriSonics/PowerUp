package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.commands.autonomous.WaitForTime;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowerLift extends CommandGroup {

    public LowerLift() {
    	addSequential(new WaitForTime(1000));
    	addSequential(new SetLiftHeight(LiftMotors.GROUND_LEVEL));
    	addSequential(new StateCommand(true));
    }
}
