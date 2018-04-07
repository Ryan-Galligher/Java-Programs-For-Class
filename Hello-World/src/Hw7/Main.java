package Hw7;

import java.util.Random;


/**
 * rpg170130
 * @author ryan Galligher
 *
 */
public class Main
{
	public static void main(String [] args)
	{
		Random rn = new Random();
		HashTable<Integer, String> hash = new HashTable<Integer, String>();
		
		System.out.println("About to start random generation, please stand by");
		
		//Randomly generates large amounts of keys and values, will take a long time to create
		int randomAmount = rn.nextInt(60) +15;
		int[] randomKeys = new int[randomAmount];
		int amnt;
		char c;
		String build;
		String[] randomAnswers = new String[randomAmount];
		System.out.println("\tAmount of items to be added is " + randomAmount);
		
		for(int i = 0; i < randomAmount; i++)
		{
			do
				{
				randomKeys[i] = rn.nextInt(900)+99;
				amnt = rn.nextInt(20)+1;
				build = "";
				for(int a = 0; a < amnt;a++)
				{
					c = (char) (rn.nextInt(126-65)+65);
					build += c;
				}
				randomAnswers[i]=build;
			}while(checkIfElsewhere(randomKeys[i],build,i,randomKeys,randomAnswers));	//makes sure that there aren't any duplicates of keys/values to not generate false errors
			
			System.out.println("\t\tKey " + i + " is " + randomKeys[i] + "\t\tString is " + build);
			
			hash.add(randomKeys[i], randomAnswers[i]);
		}
		
		System.out.println("Finished random generation, beginning testing");
		
		System.out.println("\tThe current size for the arrays in HashTable is " + hash.arraySizes() + " and the load is currently " + hash.calculateLoad() );
		for(int i = 0; i < randomAmount;i++)
		{
			if(!hash.get(randomKeys[i]).equals(randomAnswers[i]))
				System.err.println("\tERROR: " + randomKeys[i] + " DOES NOT RETURN EXPECTED VALUE " + randomAnswers[i] + " BUT INSTEAD RETURNS " +hash.get(randomKeys[i]));
		}
		System.out.println("Finished testing");
	}
	
	private static boolean checkIfElsewhere(int key, String build, int spot, int[] array, String[] ansarray)
	{
		for(int i = 0; i < spot; i++)
		{
			if(array[i] == key)
				return true;
			if(ansarray[i].equals(build))
				return true;
		}
		return false;
	}
}
