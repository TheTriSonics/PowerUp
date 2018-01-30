package org.usfirst.frc.team4003.robot.commands.autonomous;

import org.usfirst.frc.team4003.robot.Robot;
import org.usfirst.frc.team4003.robot.profiling.MotionProfile;
import org.usfirst.frc.team4003.robot.profiling.ProfileExecutor;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import edu.wpi.first.wpilibj.command.Command;

public class MotionProfileTester extends Command {

    private ProfileExecutor leftProfileExecuter;
    private ProfileExecutor rightProfileExecuter;


    public MotionProfileTester(MotionProfile profile) {
         requires(Robot.drive);
         //leftProfileExecuter = new ProfileExecutor(Robot.drive.getLeftSide(), profile);
         //rightProfileExecuter = new ProfileExecutor(Robot.drive.getRightSide(), profile);
    }

    @Override
    protected void initialize() {
        leftProfileExecuter.startMotionProfile();
        rightProfileExecuter.startMotionProfile();

    }

    @Override
    protected void execute() {
        leftProfileExecuter.controlMotionProfile();
        rightProfileExecuter.controlMotionProfile();

        //Robot.drive.setRightOutput(ControlMode.MotionProfile, rightProfileExecuter.getSetValue().value);
        //Robot.drive.setLeftOutput(ControlMode.MotionProfile, leftProfileExecuter.getSetValue().value);
    }

    @Override
    protected boolean isFinished() {
        return (leftProfileExecuter.getSetValue() == SetValueMotionProfile.Hold) 
        		&& (rightProfileExecuter.getSetValue() == SetValueMotionProfile.Hold);
    }

    @Override
    protected void end() {
        leftProfileExecuter.resetMotionProfile();
        rightProfileExecuter.resetMotionProfile();

        //Robot.drive.setLeftOutput(ControlMode.PercentOutput, 0.0);
        //Robot.drive.setRightOutput(ControlMode.PercentOutput, 0.0);

    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }
}
