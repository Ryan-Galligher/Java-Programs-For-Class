package Paradox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author ryan Galligher
 *rpg170130
 */
public class Main
{
	//regex everything true = "[A-Za-z0-9\\-\\+\\s\\|\\^]*"
	static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";	//Old attempt to use lookbehind to cut a string in parts
	public static final String INTEGRALREGEX = "((-?\\d+\\|-?\\d+)||\\|)\\s(\\s?([0-9]+||[0-9]*x(\\^\\-?[0-9]+)?||[\\+\\-]))+\\sdx";
	public static final String BOUNDSREGEX = "(-?\\d+\\|-?\\d+)";
	public static final String SPLITINFONOSPACE = "(?=(?<!\\^)[+-])";
	public static final String VALID = "[\\+\\-][0-9]*x?(\\^\\-?[0-9]+)?";	
	public static final String INPUTFILENAME = "integrals.txt";
	public static final String OUTPUTFILENAME = "answers.txt";
	
	public static BinarySearchTree<Payload> tree;
	
	public static void main(String [] args)
	{
		Scanner in = null;
		PrintWriter out = null;
		String integralPart;
		String integral;
		try {
			in = new Scanner(new File(INPUTFILENAME));
			out = new PrintWriter(new File(OUTPUTFILENAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		tree = new BinarySearchTree<Payload>();
		
		System.out.println("Set up everything, about to enter while loop");
		
		while(in.hasNext())	//for every Integral in the file
		{
			String s = in.nextLine();
			System.out.println("\tAbout to test if the line is valid. Is " + s + " valid?: " + s.matches(INTEGRALREGEX));
			if(!s.matches(INTEGRALREGEX))	//If the given line does not fit within the definition of integral, skip it
				continue;
			integralPart = parseIntoTree(s, tree);
			System.out.println("\tParsed into tree. What is currently stored in tree in order is: " + tree.infix());
			combineLikeTerms(tree);
			integral = calculateDefiniteIntegral(calculateIntegral(tree), integralPart);
			System.out.println("\tThe Finished item to be printed to the file is: " + integral + "\n\n\n");
			out.println(integral);
			tree.delete();
		}
		
		in.close();
		out.close();
		
	}
	/**
	 * Calculates the given definite integral if it is one. If it is not, it simply returns the integral with a + C
	 * @param calculated
	 * @param bounds
	 * @return
	 */
	public static String calculateDefiniteIntegral(BinarySearchTree<Payload> calculated, String bounds)
	{
		System.out.println("\tAbout to calculateDefiniteIntegral");
		String output = calculated.reverseOrder();
		if(output.charAt(0) == '+')	//Formats the output so the first item doesn't have an unnecessary + in front of it
			output = output.substring(1, output.length());
		
		if(!bounds.matches(BOUNDSREGEX))	//If we aren't supposed to find a definite integral, then simply return the string of the improper integral with the + C
			return output + " + C";
		
		ArrayList<Payload> array = tree.printAsArrayList();	//Gets an arrrayList of all the items in the BinaryTree
		String s[] = bounds.split("\\|");
		int b1 = Integer.parseInt(s[0]);
		int b2 = Integer.parseInt(s[1]);
		double total=0.0;
		for(int i = 0; i < array.size(); i++)	//For every item in the BinarySearch, find the upper and lower bound for it and subtract the lower from the upper
		{
			total += tree.search(array.get(i)).getPayload().calculateNumber(b2);
			total -= tree.search(array.get(i)).getPayload().calculateNumber(b1);
		}
		System.out.println("\tFinished to calculateDefiniteIntegral");
		
		return String.format("%s, %s = %.3f", output,bounds, total);
	}
	/**
	 * Makes the tree calculate the unbounded integral
	 * @param tree
	 * @return
	 */
	public static BinarySearchTree<Payload> calculateIntegral(BinarySearchTree<Payload> tree)
	{
		System.out.println("\tAbout to calculateIntegral");
		ArrayList<Payload> array = tree.printAsArrayList();
		for(int i = 0; i < array.size(); i++)	//Goes through each item in the BinaryTree and makes the Payloads calculate their Integrals
		{
			tree.search(array.get(i)).getPayload().takeIntegral();
		}
		System.out.println("\tFinished to calculateIntegral");
		return tree;
	}
	/**
	 * Searches through the tree and combines any terms that contain the same exponent
	 * @param tree
	 */
	public static void combineLikeTerms(BinarySearchTree<Payload> tree)
	{
		System.out.println("\tAbout to combineLikeTerms");
		ArrayList<Payload> array = tree.printAsArrayList();
		for(int i = 1; i < array.size(); i++)
		{
			for (int a = 0; a < array.size();a++)
			{
				//Iterates through each item and compares to each other item to see if any of them are of the same exponent. If they are then combine them together
				System.out.println("\t\tCurrent item " + i + " is : " + array.get(i) + "\t and the value " + a + " compared to is: " + array.get(a));
				if(i!=a && array.get(i).getExponent() == array.get(a).getExponent())
				{
					System.out.println("\t\t\tCOMBINING THESE");
					Payload pay = array.get(i).addSameExponent(array.get(a));
					tree.delete(array.get(i));
					tree.delete(array.get(a));
					tree.insert(pay);
					
					//If i > a, then when both i and a are deleted, then i decreases by 2 and a decreases by 1.
					boolean change = (i>a);
					array.remove(i);
					array.remove((change) ? a:a-1);	//if a was larger, than after i was removed then a is one spot closer than what is currently represented
					i -= (change) ? 2:1;
					a -= (change) ? 1:2;
					if(i<0)		//if either were at 0 and are decreased under that, then reset them back to 0
						i = 0;
					if(a<0)
						a=0;
					array.add(pay);
				}
			}
		}
		System.out.println("\tFinished combining like terms, the items are now: " + tree.infix());
		
		/*String[] s = tree.infix().split(" ");	//Splits the output of the binary tree into different items
		for (int i = 1; i < s.length; i++)
		{
			exp = Integer.parseInt(s[i].split("^")[1]);	
			if(exp == Integer.parseInt(s[i-1].split("^")[1]))	//If the exponents of the two given integrals are the same (so they can be added together into one concise expression)
			{
				coeff1 = Integer.parseInt(s[i].split("x")[0]);
				coeff2 = Integer.parseInt(s[i-1].split("^")[0]);
				Payload pay = new Payload(coeff1 + coeff2, exp);
				
				tree.delete(new Payload(coeff1, exp));
				tree.delete(new Payload(coeff2, exp));
				
				tree.insert(pay);
			}
		}*/
	}
	/**
	 * Parses the String into the given BinarySearchTree
	 * @param line String to be parsed into the tree
	 * @param tree BinarySearchTree to be filled with values
	 * @return the bounds for the integral
	 */
	public static String parseIntoTree(String line, BinarySearchTree<Payload> tree)
	{
		String[] parts = line.split(" ");
		String bounds = parts[0];
		
		String reconstruction = (parts[1].charAt(0) != '-' && parts[1].charAt(0) != '+') ? ("+" + parts[1]):parts[1];	//This is used to reconstruct the String without the bounds and the dx at the end, and without spaces, so that it can be properly parsed for proper reading
		for (int i = 2; i < parts.length - 1; i++)	//iterates over all but the first and last part, as the first will contain the bounds and the last will be dx
		{
			reconstruction += parts[i];
		}
		
		System.out.println("\t\tParsing into a tree, the bound is taken as " + bounds + "\t and the reconstruction is taken as: " + reconstruction);
		parts = reconstruction.split(SPLITINFONOSPACE);
		String coeff;
		String exp;
		
		for(int i = 0; i < parts.length; i++)	//Adds in every item that is parsed
		{
			System.out.println("\t\t\tThe current part to be parsed is currently: " + parts[i]);
			if(!parts[i].matches(VALID))
			{
				System.out.println("\t\t\t\tThe String is NOT Valid");
				continue;
			}
			if( !parts[i].matches("[\\-\\+0-9]*x\\^[\\-\\+0-9]*") && parts[i].contains("x"))	//If there is not an exponent in the x item, then add one so it can be parsed eaisly
			{
				System.out.println("\t\t\tThe String does NOT in fact contain ^");
				parts[i]=parts[i].replaceAll("x", "x^1");
			}
			if(!parts[i].contains("x"))
			{
				System.out.println("\t\t\t\tThe String does NOT in fact contain X");
				parts[i] = parts[i] + "x^0";
			}
			if(parts[i].matches("[\\-\\+]x[\\^\\-\\+0-9]*"))	//If the item doesn't contain a number in front of x (implied 1), adds in 1 to ease conversion
			{
				System.out.println("\t\t\t\tThe String does in fact contain [+-]x");
				parts[i] = parts[i].replaceFirst("x", "1x");
			}
			
			System.out.println("\t\t\tFinal to be cut is now: " + parts[i]);
			coeff = parts[i].split("x")[0];
			exp = parts[i].split("\\^")[1];
			
			tree.insert(new Payload(Integer.parseInt(coeff), Integer.parseInt(exp)));	//Creates new Payload from the coefficient and exponent, and adds it to the Binary Search Tree
		}
		
		System.out.println("\t\tFinished Parshing");
		
		return bounds;
	}
}
