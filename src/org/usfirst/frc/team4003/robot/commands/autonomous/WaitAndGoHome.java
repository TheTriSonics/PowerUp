package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.state.CubeStateHomeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class WaitAndGoHome extends CommandGroup {

    public WaitAndGoHome(int time) {
    	addSequential(new WaitForTime(time));
    	addSequential(new CubeStateHomeCommand());
    }
        
}
