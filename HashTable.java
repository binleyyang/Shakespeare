/* Binley Yang
 * CSC 172
 * Shakespeare
 * Email: byang8@u.rochester.edu
 * */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * From Weiss Textbook
 * */
public class HashTable implements Set<Word> {
	
	private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry[] array; // The array of elements
    private int occupied, modCount, totalWords = 0; 
    private double theSize = 0;
    private Scanner scan;
	
	/**Construct the Hash Table for words*/
	public HashTable() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	/**Construct hash table and size the approximate size*/
	public HashTable(int size) {
		allocateArray(size);
		clear();
	}
	
	/**inserts words into the table. If the word is already present, add line number to end of linked list
	 * @param Object words
	 * @return boolean value
	 * */
	 public boolean add(Word words) {
	            // Insert words as active
		 	words.removeNonAlphaNum();
		 	totalWords++;
	        int currentPosition = findPos(words.getContent());
	        if (isActive(array, currentPosition)) {
	        	array[currentPosition].element.addLineNumbers((Integer)words.getLines().getHead().data);
	            return false;
	        }

	        array[currentPosition] = new HashEntry(words, true);
	        theSize++;
	        
	            // Rehashing
	        if(++occupied > array.length / 2)
	            rehash();
	        
	        return true;
	    }

	    /**
	     * Expand the hash table.
	     */
	    private void rehash() {
	        HashEntry[] oldArray = array;

	            // Create a new double-sized, empty table
	        allocateArray(2 * oldArray.length);
	        occupied = 0;
	        theSize = 0;

	            // Copy table over
	        for (HashEntry entry : oldArray) {
	            if (entry != null && entry.isActive)
	                add(entry.element);
	        }
	    }

	    /**
	     * Method that performs quadratic probing.
	     * @param x the item to search for.
	     * @return the position where the search terminates.
	     */
	    private int findPos(String x) {
	        int offset = 1;
	        int currentPosition = myhash(x);
	        
	        while (array[currentPosition] != null && !array[currentPosition].element.getContent().equals(x)) {
	            currentPosition += offset;  // Compute ith probe
	            offset += 2;
	            if(currentPosition >= array.length)
	                currentPosition -= array.length;
	        }
	        
	        return currentPosition;
	    }

	    /**
	     * Remove from the hash table.
	     * @param x the item to remove.
	     * @return true if item removed
	     */
	    public boolean remove(Word x) {
	        int currentPosition = findPos(x.getContent());
	        if (isActive(array, currentPosition)) {
	            array[currentPosition].isActive = false;
	            theSize--;
	            return true;
	        }
	        else
	            return false;
	    }
	    
	    /**
	     * Get current size.
	     * @return the size.
	     */
	    public double size() {
	        return theSize;
	    }
	    
	    /**
	     * Get total number of words
	     * @return totalWords.
	     */
	    public int total() {
	    	return totalWords;
	    }
	   
	    public void printHT(HashTable ht){
	    	for(Word s : ht){
	    		System.out.print(s.getContent() + ", appears in positions ");
	    		s.getLines().printList();
	    		
	    		System.out.println();
	    	}
	    }
	    
	    /**Method to search the concordance/hashtable to return the word and associated line numbers
	     * @param the word 
	     * */
	    public void searchConc(String s) {
	    	scan = new Scanner(System.in);
	    	

		    	while (array[findPos(s)] == null) {
		    		System.out.println("Word not found. Please try again or enter another word.");
		    		s = scan.nextLine();
		    	}
		    	
		    	System.out.println(array[findPos(s)].element.getContent() + " occurs " + array[findPos(s)].element.getLines().getListSize() + " times, in lines: .");
		    	array[findPos(s)].element.getLines().printList();
	    	
	    }
	    
	    /**
	     * Find an item in the hash table.
	     * @param x the item to search for.
	     * @return the matching item.
	     */
	    public boolean contains(Word x) {
	        int currentPosition = findPos(x.getContent());
	        return isActive(array, currentPosition);
	    }
	    
	    @SuppressWarnings("rawtypes")
		public Object get(Word x) {
	    	int currentPosition = findPos(x.getContent());
	    	return array[currentPosition];
	    }
	     
	    /**
	     * Return true if currentPosition exists and is active.
	     * @param currentPos the result of a call to findPos.
	     * @return true if currentPos is active.
	     */
	    private boolean isActive(HashEntry[] array, int currentPosition) {
	        return array[currentPosition] != null && array[currentPosition].isActive;
	    }

	    /**
	     * Make the hash table logically empty.
	     */
	    public void clear() {
	    	occupied = 0;
	        for (int i = 0; i < array.length; i++) {
	            array[i] = null;
	        }
	    }
	    
	    private int myhash(Object x) {
	       
	    	int hashVal = x.hashCode();
	        hashVal %= array.length;
	        
	        if (hashVal < 0)
	            hashVal += array.length;

	        return hashVal;
	    }
	    
	    private static class HashEntry {
	        public Word element;   // the element
	        public boolean isActive;  // false if marked deleted

	        public HashEntry(Word e) {
	            this(e, true);
	        }

	        public HashEntry(Word e, boolean i) {
	            element  = e;
	            isActive = i;
	        }
	    }

	    /**
	     * Internal method to allocate array.
	     * @param arraySize the size of the array.
	     */
	    private void allocateArray(int arraySize) {
	        array = new HashEntry[nextPrime(arraySize)];
	    }

	    /**
	     * Internal method to find a prime number at least as large as n.
	     * @param n the starting number (must be positive).
	     * @return a prime number larger than or equal to n.
	     */
	    private static int nextPrime(int n) {
	        if (n % 2 == 0)
	            n++;

	        for (; !isPrime(n); n += 2)
	            ;

	        return n;
	    }

	    /**
	     * Internal method to test if a number is prime.
	     * @param n the number to test.
	     * @return the result of the test.
	     */
	    private static boolean isPrime(int n) {
	        if(n == 2 || n == 3)
	            return true;

	        if(n == 1 || n % 2 == 0)
	            return false;

	        for (int i = 3; i * i <= n; i += 2) {
	            if (n % i == 0)
	                return false;
	        }
	        return true;
	    }
	  
	    /**Method to make my HashTable class iterable by obtaining an Iterator object
	     * to traverse the set
	     * @return an iterator positioned before the first element in the set
	     * */
		@Override
		public Iterator<Word> iterator() {
			return new HashTableIterator();
		}
		
		/**Private class to make my HashTable class Iterable
		 * From Weiss Textbook
		 * */
		private class HashTableIterator implements Iterator<Word> {
			
	        private int expectedModCount = modCount;
	        private int currentPos = -1;
	        private int visited = 0;       
	        
	        public boolean hasNext() {
	            if (expectedModCount != modCount)
	                throw new ConcurrentModificationException();
	            
	            return visited != size();    
	        }
	        
	        public Word next() {
	            if (!hasNext())
	                throw new NoSuchElementException();
	                          
	            do {
	                currentPos++;
	            } while( currentPos < array.length && !isActive(array, currentPos));
	                            
	            visited++;
	            return (Word) array[currentPos].element;    
	        }
	        
	        public void remove() {
	            if (expectedModCount != modCount)
	                throw new ConcurrentModificationException();    
	            
	            if (currentPos == -1 || !isActive(array, currentPos))
	                throw new IllegalStateException();
	    
	            array[currentPos].isActive = false;
	            visited--;
	            modCount++;
	            expectedModCount++;
	        }
	    }
}
