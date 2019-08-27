// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2019

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   
    private Map<Character, Integer> letternum; // using map to storage the number of unique character

    /*
    initialize the class
  */
    public Rack() {
    	letternum = new HashMap<Character, Integer>();
    }
    /*
    A Rack public method to call the recursion function to store all the subset from rack
  */
	public ArrayList<String> rackset(String rack){
		String unique ="";
		int[] mult = new int[26] ;
		int k = 0;
		
    	char[]arr = rack.toCharArray();
    	Arrays.sort(arr);
        for(int i = 0; i< arr.length; i++) {
        	if(!letternum.containsKey(arr[i])) {
        		letternum.put(arr[i], 1);
        	}else {
        		letternum.put(arr[i], letternum.get(arr[i])+1);
        	}
        }
        Iterator<Map.Entry<Character, Integer>> iter = letternum.entrySet().iterator(); // using iterator to store the value to unique and array mult
        int count = 0;
        while(iter.hasNext()) {
        	Map.Entry<Character, Integer> curr = iter.next();        	
        	unique = unique + curr.getKey();
        	mult[count] = curr.getValue();
        	count++;
        }
		return allSubsets(unique, mult,k);
	}
	   /**
    Finds all subsets of the multiset starting at position k in unique and mult.
    unique and mult describe a multiset such that mult[i] is the multiplicity of the char
         unique.charAt(i).
    PRE: mult.length must be at least as big as unique.length()
         0 <= k <= unique.length()
    @param unique a string of unique letters
    @param mult the multiplicity of each letter from unique.  
    @param k the smallest index of unique and mult to consider.
    @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
    each subset is represented as a String that can have repeated characters in it.
    @author Claire Bono
  */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
        ArrayList<String> allCombos = new ArrayList<>();
      
        if (k == unique.length()) {  // multiset is empty
            allCombos.add("");
            return allCombos;
        }
      
      // get all subsets of the multiset without the first unique char
       ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
       String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
       for (int n = 0; n <= mult[k]; n++) {   
          for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
             allCombos.add(firstPart + restCombos.get(i));  
          }
          firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
       }
      
       return allCombos;
   }
    
}
