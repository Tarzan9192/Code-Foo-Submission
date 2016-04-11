import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Best_Word {
	ArrayList<String> wordList;
	Scanner sc;
	String onePt, twoPt, threePt, fourPt,
				fivePt, eightPt, tenPt;
	HashMap letters, wordToCheck;
	
	public static void main(String[] args) throws Exception{
		new Best_Word();
	}
	
	Best_Word() throws Exception{
		//Get legal words and store in wordList field.
		URL legalWords = new URL("http://www.ign.com/code-foo/2016/words.txt");
		wordList = new ArrayList<String>();
		sc = new Scanner(legalWords.openStream());	

		do{			
			wordList.add(sc.nextLine());
		}while(sc.hasNext());
		
		onePt = "aeioulnstr";
		twoPt = "dg";
		threePt = "bcmp";
		fourPt = "fhvwy";
		fivePt = "k";
		eightPt = "jx";
		tenPt = "qz";	
		prompt();
		System.out.println();
		System.out.println("Highest scoring word possible: "
							+ getBest());
		
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
	 * This method will prompt user to enter
	 * text string.
	 */
	private void prompt(){
		System.out.print("Enter string of letters: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		letters = countCharFreq(input);		
	}
	
	/**
	 * This method will return the best word
	 * given a list of valid words.
	 */
	private String getBest(){
		ArrayList<String> candidates = new ArrayList<String>();
		for(int i = 0; i < wordList.size(); i++){
			String temp = wordList.get(i);						
			if(check(temp)){
				candidates.add(temp);
			}
		}
		String[] s = candidates.toArray(new String[candidates.size()]);
		System.out.println(s.length);
		for(String st: s)
			System.out.println(st);
//		for(int i = 0; i < temp.length; i++){
//			System.out.println(temp[i]);
//		}
		return getHighestScoring(s);
	}
	
	
	private String getHighestScoring(String[] words) {
//		for(int i = 0; i < words.length; i++){
//			System.out.println(words[i]);
//		}
		int indexOfBestWord = 0;
		for(int i = 1; i < words.length; i++){
			if(score(words[indexOfBestWord]) < score(words[i])){
				indexOfBestWord = i;
			}
		}
		return words[indexOfBestWord];
	}
	
	/**
	 * @param s
	 * @return
	 */
	private int score(String s){
		int score = 0;
		for(int i = 0; i < s.length(); i++){
			char temp = s.charAt(i);
			if(onePt.indexOf(temp) > 0)
				score += 1;
			else if(twoPt.indexOf(temp) > 0)
				score += 2;
			else if(threePt.indexOf(temp) > 0)
				score += 3;
			else if(fivePt.indexOf(temp) > 0)
				score += 5;
			else if(eightPt.indexOf(temp) > 0)
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
//		for(Character c: charsToTest)
//			System.out.print(c);
//		System.out.println();
		
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
