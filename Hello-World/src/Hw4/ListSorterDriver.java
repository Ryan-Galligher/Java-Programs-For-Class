package Hw4;

import java.util.ArrayList;

public class ListSorterDriver
{

	public static void main( String [] args)
	{
		ArrayList f = new ArrayList();
		
		f.add(new Integer(2));
		f.add(new Integer(1));
		f.add(new Integer(7));
		f.add(new Integer(4));
		f.add(new Integer(3));
		f.add(new Integer(5));
		f.add(new Integer(6));
		
		ListSorter l = new ListSorter(f);
		
		printInfo(l);
		l.insertionSort();
		printInfo(l);
		System.out.println("The point 1 is at: " + l.binarySearch(0, l.getSize()-1, new Integer(1)));
		System.out.println("The point 7 is at: " + l.binarySearch(0, l.getSize()-1, new Integer(7)));
		System.out.println("The point 4 is at: " + l.binarySearch(0, l.getSize()-1, new Integer(4)));
		
		
		f.clear();
		
		f.add("hello");
		f.add("apple");
		f.add("appropriation");
		f.add("cats");
		f.add("conditioner");
		f.add("zebra");
		f.add("kellogs");
		f.add("frosted");
		f.add("flakes");
		
		l = new ListSorter(f);
		
		printInfo(l);
		l.insertionSort();
		printInfo(l);
		System.out.println("The point flakes is at: " + l.binarySearch(0, l.getSize()-1, "flakes"));
		System.out.println("The point freaks is at: " + l.binarySearch(0, l.getSize()-1, "freaks"));
		System.out.println("The point cats is at: " + l.binarySearch(0, l.getSize()-1, "cats"));
		
		f.clear();
		
		f.add("12.09");
		f.add("3.99");
		f.add("3280932.098");
		f.add("0.23");
		f.add("43");
		f.add(".07");
		f.add("2323.23");
		f.add("99.99");
		f.add("1.0");
		
		l = new ListSorter(f);
		
		printInfo(l);
		l.insertionSort();
		printInfo(l);
		System.out.println("The point flakes is at: " + l.binarySearch(0, l.getSize()-1, "43"));
		System.out.println("The point freaks is at: " + l.binarySearch(0, l.getSize()-1, "5.00"));
		System.out.println("The point cats is at: " + l.binarySearch(0, l.getSize()-1, "1.0"));
		
	}
	
	public static void printInfo(ListSorter l)
	{
		for (int i = 0; i < l.getSize();i++)
		{
			System.out.print(l.getPoint(i).toString() + ",");
		}
		System.out.println("");
	}
	
}
