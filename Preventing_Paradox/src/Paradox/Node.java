package Paradox;

/*
 * Name: 	Ryan Galligher
 * Net ID:	rpg170130
 */

/**
 * Node stores Some information and holds references to other Nodes in the list
 * @author ryan Galligher
 *rpg170130
 * @param <G>
 */
public class Node<G extends Comparable<? super G>>
{
	private G payload;
	private Node<G> rightPointer;
	private Node<G> leftPointer;
	
	public Node()
	{
		this(null);
	}
	public Node(G payload)
	{
		this.payload=payload;
	}
	public Node(G payload, Node<G> leftPointer, Node<G> rightPointer)
	{
		this.payload=payload;
		this.leftPointer=leftPointer;
		this.rightPointer=rightPointer;
	}
	
	//Standard getters and setters for the variables
	protected Node<G> getRight() {return rightPointer;}
	protected Node<G> getLeft() {return leftPointer;}
	protected G getPayload() {return payload;}
	
	protected void setLeft(Node<G> left) {this.leftPointer=left;}
	protected void setRight(Node<G> right) {this.rightPointer=right;}
	protected void setPayload(G payload) {this.payload=payload;}
}
