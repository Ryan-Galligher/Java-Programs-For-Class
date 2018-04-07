package Hw6;

public class BinaryTree<G>
{
	private G data;
	private BinaryTree<G> left;
	private BinaryTree<G> right;
	
	public BinaryTree(G num)
	{
		data=num;
		left=null;
		right=null;
	}
	
	public BinaryTree(G num, BinaryTree<G> leftSide, BinaryTree<G> rightSide)
	{
		data = num;
		left = leftSide;
		right = rightSide;
	}
	
	public double comparisonDifference(Object item1, Object item2)
	{
		//System.out.println("\t\t\t\t\tCurrently about to compare " + item1 + "-and-" + item2);
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
	
	public void addNode(G num)
	{
		double info = comparisonDifference(num, data);
		//System.out.println("\t\t\t\t\t\tThe difference was: " + info);
		if(info<0)
		{
			if(left != null)
				left.addNode(num);
			else
				left=new BinaryTree<G>(num);
		}
		else
		{
			if(right != null)
				right.addNode(num);
			else
				right = new BinaryTree<G>(num);
		}
	}
	
	public BinaryTree<G> copy()
	{
		BinaryTree<G> leftSide=null;
		BinaryTree<G> rightSide=null;
		
		if(left!=null)
			leftSide = left.copy();
		if(right!=null)
			rightSide= right.copy();
		
		return new BinaryTree<G>(data, leftSide, rightSide);
	}
	
	public boolean equals(BinaryTree<G> tree)
	{
		boolean isLeft = (left == null || tree.left == null) ? (left == null && tree.left == null):(left.equals(tree.left));
		boolean isRight = (right == null || tree.right == null) ? (right == null && tree.right == null):(right.equals(tree.right));
		return (data.equals(tree.data)) && isLeft && isRight;
	}
	
	public void traverseInOrder() {
		if( this.left != null ) {
			this.left.traverseInOrder();
		}
		System.out.print( this.data + " " );
		if( this.right != null ) {
			this.right.traverseInOrder();
		}
	}
}
