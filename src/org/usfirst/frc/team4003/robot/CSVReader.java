package org.usfirst.frc.team4003.robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import org.usfirst.frc.team4003.logging.FRCLogger;

public class CSVReader {
	String filename;
    double vmax;
    List<String> commands = new ArrayList<>();
    public CSVReader(String filename) {
	this.filename = filename;
    }

    public double[][] parseCSV() {
	File file= new File(filename);
	
	List<List<String>> lines = new ArrayList<>();
	Scanner inputStream;
	
	try{
	    inputStream = new Scanner(file);
	    vmax = Double.parseDouble(inputStream.nextLine().trim());
	    //System.out.println("vmax equals: " + vmax);
	    while(inputStream.hasNextLine()){
	    	String line= inputStream.nextLine();
	    	String[] values = line.split(",");
	    	lines.add(Arrays.asList(values));
	    }
	    
	    inputStream.close();
	    
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
//	System.out.println(lines.size());
	// the following code lets you iterate through the 2-dimensional array
	double [][] profile = new double[lines.size()][5];
	for(int j = 0; j < lines.size(); j++) {
	    List<String> line = lines.get(j);
	    for (int i = 0; i < 5; i++) {
	    	profile[j][i] = Double.parseDouble(line.get(i));
	    }
	    
	    commands.add(line.get(5));
	    
	}
	return profile;
    }
    
    public List<String> getCommands() {
    	return commands;
    }

    public double getVmax() {
	return vmax;
    }

    public static void main(String[] args) {
	String filename = args[0];
	CSVReader reader = new CSVReader(filename);
	reader.parseCSV();
    }
}
