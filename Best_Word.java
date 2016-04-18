import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;


public class Best_Word {
	private ArrayList<String> wordList, candidates;
	private String onePt, twoPt, threePt, fourPt,
				fivePt, eightPt, tenPt;
	private Scanner sc;
	private String input;
	private HashMap<Character,Integer> letters;
	private int score;
	
	public static void main(String[] args) throws Exception{
		new Best_Word();
	}
	
	Best_Word() throws Exception{
		score = 0;	
		wordList = new ArrayList<String>();
		//Get legal words and store in wordList field.
		try{
			URL legalWords = new URL("http://www.ign.com/code-foo/2016/words.txt");
			wordList = new ArrayList<String>();
			sc = new Scanner(legalWords.openStream());	

			do{			
				wordList.add(sc.nextLine());
			}while(sc.hasNext());
		}
		catch(Exception e){
			//If Internet fails...
			InputStream is = getClass().getResourceAsStream("words.txt");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			System.out.println("reading...");
			String line = br.readLine();
			do{						
				wordList.add(line);
				line = br.readLine();
			}while(line != null);
		}
				
		
		onePt = "aeioulnstr";
		twoPt = "dg";
		threePt = "bcmp";
		fourPt = "fhvwy";
		fivePt = "k";
		eightPt = "jx";
		tenPt = "qz";	
		String input = null;
		//Loop for user queries.
		try{
			do{
				input = JOptionPane.showInputDialog(
						"Enter string of letters: ");
				letters = countCharFreq(input);		
				String bestWord = getBest();
				if(bestWord != null){
					//Build string of candidate words to
					//display to user.
					String[] s = candidates.toArray(new String[candidates.size()]);
					StringBuilder sb = new StringBuilder();
					for(String str: s){
						sb.append(str + "\n");
					}
					
					//Show words possible, best word, and score
					JOptionPane.showMessageDialog(null,
							"Words Possible:\n" 
							+sb.toString() 
							+"Highest scoring word possible: "
							+ bestWord + "\nScore: " + score);
				}
				
				else
					JOptionPane.showMessageDialog(null, "No words possible.");
			}while(input != null);
		}
		catch(Exception e){
			System.exit(0);
		}
	}
	
	
	/**
	 * Takes a String and returns a HashMap containing
	 * the frequency of the characters in the string
	 * as the values.
	 * @param s	String object.
	 * @return	HashMap containing frequency of characters.
	 */
	private HashMap<Character, Integer> countCharFreq(String s){
		HashMap<Character,Integer> freqs = new HashMap<Character, Integer>();
		for(int i  = 0; i < s.length(); i++){
			Integer val = freqs.get(s.charAt(i));
			if(val != null)
				freqs.put(s.charAt(i), val + 1);
			else
				freqs.put(s.charAt(i), 1);
		}
		return freqs;
	}
		
	
	/**
	 * This method will return the best word
	 * from a list of valid words.
	 */
	private String getBest(){
		candidates = new ArrayList<String>();
		for(int i = 0; i < wordList.size(); i++){
			String temp = wordList.get(i);						
			if(check(temp)){
				candidates.add(temp);
			}
		}
		String[] s = candidates.toArray(new String[candidates.size()]);
		return getHighestScoring(s);
	}
	
	
	/**
	 * This method will search through an array
	 * of valid words, scoring each, then returning
	 * the highest scoring valid word in the array.
	 * @param words	Array of valid words.
	 * @return	Highest scoring word.
	 */
	private String getHighestScoring(String[] words) {
		int indexOfBestWord = 0;		
		for(int i = 1; i < words.length; i++){
			if(score(words[indexOfBestWord]) < score(words[i])){
				indexOfBestWord = i;				
			}
		}
		if(indexOfBestWord < words.length){
			score = score(words[indexOfBestWord]);
			return words[indexOfBestWord];
		}			
		else
			return null;
	}
	
	/**
	 * This method takes a String representing
	 * a valid Scrabble word and returns a score
	 * based on the letter values defined above.
	 * @param s	Valid word to be scored.
	 * @return	Score of word.
	 */
	private int score(String s){
		int score = 0;
		for(int i = 0; i < s.length(); i++){
			char temp = s.charAt(i);
			if(onePt.indexOf(temp) >= 0)
				score += 1;
			else if(twoPt.indexOf(temp) >= 0)
				score += 2;
			else if(threePt.indexOf(temp) >= 0)
				score += 3;
			else if(fivePt.indexOf(temp) >= 0)
				score += 5;
			else if(eightPt.indexOf(temp) >= 0)
				score += 8;
			else
				score += 10;
		}
		return score;
	}


	/**
	 * This method counts checks the occurrence of
	 * each character in the given letters against
	 * the occurrence of that letter in each valid word.
	 * If the occurrence of each letter tested match those of
	 * the candidate word, the method returns true, indicating
	 * that the candidate word can be formed using the given 
	 * letters.
	 * @param s	Candidate word to be tested.
	 * @return	True if char occurrences match.
	 */
	private boolean check(String s){
		HashMap<Character, Integer> word = countCharFreq(s);
		Set<Character> keys = word.keySet();
		Character[] charsToTest = keys.toArray(new Character[keys.size()]);	
		
		for(int i = 0; i < charsToTest.length; i++){
			//Check if word contains char to test.
			if(!letters.containsKey(charsToTest[i])){
				return false;
			}
			//Check if word has same frequency of character.
			else if((int)word.get(charsToTest[i]) > (int)letters.get(charsToTest[i])){			
				return false;
			}
		}
		return true;	
	}
		

}
