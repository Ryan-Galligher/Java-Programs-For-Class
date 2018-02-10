package Hw3;

public class CheckingAccount extends Account
{
	CheckingAccount(double balance, double annualInterest)
	{
		super(balance, annualInterest);
	}
	
	public void withdraw(double amount)
	{
		if(super.getBalance()-amount >= 0 )
			super.withdraw(amount);
		else
			super.addMonthlyServiceCharges(15);
	}
	
	public void monthlyProc()
	{
		super.addMonthlyServiceCharges(5 + (0.1*super.getNumWithdrawalsThisMonth()));
		super.monthlyProc();
	}
}
