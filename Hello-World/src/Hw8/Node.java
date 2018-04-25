package Hw8;
/*
 * Ryan Galligher, rpg170130
 */
public class Node <G>
{
	protected Node next;
	protected Node prev;
	protected G info;
	
	public Node(G info, Node previous, Node next)
	{
		this.info = info;
		this.next = next;
		prev=previous;
	}
}
