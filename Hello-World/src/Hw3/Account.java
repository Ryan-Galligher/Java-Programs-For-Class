package Hw3;

//package Hw3;
/**
 * 
 * @author Ryan Galligher, rpg170130
 *
 */
public class Account
{
	private double balance;
	private int numDepositsThisMonth;
	private int numWithdrawalsThisMonth;
	private double annualInterestRate;
	private double monthlyServiceCharges;
	private double monthlyServiceChargesLastMonth;
	
	public Account(double balance, double annualInterestRate)
	{
		this(balance, annualInterestRate, 0,0,0 );
	}
	
	public Account(double balance, double annualInterestRate, int numDepositsThisMonth, int numWithdrawalsThisMonth, int monthlyServiceCharges)
	{
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
		this.numDepositsThisMonth = numDepositsThisMonth;
		this.numWithdrawalsThisMonth = numWithdrawalsThisMonth;
		this.monthlyServiceCharges=monthlyServiceCharges;
		this.monthlyServiceChargesLastMonth=0;
	}
	
	public void deposit(double amount)
	{
		if(amount<0)
			return;
		balance+=amount;
		numDepositsThisMonth++;
	}
	
	public void withdraw(double amount)
	{
		if(amount<0)
			return;
		balance-=amount;
		numWithdrawalsThisMonth++;
	}
	
	private void calcInt()
	{
		balance+=(balance * (annualInterestRate/12));
	}
	
	public void monthlyProc()
	{
		balance -= monthlyServiceCharges;
		calcInt();
		
		monthlyServiceChargesLastMonth=monthlyServiceCharges;
		numDepositsThisMonth=0;
		numWithdrawalsThisMonth=0;
		monthlyServiceCharges=0;
	}
	
	public double getBalance() {return balance;}
	public void setBalance(double amount) {balance = amount;}
	public double getMonthlyServiceCharges() {return monthlyServiceCharges;}
	public void addMonthlyServiceCharges(double amount) {monthlyServiceCharges=amount;}
	public int getNumDepositsThisMonth() {return numDepositsThisMonth;}
	public int getNumWithdrawalsThisMonth() {return numWithdrawalsThisMonth;}
	public double getmonthlyServiceChargesLastMonth() {return monthlyServiceChargesLastMonth;}
}
