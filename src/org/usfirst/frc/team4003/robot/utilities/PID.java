package org.usfirst.frc.team4003.robot.utilities;

public class PID {
	double kp;
	double ki;
	double kd;
	double target;
	double lastError;
	double totalError;
	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		lastError = 0;
		totalError = 0;
	}
	public void setTarget(double target) {
		this.target = target;
	}
	public double getCorrection(double current){
		double error = target - current;
		totalError = totalError + error;
		double changeInError = error - lastError;
		lastError = error;
		return kp * error + ki * totalError + kd * changeInError;
	}
}
