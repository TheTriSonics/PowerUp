package org.usfirst.frc.team4003.robot.subsystems;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.ClimberCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberMotor extends Subsystem {

    //VictorSPX lift = new VictorSPX(RobotMap.CLIMBER_MOTOR);
    
    double maxPower = 0.5;
    
    public void setPower(double power) {
    	if (Math.abs(power) < 0.05) power = 0;
    	power *= maxPower;
		//lift.set(ControlMode.PercentOutput, power);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ClimberCommand());
    }
}

