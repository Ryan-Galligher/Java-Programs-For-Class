package Version2;
 
/*
 * Name: 	Ryan Galligher
 * Net ID:	rpg170130
 */

/**
 * Node stores Some information and holds references to other Nodes in the list
 * @author ryan
 *
 * @param <G>
 */
public class Node<G>
{
	protected G payload;
	protected Node<G> previousItem;
	protected Node<G> nextItem;
	
	public Node(G payload)
	{
		this.payload=payload;
	}
	public Node(G payload, Node previousItem, Node nextItem)
	{
		this.payload=payload;
		this.previousItem=previousItem;
		this.nextItem=nextItem;
	}
	
	//Standard getters and setters for the variables
	protected Node getPrevious() {return previousItem;}
	protected Node getNext() {return nextItem;}
	protected G getPayload() {return payload;}

	protected void setPrevious(Node previousItem) {this.previousItem=previousItem;}
	protected void setNext(Node nextItem) {this.nextItem=nextItem;}
	protected void setPayload(G payload) {this.payload=payload;}
}
