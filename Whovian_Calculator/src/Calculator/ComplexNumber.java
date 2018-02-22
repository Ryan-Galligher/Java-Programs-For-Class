/*
 * Ryan Galligher, rpg170130
 */

package Calculator;

public class ComplexNumber extends Number
{
	double complexNum;
	/**
	 * Initializer
	 * @param realnumber the real number
	 * @param complexNumber the imaginary number
	 */
	public ComplexNumber(double realnumber, double complexNumber)
	{
		super(realnumber);
		complexNum = complexNumber;
	}
	/**
	 * Returns the imaginary number.
	 * @return imaginary number double. 
	 */
	public double getImaginaryNum() {return complexNum;}
	/**
	 * Sets the imaginary number.
	 * @param complexNumber the imaginary number.
	 */
	public void setImaginaryNum(double complexNumber) {complexNum = complexNumber;}
	/**
	 * Prints out the information of Complex Numbers as a String.
	 */
	public String toString()
	{
		if(super.getNum()!=0)
		{
			if(complexNum!=0)
				return super.toString() + ((complexNum<0) ? "":"+") + ((Math.ceil(complexNum)!=Math.floor(complexNum)) ? String.format("%.2f", complexNum):String.format("%.0f", complexNum) ) +"i";
			else
				return super.toString();
		}
		else
			return ((Math.ceil(complexNum)!=Math.floor(complexNum)) ? String.format("%.2f", complexNum):String.format("%.0f", complexNum) ) +"i";
	}
	
	/**
	 * Determines if the given object has the same values as this one.
	 */
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ComplexNumber))
			return false;
		else
			return ( ((ComplexNumber)obj).getNum() == super.getNum() && ((ComplexNumber)obj).getImaginaryNum() == getImaginaryNum() ) ? true:false;
	}
	
	/**
	 * Returns a ComplexNumber that is the reciprocal to the current one
	 * @return ComplexNumber that is reciprocal of current one.
	 */
	public ComplexNumber reciprocal()
	{
		double scale = super.getNum()*super.getNum() + getImaginaryNum()*getImaginaryNum();
		//System.out.println("\t\t\tThe Scale for the reciprocal is: " + scale);
		return new ComplexNumber(super.getNum()/scale, (-1)*getImaginaryNum()/scale);
	}
	
	/**
	 * Takes in another CompelxNumber and a char for the numerical operation to be performed (+,-, etc) and returns a ComplexNumber of the result.
	 */
	public ComplexNumber changeTogether(Object num, char orderOfOperation)
	{
		if(!(num instanceof ComplexNumber))
			throw new IllegalArgumentException("num must be a ComplexNumber");
		
		if(orderOfOperation=='+')
			return new ComplexNumber( super.getNum()+((ComplexNumber)num).getNum() , getImaginaryNum()+((ComplexNumber)num).getImaginaryNum() );	//Adds the numbers together
		if(orderOfOperation=='-')
			return new ComplexNumber( super.getNum()-((ComplexNumber)num).getNum() , getImaginaryNum()-((ComplexNumber)num).getImaginaryNum() );
		if(orderOfOperation=='*')
			return new ComplexNumber( super.getNum()*((ComplexNumber)num).getNum() - getImaginaryNum()*((ComplexNumber)num).getImaginaryNum() , super.getNum()*((ComplexNumber)num).getImaginaryNum() + getImaginaryNum()*((ComplexNumber)num).getNum() );
		if(orderOfOperation=='/')
			return changeTogether(((ComplexNumber)num).reciprocal(), '*');
		throw new IllegalArgumentException("Incorrect order of operation");
	}
	
	/**
	 * Takes in another ComplexNumber and a String of the relational expression that will be performed on it (=,/=,etc) and returns the boolean of if the expression is true.
	 */
	public boolean compareTogether(Object num, String s)
	{
		if(!(num instanceof ComplexNumber))
			throw new IllegalArgumentException("num must be a ComplexNumber");
		
		if(s.contains("="))	//If it is one of the equals/doesn't equal, returns if it does/doesn't
			return ((s.contains("/")) ? !equals(num):equals(num));
		if(s.contains(">"))	
			//return ( (super.equals(num)) ? getImaginaryNum()>((ComplexNumber)num).getImaginaryNum():super.compareTogether(num, s) );
			return (Math.sqrt(Math.pow(super.getNum(), 2) + Math.pow(getImaginaryNum(), 2)) > Math.sqrt(Math.pow(((ComplexNumber)num).getNum(), 2) + Math.pow(((ComplexNumber)num).getImaginaryNum(), 2)) );	//Determines if modulus for current complex number is greater than other modulus
		if(s.contains("<"))
			//return ( (super.equals(num)) ? getImaginaryNum()<((ComplexNumber)num).getImaginaryNum():super.compareTogether(num, s) );
			return (Math.sqrt(Math.pow(super.getNum(), 2) + Math.pow(getImaginaryNum(), 2)) < Math.sqrt(Math.pow(((ComplexNumber)num).getNum(), 2) + Math.pow(((ComplexNumber)num).getImaginaryNum(), 2)) );
			
		throw new IllegalArgumentException("Incorrect order of operation");
	}
	
	/*public ComplexNumber changeTogether(Number num, char orderOfOperation, boolean isComplexNumberFirst)
	{
		if(orderOfOperation=='+')
			return new ComplexNumber( super.getNum()+num.getNum() , getImaginaryNum());
		if(orderOfOperation=='-')
			return ((isComplexNumberFirst) ?  (new ComplexNumber( super.getNum()+num.getNum() , getImaginaryNum())):(new ComplexNumber(num.getNum()-super.getNum(), -getImaginaryNum())) );
		if(orderOfOperation=='*')
			return new ComplexNumber(super.getNum()*num.getNum(), getImaginaryNum()*num.getNum());
		if(orderOfOperation=='/')
		{
			if(isComplexNumberFirst)
				return new ComplexNumber(super.getNum()/getNum(), getImaginaryNum()/getNum());
			else
			{
				ComplexNumber recip = this.reciprocal();
				double denominator = changeTogether(recip, '*').getNum();
				return new ComplexNumber( num.getNum()*recip.getNum()/denominator , num.getNum()*recip.getImaginaryNum()/denominator );
			}
		}
		
		throw new IllegalArgumentException("Incorrect order of operation");
	}*/
}
