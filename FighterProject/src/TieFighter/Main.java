package TieFighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
	public static final String GALAXYREGEX = "";
	public static final String PILOTSREGEX = "";
	public static final String GALAXYFILENAME = "";
	public static final String PILOTSFILENAME = "";
	public static final String PATROLSOUTPUTFILE = "";
	
	public static void main(String [] args)
	{
		Scanner galaxy=null;
		Scanner pilots=null;
		PrintWriter patrols = null;
		try
		{
			galaxy = new Scanner(new File(GALAXYFILENAME));
			pilots = new Scanner(new File(PILOTSFILENAME));
			patrols = new PrintWriter(new File(PATROLSOUTPUTFILE));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if(galaxy == null || pilots == null || patrols == null)
			System.exit(0);
		
		WeightedGraph graph;
		
	}

}
