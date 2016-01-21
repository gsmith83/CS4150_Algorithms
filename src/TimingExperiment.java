import java.util.Iterator;
import java.util.TreeSet;

public class TimingExperiment
{
	TreeSet<Integer> treeSet = new TreeSet<Integer>();
	
	// Generates a TreeSet with random integers between the values of 0 and 2 million
	public void generateRandom(int power)
	{
		// the size of the tree will be 2^power
		int size = (int)Math.pow(2, power);
		
		for(int i = 0; i < size; i++)
		{
			int element = (int)(Math.random() * 2000000);
			while(treeSet.contains(element))
			{
				element = (int)(Math.random() * 2000000);
			}
			treeSet.add(element);
		}
		
		// verify there are 2^power elements
		if(size != treeSet.size())
			System.err.println("TreeSet size = " + treeSet.size());
	}
	
	// generates a TreeSet with the set of integers [0, 2^power]
	public void generateTreeSet(int power)
	{
		int size = (int)Math.pow(2, power);
		
		for(int i = 0; i < size; i++)
		{
			treeSet.add(i);
		}
		
		if(size != treeSet.size())
			System.out.println("TreeSet size = " + treeSet.size());
	}
	
	
	// Find an integer in the treeSet
	
	
	
	
	public static void main(String[] args)
	{
		TimingExperiment exp = new TimingExperiment();
		exp.generateRandom(20);
		
		
		// verify elements 
		Iterator<Integer> iterator = exp.treeSet.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
}
