/*
 * Ryan Galligher, rpg170130
 */

package Calculator;

public class Number 
{
	double realnum;
	
	public Number(double num)
	{
		realnum=num;
	}
	
	public double getNum() {return realnum;}
	public void setNum(double num) {realnum=num;}
	/**
	 * If the floor and ceiling are the same (meaning that it is a round integer) then format out the excess zeros, else simply bound the decimal to 2 decimal places
	 */
	public String toString() {return ((Math.ceil(realnum)!=Math.floor(realnum)) ? String.format("%.2f", realnum):String.format("%.0f", realnum) ) ;}
	
	public boolean equals(Object num)
	{
		if( !(num instanceof Number) )
			return false;
		else
			return ((((Number)num).getNum() == realnum) ? true:false );
			
	}
	/**
	 * Takes in a Number Object and a char for the order of Operation performed (+,-,etc) and returns the Number which contains the results of the operation.
	 * @param num Number Object to be operated with.
	 * @param orderOfOperation Char of the operation to be performed (+,-,etc)
	 * @return Number that contains the result
	 */
	public Number changeTogether(Object num, char orderOfOperation)
	{
		if(!(num instanceof Number))
			throw new IllegalArgumentException("num must be a Number");
		
		if(orderOfOperation=='+')
			return new Number( getNum()+((Number)num).getNum() );
		if(orderOfOperation=='-')
			return new Number( getNum()-((Number)num).getNum()  );
		if(orderOfOperation=='*')
			return new Number( getNum()*((Number)num).getNum()  );
		if(orderOfOperation=='/')
			return new Number( getNum()/((Number)num).getNum()  );
		throw new IllegalArgumentException("Incorrect order of operation");
	}
	
	/**
	 * Takes in Number Object and a String for the expression to perform, and returns with a boolean if the expression was correct or not.
	 * @param num Number Object to be compared against.
	 * @param s String of comparison operator.
	 * @return if the expression is true or not.
	 */
	public boolean compareTogether(Object num, String s)
	{
		if(!(num instanceof Number))
			throw new IllegalArgumentException("num must be a Number");
		
		if(s.contains("="))
			return ((s.contains("/")) ? !equals(num):equals(num));
		if(s.contains(">"))
			return getNum()>((Number)num).getNum();
		if(s.contains("<"))
			return getNum()<((Number)num).getNum();
		
		throw new IllegalArgumentException("Incorrect order of operation");
	}
	
	/*public boolean isGreaterThan(Object num)
	{
		if(!(num instanceof Number))
			throw new IllegalArgumentException("num must be a Number");
		
		if(((Number)num).getNum() < getNum())
			return true;
		return false;
	}
	public boolean isLessThan(Object num)
	{
		if(!(num instanceof Number))
			throw new IllegalArgumentException("num must be a Number");
		
		if(((Number)num).getNum() > getNum())
			return true;
		return false;
	}*/
}
