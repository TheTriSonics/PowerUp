package org.usfirst.frc.team4003.robot.profiling;

public abstract class MotionProfile {

    private String name;
    private double[][] points;
    private int sensorUnitsPerRotation;
    
    public String getName() {
        return this.name;
    }

    public double[][] getPoints() {
        return this.points;
    }

    public int getSensorUnitsPerRotation() {
        return this.sensorUnitsPerRotation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(double[][] points) {
        this.points = points;
    }

    public void setSensorUnitsPerRotation(int sensorUnitsPerRotation) {
        this.sensorUnitsPerRotation = sensorUnitsPerRotation;
    }

}
