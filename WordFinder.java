
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
This contains the main method. 
This class will have a main that's responsible for processing the command-line argument, and handling any error processing. 
It will probably also have the main command loop.
 Most of the other functionality will be delegated to other object(s) created in main and their methods.
*/
public class WordFinder implements Comparator<Map.Entry<String, Integer>>{
	private static AnagramDictionary Adict;
	private static Map<String, Integer> wordfind; // using map to store the list of rackscore
	private ArrayList<Map.Entry<String, Integer>> wordscore; // arraylist to sort them in decreasing order
	
    /*
    initialize the class
  */
	public WordFinder() {
		wordfind = new TreeMap<String, Integer>();
		wordscore = new ArrayList<Map.Entry<String, Integer>>();
	}
	public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) { //override the compare interface
	     return o2.getValue() - o1.getValue();
	}
	
	public static void main(String[] args) {
	    String filename = "";
	    // choose different dictionary according to the command-line argument
	    if(args.length < 1) {
	    	filename = "bin/sowpods.txt";
	    }else {
	    	filename = args[0];
	    }    
	    String s = "";
	    Scanner in = new Scanner(System.in);
	    System.out.println("Type . to quit.");
		try {
	          //filename = "bin/testFiles/tinyDictionary.txt";	
			  Adict = new AnagramDictionary(filename);
	          while(!s.equals(".")) {
	        	  System.out.println("Rack?");
	        	  s = in.next();
	        	  if(!s.equals(".")) {
	        	      Display(s);
	        	  }    
	          }
		  }catch(FileNotFoundException e) {
			  System.out.println("ERROR: Can not find the flie:" + filename);		  
	         // e.printStackTrace();
	      }
		 in.close();
	}
	// a method used to get all set of rack include the word that less then itself and display it by call sorted function
    public static void Display(String s) {
    	 Rack rack = new Rack();
    	 ArrayList<String> racklist = rack.rackset(s); 
    	 WordFinder wordfinder = new WordFinder();
    	 int count = 0;
    	 for(int i = 0; i < racklist.size();i++) {
    		 for(int j = 0; j < Adict.getAnagramsOf(racklist.get(i)).size(); j++) {
    			 String key = Adict.getAnagramsOf(racklist.get(i)).get(j);				
    	         if (!wordfind.containsKey(key)) {
    	        	 ScoreTable rackscore = new ScoreTable(key);
    	        	 wordfind.put(key, rackscore.Score());;
    	         }			 
    			 count++;
    		 }
          }	
    	 System.out.println("We can make " + count + " words from \"" + s + "\"");
    	 System.out.println("All of the words with their scores (sorted by score):");
    	 wordfinder.Sorted();
    }
    // a method sort the wordscore in decreasing order, store them in the data of ArrayList<Map.Entry<String, Integer>>() and print them out 
    public void Sorted() {
	      for (Map.Entry<String, Integer> curr : wordfind.entrySet()) {
	          wordscore.add(curr);
	       }
	      Collections.sort(wordscore, new WordFinder());
	      for(int i = 0; i< wordscore.size();i++) {
	    	  System.out.println(wordscore.get(i).getValue() + ": " + wordscore.get(i).getKey());
	      }	      
   }
    
}
