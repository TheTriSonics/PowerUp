package org.usfirst.frc.team4003.robot.profiling;

import java.util.ArrayList;

import org.usfirst.frc.team4003.robot.CSVReader;

public class DriveTrainProfile {
	
	private String profileFile;
	private ArrayList<Waypoint> leftWaypoints;
	private ArrayList<Waypoint> rightWaypoints;
	
	public DriveTrainProfile(String profileFile) {
		this.profileFile = profileFile;
		leftWaypoints = new ArrayList<Waypoint>();
		rightWaypoints = new ArrayList<Waypoint>();
		double[][] profilePoints = new CSVReader(this.profileFile).parseCSV();
		
		for (double[] point : profilePoints) {
			Waypoint leftWaypoint = new Waypoint();
			Waypoint rightWaypoint = new Waypoint();
			
			double leftPos = point[0];
	    	double leftVel = point[1];
	    	double rightPos = point[2];
	    	double rightVel = point[3];
	    	double heading = point[4];
	    	leftWaypoint.position = leftPos;
	    	leftWaypoint.velocity = leftVel;
	    	leftWaypoint.angle = heading;
	    	rightWaypoint.position = rightPos;
	    	rightWaypoint.velocity = rightVel;
	    	rightWaypoint.angle = heading;
	    	leftWaypoints.add(leftWaypoint);
	    	rightWaypoints.add(rightWaypoint);
		}
	}
	public ArrayList getLeftWaypoints() {
		return leftWaypoints;
	}
	public ArrayList getRightWaypoints() {
		return rightWaypoints;
	}
	public String getProfileFile() {
		return this.profileFile;
	}
	
	public void setProfileFile(String filePath) {
		this.profileFile = filePath;
	}
	
	

}
