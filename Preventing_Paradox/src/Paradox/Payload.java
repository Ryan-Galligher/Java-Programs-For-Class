package Paradox;

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
	private String type;
	
	public Payload()
	{
		coefficient=exponent=0;
		coefficientFraction=type="";
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
		if(exponent != 0)
			return "" + coefficientFraction + "x^" + exponent;
		return coefficientFraction;
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
		return new Payload(p.getCoefficient()+coefficient, exponent, p.getCoefficientFraction() + "+" + coefficientFraction);
	}
	
	/**
	 * Calculates the Integral of the current Payload and resets its values as such
	 */
	public void takeIntegral()
	{
		if(exponent != -1)	//If the exponent is a -1, then the integral of it is a natural log
		{
			exponent++;
			if(coefficient % exponent != 0 )
				coefficientFraction = "(" + (int)coefficient + "/" + exponent + ")";
			else
				coefficientFraction = "" + (int)(coefficient/exponent);
			coefficient /= exponent;
		}
		else
		{
			coefficientFraction = coefficient + "ln";
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
		if(arg0 instanceof Payload)
		{
			diffCoeff = Double.compare(coefficient,((Payload)arg0).getCoefficient());
			diffExp = exponent - ((Payload)arg0).getExponent();
		}
		return (diffExp != 0) ? diffExp:diffCoeff;
	}
}
