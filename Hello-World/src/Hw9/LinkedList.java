package Hw9;

//Java program to illustrate merge sorted
//of linkedList

public class LinkedList 
{
    Node head;
    int compare;
    int assign;
    
    public LinkedList()
    {
    	head=null;
    	compare=0;
    	assign=0;
    }
    public int getCompare() {return compare;}
    public int getAssign() {return assign;}
/* a node of the doubly linked list */ 
    static class Node{
        private int data;
        private Node next;
        private Node prev;
         
        Node(int d){
            data = d;
            next = null;
            prev = null;
        }
    }
     
    Node SortedInsert(Node head,int data) {

        Node newn = new Node(data);

        Node ptr = head;

        if (ptr == null) {
            head = newn;

        } else if ( ptr.data > newn.data ) {
            newn.next = ptr;
            ptr.prev = newn;
            head = newn;

        } else {
            Node nex = head.next;

            while (nex != null && nex.data <= newn.data) {
                ptr = nex;
                nex = nex.next;
            }

            ptr.next = newn;
            newn.prev = ptr;

            if (nex != null) {
                nex.prev = newn;
                newn.next = nex;
            }
        }

        return head;   
    }
    
// A utility function to find last node of linked list    
    Node lastNode(Node node){
        while(node.next!=null)
            node = node.next;
        return node;
    }
     
 // Split a doubly linked list (DLL) into 2 DLLs of
    // half sizes
    Node split(Node head) {
        Node fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        return temp;
    }
 
    Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node second = split(node);
 
        // Recur for left and right halves
        node = mergeSort(node);
        second = mergeSort(second);
 
        // Merge the two sorted halves
        return merge(node, second);
    }
 
    // Function to merge two linked lists
    Node merge(Node first, Node second) {
        // If first linked list is empty
        if (first == null) {
            return second;
        }
 
        // If second linked list is empty
        if (second == null) {
            return first;
        }
 
        // Pick the smaller value
        if (first.data < second.data) {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }
/* Considers last element as pivot, places the pivot element at its
   correct position in sorted array, and places all smaller (smaller than
   pivot) to left of pivot and all greater elements to right of pivot */
    Node partition(Node l,Node h)
    {
       // set pivot as h element
        int x = h.data;
         
        // similar to i = l-1 for array implementation
        Node i = l.prev;
         
        // Similar to "for (int j = l; j <= h- 1; j++)"
        for(Node j=l; j!=h; j=j.next)
        {
            if(j.data <= x)
            {
                // Similar to i++ for array
                i = (i==null) ? l : i.next;
                int temp = i.data;
                i.data = j.data;
                j.data = temp;
            }
        }
        i = (i==null) ? l : i.next;  // Similar to i++
        int temp = i.data;
        i.data = h.data;
        h.data = temp;
        return i;
    }
     
    /* A recursive implementation of quicksort for linked list */
    void _quickSort(Node l,Node h)
    {
    	compare++;
        if(h!=null && l!=h && l!=h.next){
        	assign++;
            Node temp = partition(l,h);
            _quickSort(l,temp.prev);
            _quickSort(temp.next,h);
        }
    }
     
    public void quickSort() {quickSort(head);}
    
    // The main function to sort a linked list. It mainly calls _quickSort()
    public void quickSort(Node node)
    {
        // Find last node
        Node head1 = lastNode(node);
         
        // Call the recursive QuickSort
        _quickSort(node,head1);
    }
     
     // A utility function to print contents of arr
     public void printList(Node head)
     {
        while(head!=null){
            System.out.print(head.data+" ");
            head = head.next;
        }
    }
     
    /* Function to insert a node at the beginging of the Doubly Linked List */
    void push(int new_Data)
    {
        Node new_Node = new Node(new_Data);     /* allocate node */
         
        // if head is null, head = new_Node
        if(head==null){
            head = new_Node;
            return;
        }
         
        /* link the old list off the new node */
        new_Node.next = head;
         
        /* change prev of head node to new node */
        head.prev = new_Node;
         
        /* since we are adding at the begining, prev is always NULL */
        new_Node.prev = null;
         
        /* move the head to point to the new node */
        head = new_Node;
    }
}