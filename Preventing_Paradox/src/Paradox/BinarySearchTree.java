package Paradox;

import java.util.ArrayList;

/**
 * 
 * @author ryan Galligher
 *rpg170130
 * @param <G>
 */
public class BinarySearchTree<G extends Comparable<? super G>>
{
	private Node<G> root;
	
	public BinarySearchTree()
	{
		root = null;
	}
	public BinarySearchTree(Node<G> node)
	{
		root = node;
	}
	
	public Node<G> getRoot(){return root;}
	public void setRoot(Node<G> node) {root = node;}
	
	/**
	 * Returns an ArrayList of everything in the Binary Tree
	 * @return
	 */
	public ArrayList<G> printAsArrayList(){return printAsArray(new ArrayList<G>(), root);}
	private ArrayList<G> printAsArray(ArrayList<G> arr, Node<G> node)
	{
		if(node == null)
			return arr;
		ArrayList<G> array = printAsArray(arr, node.getLeft());
		array = printAsArray(array, node.getRight());
		array.add(node.getPayload());
		return array;
	}
	
	/**
	 * Returns the size of the binary search tree
	 * @return
	 */
	public int size() {return size(root);}
	private int size(Node<G> node)
	{
		if(node == null)
			return 0;
		
		int right = size(node.getRight());
		int left = size(node.getLeft());
		
		return right+left+1;
	}
	
	
	/**
	 * Helper method that takes the item and inserts it into the array
	 * @param item
	 */
	public void insert(G item) {if(root == null) {root = new Node<G>(item);}else{insert(root, item);}}
	/**
	 * Recursively goes through the binary tree and places the item at proper place
	 * @param node
	 * @param item
	 */
	private void insert(Node<G> node, G item)
	{		
		double info = item.compareTo(node.getPayload());	//returns the difference between the current node and item
		if(info < 0) //if the item is smaller than the current node, it goes to the left. Else it goes to the right
		{
			if(node.getLeft() != null)	//In case the left node is not empty, continue down that branch
				insert(node.getLeft(), item);
			else	//if the left node is a leaf, then add the item there
				node.setLeft(new Node<G>(item));
		}
		else	//if item is larger than or equal to current node
		{
			if(node.getRight() != null)
				insert(node.getRight(), item);
			else
				node.setRight(new Node<G>(item));
		}
	}
	
	/**
	 * Looks through the binary tree for the given value, and returns the node if found and null if it isn't
	 * @param item
	 * @return
	 */
	public Node<G> search(G item){return search(root, item);}
	private Node<G> search(Node<G> node, G item)
	{
		if(node == null || node.getPayload().equals(item))
			return node;
		if(node.getPayload().compareTo(item) > 0)
			return search(node.getLeft(), item);
		return search(node.getRight(), item);
	}
	
	/**
	 * Deletes all items in the binary tree
	 */
	public void delete() {delete(null);}
	/**
	 * Deletes the instance of the given item item in the binary tree
	 * @param item
	 */
	public boolean delete(G item)
	{
		if (root == null)
			return false;
		else 
		{
	        if (root.getPayload().equals(item) || item == null)
	        {
	        	Node<G> auxRoot = new Node<G>(null);
	        	auxRoot.setLeft(root);
	        	boolean result = delete(item, root, auxRoot);
	        	root = auxRoot.getLeft();
	        	return result;
	        }
	        else
	        	return delete(item, root, null);
		}
	}
	private boolean delete(G item, Node<G> node, Node<G> parent)
	{
		if (node.getPayload().compareTo(item) >0)
		{
            if (node.getLeft() != null)
                  return delete(item, node.getLeft(), node);
            else
                  return false;
		} else if (node.getPayload().compareTo(item)<0) 
		{
            if (node.getRight() != null)
                  return delete(item, node.getRight(), node);
            else
                  return false;
		} else 
		{
            if (node.getLeft() != null && node.getRight() != null) {
                  node.setPayload(minValue(node.getRight()));
                  delete(node.getPayload(), node.getRight(), node);
            } else if (parent.getLeft().equals(node)) {
            	parent.setLeft((node.getLeft() != null) ? node.getLeft():node.getRight());
            } else if (parent.getRight().equals(node)) {
            	parent.setRight((node.getLeft() != null) ? node.getLeft():node.getRight());
            }
            return true;
		}
		
		/*
		if(node == null)
			return false;
		if(item == null)
		{
			delete(null, node.getLeft());
			delete(null, node.getRight());
		}
		
		boolean deleteCurrent = (node.getPayload().equals(item));
		boolean deleteLeft = (node.getLeft() != null &&  node.getLeft().getPayload().equals(item));
		boolean deleteRight = (node.getRight() != null && node.getRight().getPayload().equals(item));
		
		
		if(deleteLeft)
			{
				
			}
		else 
			{delete(item, node.getLeft());}
		if(deleteRight) 
			{
				
			}
		else 
			{delete(item, node.getLeft());}
		if(deleteCurrent)
		{
			if(node.equals(root))
				root = null;
		}
		*/
	}
	private G minValue(Node<G> node)
	{
		if(node.getLeft() == null)
			return node.getPayload();
		else
			return minValue(node.getLeft());
	}
	
	/**
	 * Returns a Sring of all of the items in the tree in prefix form
	 * @return
	 */
	public String prefix() {return prefix(root);}
	private String prefix(Node<G> node)
	{
		if(node == null)
			return "";
		String left = prefix(node.getLeft());
		String right = prefix(node.getRight());
		
		if(!left.equals(""))
			left += " ";
		if(!right.equals(""))
			right += " ";
		
		return node.getPayload().toString() + left + right;
	}
	
	/**
	 * Returns a String  of all of the items in the tree in in order form
	 * @return
	 */
	public String infix() {return infix(root);}
	private String infix(Node<G> node)
	{
		if(node == null)
			return "";
		String left = prefix(node.getLeft());
		String right = prefix(node.getRight());
		
		if(!left.equals(""))
			left += " ";
		if(!right.equals(""))
			right += " ";
		
		return left + node.getPayload().toString() + right;
	}
	
	/**
	 * Returns a String of all of the items in the tree in postfix form
	 * @return
	 */
	public String postfix() {return postfix(root);}
	private String postfix(Node<G> node)
	{
		if(node == null)
			return "";
		String left = prefix(node.getLeft());
		String right = prefix(node.getRight());
		
		if(!left.equals(""))
			left += " ";
		if(!right.equals(""))
			right += " ";
		
		return left + right + node.getPayload().toString();
	}
}
