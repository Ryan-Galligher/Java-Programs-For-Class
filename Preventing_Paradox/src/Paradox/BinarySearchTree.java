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
		this(null);
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
		if(node == null)	//if node is null, then went past leaf and so shouldn't count as part of the size
			return 0;
		
		int right = size(node.getRight());
		int left = size(node.getLeft());
		
		return right+left+1;	//returns size for left branch + right branch plus current item
	}
	/**
	 * Returns the depth of the current tree
	 * @return
	 */
	public int depth() {return depth(root);}
	private int depth(Node<G> node)
	{
		if(node == null)
			return 0;
		int right = depth(node.getRight());
		int left  = depth(node.getLeft());
		
		return ((right>left) ? right:left) + 1;
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
			if(node.getRight() != null)	//if right doesn't exist, insert the node directly there
				insert(node.getRight(), item);
			else	//if right does exist, continue down it
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
	public void delete() {delete(null);root=null;}
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
	        	System.out.println("\t\t\tWe are out. the Current root is " + root);
	        	return result;
	        }
	        else
	        	return delete(item, root, null);
		}
	}
	private boolean delete(G item, Node<G> node, Node<G> parent)
	{
		if(node == null)	//If the current node is null, exits current iteration (used when deleting whole tree and using later if(item==null))
			return true;
		
		//System.out.println("\t\t\t\tTHE WE ARE IN PART " + node.getPayload());

		
		if (node.getPayload().compareTo(item) >0)	//If value less than current, go left in tree
		{
            if (node.getLeft() != null)
                  return delete(item, node.getLeft(), node);
            else
                  return false;
		} else if (node.getPayload().compareTo(item)<0) //If value greater than current, go right
		{
            if (node.getRight() != null)
                  return delete(item, node.getRight(), node);
            else
                  return false;
		} else //If value same as current OR item==null (which means delete entire tree)
		{
			boolean isRight = true;	//Used for if deleting everything, to make sure to recall correct node for parent
            if (node.getLeft() != null && node.getRight() != null) {	//If the current node has two filled branches, then it can't be outright deleted
                  node.setPayload(minValue(node.getRight()));	//so in order to get around that, overwrite the current value with the smallest value greater than current
                  delete(node.getPayload(), node.getRight(), node);	//Then go and delete the value we just overwrote current value with
            } else if (node.equals(parent.getLeft())) {	//node is a branch with only a single (or no) child, then need to overwrite the parent's value for node with the child of this current node (or null if leaf)
            	isRight=false;
            	parent.setLeft((node.getLeft() != null) ? node.getLeft():node.getRight());
            } else if (node.equals(parent.getRight())) {
            	parent.setRight((node.getLeft() != null) ? node.getLeft():node.getRight());	//same as previous if statment but for opposite side
            }
            
            if(item != null)	//If you aren't deleting the entire tree, then exit out of the delete method, else redelete the paren'ts connection with this node
            	return true;
            else
            	return delete(item, (isRight) ? parent.getRight():parent.getLeft(), parent);
		}
		
		/*Previous attempt, left for reference
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
	/**
	 * Used by the delete function to find the lowest value higher than node when started and returns that value
	 * @param node
	 * @return
	 */
	private G minValue(Node<G> node)
	{
		if(node.getLeft() == null)
			return node.getPayload();
		else
			return minValue(node.getLeft());
	}
	
	/**
	 * Returns a String of all of the items in the tree in prefix form
	 * @return
	 */
	public String prefix() {return prefix(root);}
	private String prefix(Node<G> node)
	{
		if(node == null)
			return "";
		String left = prefix(node.getLeft());
		String right = prefix(node.getRight());
		String center = node.getPayload().toString();
		
		if(!left.equals(""))
			left += " ";
		if(!right.equals(""))
			right += " ";
		if(!right.equals("") || !left.equals(""))
			center+=" ";
		
		return center + left + right;
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
		String center = node.getPayload().toString();
		String left = infix(node.getLeft());
		String right = infix(node.getRight());
		
		if(!left.equals(""))
			left += " ";
		if(!right.equals(""))
			right += " ";
		if(!right.equals(""))
			center+=" ";
		
		return left + center + right;
	}
	/**
	 * Returns a String of the items in the opposite sorted order
	 * @return
	 */
	public String reverseOrder() {return reverseOrder(root);}
	public String reverseOrder(Node<G> node)
	{
		String[] str = infix().split(" ");
		String output = "";
		for(int i = str.length-1; i >= 0; i--)
		{
			output+=str[i] + " ";
		}
		return output.substring(0, output.length()-1);
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
		String center = node.getPayload().toString();
		String left = postfix(node.getLeft());
		String right = postfix(node.getRight());
		
		if(!left.equals(""))
			left += " ";
		if(!right.equals(""))
			right += " ";
		if(!right.equals("") || !left.equals(""))
			center = " " + center;
		
		return left + right + center;
	}
}
