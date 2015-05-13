public class QPHash {
	 
    private myNode[] hashTable;
    private int currentIndex;
    private int numberElements;
    private final static int DEFAULT_SIZE = 100;
    
    public QPHash() {
        this(DEFAULT_SIZE);
    }
    
    public QPHash(int startSize) {
        hashTable = new myNode[startSize];
        numberElements = 0;
        currentIndex = 0;
    }
    
    /**
    * This function allows rudimentary iteration through the QPHash.
    * The ordering is not important so long as all added elements are returned only once.
    * It should return null once it has gone through all elements
    * @return Returns the next element of the hash table. Returns null if it is at its end.
    */
    public String getNextKey() {
        //TODO returns the next key in the hash table.
        //You will need external tracking variables to account for this.
    	for(int i = currentIndex + 1 ; i < hashTable.length ; i++) {
    		if(hashTable[i] != null) {
    			currentIndex = i;
    			return hashTable[currentIndex].key;
    		}
    	}
    	return null;
    }
	    
    /**
    * Adds the key to the hash table.
    * If there is a collision, a new location should be found using quadratic probing.
    * If the key is already in the hash table, it increments that key's counter.
    * @param keyToAdd : the key which will be added to the hash table
    */
    public void insert(String keyToAdd) {
        //TODO Implement insert into the hash table.
        //If keyToAdd is already in the hash table, then increment its count.
	    
	    if(numberElements >= hashTable.length/2)
		    doubleHashTableSize();

	    int search = hash(keyToAdd);
	    
	    for(int i = 0 ; i < hashTable.length ; i++) {
		    int attemptedIndex = hashIndex(search, i);
		    if(hashTable[attemptedIndex] == null) {	// index is empty, we can add!
			    hashTable[attemptedIndex] = new myNode(keyToAdd);
			    numberElements++;
			    break;
		    } else {								// index is not empty, either it 
		    										// contains required key, or it doesn't
			    if(hashTable[attemptedIndex].key.equalsIgnoreCase(keyToAdd)) {
				    hashTable[attemptedIndex].count++;
				    break;
			    }
			    continue;
		    }
		    
	    }
    }
	    
    private void doubleHashTableSize() {
	    myNode[] newHashTable = new myNode[2 * hashTable.length];
	    
	    for(int i = 0 ; i < hashTable.length ; i++)
	    	newHashTable[i] = hashTable[i];
	    
	    hashTable = newHashTable;
    }
    
    /**
    * Returns the number of times a key has been added to the hash table.
    * @param keyToFind : The key being searched for
    * @return returns the number of times that key has been added.
    */
    public int findCount(String keyToFind){
        //TODO Implement findCount such that it returns the number of times keyToFind
        // has been added to the data structure.
    	int search = hash(keyToFind);
    	
    	for(int i = 0 ; i < hashTable.length ; i++) {
		    int attemptedIndex = hashIndex(search, i);
		    if(hashTable[attemptedIndex] == null)	// index is empty, we can add!
			    return 0;
			else {
				if(!hashTable[attemptedIndex].key.equalsIgnoreCase(keyToFind))
					continue;
				else
					return hashTable[attemptedIndex].count;
			}
    	}
    	return 0;
    }
    
    private int hashIndex(int search, int i) {
    	return (int)(search + Math.pow(i, 2)) % hashTable.length;
    }
    
    private int hash(String keyToHash) {
        int checkSum = 0;
        for(char ch: keyToHash.toCharArray()) {
            checkSum += ch;
        }
//        return keyToHash.length() % hashTable.length;
        return (int) ((Math.pow(checkSum,2) + keyToHash.length()) % hashTable.length);
    }
    
    /**
	 * PRE: temporarily changes value of globals
	 * @return integer variable containing the number of words
	 */
	public int getTotalCount() {
		// storing global data in temp variables
		int currentIndexVal = currentIndex;
		
		// setting global variables to default
		currentIndex = 0;
		
		int sum = 0;
		String key = getNextKey();
		while(key!=null) {
			sum += findCount(key);
			key = getNextKey();
		}
		// returning global variables to previous state
		currentIndex = currentIndexVal;
				
		return sum;
	}
    
    //My Node Class
    private class myNode {
        private String key;
        private int count;
        
        public myNode(String key) {
            this.key = key;
            this.count = 1;
        }
    }
	    
}
