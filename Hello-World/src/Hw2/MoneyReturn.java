package Hw2;
import java.util.Scanner;

public class MoneyReturn {
		
	static int[] centValues = {1,5,10,25};
	static int[] dollarValues = {1,5,10,20,50,100};
	
	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the amount: ");
		String input = sc.nextLine();
		String[] str = input.split(" ");
		int[] amount = new int[2];
		int[] dollarAmounts = new int[6];			//Dollars are 1,5,10,20,50,100
		int[] centAmounts = new int[4];				//Cents are 1,5,10,25
		sc.close();
		
		for(String string:str)
		{
			if(!string.equals(""))
			{
				if(string.contains("."))
				{
					amount[0] = Integer.parseInt(string.substring(0, string.indexOf(".")));
					amount[1] = Integer.parseInt(string.substring(string.indexOf(".") + 1));
					break;
				}
				else
				{
					amount[0] = Integer.parseInt(string);
					amount[1] = 0;
				}
			}
		}		
		breakMoney(amount[0], dollarAmounts, dollarValues);
		breakMoney(amount[1], centAmounts, centValues);
		
		//System.out.println(printInfo(dollarAmounts, centAmounts));
		
		printInfo(dollarAmounts, centAmounts);
	}
	
	private static void breakMoney(int value, int[] amounts, int[] values)
	{
		for(int i = amounts.length-1; i >= 0; i--)
		{
			amounts[i] = value/values[i];
			value %= values[i];
		}
	}
	
	private static void printInfo(int[] dollarAmount, int[] centAmount)
	{
		/*String output = "|	Dollar	|	Cent	|";
		for (int i = 0; i < dollarAmount.length || i < centAmount.length ; i++)
		{
			output += "|	" + ((i < dollarAmount.length) ? dollarAmount[i]:"0") + "	|	" + ((i < centAmount.length) ? centAmount[i]:"0") + "	|";
		}
		*/
		//TextTable tt = new TextTable({"Dollar","Cents"}, )
		
		//return output;
		
		System.out.format("%12s %5s %2s %5s %5s \n","Dollar Value", "Dollar", "|", "Cent", "Cent Value");

		for (int i = 0; i < ((dollarValues.length > centValues.length) ? dollarValues.length:centValues.length); i++) {
		        System.out.format("%12d %5d %3s %5d %5d \n",((i < dollarValues.length) ? dollarValues[i]:0) ,((i < dollarAmount.length) ? dollarAmount[i]:0), "|" , ((i < centAmount.length) ? centAmount[i]:0), ((i < centValues.length) ? centValues[i]:0) );
		}
	}

}
