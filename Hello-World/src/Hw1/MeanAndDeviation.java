package Hw1;
import java.text.DecimalFormat;
import java.util.Scanner;

public class MeanAndDeviation {

	//Write a program that prompts the user to enter ten numbers, and displays the mean and standard deviations of these numbers.
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean allowThrough=true;
		int[] nums = new int[10];
		Scanner scan = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("#.##");
		do {
			try {
				System.out.print("Enter the 10 numbers: ");
				for (int i = 0; i < 10; i++)
				{
					nums[i] = scan.nextInt();
				}
				allowThrough=true;
			}catch(Exception e) {System.out.println("Information was not input correctly, please try again.");allowThrough=false;}
		}while(!allowThrough);
		scan.close();
		
		double mean = 0;
		for (int i = 0; i < nums.length; i++)
		{
			mean += nums[i];
		}
		mean = mean / nums.length;
		
		double standardDeviation = 0;
		for(int i = 0; i < nums.length; i++)
		{
			standardDeviation += (int) Math.pow((nums[i]-mean),2);
		}
		standardDeviation /= nums.length;
		
		System.out.println("The mean is " + df.format(mean));
		System.out.println("The Standard Deviation is " + df.format(standardDeviation));
		
	}

}
