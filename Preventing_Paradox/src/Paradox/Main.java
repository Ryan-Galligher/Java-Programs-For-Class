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
	public static final String INTEGRALREGEX = "((-?\\d+|-?\\d+)|||)\\s((\\s?([0-9]+||[0-9]*x(^-?[0-9]+)?))||[+-])+\\sdx";
	public static final String BOUNDSREGEX = "(-?\\d+|-?\\d+)";
	public static final String SPLITINFONOSPACE = "";
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
		
		while(in.hasNext())	//for every Integral in the file
		{
			String s = in.nextLine();
			if(!s.contains(INTEGRALREGEX))	//If the given line does not fit within the definition of integral, skip it
				continue;
			integralPart = parseIntoTree(s, tree);
			combineLikeTerms(tree);
			integral = calculateDefiniteIntegral(calculateIntegral(tree), integralPart);
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
		if(!bounds.contains(BOUNDSREGEX))
			return calculated.infix() + " + C";
		
		ArrayList<Payload> array = tree.printAsArrayList();
		String s[] = bounds.split("|");
		int b1 = Integer.parseInt(s[0]);
		int b2 = Integer.parseInt(s[1]);
		double total=0.0;
		for(int i = 0; i < array.size(); i++)
		{
			total += tree.search(array.get(i)).getPayload().calculateNumber(b1);
			total -= tree.search(array.get(i)).getPayload().calculateNumber(b1);
		}
		
		return String.format("%s = $f.3", tree.infix(), total);
	}
	/**
	 * Makes the tree calculate the unbounded integral
	 * @param tree
	 * @return
	 */
	public static BinarySearchTree<Payload> calculateIntegral(BinarySearchTree<Payload> tree)
	{
		ArrayList<Payload> array = tree.printAsArrayList();
		for(int i = 0; i < array.size(); i++)
		{
			tree.search(array.get(i)).getPayload().takeIntegral();
		}
		return tree;
	}
	/**
	 * Searches through the tree and combines any terms that contain the same exponent
	 * @param tree
	 */
	public static void combineLikeTerms(BinarySearchTree<Payload> tree)
	{
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
		
		String reconstruction = (parts[1].contains("[+-]")) ? parts[1]:("+" + parts[1]);	//This is used to reconstruct the String without the bounds and the dx at the end, and without spaces, so that it can be properly parsed for proper reading
		for (int i = 2; i < parts.length - 1; i++)	//iterates over all but the first and last part, as the first will contain the bounds and the last will be dx
		{
			reconstruction += parts[i];
		}
		parts = reconstruction.split(SPLITINFONOSPACE);
		
		for(int i = 0; i < parts.length; i++)
		{
			tree.insert(new Payload(Integer.parseInt(parts[i].split("x")[0]), Integer.parseInt(parts[i].split("^")[1])));	//Creates new Payload from the coefficient and exponent, and adds it to the Binary Search Tree
		}
		
		
		return bounds;
	}
}
