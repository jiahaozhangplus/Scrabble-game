/**
   A class that have all the value from 'a' to 'Z'
 */
public class ScoreTable {
	// using a hard-coded numscore to store the score in alphabetic order
	private final static int[] numscore = new int[ ]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private String str;
	public ScoreTable(String s) {
    	str = s.toLowerCase();
    }
	public int Score() { //the function that calculate the score of given string
		char[]arr = str.toCharArray();
		int sum = 0;
		for(int i = 0;i < arr.length;i++) {
			sum += numscore[arr[i]- 'a'];
		}
		return sum;
	}
}
