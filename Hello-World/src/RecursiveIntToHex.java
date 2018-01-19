import java.util.Scanner;

public class RecursiveIntToHex {

	/*
	 *  Write a recursive method that converts an integer to hexadecimal.  The function should print out the hexadecimal character instead 
	 * of returning the character.  Write a test program that prompts the user to enter an integer and displays its hexadecimal equivalent.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean allowThrough=true;
		Scanner scan = new Scanner(System.in);
		int readnum = 0;
		do {
			try {
				System.out.print("Enter the integer to be converted: ");
				readnum = scan.nextInt();
				allowThrough=true;
			}catch(Exception e) {System.out.println("Information was not input correctly, please try again.");allowThrough=false;}
		}while(!allowThrough);
		scan.close();
		
		intToHex(readnum);
		
	}
	
	public static void intToHex(int num)
	{
		int number16s = num / 16;
		int remainder = num % 16;
		
		if (number16s != 0)
			intToHex(number16s);
		if(needConvertChar(remainder))
			System.out.print(convertChar(remainder));
		else
			System.out.print(remainder);
	}
	
	public static boolean needConvertChar(int num)
	{
		return (num >= 10);
	}
	
	public static char convertChar(int num)
	{
		return (char)(65+num-10);
	}

}
