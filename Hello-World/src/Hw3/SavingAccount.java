package Hw3;

//package Hw3;
/**
 * 
 * @author Ryan Galligher, rpg170130
 *
 */
public class SavingAccount extends Account
{
	private final int MINIMUMBALANCE = 25;
	private boolean accountActive = true;
	
	public SavingAccount(double balance, double annualInterest)
	{
		super(balance, annualInterest);
	}
	
	@Override
	public void withdraw(double amount)
	{
		if(accountActive)
			super.withdraw(amount);
		
		
		if(super.getBalance() - amount < MINIMUMBALANCE)
			accountActive=false;
	}
	
	@Override
	public void deposit(double amount)
	{
		if(!accountActive && super.getBalance() + amount > MINIMUMBALANCE)
			accountActive=true;
		
		super.deposit(amount);
	}
	
	public void monthlyProc()
	{
		if(super.getNumWithdrawalsThisMonth()>4)
			super.addMonthlyServiceCharges(super.getNumWithdrawalsThisMonth()-4);
		super.monthlyProc();
		if(super.getBalance()<MINIMUMBALANCE)
			accountActive=false;
	}
}
