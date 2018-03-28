package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import hashTable.HashTable;
import hashTable.SeparateChaining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;

import org.junit.Ignore;
import org.junit.Test;

public class HashTableTest {

	protected HashTable<Integer,String> ht;
	protected int hashSize;
	
	@Ignore
	@Test
	public void separateChaining() 
	{
		System.out.println("========================================");
		System.out.println("\n -Separate Chaining Test:");
		ht = new SeparateChaining<Integer,String>();
		System.out.println(" -Initial Test:");
		System.out.println("  -Checking Hash Table if empty.");
		assertEquals(ht.isEmpty(),true);
		System.out.println("  -Checking Hash Table size.");
		assertTrue( ht.tableLength() >= 0 );
		hashSize = ht.tableLength();
		System.out.println("  -Hash Table size is "+hashSize+".");
		
		System.out.println("\n -Insertion and Search Test:");
		System.out.println("  -Inserting 10 entries without collision.");
		for (int i = 0; i < 10; i++) {	ht.put((Integer)i, i+"");	}
		System.out.println("  -Checking size again.");
		assertEquals(ht.isEmpty(),false);
		System.out.println("  -Checking number of entries.");
		assertTrue(ht.size() == 10);  
		System.out.println("  -Making an iterable object.");
		Iterable<Integer> iterator = ht.keys();
		System.out.println("  -Checking the previously inserted elements using the iterable Object.");
		for (Integer x : iterator) 
		{	assertTrue(Integer.parseInt(ht.get(x))==x);	}
		System.out.println("  -Insert 10 entries that causes collisions.");
		for (int i = 10; i < 20; i++) {	ht.put((Integer)i, i+"");	}
		System.out.println("  -Checking if the size is doubled.");
		assertTrue(ht.tableLength()<2*hashSize);
		System.out.println("  -Size not doubled as expected.");
		System.out.println("  -Checking number of entries.");
		assertTrue(ht.size() == 20); 
		System.out.println("  -Insert entries that causes collisions and cause size to be doubled.");
		for (int i = 20; i < 30; i++) {	ht.put((Integer)i, i+"");	}
		assertTrue(ht.tableLength()>=2*hashSize);
		hashSize = ht.tableLength();
		System.out.println("  -The new hash table size = "+hashSize+".");
		System.out.println("  -Checking number of entries.");
		assertTrue(ht.size() == 30); 
		System.out.println("  -Regenerate the iterable object.");
		iterator = ht.keys();
		System.out.println("  -Checking the previously inserted elements using the iterable Object.");
		for (Integer x : iterator) 
		{	assertTrue(Integer.parseInt(ht.get(x))==x);	}
		System.out.println("  -Insert dublicates.");
		for (int i = 0; i < 30; i++) {	ht.put((Integer)i, i+"");	}
		System.out.println("  -Checking number of entries.");
		assertTrue(ht.size() == 30); 
		
		System.out.println("\n -Deletion and Search Test:");
		System.out.println("  -Deleting entries from 10 to 19.");
		for (int i = 10; i < 20; i++) {	ht.delete((Integer)i);	}
		System.out.println("  -Checking number of entries.");
		assertTrue(ht.size() == 20); 
		System.out.println("  -Get entries from 10 to 19.");
		for (int i = 10; i < 20; i++) {	assertTrue(ht.get((Integer)i)==null);	}
		System.out.println("  -Entries not found as expected.");
		System.out.println("  -Trying to get the rest of the entries.");
		for (int i = 0; i < 10; i++) {	assertTrue(ht.get((Integer)i).equals(i+""));	}
		for (int i = 20; i < 30; i++) {	assertTrue(ht.get((Integer)i).equals(i+""));	}
		System.out.println("  -Successfully access entries.");
		System.out.println("  -Checking if contains entries from 10 to 19.");
		for (int i = 10; i < 20; i++) {	assertTrue(!ht.contains((Integer)i));	}
		System.out.println("  -Entries not found as expected.");
		System.out.println("  -Checking if contains the rest of the entries.");
		for (int i = 0; i < 10; i++) {	assertTrue(ht.contains((Integer)i));	}
		for (int i = 20; i < 30; i++) {	assertTrue(ht.contains((Integer)i));	}
		System.out.println("  -Successfully found entries.");
		System.out.println("  -Checking if contains entries out of range.");
		assertTrue(!ht.contains((Integer)100));
		assertTrue(!ht.contains((Integer)1000));
		assertTrue(!ht.contains((Integer)10000));
		assertTrue(!ht.contains((Integer)100000));
		System.out.println("  -Entries not found as expected.");
		System.out.println("  -Deleting all entries.");
		for (int i = 0; i < 10; i++) {	ht.delete((Integer)i);	}
		for (int i = 20; i < 30; i++) {	ht.delete((Integer)i);	}
		System.out.println("  -Checking number of entries.");
		assertTrue(ht.size() == 0);
		System.out.println("  -Checking hash table if empty.");
		assertEquals(ht.isEmpty(),true);
		System.out.println("  -Trying to get any of the deleted elements.");
		for (int i = 0; i < 30; i++) {	assertTrue(ht.get((Integer)i)==null);	}
		System.out.println("  -Checking if contains the deleted entries.");
		for (int i = 0; i < 30; i++) {	assertTrue(!ht.contains((Integer)i));	}
		
		System.out.println("\n Separate Chaining Test Completed successfully.");
	}

	private static int insertionEntries = 10000;
	//@Ignore
	@Test
	public void generalTest()
	{
		
		ArrayList<Integer> collisions = new ArrayList<Integer>();
		ArrayList<Integer> memoryUsed = new ArrayList<Integer>();
		Integer[] shuffledArray = new Integer[insertionEntries];
		shuffleArray(shuffledArray);

		System.out.println("separateChaining");
		ht = new SeparateChaining<Integer,String>();
		for(int i = 0 ; i < insertionEntries ; i++)
		{
			ht.put(shuffledArray[i], shuffledArray[i]+"");
			collisions.add(ht.getNumberOfCollisions());
			memoryUsed.add(ht.getMemoryUsed());
		}
		
		System.out.println("memory: "+ht.getMemoryUsed()+" ,collisions: "+ht.getTotalCollison());
		saveToFile(collisions,"SeparateChaining_collisions");
		saveToFile(memoryUsed,"SeparateChaining_memoryUsed");

	}
	
	public void shuffleArray(Integer array[])
	{
		for(int i = 0 ; i < array.length ; i++)
		{
			array[i] = i;
		}
	    Collections.shuffle(Arrays.asList(array)); //shuffle array
	}

	
	public void saveToFile(ArrayList<Integer> theList,String name)
	{
		/// opening file
		Formatter z = null ;
		try{
			z=new Formatter("C:\\Users\\HP\\Desktop\\"+name+".txt");
		}
		catch(Exception e){
			System.out.println("There is an Error" + e);
		}
		
		// add theList to the File
		for (int i = 0; i <theList.size(); i++) {
			z.format("%s\t", theList.get(i)+"");
		}
		// save and exit
		z.close();	
	}
}
