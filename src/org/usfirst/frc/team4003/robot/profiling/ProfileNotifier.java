package org.usfirst.frc.team4003.robot.profiling;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ProfileNotifier implements Runnable {

    private TalonSRX talon;

    public ProfileNotifier(TalonSRX talon) {
        this.talon = talon;
    }

    @Override
    public void run() {
        this.talon.processMotionProfileBuffer();
    }

}
