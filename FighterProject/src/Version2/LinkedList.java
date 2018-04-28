package Version2;

/*
 * Name: 	Ryan Galligher
 * Net ID:	rpg170130
 */

public class LinkedList
{
	Node<Payload> head;
	Node<Payload> tail;
	Node<Payload> spot;
	int size;
	
	public LinkedList()
	{
		head=null;
		tail=null;
		spot=null;
		size=0;
	}
	
	public LinkedList(Node<Payload> node)
	{
		this();
		head=node;
		tail=node;
		spot=node;
		size++;
	}
	
	protected Node<Payload> getHead() {return head;}
	protected Node<Payload> getTail() {return tail;}
	protected void setHead(Node<Payload> head) {this.head=head;}
	protected void setTail(Node<Payload> tail) {this.tail=tail;}
	
	@Override
	public String toString()
	{
		if(spot!=null)
		{
			String s = String.format("%s\t\t%.2f\r\n",  spot.getPayload().getPilotName(),  spot.getPayload().getPatrolArea());
			spot=spot.nextItem;
			return s + toString();
		}
		spot=head;
		return "";	
	}
	/**
	 * Adds the Payload object to the list
	 * @param item the Payload to be added to the list
	 */
	public void add(Payload item)
	{
		if(head!=null)
		{
			Node<Payload> n = new Node<Payload>(item,tail, null);
			tail.nextItem=n;
			tail=n;
		}
		else
		{
			head=new Node<Payload>(item,null, null);
			tail=head;
		}
		size++;
		spot=head;

	}
	/**
	 * Adds the pilot name and area to the list
	 * @param name name of the pilot
	 * @param area area that the pilot travels
	 */
	public void add(String name, double area)
	{
		add(new Payload(name, area));
	}
	/**
	 * 
	 * @return the size of the linked list
	 */
	public int size()
	{
		/*int size=0;
		spot=head;
		while(spot!=null)
		{
			size++;
			spot=spot.nextItem;
		}
		spot=head;*/
		return size;
	}
	
	/**
	 * Returns the name of a pilot at given spot
	 * @param spot
	 * @return the name of the pilot at spot, and if spot item is too large, then returns null
	 */
	public String getName(int place)
	{
		if(place>size)
			return null;
		for(int i = 0; i < place-1;i++)
		{
			spot=spot.nextItem;
		}
		String s = spot.getPayload().getPilotName();
		spot=head;
		return s;
	}
	
	/**
	 * Returns the area of a pilot at given spot
	 * @param place
	 * @return the area of the pilot at spot, and if spot item is too large, returns -1
	 */
	public double getArea(int place)
	{
		if(place>size)
			return -1;
		for(int i = 0; i < place-1;i++)
		{
			spot=spot.nextItem;
		}
		double d = spot.getPayload().getPatrolArea();
		spot=head;
		return d;
	}
	
	/**
	 * Searches the list for the given pilot area and returns if there are any
	 * @param d the area to search for in the list
	 * @return if there is at least one instance of the given area stored within the list
	 */
	public boolean containsArea(double d)
	{
		while(spot!=null)
		{
			if( (((double)Math.round(spot.getPayload().getPatrolArea())*100)/100.0)!=d)
				spot=spot.nextItem;
			else
				break;
		}
		boolean returns = spot==null;
		spot=head;
		return returns;
	}
	
	/**
	 * Searches the list for the given pilot name and returns a boolean of if the list contains it.
	 * @param s The name of the pilot to be searching for
	 * @return if there is at least one pilot within the list with the given name.
	 */
	public boolean containsPilot(String s)
	{
		while(spot!=null)
		{
			if(spot.getPayload().getPilotName().equals(s))
				spot=spot.nextItem;
			else
				break;
		}
		boolean returns = spot==null;
		spot=head;
		return returns;
	}
	
	/**
	 * Returns the info stored within the node at {@bold place} as a String
	 * @param place the spot in the list to return the information stored in the node
	 * @return String of the information stored in the node, and will return null if invalid spot number
	 */
	public String getInfo(int place)
	{
		System.out.println("\t\tGetting info at place " + place + " and the currently recorded size of the list is " + size);
		if(place>=size&&place<0)
			return null;
		for(int i = 0; i < place-1;i++)
		{
			System.out.print("Current spot is value " + spot);
			System.out.println(" Current value is " + spot.payload.pilotName);
			spot=spot.nextItem;
		}
		System.out.println("" +spot + place);
		double d = spot.getPayload().getPatrolArea();
		String s = spot.getPayload().getPilotName();
		spot=head;
		return String.format("%s\t%.2f", s,d);
	}
	
	//used by the sort method so don't need many if statements in method
	/**
	 * Used to determine if the contents of a node are the same as a given object.
	 * @param one An Object to be compared
	 * @param two A Node<Payload> to determine
	 * @return if the two contain the same information
	 */
	public boolean doTheyEqual(Object one, Node<Payload> two)
	{
		if(one instanceof String)
		{
			return ((String)one).equals(((Payload) two.getPayload()).getPilotName());
		}
		if(one instanceof Double || one instanceof Integer)
		{
			if(one instanceof Double)
				return ( ((Double)one).doubleValue() == ((Payload) two.getPayload()).getPatrolArea() );
			else
				return ( ((Integer)one).intValue() == (int)((Payload) two.getPayload()).getPatrolArea() );
		}
		return false;
	}
	//Also used by the sort method
	/**
	 * Compares the two given Objects and returns back a numerical value as to the difference between them. So positive number means item1>item2, negative number means item1<item2, 0 means item1==item2
	 * @param item1 first item to be compared, has second item subtracted from it.
	 * @param item2 second item to be compared.
	 * @return the numerical difference between the two Objects
	 */
	public double comparisonDifference(Object item1, Object item2)
	{
		System.out.println("\t\t\t\t\tCurrently about to compare " + item1 + "-and-" + item2);
		if(item1 instanceof Integer && item2 instanceof Integer)
			return ((Integer)item1).intValue() - ((Integer)item2).intValue();
		if(item1 instanceof Double && item2 instanceof Double)
			return ((Double)item1).doubleValue() - ((Double)item2).doubleValue();
		if(item1 instanceof Float && item2 instanceof Float)
			return ((Float)item1).doubleValue() - ((Float)item2).doubleValue();
		if(item1 instanceof String && item2 instanceof String)
			return ((String)item1).compareTo( ((String)item2));
			
		throw new IllegalArgumentException();
	}
	
	/**
	 * Sorts the linked list in either Ascending or Decending order, and either by name or by area, depending upon the parameters given.
	 * @param inAscendingOrder boolean of if the list is to be sorted in ascending or decending order
	 * @param byName boolean of if the list is to be sorted by name or by area
	 */
	public void sort(boolean inAscendingOrder, boolean byName)
	{
		//if there is even a list at all, continue
		if(head==null)
			return;
		
		//sets spot to the first item in the list
		spot = head;
		//while spot has not reached the second to the end of the list
		while(spot!=null && spot.nextItem!=null)
		{
			System.out.println("\t\t\t\tInside sort method, current next item: " + spot.nextItem);
			spot = spot.nextItem;
			//saves the value currently in spot
			Object k = (byName ? spot.payload.pilotName:spot.payload.patrolArea);
			//creates traversal pointer n
			Node<Payload> n = spot;
			//While n is not the first item in the list and the values in the previous are either too big/small, depending upon ascending/descending order. (if inAscendingOrder==true, then section true if n.prev > spot, meaning that another loops needs to go by in order to sort in ascending order)
			while(n.previousItem!=null && ( (inAscendingOrder) == (comparisonDifference((byName ? n.previousItem.payload.pilotName:n.previousItem.payload.patrolArea),k)>=0) ) ) 
			{
				n=n.previousItem;
			}
			//So now n stands for a place where spot belongs
			//now needs to change all of the pointers around so spot is in the place to the left of n is currently hovering.
			
			//in order to prevent infinite loops, if the two values are the same then skip putting the spot in front of n
			if(n.previousItem!=null && comparisonDifference((byName ? n.previousItem.payload.pilotName:n.previousItem.payload.patrolArea),k)==0)
				continue;
			if(n==spot)
				continue;
			
			System.out.println("\t\t\t\tAbout to switch spots for pilot spot " + spot.payload.pilotName + "and for n " + n.payload.pilotName);
			
			Node<Payload> placeholder = spot.previousItem;
			
			//Sets the node in front of n to point to spot
			if(n.previousItem!=null)
				n.previousItem.nextItem=spot;
			//Sets items around spot to point to each other
			if(spot.nextItem!=null)
				spot.nextItem.previousItem=spot.previousItem;
			Node<Payload> place2=spot.nextItem;
			//sets spot to point to before and after positions
			spot.nextItem=n;
			spot.previousItem=n.previousItem;
			//sets n to finally point to previous as spot
			n.previousItem=spot;
			
			placeholder.nextItem=place2;
			
			spot=placeholder;
		} 
		
		resetValues(spot);

	}
	
	/**
	 * If the head and tail pointers of the list get messed up, uses a pointer variable connected to at least one part in the list to reset them.
	 * @param spot a pointer to a value in the linked list that can be used to reset others.
	 */
	private void resetValues(Node<Payload> spot)
	{
		while(spot.previousItem!=null)
		{
			spot=spot.previousItem;
		}
		head=spot;
		while(spot.nextItem!=null)
		{
			spot=spot.nextItem;
		}
		tail=spot;
		spot=head;
	}
	
}
