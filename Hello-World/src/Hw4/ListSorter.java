package Hw4;

import java.util.ArrayList;;

public class ListSorter
{
	private ArrayList list;
	
	public ListSorter(ArrayList list)
	{
		this.list = list;
	}
	
	
	public Object binarySearch(int startPoint, int endPoint, Object valueToFind)
	{
		//System.out.println("\tThe current values for this loop is start,end form: " + startPoint + "," + endPoint); 
		if(endPoint>=startPoint)	//If the end point is not the same as the start point, aka there are still elements in the array
		{
			int mid = (int) (startPoint + (endPoint-startPoint)/2);
			//System.out.println("\t\tmid: " + mid);
			if(comparisonDifference(list.get(mid), valueToFind) == 0)
				return mid;
			
			if(comparisonDifference(list.get(mid),valueToFind) > 0)
				return binarySearch(startPoint,mid-1,valueToFind);
			
			return binarySearch(mid+1, endPoint, valueToFind);
		}
		return -1;	//Item doesn't exist
	}
	
	
	public void insertionSort()
	{
		for(int i = 1; i<list.size(); ++i)
		{
			Object k = list.get(i);
			int j = i-1;
			while(j>=0 && comparisonDifference(list.get(j),k)>0)
			{
				list.set(j+1, list.get(j));
				j = j-1;
			}
			list.set(j+1, k);
		}
	}
	
	public double comparisonDifference(Object item1, Object item2)
	{
		if(item1 instanceof Integer && item2 instanceof Integer)
			return ((Integer)item1).intValue() - ((Integer)item2).intValue();
		if(item1 instanceof Double && item2 instanceof Double)
			return ((Double)item1).doubleValue() - ((Double)item2).doubleValue();
		if(item1 instanceof Float && item2 instanceof Float)
			return ((Float)item1).doubleValue() - ((Float)item2).doubleValue();
		if(item1 instanceof String && item2 instanceof String)
			return ((String)item1).compareTo( ((String)item2));
			
		throw new IllegalArgumentException();
	}
	
	public Object getPoint(int i)
	{
		return list.get(i);
	}
	public int getSize()
	{
		return list.size();
	}
}
