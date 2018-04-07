import java.util.Scanner;

public class Tester
{
	public static final String INFOREGEX = "([A-Za-z\\-']+\\s)+(-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?\\s?){4,}";
	public static final String COMMANDREGEX = "sort\\s(area||pilot)\\s(asc||dec)||-?\\d+(\\.\\d+)?||([A-Za-z\\-']+\\s?)+";
	
	public static void main(String [] args)
	{
		Scanner s = new Scanner(System.in);
		String input;
		while(true)
		{
			System.out.print("NEXT TEST CASE: ");
			input=s.nextLine();
			System.out.println("Is it A INFO INPUT?: " + input.matches(INFOREGEX));
			System.out.println("Is it A COMMAND INPUT?: " + input.matches(COMMANDREGEX));
			System.out.println("");
		}
	}
}
