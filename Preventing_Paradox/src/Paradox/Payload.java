package Paradox;

import java.math.BigInteger;

/**
 * 
 * @author ryan Galligher
 *rpg170130
 */
public class Payload implements Comparable<Object>
{
	private String coefficientFraction;
	private double coefficient;
	private int exponent;
	
	public Payload()
	{
		coefficient=exponent=0;
		coefficientFraction="";
	}
	
	public Payload(double coefficient, int exponent)
	{
		this.coefficient=coefficient;
		this.exponent=exponent;
		coefficientFraction = "" + coefficient;
	}
	
	public Payload(double coefficient, int exponent, String coefficientFraction)
	{
		this.coefficient=coefficient;
		this.exponent=exponent;
		this.coefficientFraction = coefficientFraction;
	}
	
	public double getCoefficient() {return coefficient;}
	public int getExponent() {return exponent;}
	public String getCoefficientFraction() {return coefficientFraction;}
	
	public void setCoefficient(double coefficient) {this.coefficient = coefficient;}
	public void setExponent(int exponent) {this.exponent = exponent;}
	public void setCoefficientFraction(String fraction) {coefficientFraction = fraction;}

	
	public String toString() 
	{
		String coeffFracPrnt="";
		if(coefficient > 0)	//If there isn't a negative sign in the front, then add +
			coeffFracPrnt+="+";
		if(coefficient != 1 && coefficient != -1)	//If coefficient isn't a 1, just add it. If is 1/-1, then output will only show +/-x
			coeffFracPrnt+=coefficientFraction;
		if(coefficient == -1)	//If coeff is -1, then in order for neg sign to show up manually add -
			coeffFracPrnt+="-";
		if(coeffFracPrnt.equals(""))	//If non of the above were added (somehow), just add in the coefficientFraction
			coeffFracPrnt=coefficientFraction;
		if(exponent != 0)
		{
			if(exponent == 1)
				return coeffFracPrnt + "x";
			return "" + coeffFracPrnt + "x^" + exponent;
		}
		return coeffFracPrnt;
	}

	/**
	 * Adds together two different payload classes if they are able to be added together
	 * @param p
	 * @return
	 */
	public Payload addSameExponent(Payload p)
	{
		if(p.getExponent() != exponent)
			return null;
		String prntFrcn = "";
		if(Double.compare(p.getCoefficient()+coefficient, (double)((int)p.getCoefficient()+(int)coefficient))==0)	//If the two coefficients can be added to form an integer, represent it as an integer. Else, return it as a double
			prntFrcn="" + (((int)p.getCoefficient())+((int)coefficient));
		else
			prntFrcn = p.getCoefficientFraction() + "+" + coefficientFraction;
		return new Payload(p.getCoefficient()+coefficient, exponent, prntFrcn);
	}
	
	/**
	 * Calculates the Integral of the current Payload and resets its values as such
	 */
	public void takeIntegral()
	{
		if(exponent != -1)	//If the exponent is a -1, then the integral of it is a natural log
		{
			exponent++;
			double coeffRed = coefficient;
			int expRed = exponent;
			int gcd = 1;
			if(Double.compare(coefficient, (double)((int)coefficient))==0)	//if coefficient is essentially an integer
				gcd = BigInteger.valueOf((int)coefficient).gcd(BigInteger.valueOf(exponent)).intValue();	//Converts ints to BigIntegers, finds their gcd, then enters out int for gcd
			coeffRed/=gcd;
			expRed/=gcd;
			if(coefficient % exponent != 0 )	//If the two values don't divide well into each other and need to be represented as a fraction
			{
				if(coefficient >= 0 && exponent >= 0)
					coefficientFraction = "(" + (int)coeffRed + "/" + expRed + ")";
				if(coefficient < 0 && exponent < 0 )
					coefficientFraction = "(" + (int)(coeffRed*-1) + "/" + (expRed*-1) + ")";
				if(coefficient < 0 && exponent >= 0 )
					coefficientFraction = "-(" + (int)(coeffRed*-1) + "/" + expRed + ")";
				if(coefficient >= 0 && exponent < 0 )
					coefficientFraction = "-(" + (int)coeffRed + "/" + (expRed*-1) + ")";
			}
			else	//two values divide well into each other
				coefficientFraction = "" + (int)(coefficient/exponent);
			coefficient /= exponent;
		}
		else
		{
			//System.out.println(((Double.compare(((double)((int)coefficient)), coefficient)==0) ? ((int)coefficient):coefficient));
			coefficientFraction =  "" + coefficient + "lnx";
			exponent++;
		}
	}
	/**
	 * Takes in the given number and calculates what this current expression is
	 * @param num
	 * @return
	 */
	public double calculateNumber(double num)
	{
		double total=0;
		if(coefficientFraction.contains("ln"))
			total = coefficient * Math.log(num);
		else
			total = coefficient * Math.pow(num, exponent);
		return total;
	}
	
	@Override
	/**
	 * Overrides the compareTo method and compares two different items in size, with the exponent being more important, then the coefficient
	 */
	public int compareTo(Object arg0) {
		int diffCoeff=0;
		int diffExp=0;
		if(arg0 instanceof Payload)	//Simply returns 0 if given an object that is not comparable to current Object
		{
			diffCoeff = Double.compare(coefficient,((Payload)arg0).getCoefficient());
			diffExp = exponent - ((Payload)arg0).getExponent();
		}
		return (diffExp != 0) ? diffExp:diffCoeff;
	}
}
