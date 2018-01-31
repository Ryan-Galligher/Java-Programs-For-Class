import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplaySalary 
{
	static final int FIRSTNAMESPOT = 0;
	static final int LASTNAMESPOT  = 1;
	static final int JOBSPOT = 2;
	static final int SALARYSPOT = 3;
	
	public static void main(String [] args)
	{
		try
		{
			String[] jobs = {"assistant","associate","full","all"};
			ArrayList<String[]> stuff = new ArrayList<String[]>();
			//String[] cutPieces;
			File f = new File("Salary.txt");
			Scanner sc = new Scanner(f);
			
			if(!f.exists())
				throw new IOException("File does not exist");
			//System.out.println("Got past determining if the file Existed");
			while(sc.hasNextLine())
			{
				stuff.add(sc.nextLine().split(" "));
			}
			//System.out.println("Got past reading in the file");
			sc.close();
			for(int i = 0; i < jobs.length; i++)
			{
				printAverageSalaries(stuff, ((!jobs[i].equals("all") ? (new String[] {jobs[i]}):jobs)) );
			}
			//System.out.println("Finished");
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	public static void printAverageSalaries(ArrayList<String[]> allPeople, String[] allJobsToCalculate)
	{
		double totalSalaries = 0.0;
		int totalCountedMembers = 0;
		for (int i = 0; i < allJobsToCalculate.length; i++)	//for every type of job to be calculated
		{
			for(int a = 0; a < allPeople.size(); a++)	//for every single person to be looked at
			{
				if(allPeople.get(a)[JOBSPOT].equals(allJobsToCalculate[i]))		//if that person has the specified job, then add them
				{
					totalCountedMembers++;
					totalSalaries += Double.parseDouble(allPeople.get(a)[SALARYSPOT]) ;
				}
			}
		}
		
		String allJobs = "";
		for (int i = 0; i < allJobsToCalculate.length; i++)
		{
			allJobs += allJobsToCalculate[i] + " faculty, ";
			//System.out.println(allJobs);
		}
		if(!allJobs.contains("all"))
			allJobs = allJobs.substring(0,allJobs.length()-2);
		else
			allJobs = "all faculty";
		//System.out.println(allJobs);
		
		double total = totalSalaries/totalCountedMembers;

		
		System.out.format("The total salaries of " + allJobs + " is " + "%.2f\n",  totalSalaries);
		System.out.format("The average salaries of " + allJobs + " is " +"%.2f\n", total );
		System.out.println("");
	}
}
