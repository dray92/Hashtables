public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		//TODO Initialize the hash tables 
		ChainingHash hTable = new ChainingHash();
		QPHash qTable = new QPHash();
		//TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		String[] array = FileInput.readShakespeare();
	    for (int i = 0; i < array.length; i++) {
	    	hTable.insert(array[i]);
	    }
	    array = FileInput.readBacon();
	    for (int i = 0; i < array.length; i++) {
	    	qTable.insert(array[i]);
	    }
	    
	    double sum = 0.0d;
	    int chainTot = hTable.getTotalCount();
	    int quadTot = qTable.getTotalCount();
	    double mostDifferent = 0.0d, value = 0.0d;
	    String st = hTable.getNextKey();
	    System.out.println("Chain total: " + chainTot);
	    System.out.println("Quadratic total: " + quadTot);
	    System.out.println("courtier count: " + hTable.findCount("a"));
	    while(st!=null) {
	    	// check if st is there in the other one by 
	    	// calling it's findCount and checking if it's 0
	    	value = ( hTable.findCount(st)/chainTot - qTable.findCount(st)/quadTot )^2;
	    	sum += value;
	    	mostDifferent = Math.max(mostDifferent, value);
	    	st = hTable.getNextKey();
	    	
	    }
	    
	    st = qTable.getNextKey();
	    while(st!=null) {
	    	if(hTable.findCount(st) == 0) {
	    		value = ( hTable.findCount(st)/chainTot - qTable.findCount(st)/quadTot )^2;
		    	sum += value;
		    	mostDifferent = Math.max(mostDifferent, value);
	    	}
	    	st = qTable.getNextKey();
	    }
	    
	    System.out.println(sum);
	    System.out.println(mostDifferent);
	    
//	    // I think this is working decently
//	    hTable.printAll();
//	    
//	    // I think this is working decently
//	    System.out.println(hTable.getNextKey());
//	    System.out.println(hTable.getNextKey());
//	    System.out.println(hTable.getNextKey());
//	    System.out.println(hTable.getNextKey());
//	    System.out.println(hTable.getNextKey());
//
//	    System.out.println("Total number of words: " + hTable.getTotalCount());
//	    System.out.println("Total number of unique words: " + hTable.getTotalUniqueCount());
	    
		//TODO Initialize necessary variables for calculating the square error
		// and most distant word.
		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
	}

}
