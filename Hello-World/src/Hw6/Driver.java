package Hw6;

public class Driver
{
	public static void main(String [] args)
	{
		BinaryTree<String> tree = new BinaryTree<String>("meow");
		
		tree.addNode("hello");
		tree.addNode("goodbye");
		tree.addNode("Chickennuggets");
		tree.addNode("NEVERAGAIN");
		
		 BinaryTree<String> stuff = tree.copy();
		 
		 System.out.print("In Order tree is: ");
		 tree.traverseInOrder();
		 System.out.println("");
		 
		 System.out.print("In Order stuff (copy of tree) is: ");
		 stuff.traverseInOrder();
		 System.out.println("");
		 
		 BinaryTree<Integer> things = new BinaryTree<Integer>(10);
		 things.addNode(7);
		 things.addNode(11);
		 things.addNode(7);
		 things.addNode(12);
		 things.addNode(34);
		 things.addNode(58);
		 things.addNode(22);
		 
		 System.out.print("In order things is: ");
		 things.traverseInOrder();
		 System.out.println("");
		 
		 System.out.println("Is stuff equal to thing?: " + stuff.equals(things));
		 System.out.println("Is stuff equal to tree?: " + stuff.equals(tree));
	}
}
