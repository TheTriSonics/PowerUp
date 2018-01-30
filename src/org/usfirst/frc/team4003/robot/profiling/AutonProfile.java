package org.usfirst.frc.team4003.robot.profiling;

import org.usfirst.frc.team4003.robot.RobotMap;

public class AutonProfile extends MotionProfile {

    public AutonProfile() {
        setName("Testing");
        
        /* 
         * 
         * The order of the array is in position (revolutions)
         * followed by velocity (rotations/min)
         * followed by duration.
         * 
         * */
        setPoints(new double[][]{{0, 0, 10}});
        setSensorUnitsPerRotation(RobotMap.EXAMPLE_MOTOR_UNITS_PER_ROTATION);
    }

}
