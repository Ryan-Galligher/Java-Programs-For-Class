package Hw6;

public class Node <G>
{
	private G data;
	private Node left;
	private Node right;
	
	public Node(G num)
	{
		data=num;
		left=null;
		right=null;
	}
	
	public Node(G num, Node<G> leftSide, Node<G> rightSide)
	{
		data = num;
		left = leftSide;
		right = rightSide;
	}
	
	public boolean equals(Object node)
	{
		if (node instanceof Node<?>)
		{
	        if ( ((Node<?>)node).data.equals(data) )
	        {
	        	boolean isLeft = (left == null || ((Node<?>)node).left == null) ? (left == null && ((Node<?>)node).left == null):(left.equals(((Node<?>)node).left));
	    		boolean isRight = (right == null || ((Node<?>)node).right == null) ? (right == null && ((Node<?>)node).right == null):(right.equals(((Node<?>)node).right));
	    		return isLeft && isRight;
	        }
	    }
	    return  node.equals(data);
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
		return 0.0;
	}
	
	
	public void addNode(G obj)
	{
		if(comparisonDifference(obj, data)<0)
		{
			if(left != null)
				left.addNode(obj);
			else
				left=new Node<G>(obj);
		}
		else
		{
			if(right != null)
				right.addNode(obj);
			else
				left = new Node<G>(obj);
		}
	}
	
	public Node copy()
	{
		Node<G> leftSide=null;
		Node<G> rightSide=null;
		
		if(left!=null)
			leftSide = left.copy();
		if(right!=null)
			rightSide= right.copy();
		
		return new Node<G>(data, leftSide, rightSide);
	}
}
