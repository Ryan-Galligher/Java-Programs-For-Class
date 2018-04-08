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
	
	
	public static final String DEFAULTPATHTOREADINFO="pilot_routes.txt";
	public static final String DEFAULTPATHTOREADCOMMAND="commands.txt";
	public static final String DEFAULTPATHTOWRITEINFO="pilot_areas.txt";
	public static final String DEFAULTPATHTOWRITECOMMANDS="results.txt";
	public static final short XPOSITION = 0;
	public static final short YPOSITION = 1;
	
	
	public static final String INFOREGEX = "([A-Za-z0-9\\-']+\\s)+((-?\\d+(\\.\\d+)?||\\.\\d+),(-?\\d+(\\.\\d+)?||\\.\\d+)\\s?){4,}";
	public static final String COMMANDREGEX = "sort\\s(area||pilot)\\s(asc||dec)||(-?\\\\d+(\\.\\d+)?||\\.\\d+)||([A-Za-z\\-']+\\s?)+";
	public static final String COMMANDREGEXSORT = "sort\\s(area||pilot)\\s(asc||dec)";
	public static final String COMMANDREGEXAREA = "(-?\\d+(\\.\\d+)?||\\.\\d+)";
	public static final String COMMANDREGEXNAME = "([A-Za-z\\-']+\\s?)+";
	
	private static LinkedList list;
	
	public static void main (String [] args)
	{
		try {
			
			list = new LinkedList();
			
			//Program called through the main method.
			//call checkFile(String), and
				//if true then save the file,
				//else throw exception.
			File readInfo = checkFile(DEFAULTPATHTOREADINFO, false);
			File readCommands = checkFile(DEFAULTPATHTOREADCOMMAND, false);
			File writeList = checkFile(DEFAULTPATHTOWRITEINFO, true);
			File writeCommands = checkFile(DEFAULTPATHTOWRITECOMMANDS,true);
			Scanner s = new Scanner(System.in);
			
			while( writeCommands == null ||readInfo == null || writeList == null || readCommands == null)	//If one of the files was not found properly, asks the user to input the file path
			{
				System.out.println("Please Indicate Path To The File To " + ((readInfo == null) ? "Read":"Write"));
				if(readInfo == null)
					readInfo = checkFile(s.nextLine(), false);
				if(readCommands==null)
					readCommands = checkFile(s.nextLine(),false);
				if(writeList == null)
					writeList = checkFile(s.nextLine(), true);
				if(writeCommands == null)
					writeCommands = checkFile(s.nextLine(), true);
			
			}
			s.close();
			s = new Scanner(readInfo);
			//The program then calls readFromFile(Scanner), which will begin to read in the file.
			readFromFile1(s);
			s.close();
			
			System.out.print("The current list is: \n" + list.toString());
			
			s = new Scanner(readCommands);
			String output = readFromFile2(s);
			s.close();
			
			saveToFile(output,writeCommands);
			
			saveToFile(list.toString(),writeList);
			//System.out.println("Finished");
			//and end the program.
			
		}catch(Exception e) { e.printStackTrace();System.exit(1);}
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
		}catch(Exception e) {return null;}
	}
	
	/**
	 * Reads from the Commands file, filters out incorrect commands, executes the commands and saves the output to a String to be returned
	 * @param s Scanner to the file that contains the commands
	 * @return The output of all the commands contained in the file, split by /r/n
	 */
	private static String readFromFile2(Scanner s)
	{
		System.out.println("\tEntered readFromFile2");
		String output="";
		//For every valid line within the file
		while(canContinueReadingFile(s))
		{
			String str = s.nextLine();
			System.out.println("\t\tdoes " + str + " match as a command regex? " + str.matches(COMMANDREGEX));
			//If the command isn't in a valid command format, then skip the current line
			if(!str.matches(COMMANDREGEX))
				continue;
			if(str.matches(COMMANDREGEXSORT))
			{
				String[] stuff = str.split(" ");
				System.out.println("\t\t\tStarted sorting");
				list.sort(stuff[2].contains("asc"), stuff[1].contains("pilot"));
				System.out.println("\t\t\tFinished sorting");
				System.out.println("\t\tAnd the toString is: " + list.toString());
				output+= String.format("%30s\t\tHead: %30s Tail: %30s\r\n", str,list.getInfo(0), list.getInfo(list.size()-1));
			}
			else {
				if(str.matches(COMMANDREGEXAREA))
				{
					double d = ((double)Math.round(Double.parseDouble(str)*100))/100.00;
					output+=String.format("%30s\t\t%30f %9s\r\n", str,d,(list.containsArea(d) ? "found":"not found"));
				}
				if(str.matches(COMMANDREGEXNAME))
				{
					output+=String.format("%30s\t\t%30s %9s\r\n", str,str,(list.containsPilot(str) ? "found":"not found"));
				}
			}
			System.out.println("\t\tCurrently the output after doing things is: \n" + output);
		}
		return output;
	}
	
	/**
	 * Attempts to read all the information from the file and save it to {@link vertices}
	 * @param s The Scanner of the file that is to be read from
	 */
	private static void readFromFile1(Scanner s)
	{
		//calls the method canReadFile(Scanner s) and if there is still more information that can be read in,
			//it then reads in the pilot and the all of the vertices
			//cut up the name of the pilot and vertices from each other
			//and will add them to the 3d array and the pilot array.
		//if there are too many pilots/vertices/coordinates, ignore them
		//if there are too few, then fill the excess with null
		String[] toParse;
		int spot=0;
		String name = "";
		double[][] vertices;
		String str;
		
		System.out.println("\tEntered into readFromFile1");
		
		while (canContinueReadingFile(s))
		{
			str =s.nextLine();
			
			System.out.println("\t\tJust read " + str + " from file");
			
			if(!str.matches(INFOREGEX))
				continue;
			toParse = str.split(" ");
			if(toParse[0].equals(""))
				continue;
			
			//Now needs to go through to determine how many different many pieces of the now cut up array are names
			name="";
			for(int i = 0; i < toParse.length;i++)
			{
				if(toParse[i].matches("-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?"))
				{
					spot=i;
					break;
				}
				name+=toParse[i]+" ";
			}
			name=name.substring(0, name.length()-1);
			System.out.println("\t\t\tThe Name is: " + name);
			
			vertices = new double[toParse.length-spot][2];
			
			//Now goes through and makes one double array with all of the coordinates
			for (int vertexSpot = spot; vertexSpot < toParse.length  ; vertexSpot++ )
			{
				//If there is a vertex for that possible spot, then put in the grabbed int for it, else fill it with null
				vertices[vertexSpot-spot][XPOSITION] = (vertexSpot < toParse.length) ? Double.parseDouble(toParse[vertexSpot].split(",")[0]):0;	//where default nonexistent point is set as 0
				vertices[vertexSpot-spot][YPOSITION] = (vertexSpot < toParse.length) ? Double.parseDouble(toParse[vertexSpot].split(",")[1]):0;
			}
		
			list.add(name, calculateArea(vertices));
		}
		
		System.out.println("\tOutside readFile1 while loop");
	}

	/**
	 * Determines if there are still any other values left in the file that can be read in
	 * @param s the Scanner connected to the file to be read from
	 * @return if there are still more values in the File
	 */
	private static boolean canContinueReadingFile(Scanner s)	
	{
		//When called, can read file will attempt to determine if there are any other values by:
			//1) seeing if there is no \n so no next line
			//2) take the next line, then determine if there is anything left in the file
		//and will return true if there are any other lines in the file and false if there aren't.
		
		if(s.hasNextLine() )
			return true;
		else
			return false;
	}
	
	/**
	 * Calculates the areas of the paths of the different pilots
	 * @return the areas of the different pilots
	 */
	private static double calculateArea(double[][] vertices)
	{
		//takes the calculated area and add it to an area array.
			//will calculate the the area by calculating:
				//For every vertice besides the last one
					//add the current x vertice and the next x vertice,
					//subtract the next y vertice and the current y vertices,
					//multiply those 2 values together
					//add the value to the overall total,
				//and afterwards multiply the total by 1/2.
		System.out.print("\t\t\tvertices are: ");
		for(int i = 0; i < vertices.length;i++)
		{
			System.out.print(vertices[i][XPOSITION]+","+vertices[i][YPOSITION]+" ");
		}
		System.out.println("");
		
		double area = 0.0;
		for(int vertice = 0; vertice < vertices.length -1; vertice++)
		{
			if(vertice == 0 || ( vertices[vertice][XPOSITION] != vertices[0][XPOSITION] || vertices[vertice][YPOSITION] != vertices[0][YPOSITION] ) )
				area += (vertices[vertice + 1][XPOSITION] + vertices[vertice][XPOSITION])*(vertices[vertice + 1][YPOSITION] - vertices[vertice][YPOSITION]);
		}
		area = Math.abs(area) * 1/2;
		System.out.println("\t\t\t\tThe area that was found for this was: " + area);
		return area;
	}

	/**
	 * Saves the given string of information and saves it to the given file
	 * @param info The String of information that is to be saved to the file
	 * @param file The file that the String is to be saved to
	 * @throws FileNotFoundException
	 */
	private static void saveToFile(String info, File file) throws FileNotFoundException
	{
		PrintWriter save = new PrintWriter(file);

		String[] s = info.split("\n");
		for(int i = 0; i < s.length; i++)
		{
			save.write(s[i]);
			save.println();
		}
		
		save.close();
	}
	
	
	
}