// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2019

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
    private Map<String, ArrayList<String>> anagram;// we create a map data structure to store the anagram dictionary
   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
    public AnagramDictionary(String fileName) throws FileNotFoundException {
    	anagram = new HashMap<String, ArrayList<String>>();
    	File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()) {
        	String strOri = sc.next();// we read the string line by line
        	ArrayList<String> list = new ArrayList<String>(); // create arraylist to store the string with same char
        	char[]arr = strOri.toCharArray();
        	Arrays.sort(arr);// sort the original string in alphabetic order
        	String strSort = String.valueOf(arr);
        	if(!anagram.containsKey(strSort)) {
        		list.add(strOri);
        		anagram.put(strSort, list);
        	}else {
        		anagram.get(strSort).add(strOri);
        	}
        	
        }
        sc.close();
    }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
    public ArrayList<String> getAnagramsOf(String s) {
    	char[]arr = s.toCharArray();
    	Arrays.sort(arr);
	    String strSort = String.valueOf(arr);
	    if(anagram.containsKey(strSort)) {
	    	return anagram.get(strSort); // get all the String Arraylist of same character
	    }
        return new ArrayList<String>();
    }
     
 }
