package Hw9;

 public class BubbleSort {
 /** Bubble sort method */
	 int[] list;
	 int compare;
	 int assign;
	 public BubbleSort(int[] list)
	 {
		 this.list = list;
		 compare=0;
		 assign=0;
	 }
	 public int getAssign() {return assign;}
	 
	 public  void bubbleSort() {bubbleSort(list);}
 public  void bubbleSort(int[] list) {
 boolean needNextPass = true;

 for (int k = 1; k < list.length && needNextPass; k++) {
 // Array may be sorted and next pass not needed
	 needNextPass = false;
	 for (int i = 0; i < list.length - k; i++) {
		 compare++;
		 if (list[i] > list[i + 1]) {
			 // Swap list[i] with list[i + 1]
			 assign++;
			 int temp = list[i];
			 list[i] = list[i + 1];
			 list[i + 1] = temp;
			
			 needNextPass = true; // Next pass still needed
		 }
	 }
 }
 }

 public int getComparison() {return compare;}
 /** A test method */
 public static void main(String[] args) {
 int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
// bubbleSort(list);
 for (int i = 0; i < list.length; i++)
 System.out.print(list[i] + " ");
 }
 }