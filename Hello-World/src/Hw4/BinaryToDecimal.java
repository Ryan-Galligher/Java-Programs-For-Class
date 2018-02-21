package Hw4;

/*
 * Ryan Galligher, rpg170130
 */

public class BinaryToDecimal 
{
	private static final String BINARYREGEX="[01]+";
	
	public static void main(String [] args)	//This is the Driver function that will test the bin2Dec function
	{
		String[] inputToRead={"1","0","100","11","11101010110100001001010101", "2", "12114001", "hello", "10101"};
		for(int i = 0; i < inputToRead.length;i++)
		{
			try {
				System.out.println(bin2Dec(inputToRead[i]));
			}catch(BinaryFormatException e) {e.printStackTrace();}
		}
	}
	
	public static int bin2Dec(String binary) throws BinaryFormatException
	{
		int output=0;
		if(!binary.matches(BINARYREGEX))
			throw new BinaryFormatException(binary + " is not a proper binary String");
		for(int i = binary.length()-1; i >= 0 ; i--)
			output+=Character.getNumericValue(binary.charAt(i))*Math.pow(2, binary.length()-i-1);
		return output;
	}
}
