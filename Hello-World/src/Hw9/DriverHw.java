package Hw9;

import java.util.Random;

public class DriverHw
{
	public static void Main(String [] args)
	{
		Random rn = new Random();
		int[] arr1 = new int[5000];
		int[] arr2;
		int[] arr3;
		int[] arr4;
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		LinkedList list3 = new LinkedList();
		int tempnum;
		
		for(int i = 0; i < arr1.length ; i++)
		{
			tempnum = rn.nextInt();
			arr1[i] = tempnum;
			list1.push(tempnum);
			list2.push(tempnum);
			list3.push(tempnum);
		}
		arr2 = arr1.clone();
		arr3 = arr1.clone();
		arr4 = arr1.clone();
		
		BubbleSort bs = new BubbleSort(arr1); bs.bubbleSort();
		InsertionSort ins = new InsertionSort(arr2); ins.insertionSort();
		SelectionSort slt = new SelectionSort(arr3); slt.selectionSort();
		
		MergeSort mg = new MergeSort(arr4); mg.mergeSort();
		list1.quickSort();
		
		
		int bubbleComparisons = bs.getComparison();
		int selectionsComparisons = slt.getComparison();
		int insertionArrComparisons = ins.getComparison();
		
		int mergeComparisons = mg.getComparison();
		int quickCompare = list1.compare;
		
		
		int bubbleAssign = bs.getAssign();
		int selectionAssign = slt.getAssign();
		int insertionAssign = ins.getAssign();
		
		int mergeSort = mg.getAssignment();
		int quickAssign = list1.getAssign();
		
		System.out.println("Bubble sort Comparison number: " + bubbleComparisons + " And the Assign is: " + bubbleAssign);
		System.out.println("Selection sort Comparison number: " + selectionsComparisons + " And the Assign is: " + selectionAssign);
		System.out.println("Insertion sort Comparison number: " + insertionArrComparisons + " And the Assign is: " + insertionAssign);
		
		
		System.out.println("Merge sort Comparison number: " + mergeComparisons + "And the Assign is: " + mergeSort);
		
	}

}
