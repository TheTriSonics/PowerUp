package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.commands.SetIntakeState;
import org.usfirst.frc.team4003.robot.commands.SetLiftHeight;
import org.usfirst.frc.team4003.robot.state.StateCommand;
import org.usfirst.frc.team4003.robot.subsystems.IntakeMotors;
import org.usfirst.frc.team4003.robot.subsystems.LiftMotors;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabCube extends CommandGroup {

    public GrabCube() {
    	addSequential(new StateCommand(true));//Going into pulling in state
    	addSequential(new WaitForTime(600));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	//addSequential(new SetLiftHeight(LiftMotors.DRIVING));
    	//addSequential(new StateCommand(true));

    }
    
    public GrabCube(int pause) {
    	addSequential(new StateCommand(true));//Going into pulling in state
    	addSequential(new WaitForTime(pause));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	//addSequential(new SetLiftHeight(LiftMotors.DRIVING));
    	//addSequential(new StateCommand(true));

    }
    
    public GrabCube(int pause, int direction) {
    	addSequential(new StateCommand(true));//Going into pulling in state
    	addSequential(new WaitForTime(pause));
    	//addSequential(new SetIntakeState(direction));
    	addSequential(new WaitForTime(pause));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	//addSequential(new SetLiftHeight(LiftMotors.DRIVING));
    	//addSequential(new StateCommand(true));

    }
    
    public GrabCube(int pause, int direction, int dummy) {
    	addSequential(new StateCommand(true));//Going into pulling in state
    	addSequential(new WaitForTime(100));
    	addSequential(new SetIntakeState(IntakeMotors.LEFT));
    	addSequential(new WaitForTime(200));
    	addSequential(new SetIntakeState(IntakeMotors.ON));
    	addSequential(new WaitForTime(300));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    	addSequential(new StateCommand(true));
    	addSequential(new WaitForTime(200));
    }
}
