/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4003.robot.Robot;

public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        double yOutput = -Robot.oi.driver.getY(Hand.kLeft);
        double xOutput = -Robot.oi.driver.getY(Hand.kRight);
        //Robot.drive.arcadeDrive(yOutput, xOutput, false);
        Robot.drive.setPower(yOutput, xOutput);
        //Robot.drive.setRightOutput(ControlMode.PercentOutput, xOutput);
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
    	Robot.drive.setPower(0,0);
        //Robot.drive.setLeftOutput(ControlMode.PercentOutput, 0.0);
        //Robot.drive.setRightOutput(ControlMode.PercentOutput, 0.0);

    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }
}
