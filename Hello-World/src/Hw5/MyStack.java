package Hw5;
/*
 * Ryan Galligher, rpg170130
 */
public class MyStack<G>
{
	private Node top;
	private Node tail;
	//private Node current;
	
	public MyStack()
	{
		top=null;
		tail=null;
		//current=null;
	}
	
	public void push(G info)
	{
		if(top == null)
		{
			top=new Node<G>(info, null, null);
			tail=top;
		}
		else
		{
			tail.next=new Node<G>(info, tail, null);
			tail = tail.next;
		}
	}
	
	public G pop()
	{
		if(tail==null)
			return null;
		else
		{
			G stuff = (G) tail.info;
			tail = tail.prev;
			if(tail==null)
				top=null;
			else
				tail.next=null;
			return stuff;
		}
	}
	
	public boolean empty()
	{
		return (top==null);
	}
	
	public String returnAll()
	{
		String total = "";
		Node n = top;
		while(n != null)
		{
			total += " " + n.info;
			n=n.next;
		}
		return total;
	}
}
