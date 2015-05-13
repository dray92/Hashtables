import java.util.*;
public class ChainingHash {
		
	private int currentIndex;	// to keep track of the rows of the hashTable
    private myNode rowPointer;	// to keep track of the node(s) of each row of the hashTable
    private myNode[] hashTable;
    private final static int DEFAULT_SIZE = 100;
    
    // WORKING AS EXPECTED
    public ChainingHash() {
    	this(DEFAULT_SIZE); 
    }
		
    // WORKING AS EXPECTED
	public ChainingHash(int startSize) {
		hashTable = new myNode[startSize];
		this.currentIndex = -1;
		this.rowPointer = null;
	}

	/**
	 * This function allows rudimentary iteration through the ChainingHash.
	 * The ordering is not important so long as all added elements are returned only once.
	 * It should return null once it has gone through all elements
	 * @return Returns the next element of the hash table. Returns null if it is at its end.
	 */
	public String getNextKey() {
		if(rowPointer == null) {	// first case; iterate till you get to 
									// the first index; if you don't return null				
			for(int i = currentIndex+1 ; i < hashTable.length ; i++) {
				if(hashTable[i] != null) {
					rowPointer = hashTable[i];
					currentIndex = i;
					return rowPointer.key;
				} 
			}
			return null;
		} else {					// rowPointer has been pointing to something
									// expecting currentIndex to point to correct row
			if(rowPointer.next != null) {	// rowPointer has a next
				rowPointer = rowPointer.next;
				return rowPointer.key;
			} else {				// rowPointer does not have a next
				for(int i = currentIndex+1 ; i < hashTable.length ; i++) {
					if(hashTable[i] != null) {
						rowPointer = hashTable[i];
						currentIndex = i;
						return rowPointer.key;
					}
				}
				return null;
			}
		}
	}
    
	/**
	 * Adds the key to the hash table.
	 * If there is a collision, it should be dealt with by chaining the keys together.
	 * If the key is already in the hash table, it increments that key's counter.
	 * @param keyToAdd : the key which will be added to the hash table
	 */
	public void insert(String keyToAdd) {
		int index = hash(keyToAdd);
        myNode firstNode = hashTable[index];
       
        if(firstNode != null) {
        	// we have at least one node in this row
        	myNode cur = firstNode;
        	if(cur.key.equalsIgnoreCase(keyToAdd)) {
        		cur.incrementor();
        		return;
        	}
      	 
        	//we have to parse through this list of myNodes
        	while(cur.next != null) {
        		if(cur.key == keyToAdd) {
        			cur.incrementor();
        			return;
        		} else 
        			cur = cur.next;
        	}
      	 
        	cur.next = new myNode(keyToAdd);
      	 
        } else {
        	hashTable[index] = new myNode(keyToAdd);
        }
	}
    
	/**
	 * Returns the number of times a key has been added to the hash table.
	 * @param keyToFind : The key being searched for
	 * @return returns the number of times that key has been added.
	 */
	public int findCount(String keyToFind) {
		int index = hash(keyToFind);
		myNode firstNode = hashTable[index];
		
		// if firstNode == null, return 0
		if(firstNode == null) return 0;
		
		while(firstNode != null) {
			String key = firstNode.key;
			if(key.equalsIgnoreCase(keyToFind)) {
				return firstNode.count;
			}
			firstNode = firstNode.next;
		}
		return 0;
	}

	private int hash(String keyToHash) {
		int checkSum = 0;
		for(char ch: keyToHash.toCharArray()) {
			checkSum += ch;
		}
		return checkSum % hashTable.length;
	}
    
	public void printAll() {
		System.out.println(Arrays.toString(hashTable));
	}
    
	public int size() {
		return hashTable.length;
	}
	
	/**
	 * PRE: temporarily changes value of globals
	 * @return integer variable containing the number of words
	 */
	public int getTotalCount() {
		// storing global data in temp variables
		myNode currentPointer = rowPointer;
		int currentIndexVal = currentIndex;
		
		// setting global variables to default
		rowPointer = null;
		currentIndex = 0;
		
		int sum = 0;
		String key = getNextKey();
		while(key!=null) {
			System.out.println(key);
			sum += findCount(key);
			key = getNextKey();
		}
		// returning global variables to previous state
		rowPointer = currentPointer;
		currentIndex = currentIndexVal;
				
		return sum;
	}
	
	/**
	 * PRE: temporarily changes value of globals
	 * @return integer variable containing the number of unique words
	 */
	public int getTotalUniqueCount() {
		// storing global data in temp variables
		myNode currentPointer = rowPointer;
		int currentIndexVal = currentIndex;
		
		// setting global variables to default
		rowPointer = null;
		currentIndex = 0;
		
		int sum = 0;
		String key = getNextKey();
		while(key!=null) {
			sum += 1;
			key = getNextKey();
		}
		
		// returning global variables to previous state
		rowPointer = currentPointer;
		currentIndex = currentIndexVal;
		
		return sum;
	}
    
	private class myNode {
		private String key;
        private int count;
        private myNode next;
       
        public myNode(String key) {
        	this.key = key;
        	this.count = 1;
        	this.next = null;
        }
       
        public void incrementor() {
        	count++;
        }
    }
}
