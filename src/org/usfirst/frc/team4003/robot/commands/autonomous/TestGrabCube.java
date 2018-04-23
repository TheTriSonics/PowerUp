package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestGrabCube extends CommandGroup {

    public TestGrabCube() {
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(1000));
    	addSequential(new GrabCube(300, IntakeMotors.RIGHT, 3));
    	addSequential(new SetLiftHeight(LiftMotors.GROUND_LEVEL));
    	addSequential(new WaitForTime(4000));
    }
}
