/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4003.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static boolean DEBUG = true;

	public static final double ENCODER_TICKS_PER_INCH = 6;
    public static final int EXAMPLE_MOTOR_UNITS_PER_ROTATION = 4096;
    
    public static int DRIVER_CONTROLLER = 0;

    public static final int LEFT_ONE = 3;
	public static final int LEFT_TWO = 4;
	public static final int LEFT_THREE = 5;
	public static final int RIGHT_ONE = 6;
	public static final int RIGHT_TWO = 1;
	public static final int RIGHT_THREE = 2;

	public static final int LEFT_ENCODER_A = 2;
	public static final int LEFT_ENCODER_B = 3;
	
	public static final int RIGHT_ENCODER_A = 0;
	public static final int RIGHT_ENCODER_B = 1;
	
	public static final int GEAR_RELEASE = 1;

}
