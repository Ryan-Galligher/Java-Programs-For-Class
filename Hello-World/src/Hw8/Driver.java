//Ryan Galligher rpg170130
//Dylan Cooledge dac170430
package Hw8;

import java.io.File;

/**
 * @author Ryan Galligher
 * rpg170130
 */
public class Driver
{
	static String graph1Name = "graph1.txt";
	static String graph2Name = "graph2.txt";
	
	public static void main(String [] args)
	{
		File gr1 = new File(graph1Name);
		File gr2 = new File(graph2Name);
		MyUnweightedGraph graph = new MyUnweightedGraph();
		
		try
		{
			graph.createGraph(gr1);
			//System.out.println("Is the graph currently empty? " + graph.isEmpty());
			//System.out.println("The depth first search for the graph is: " + graph.depthFirstSearch());
			//System.out.println("Is the entirety of the graph connected? " + graph.isConnected());
			System.out.println("Is bipartite? " + graph.isBipartite());
			System.out.println(graph.printMatrix());
			
			System.out.println("\n");
			
			graph.createGraph(gr2);
			System.out.println(graph.printMatrix());
			//System.out.println("Is the graph currently empty? " + graph.isEmpty());
			//System.out.println("The depth first search for the graph is: " + graph.depthFirstSearch());
			//System.out.println("Is the entirety of the graph connected? " + graph.isConnected());
			System.out.println("Is bipartite? " + graph.isBipartite());
			System.out.println(graph.printMatrix());

		}catch(Exception e){e.printStackTrace();}
		
	}
}
