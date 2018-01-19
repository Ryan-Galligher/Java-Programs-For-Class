package TieFighter;

import java.io.File;
import java.util.Scanner;

public class Main
{
	/*
	 * Name: 	Ryan Galligher
	 * Net ID:	rpg170130
	 */
	
	//[1][2][3] for 3D array are for: 
	//[1] is for every single pilot
	//[2] is for every single vertex that the pilot made
	//[3] is for the x and y coordinates, with x =0 and y=1

	public static void main (String [] args)
	{
		//Program called through the main method.
		//call checkFile(String), and
			//if true then save the file,
			//else throw exception.
		
		//The program then calls readFile(File), which will begin to read the file.
		
		//for every pilot, 
			//call calculateArea() 
		
		//call saveOutpt() to output the calculated information into a file,
		
		//and end the program.
	}
	
	private File checkFile(String pathToFile)
	{
		//When called, attempts to set up a File with the path provided,
			//and returns the File if it sets it up correctly
			//and returns null if it can't
		return null;
	}
	
	private void readFile(File f)
	{
		//Puts the file within a scanner
		//calls the method canReadFile(Scanner s) and if there is still more information that can be read in,
			//it then reads in the pilot and the all of the vertices
			//cut up the name of the pilot and vertices from each other
			//and will add them to the 3d array and the pilot array.
		//if there are too many pilots/vertices/coordinates
			//throw exception
		
	}

	private boolean canReadFile(Scanner s)
	{
		//When called, can read file will attempt to determine if there are any other values by:
			//1) seeing if there is no \n so no next line
			//2) take the next line, then determine if there is anything left in the file
		//and will return true if there are any other lines in the file and false if there aren't.
		return true;
	}
	
	private void calculateAreas()
	{
		//takes the calculated area and add it to an area array.
		//For every pilot that is put into the pilot array,
			//will calculate the the area by calculating:
				//For every vertice besides the last one
					//add the current x vertice and the next x vertice,
					//subtract the next y vertice and the current y vertice,
					//multiply those 2 values together
					//add the value to the overall total,
				//and afterwards multiply the total by 1/2.
			//Then save the calculated value to another array.
	}
	
	private void saveOutput()
	{ 
		//Will proceed to save the calculated values into the output file, by
		//first create a new output file, and to overwrite/clear any existing output file if it exists.
		
		//if can't write to the chosen file,
			//throw exception
		
		//It will then save the information to the file.
	}
}