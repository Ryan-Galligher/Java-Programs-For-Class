package Hw5;
/*
 * Ryan Galligher, rpg170130
 */
public class MyQueue<G>
{
	private Node top;
	private Node tail;
	//private Node current;
	
	public MyQueue()
	{
		top=null;
		tail=null;
		//current=null;
	}
	
	public void add(G info)
	{
		if(top == null)
		{
			top=new Node(info, null, null);
			tail=top;
		}
		else
		{
			tail.next=new Node(info, tail, null);
			tail = tail.next;
		}
	}
	
	public G remove()
	{
		if(top==null)
			return null;
		else
		{
			G stuff = (G) top.info;
			top = top.next;
			if(top==null)
				tail=null;
			else
				top.prev=null;
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
