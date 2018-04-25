package Hw9;

class SelectionSort
{
	 int[] list;
	 int compare;
	 int assign;
	 public SelectionSort(int[] list)
	 {
		 this.list = list;
		 compare=0;
		 assign=0;
	 }
	 public  void selectionSort() {sort(list);}
	 public int getComparison() {return compare;}
	 public int getAssign() {return assign;}
	
    void sort(int arr[])
    {
        int n = arr.length;
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
            {
            	compare++;
                if (arr[j] < arr[min_idx])
                {
                    min_idx = j;
                }
            }
 
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            assign+=2;
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
 
    // Prints the array
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
 
    // Driver code to test above
    public static void main(String args[])
    {
//        SelectionSort ob = new SelectionSort();
        int arr[] = {64,25,12,22,11};
//        ob.sort(arr);
        System.out.println("Sorted array");
 //       ob.printArray(arr);
    }
}