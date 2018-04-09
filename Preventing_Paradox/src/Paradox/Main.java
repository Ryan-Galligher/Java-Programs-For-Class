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
		String output = calculated.infix();
		
		if(!bounds.matches(BOUNDSREGEX))
			return output + " + C";
		
		ArrayList<Payload> array = tree.printAsArrayList();
		String s[] = bounds.split("\\|");
		int b1 = Integer.parseInt(s[0]);
		int b2 = Integer.parseInt(s[1]);
		double total=0.0;
		for(int i = 0; i < array.size(); i++)
		{
			total += tree.search(array.get(i)).getPayload().calculateNumber(b1);
			total -= tree.search(array.get(i)).getPayload().calculateNumber(b1);
		}
		System.out.println("\tFinished to calculateDefiniteIntegral");
		
		return String.format("%s = %.3f", tree.infix(), total);
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
		for(int i = 0; i < array.size(); i++)
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
			if(array.get(i).getExponent() == array.get(i-1).getExponent())
			{
				Payload pay = array.get(i).addSameExponent(array.get(i-1));
				tree.delete(array.get(i));
				tree.delete(array.get(i-1));
				tree.insert(pay);
				
				array.remove(i--);
				array.remove(i);
				array.set(i--, pay);
			}
		}
		System.out.println("\tFinished combining like terms");
		
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
		
		String reconstruction = (parts[1].contains("-") && !parts[1].contains("^-")) ? parts[1]:("+" + parts[1]);	//This is used to reconstruct the String without the bounds and the dx at the end, and without spaces, so that it can be properly parsed for proper reading
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
				continue;
			if( !parts[i].matches("[\\-\\+0-9]*x\\^[\\-\\+0-9]*") && parts[i].contains("x"))	//If there is not an exponent in the x item, then add one so it can be parsed eaisly
			{
				System.out.println("\t\t\tThe String does NOT in fact contain ^");
				parts[i]=parts[i].replaceAll("x", "x^1");
			}
			if(!parts[i].contains("x"))
			{
				System.out.println("\t\t\tThe String does NOT in fact contain X");
				parts[i] = parts[i] + "x^0";
			}
			if(parts[i].matches("[\\-\\+]x[\\^\\-\\+0-9]*"))	//If the item doesn't contain a number in front of x (implied 1), adds in 1 to ease conversion
			{
				System.out.println("\t\t\tThe String does in fact contain [+-]x");
				parts[i] = parts[i].replaceFirst("x", "1x");
			}
			
			System.out.println("\t\tFinal to be cut is now: " + parts[i]);
			coeff = parts[i].split("x")[0];
			exp = parts[i].split("\\^")[1];
			
			tree.insert(new Payload(Integer.parseInt(coeff), Integer.parseInt(exp)));	//Creates new Payload from the coefficient and exponent, and adds it to the Binary Search Tree
		}
		
		System.out.println("\t\tFinished Parshing");
		
		return bounds;
	}
}
