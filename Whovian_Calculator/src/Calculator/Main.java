/*
 * Ryan Galligher, rpg170130
 */

package Calculator;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;


public class Main
{
	private static final String PATHTOREADFILE = "expressions4.txt";
	private static final String PATHTOWRITEFILE = "results.txt";
	private static final String DECIMALREGEX = "-?\\d+(\\.\\d+)?";
	private static final String IMAGINARYREGEX="-?\\d+(\\.\\d+)?+i";
	private static final String COMPLEXREGEX="-?\\d+(\\.\\d+)?[+-]\\d+(\\.\\d+)?i";
	private static final String ALLOWEDOPERATORS="[*+-/><=]|/=";
	
	public static void main(String [] args)
	{
		try {
			Scanner s = new Scanner(new File(PATHTOREADFILE));
			File f = new File(PATHTOWRITEFILE);
			if(f.exists())	//If the file currently exits, then it will clear the file before allowing for truncation
				f.delete();
			BufferedWriter fileToWrite = new BufferedWriter(new FileWriter(f,true));
			
			
			//Read in from the file and calculate all of the stiff
			Object[] obj;
			while(s.hasNextLine())
			{
				obj = readLine(s);
				//System.out.println("Is the current object null?: " + (obj==null));
				if(obj==null)
					continue;
				if(isRelationalOperator((String)obj[1]))
					writeExpression(obj, Boolean.toString(calculateRelationalExpression(obj)), fileToWrite);
				else
					writeExpression(obj, calculateNumericalExpression(obj),fileToWrite);
			}
			//System.out.println("\tIs there still a next line?: " + s.hasNextLine());
			fileToWrite.close();
			s.close();
			System.out.println("Finished completely and well");
		}catch(IOException e) {/*e.printStackTrace();*/ System.exit(1);}
	}
	
	/**
	 * Reads a single line of the File and if it is a valid input then it will save the information.
	 * @param s Scanner of the File to read in
	 * @return An Object Array Containing the info in [num1,comparison,num2] format
	 */
	public static Object[] readLine(Scanner s)
	{
		//Parses the information read in from Scanner
		String information = s.nextLine();
		String[] parsedInfo;
		Object[] returnInfo = new Object[3];
		//System.out.println("\tThe information that is in the current line is: " + information);

		parsedInfo = information.split(" ");
		
		
		if(parsedInfo.length != 3)
			return null;
		if(!parsedInfo[0].matches(COMPLEXREGEX) && !parsedInfo[0].matches(IMAGINARYREGEX) && !parsedInfo[0].matches(DECIMALREGEX))
			return null;
		if(!parsedInfo[2].matches(COMPLEXREGEX) && !parsedInfo[2].matches(IMAGINARYREGEX) && !parsedInfo[2].matches(DECIMALREGEX))
			return null;
		if(!parsedInfo[1].matches(ALLOWEDOPERATORS))
			return null;
		
		//parse the stuff into String[3], for first, comparison, and second
		returnInfo[0] = createNumbers(parsedInfo[0]);
		returnInfo[1] = parsedInfo[1];
		returnInfo[2] = createNumbers(parsedInfo[2]);
		
		return returnInfo;
	}
	
	/**
	 * Takes in a String and then convert it to either a Number or a ComplexNumber and return it.
	 * @param s	The String of information
	 * @return The Object Number or ComplexNumber
	 */
	public static Object createNumbers(String s)
	{
		//System.out.println("\t\tThe String currently being cut is: " + s);
		if(!s.contains("i"))	//if the item can't be either a complex number or an imaginary number so therefore must be a normal number
			return new Number(Double.parseDouble(s));
		else
		{
			if(s.contains("+") || (s.contains("-") && !s.matches(IMAGINARYREGEX)))		//If the item is a Complex number
			{
				String[] stuff;
				if(s.contains("+"))		//if complex number has addition
					stuff = s.split("\\+");
				else	//if complex number has subtraction
				{
					if(s.startsWith("-"))	//if it gets through this if statment, it HAS to be -real-imagin"i"
					{
						stuff = s.split("-");
						stuff[2] = "-"+stuff[2];
						stuff[1] = "-"+stuff[1];
						stuff = new String[]{stuff[1],stuff[2]};
					}
					else
					{
						stuff = s.split("-");
						stuff[1] = "-"+stuff[1];
					}
				}
				return new ComplexNumber(Double.parseDouble(stuff[0]), Double.parseDouble(stuff[1].substring(0,stuff[1].length()-1)));
			}	//If the number is an imaginary number
			else
				return new ComplexNumber(0,Double.parseDouble( s.substring(0, s.length()-1) ));
		}
	}
	
	/**
	 * If the given String holds a Relational Operator or a Numerical Operator.
	 * @param str The given String that is either a Relational or Numerical Operator.
	 * @return true if Relational Operator and false if Numerical Operator.
	 */
	public static boolean isRelationalOperator(String str)
	{
		if(str.contains(">") || str.contains("<") || str.contains("="))
			return true;
		return false;
	}
	/**
	 * Calculates the Numerical output of the comparison given within the 3 values in the Object Array and returns the output as a String.
	 * @param obj The Object Array that holds the needed data in {Number, Operator, Number} from.
	 * @return the value after the operation.
	 */
	public static String calculateNumericalExpression(Object[] obj)
	{
		String s;
		if(obj[0] instanceof ComplexNumber || obj[2] instanceof ComplexNumber)		//If either of the numbers are complex Numbers
		{			
					//First part, either returns first as the Complex number it is or makes it one, then .changeTogether and either return second as the ComplexNumber it is or as a new one and then puts in the char value that the String to compare is at
			s = (
					(
						(obj[0] instanceof ComplexNumber)
							? (ComplexNumber)obj[0]:(new ComplexNumber( ((Number)obj[0]).getNum(), 0 ) ) 
					)
					.changeTogether
					(
						(obj[2] instanceof ComplexNumber)
							? (ComplexNumber)obj[2]:(new ComplexNumber( ((Number)obj[2]).getNum(), 0 ) )
							, ((String)obj[1]).charAt(0)
					).toString()
				).toString();
			
		}
		else	//Else, change them together as Numbers
			s = ((Number)obj[0]).changeTogether((Number)obj[2], ((String)obj[1]).charAt(0)).toString();
		return s;
			
	}
	/**
	 * Calculates if the Relational Expression is true or false.
	 * @param obj The Object Array that holds the needed data in {Number, Operator, Number} from.
	 * @return if expression is true.
	 */
	public static boolean calculateRelationalExpression(Object[] obj)
	{
		boolean bol;
		
		
		if(obj[0] instanceof ComplexNumber || obj[2] instanceof ComplexNumber)		//If either of the numbers are complex Numbers
		{			
					//First part, either returns first as the Complex number it is or makes it one, then .changeTogether and either return second as the ComplexNumber it is or as a new one and then puts in the char value that the String to compare is at
			bol=( 
					(
						(obj[0] instanceof ComplexNumber) 
							? (ComplexNumber)obj[0]:(new ComplexNumber( ((Number)obj[0]).getNum(), 0 ) )
					)
					.compareTogether
					(
							(obj[2] instanceof ComplexNumber)
								? (ComplexNumber)obj[2]:(new ComplexNumber( ((Number)obj[2]).getNum(), 0 ) )
								, ((String)obj[1])
					) 
				);
			
		}
		else	//Else, change them together as Numbers
			bol = ((Number)obj[0]).compareTogether((Number)obj[2], ((String)obj[1]));
		
		
		return bol;
	}
	
	
	/**
	 * Will take the expression and the result and format it into a String to then Save them to a file.
	 * @param obj The Object Array that contains the expression.
	 * @param result The result for the given expression.
	 * @param BufferedWriter to write to the file.
	 */
	public static void writeExpression(Object[] obj, String result, BufferedWriter fileToWrite)
	{
		String stuff = formatToInfoString(obj[0])+" " + formatToInfoString(obj[1]) +" "+formatToInfoString(obj[2]);
		//String stuff = s;
		while(stuff.length() < 40 )
			stuff+=" ";
		//String stuff = String.format("%s %20s %s",  "", result);
		stuff += result;
		//writeExpression(stuff, fileToWrite);
		//System.out.println("\tAbout to write the output to the file, and the string is formatted as:\n\t"+stuff+"\n");
		try {
			
			fileToWrite.write(stuff);
			fileToWrite.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	/**
	 * Formats an Object to be printed out if it is either a ComplexNumber, Number or String. 
	 * @param obj Object that can be either ComplexNumber, Number, or String.
	 * @return The String to be printed.
	 */
	public static String formatToInfoString(Object obj)
	{
		if(obj instanceof ComplexNumber)
		{
			return ((ComplexNumber)obj).toString();
		}
		if(obj instanceof Number)
		{
			return ((Number)obj).toString();
		}
		if(obj instanceof String)
		{
			return (String)obj;
		}
		return "";
	}
	
	
	/**
	 * Will take the expression and the result and format it into a String to then call WriteExpression(String) to output.
	 * @param obj The Object Array that contains the expression.
	 * @param result The relational result for the given expression.
	 */
/*	public static void writeExpression(Object[] obj, boolean result, BufferedWriter fileToWrite)
	{
		String s = formatToInfoString(obj[0])+" " + formatToInfoString(obj[1]) +" "+formatToInfoString(obj[2]);
		String stuff = s;
		while(stuff.length() -s.length() < 30 )
			stuff+=" ";
		//String stuff = String.format("%s %20s %s",  "", result);
		stuff += result;
		writeExpression(stuff, fileToWrite);
	}*/
	/**
	 * Writes out the final information into the file.
	 * @param output The output string to be written to the file.
	 * @param fileToWrite The BufferedWriter to the file.
	 */
/*	public static void writeExpression(String output, BufferedWriter fileToWrite)
	{
		System.out.println("\t\tAbout to write the output to the file, and the string is formatted as:\n\t\t"+output+"\n");
		try {
			
			fileToWrite.write(output);
			fileToWrite.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
