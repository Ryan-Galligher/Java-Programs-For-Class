package Hw9;

 public class InsertionSort {
	 
	 int[] list;
	 int compare;
	 int assign;
	 
	 public InsertionSort(int[] list)
	 {
		 this.list = list;
		 compare=0;
		 assign=0;
	 }
	 public  void insertionSort() {insertionSort(list);}
	 public int getComparison() {return compare;}
	 public int getAssign() {return assign;}
	 
	 
 /** The method for sorting the numbers */
	 public void insertionSort(int[] list) {
		 for (int i = 1; i < list.length; i++)
		 {
			 /** Insert list[i] into a sorted sublist list[0..i-1] so that
			 list[0..i] is sorted. */
			 int currentElement = list[i];
			 int k;
			 for (k = i - 1; k >= 0 && list[k] > currentElement; k--)
			 {
				 compare++;
				 assign++;
				 list[k + 1] = list[k];
			 }
			compare++;
			 // Insert the current element into list[k + 1]
			 list[k + 1] = currentElement;
		}
	}
 }