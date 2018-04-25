//Ryan Galligher rpg170130
//Dylan Cooledge dac170430
package Hw8;

import java.io.File;

/**
 * @author Ryan Galligher
 * rpg170130
 */
public interface MyGraph
{
	
	
	public boolean isEmpty();
	public void createGraph(File f) throws Exception;
	public String depthFirstSearch();
	public boolean isConnected();
}
