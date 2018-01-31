package TieFighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	
	static final String DEFAULTPATHTOREAD="pilot_routes.txt";
	static final String DEFAULTPATHTOWRITE=" pilot_areas.txt";
	static final short XPOSITION = 0;
	static final short YPOSITION = 1;
	static final short TOTALPILOTS = 20;
	static final short TOTALVERTICES = 16;
	private static int[][][] vertices = new int[TOTALPILOTS][TOTALVERTICES][2];
	private static String[] pilotNames = new String[TOTALPILOTS];
	
	public static void main (String [] args)
	{
		try {
			//Program called through the main method.
			//call checkFile(String), and
				//if true then save the file,
				//else throw exception.
			File readFile = checkFile(DEFAULTPATHTOREAD, false);
			File writeFile = checkFile(DEFAULTPATHTOWRITE, true);
			Scanner s = new Scanner(System.in);
			
			while(readFile == null || writeFile == null)	//If one of the files was not found properly, asks the user to input the file path
			{
				System.out.println("Please Indicate Path To The File To " + ((readFile == null) ? "Read":"Write"));
				if(readFile == null)
					readFile = checkFile(s.nextLine(), false);
				else
					writeFile = checkFile(s.nextLine(), true);
			}
			s.close();
			s = new Scanner(readFile);
			//The program then calls readFromFile(Scanner), which will begin to read in the file.
			readFromFile(s);
			s.close();
			
			//call saveOutput() to output the calculated information from calculateAreas() into a file,
			saveOutput(calculateAreas(), writeFile);
			
			//and end the program.
			
		}catch(Exception e) {e.printStackTrace(); System.exit(1);}								//DELETE PRINTSTACKTRACE WHEN FINISHED
	}
	/**
	 * If the file is readable and able to be created it creates a File
	 * @param pathToFile The String of the path to the file that should be created
	 * @param replaceFile If the file could be zeroed out if one already exists
	 * @return The File if it was able to be created/found
	 */
	private static File checkFile(String pathToFile, boolean replaceFile)		//************************ASK ABOUT IF FOR GRADING WE NEED TO NOT CLEAR OUT THE OUTPUT FILE: Answer:Yes clear it
	{
		//When called, attempts to set up a File with the path provided,
			//and returns the File if it sets it up correctly
			//and returns null if it can't
		try {
			if(!replaceFile)
				return new File(pathToFile);
			else
			{
				File f = new File(pathToFile);
				if(f.isFile())	//If the file already exists when it shouldn't have anything in it, clear it out
					new FileWriter(pathToFile).close();
				return f;
			}
		}catch(Exception e) {e.printStackTrace();return null;}					//DELETE PRINTSTACKTRACE WHEN FINISHED
	}
	
	/**
	 * Attempts to read all the information from the file and save it to {@link vertices}
	 * @param s The Scanner of the file that is to be read from
	 */
	private static void readFromFile(Scanner s)
	{
		//calls the method canReadFile(Scanner s) and if there is still more information that can be read in,
			//it then reads in the pilot and the all of the vertices
			//cut up the name of the pilot and vertices from each other
			//and will add them to the 3d array and the pilot array.
		//if there are too many pilots/vertices/coordinates, ignore them
		//if there are too few, then fill the excess with null
		String[] toParse;
		for (short numPilotsProcessed = 0; numPilotsProcessed < TOTALPILOTS && canContinueReadingFile(s); numPilotsProcessed++)
		{
			toParse = s.nextLine().split(" ");
			pilotNames[numPilotsProcessed] = toParse[0];
			for (short vertexSpot = 1; vertexSpot < TOTALVERTICES -1 ; vertexSpot++ )
			{
				//If there is a vertex for that possible spot, then put in the grabbed int for it, else fill it with null
				vertices[numPilotsProcessed][vertexSpot - 1][0] = (vertexSpot < toParse.length) ? Integer.parseInt(toParse[vertexSpot].split(",")[0]):0;	//where default nonexistent point is set as 0
				vertices[numPilotsProcessed][vertexSpot - 1][1] = (vertexSpot < toParse.length) ? Integer.parseInt(toParse[vertexSpot].split(",")[1]):0;

			}
		}
		
	}

	/**
	 * Determines if there are still any other values left in the file that can be read in
	 * @param s the Scanner connected to the file to be read from
	 * @return if there are still more values in the File
	 */
	private static boolean canContinueReadingFile(Scanner s)				//**************************************************FINISH THIS
	{
		//When called, can read file will attempt to determine if there are any other values by:
			//1) seeing if there is no \n so no next line
			//2) take the next line, then determine if there is anything left in the file
		//and will return true if there are any other lines in the file and false if there aren't.
		
		if(s.hasNextLine())
			return true;
		else
			return false;
	}
	
	/**
	 * Calculates the areas of the paths of the different pilots
	 * @return the areas of the different pilots
	 */
	private static double[] calculateAreas()
	{
		//takes the calculated area and add it to an area array.
		//For every pilot that is put into the pilot array,
			//will calculate the the area by calculating:
				//For every vertice besides the last one
					//add the current x vertice and the next x vertice,
					//subtract the next y vertice and the current y vertices,
					//multiply those 2 values together
					//add the value to the overall total,
				//and afterwards multiply the total by 1/2.
			//Then save the calculated value to another array.
		double area = 0.0;
		double[] areas = new double[TOTALPILOTS];
		for(int pilot=0; pilot < TOTALPILOTS - 1; pilot++)
		{
			area = 0.0;
			for(int vertice = 0; vertice < vertices[pilot].length -1; vertice++)
			{
				area += (vertices[pilot][vertice + 1][XPOSITION] + vertices[pilot][vertice][XPOSITION])*(vertices[pilot][vertice + 1][YPOSITION] - vertices[pilot][vertice][YPOSITION]);
			}
			area = Math.abs(area) * 1/2;
			areas[pilot] = area;
		}
		return areas;
	}
	
	
	
	private static void saveOutput(double[] areas, File file) throws FileNotFoundException
	{
		PrintWriter save = new PrintWriter(file);
		//if can't write to the chosen file,
			//throw exception
		for(int i = 0; i < pilotNames.length ; i++)
		{
			if(pilotNames[i] != null)
				save.write(pilotNames[i] + " " + areas[i]);
			else
				break;
		}
		save.close();
	}
	
	
	/**
	 * Saves the information about the pilots and vertices to {@bold file}
	 * @param file File to write the read in information formatted correctly
	 */
	private static void saveReadOutput(File file) throws FileNotFoundException
	{ 	
		//try {
			PrintWriter save = new PrintWriter(file);
			//if can't write to the chosen file,
				//throw exception
			String total;
			short spot;
			for(int i = 0; i < pilotNames.length ; i++)
			{
				if(pilotNames[i] != null)
				{
					spot=0;
					total = pilotNames[i];
					while(spot < TOTALVERTICES )
					{
						if(vertices[i][spot][XPOSITION] != vertices[i][0][XPOSITION] && vertices[i][spot][YPOSITION] != vertices[i][0][YPOSITION])		//if this current point is the same as the first, then it needs to input it to be printed and then afterwards exit
							total += " " + vertices[i][spot][XPOSITION] + "," + vertices[i][spot][YPOSITION];
						else
						{
							total += " " + vertices[i][spot][XPOSITION] + "," + vertices[i][spot][YPOSITION];
							break;
						}
					}
					save.write(total);
				}
				else
					break;
			}
			save.close();
			//It will then save the information to the file.
		//}catch(Exception e) {e.printStackTrace();}							//************************ASK ABOUT IF FOR GRADING WE NEED TO NOT CLEAR OUT THE OUTPUT FILE
		
	}
}