package Version2;

/*
 * Name: 	Ryan Galligher
 * Net ID:	rpg170130
 */

/**
 * Class saves the information for the name of the pilot, overall patrol area and a boolean flag.
 * @author ryan
 *
 */
public class Payload
{
	boolean flag;
	String pilotName;
	double patrolArea;
	
	public Payload(String pilotName)
	{
		this.pilotName = pilotName;
		this.patrolArea=0.0;
		flag=false;
	}
	public Payload(String pilotName, double area)
	{
		this.pilotName=pilotName;
		patrolArea=area;
		flag=false;
	}
	
	//Standard getters and setters for the flag, patrolArea and Pilot names.
	public double getPatrolArea(){return patrolArea;}
	public String getPilotName(){return pilotName;}
	public boolean getFlag(){return flag;}
	
	protected void setPatrolArea(double patrolArea){this.patrolArea=patrolArea;}
	protected void setPilotName(String pilotName){this.pilotName=pilotName;}
	protected void setFlag(boolean flag){this.flag=flag;}
	
}
