package Hw3;

//package Hw3;
/**
 * 
 * @author Ryan Galligher, rpg170130
 *
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	static Scanner reader = new Scanner(System.in);
	static int amountRepeats;
	static CheckingAccount check;
	static SavingAccount save;
	
	public static void main(String [] args)
	{
		amountRepeats=0;
		do	//constantly attempt to read in input as to how many times to accept input and to print out the information
		{
			System.out.print("How many times do you want to run through different Checking and Saving Accounts: ");
			try {
				amountRepeats= Integer.parseInt(reader.nextLine());
			}catch(Exception e) {System.out.println("Unable to properly parse input");}
		}while(amountRepeats<=0);
		
		
		//For every single time that there should be inputted values
		boolean canContinue=false;
		ArrayList<Double> deposits = new ArrayList<Double>();
		ArrayList<Double> withdrawals = new ArrayList<Double>();
		for(int i = 0; i < amountRepeats; i++)		//Repeat taking in input for the checking/saving account and depositing/withdrawing data
		{
			deposits.clear();
			withdrawals.clear();
			double balance = getInput("What is the balance: ");
			double annualInterestRate = getInput("What is the annual Interest Rate: ");
			
			
			//System.out.println("The balance: " + balance);
			
			
			//Collects all of the deposits to make
			do
			{
				deposits.add(getInput("One deposit to be made: "));
				
				if(deposits.get(deposits.size()-1) < 0)
				{
					deposits.remove(deposits.size()-1);
					System.out.println("Not allowed to input negative deposits");
				}
				
				System.out.print("Another round of deposits?[Y/N]: ");
				canContinue=reader.nextLine().contains("Y");
			}while(canContinue);
			
			//Collects all of the withdrawals made
			canContinue=false;
			do
			{
				withdrawals.add(getInput("One withdrawl to be made: "));
				
				if(withdrawals.get(withdrawals.size()-1) < 0)
				{
					withdrawals.remove(withdrawals.size()-1);
					System.out.println("Not allowed to input negative withdrawals");
				}
				
				System.out.print("Another round of withdrawl?[Y/N]: ");
				canContinue=reader.nextLine().contains("Y");
			}while(canContinue);
			
			check = new CheckingAccount(balance, annualInterestRate);
			save = new SavingAccount(balance, annualInterestRate);
			
			//for both the checking and saving account, deposit all values then withdraw them
			for(int a = 0; a < deposits.size(); a++)
			{
				check.deposit(deposits.get(a));
				save.deposit(deposits.get(a));
			}
			for(int a = 0; a < withdrawals.size(); a++)
			{
				check.withdraw(withdrawals.get(a));
				save.withdraw(withdrawals.get(a));
			}
			
			//Now print out all of the necessary information
			System.out.println("");
			printAccount(check, balance, totalValues(deposits), totalValues(withdrawals), true);
			System.out.println("");
			printAccount(save, balance, totalValues(deposits), totalValues(withdrawals), false);
			//System.out.println("Finished round " + (i+1) + " out of " + amountRepeats);
			System.out.println("\n\n");
		}
	}
	
	public static double getInput(String s)
	{
		do 
		{
			System.out.print(s);
			try 
			{
				return Double.parseDouble(reader.nextLine());
			}catch(Exception e) {System.out.println("Unable to properly parse input");}
		}while(true);
	}
	
	public static double totalValues(ArrayList<Double> arr)
	{
		double total = 0.0;
		for (int i = 0; i < arr.size(); i++)
		{
			total+=arr.get(i);
		}
		return total;
	}
	
	public static void printAccount(Account acc, double beginningBalance, double totalDeposits, double totalWithdrawals, boolean isChecking)
	{
		if(isChecking)
			System.out.println("Checking Account");
		else
			System.out.println("Saving Account");
			
		System.out.format("Beginning Balance:			%.2f\n" , beginningBalance);
		
		System.out.format("Total Deposit Amount:			%.2f\n" , totalDeposits);
		System.out.format("Total Withdrawal Amount:		%.2f\n",totalWithdrawals);
		acc.monthlyProc();
		System.out.format("Service Charges:			%.2f\n" , acc.getmonthlyServiceChargesLastMonth());
		System.out.format("Ending Balance:				%.2f\n",acc.getBalance());
	}
	
}