package Hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Ryan Galligher, rpg170130
 */

public class Main
{
	public static final String INPUTFILE1 = "input1.txt";
	public static final String INPUTFILE2 = "input2.txt";
	public static final String OUTPUTFILE1="output1.txt";
	public static final String OUTPUTFILE2="output2.txt"; 
	
	public static void main(String [] args)
	{
		try {
			
			textFileStackManipulation(new File(INPUTFILE1),new File(OUTPUTFILE1), new MyStack<String>());
			textFileQueueManipulation(new File(INPUTFILE2),new File(OUTPUTFILE2), new MyQueue<String>());
			textCompareItemsInQueues(new File(INPUTFILE1),new File(INPUTFILE2),new MyQueue<String>(),new MyQueue<String>());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void textFileStackManipulation(File input, File output, MyStack<String> stack) throws FileNotFoundException
	{
		Scanner s = new Scanner(input);
		while(s.hasNext())
		{
			//System.out.println("\tNumber On Stack");
			stack.push(s.next());
			
		}
		PrintWriter printer = new PrintWriter(output);
		//System.out.println("Stack contains: " + stack.returnAll());
		
		while(!stack.empty())
		{
			printer.write(" " + stack.pop());
			//System.out.println("\tNumber Printed from Stack and empty()?: " + stack.empty());
		}
		s.close();
		printer.close();
	}
	
	public static void textFileQueueManipulation(File input, File output, MyQueue queue) throws FileNotFoundException
	{
		Scanner s = new Scanner(input);
		while(s.hasNext())
		{
			queue.add(s.next());
		}
		s.close();
		
		PrintWriter printer = new PrintWriter(output);
		//DO SOMETHING TO DEQUEUE EACH CHARACTER, CONVERT TO UPPERCASE, THEN CONTINUE    *************************
		//System.out.println("Queue contains: " + queue.returnAll());
		
		String stuff;
		
		while(!queue.empty())
		{
			stuff = queue.remove().toString();
			//System.out.println("Before: " + stuff);
			//System.out.println("\t"+stuff.toUpperCase());
			stuff=stuff.toUpperCase();
			//System.out.println("After: " + stuff);
			
			printer.write(" " + stuff);
		}
		printer.close();
	}
	
	public static void textCompareItemsInQueues(File input1, File input2, MyQueue<String> queue1, MyQueue<String> queue2) throws FileNotFoundException
	{
		Scanner s = new Scanner(input1);
		while(s.hasNext())
		{
			queue1.add(s.next());
		}Scanner sc = new Scanner(input2);
		while(sc.hasNext())
		{
			queue2.add(sc.next());
		}
		s.close();
		sc.close();
		
		while(!queue1.empty() || !queue2.empty())
		{
			if(!queue1.empty() && !queue2.empty())
			{
				if(queue1.remove().toString().equals(queue2.remove().toString()))
				{
					System.out.println("The Files are not the Same");
					return;
				}
			}
			else
			{
				System.out.println("The Files are not the Same");
				return;
			}
		}
		System.out.println("The Files are the Same");
		
	}
	
}
