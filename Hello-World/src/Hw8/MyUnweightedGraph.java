//Ryan Galligher rpg170130
//Dylan Cooledge dac170430
package Hw8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Ryan Galligher
 * rpg170130
 */
public class MyUnweightedGraph implements MyGraph
{
	int currentSizeGraph;
	int maxSizeGraph;
	int[][] matrix;
	boolean[] visited;
	int modifier = 1;	//This is used b/c the read in values are from 1-10, but arrays are in form 0-9, so use modifier to adjust to proper array format
	
	public MyUnweightedGraph()
	{
		maxSizeGraph = 10;
		matrix = new int[maxSizeGraph][maxSizeGraph];
		visited = new boolean[maxSizeGraph];
		currentSizeGraph = 0;
	}
	public MyUnweightedGraph(int maxSize)
	{
		maxSizeGraph = maxSize;
		matrix = new int[maxSizeGraph][maxSizeGraph];
		visited = new boolean[maxSizeGraph];
		currentSizeGraph = 0;
	}
	
	public boolean isEmpty()
	{
		//System.out.println("currentSizeGraph is " + currentSizeGraph);
		return (currentSizeGraph -1 < 0);
	}
	
	public void createGraph(File f) throws Exception
	{
		Scanner s = new Scanner(f);
		currentSizeGraph = Integer.parseInt(s.nextLine());
		
		if(currentSizeGraph > maxSizeGraph || currentSizeGraph <= 0)
		{
			s.close();
			throw new IllegalArgumentException();
		}
		matrix = new int[maxSizeGraph][maxSizeGraph];
		
		String[] parsedInput;
		
		for(int i = 0; i < currentSizeGraph; i++)	//each line is considered in order, so first line represents connections between vector 1 and other vectors, etc
		{
			parsedInput = s.nextLine().split(" ");
			for(int a = 0; a < parsedInput.length; a++)	//iterates through each edge listed for current vector, and adds info to the matrix
			{
				matrix[i][Integer.parseInt(parsedInput[a]) - modifier] = -1;
			}
			
		}
		
		s.close();
	}
	
	public void setGraphColorless()
	{
		for(int i = 0; i < maxSizeGraph; i++)
		{
			for (int a = 0; a < maxSizeGraph; a++)
			{
				if(matrix[i][a] != -1 && matrix[i][a] != 0)
					matrix[i][a] = -1;
			}
		}
	}
	
	public String depthFirstSearch()
	{
		for(int i = 0; i < visited.length; i++)
		{
			visited[i]=false;
		}
		
		int x = 0;
		int y = 0;
		while(matrix[x][y] == 0)	//While there hasn't been a found starting vertex for the graph
		{
			if(y != currentSizeGraph -1)	//if the y is not at the end of the available matrix, move it down one
				y++;
			else	//if y is at its bottommost point
			{
				if(x != currentSizeGraph -1)	//if x not at the rightmost available place, set up next vector
				{
					y=0;
					x++;
				}
				else	//x and y are at max, meaning that this is empty graph
					return "";
			}
		}
		
		//System.out.println("the current x is " + x + " and y is " + y);
		
		return depthFirstSearch(x);
	}
	private String depthFirstSearch(int x)		// recursive depth determining function
	{
		if(visited[x])
			return "";
		
		String output = "" + (x + modifier) + " ";
		for(int i = 0; i < currentSizeGraph; i++)
		{
			if(matrix[x][i] != 0)
			{
				visited[x] = true;
				output += "" + depthFirstSearch(i);
			}
		}
		return output;
	}
	
	public boolean isConnected()
	{
		String[] output = depthFirstSearch().split(" ");
		boolean found;
		for(int i = 0; i < currentSizeGraph; i++)	//For every vector that should be included within the graph
		{
			found = false;	//the vector has not yet been found to exist within the connected graph
			//System.out.println("Current number to find is: " + i + " and so the value to compare is " + (modifier+i));
			for(int a = 0; a < output.length; a++)	//looking through every item outputted by the depth first search
			{
				//System.out.println("\tCurrent item in output is " + a + " which is value " + Integer.parseInt(output[a]));
				if(Integer.parseInt(output[a]) == i + modifier)	//If the desired vector is found, list it as such and short the loop go find the next one
				{
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		return true;
		
		
	}
	
	public String printMatrix()
	{
		String total = "";
		for(int i = 0; i < matrix.length; i++)
		{
			for (int a = 0; a < matrix[i].length; a++)
			{
				total += matrix[i][a] + " ";
			}
			total+="\n";
		}
		return total;
	}
	
	public boolean isBipartite()
	{
		MyQueue<Integer> queue = new MyQueue();
		int x = 0;
		int y = 0;
		while(matrix[x][y] == 0)	//While there hasn't been a found starting vertex for the graph
		{
			if(y != currentSizeGraph -1)	//if the y is not at the end of the available matrix, move it down one
				y++;
			else	//if y is at its bottommost point
			{
				if(x != currentSizeGraph -1)	//if x not at the rightmost available place, set up next vector
				{
					y=0;
					x++;
				}
				else	//x and y are at max, meaning that this is empty graph
					return false;
			}
		}
		queue.add(x);
		
		while(!queue.empty())
		{
			int item = queue.remove();
			for(int i = 0; i < currentSizeGraph; i++)		//loop through nodes
			{
				if(matrix[item][i] != 0)		//if i is neighbor
				{
					if(matrix[item][i] == -1) {
						for (int a = 0; a < maxSizeGraph; a++)
						{	
							if(matrix[a][i] == -1) {
								if(matrix[item][i] == 2) {
									matrix[a][i] = 1;
								}else {
									matrix[a][i] = 2;
								}
								queue.add(i);
							}
							else if(matrix[item][i] != matrix[a][i]) {
								
							}else {
								return false;
							}
						}
					}else {
						if(matrix[item][i] == matrix[item][item])
							return false;
					}
				}
			}
		}
		return true;
	}

}
