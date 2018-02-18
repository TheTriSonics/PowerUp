package org.usfirst.frc.team4003.robot.profiling;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4003.robot.CSVReader;

public class DriveTrainProfile {
	
	private String profileFile;
	private ArrayList<Waypoint> leftWaypoints;
	private ArrayList<Waypoint> rightWaypoints;
	private List<String> commands;
	
	public DriveTrainProfile(String profileFile) {
		this.profileFile = profileFile;
		leftWaypoints = new ArrayList<Waypoint>();
		rightWaypoints = new ArrayList<Waypoint>();
		CSVReader reader = new CSVReader(this.profileFile);
		double[][] profilePoints = reader.parseCSV();
		commands = reader.getCommands();
		
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
	
	public String getCommand(int j) {
		return commands.get(j);
	}
	
	public ArrayList<Waypoint> getLeftWaypoints() {
		return leftWaypoints;
	}
	public ArrayList<Waypoint> getRightWaypoints() {
		return rightWaypoints;
	}
	public String getProfileFile() {
		return this.profileFile;
	}
	
	public void setProfileFile(String filePath) {
		this.profileFile = filePath;
	}
	
	

}
