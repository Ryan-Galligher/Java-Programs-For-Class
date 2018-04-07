package Hw7;
/**
 * rpg170130
 * @author ryan Galligher
 *
 */
public class HashTable<K,V>
{
	int[] keys;
	static final double acceptableLoadValue = .5;
	Entry<K, V>[] table;
	
	public HashTable()
	{
		table = new Entry[9];
		keys = new int[9];
	}

	private int hash(int hashCode)
	{
		return (hashCode%900)+99;
	}
	
	/**
	 * Returns the spot where can place item/item is
	 * @param key
	 * @param isSettingUpSpot
	 * @return
	 */
	private int getSpotInTable(K key, boolean isSettingUpSpot, int[] givenTable)
	{
		int N = givenTable.length;
		int k = hash(key.hashCode());
		int j = 0;
		int pos = (int) ((k+Math.pow(j, 2))%N);
		
		
		if(isSettingUpSpot)//if the spot is supposed to be empty to add in a value, enter if. If it is supposed to find the place of where a current key is, will look for a key.
		{
			do
			{
				while(givenTable[pos]!=0 && j<=(N/2)+2)	//Quadratically probe the table a number of times up to half the entire length, as if it continues past this it will most likely be in an infinite loop
				{
					pos = (int) ((k+Math.pow(++j, 2))%N);
				}
				if(j>(N/2))
				{
					resize(true);
					k = hash(key.hashCode());
					j=0;
				}
			}while(j>(N/2));	//repeat until a viable spot comes up
		}
		else
		{
			while((givenTable[pos]==0 || givenTable[pos]!=k))
			{
				pos = (int) ((k+Math.pow(++j, 2))%N);
			}
		}
		
		return pos;
	}
	
	private Entry<K,V> getEntry(K key)
	{
		int itemIndex = getSpotInTable(key, false, keys);
		if(table[itemIndex] != null)
		{
			Entry<K,V> item = table[itemIndex];
			if(item.getKey().equals(key))
				return item;
		}
		
		return null;
	}
	
	public void add(K key, V item)
	{
		//adds in the item given with given key
		
		int spot = getSpotInTable(key, true, keys);
		keys[spot]=hash(key.hashCode());
		table[spot] = new Entry<K,V>(key,item);
		
		resize();
	}
	
	public V get(K key)
	{
		int itemIndex = getSpotInTable(key, false, keys);
		if(table[itemIndex] != null)
		{
			Entry<K,V> item = table[itemIndex];
			if(item.getKey().equals(key))
				return item.getValue();
		}
		
		return null;
	}
	
	private void resize()
	{
		if( calculateLoad() >= acceptableLoadValue)
		{
			resize(true);
		}
	}
	
	private void resize(boolean canProceed)
	{
		if(canProceed)
		{
			int[] newKeys = new int[keys.length*2];
			Entry<K,V>[] newItems = new Entry[table.length*2];
			K currentKey;
			int spot;
			
			for(int i = 0; i < table.length; i++)
			{
				if(table[i]!=null)
				{
					//Then grabs the key, find values, recalculate key based off of item and plug in key to array
					currentKey = table[i].getKey();
					spot = getSpotInTable(currentKey, true, newKeys);
					newKeys[spot] = hash(currentKey.hashCode());	//finds the spot where the key would be placed in the new array, and places it there
					newItems[spot] = getEntry(currentKey);
				}
			}
			keys = newKeys;
			table = newItems;
		}
	}
	
	public double calculateLoad()
	{
		double num = 0;
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i]!=0)
				num++;
		}
		return num/keys.length;
	}
	public int arraySizes()
	{
		return keys.length;
	}
}
