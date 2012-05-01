package dict;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class DictionaryTable {

	// Initialize variables
	private Hashtable<String, Integer> dictionaryHashTable = new Hashtable<String, Integer>();
	private EnglishScrabbleScorer wordScorer = new EnglishScrabbleScorer();

	@SuppressWarnings("boxing")
	public DictionaryTable() {

		// Open dictionary file
		Scanner readFile = null;
		File dictionary = new File("TWL06.txt");

		// Create scanner to read file. Catch exception if file not found.
		try {
			readFile = new Scanner(dictionary);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Iterate through file, adding each word as key and giving it a
		// non-null value
		while (readFile.hasNext()) {
			dictionaryHashTable.put(readFile.nextLine(), 1);
		}
	}

	// Search Dictionary Table for input word
	public boolean containsWord(String word) {
		return dictionaryHashTable.containsKey(word.toUpperCase());
	}

	public String getBestWordIn(int maxLen, String letters) {
		// Vector to store all high-scoring words of every length
		Vector<String> goodWordCollection = new Vector<String>();

		// initialise variables
		int minVal = 0, currentScore = 0;
		String result = "";

		// iterate from 0 to max length, calling the getAllGoodWordsOfLen() method
		// each time and adding those results to a Vector of Strings
		for (int len = 0; len <= maxLen; len++) {
			goodWordCollection.addAll(getAllWordsOfLength(len, minVal, letters));

		}

		// looks through collection for best string word
		for (int i = 0; i < goodWordCollection.size(); i++) {
			if (wordScorer.getScore(goodWordCollection.elementAt(i)) > currentScore) {
				result = goodWordCollection.elementAt(i);
				minVal = wordScorer.getScore(goodWordCollection.elementAt(i));
			}
		}

		// return the best scoring word
		return result;
	}

	// wrapper method
	public Vector<String> getAllWordsOfLength(int len, int minVal, String in) {
		StringBuffer letters = new StringBuffer();
		
		for (int i = 0; i < in.length(); i++) {
			letters.append(in.charAt(i));
		}
		
		StringBuffer word = new StringBuffer();
		Vector<String> results = new Vector<String>();
		getAllWordsOfLength(len, minVal, letters, word, results);
		return results;
	}

	@SuppressWarnings("boxing")
	public Vector<String> getAllWordsOfLength(int len, int minVal, StringBuffer letters, StringBuffer word,
			Vector<String> results) {
		if (word.length() != len) {
			for (int i = 0; i < letters.length(); i++) {
				Character c = letters.charAt(i);
				word.append(c);
				letters.deleteCharAt(i);
				getAllWordsOfLength(len, minVal, letters, word, results);
				letters.insert(i, c);
				word.deleteCharAt(word.length() - 1);
			}
		} else {
			String checkWord = word.toString();

			// Add word to results if found in dictionary, not already in
			// results and score > minVal
			if (containsWord(checkWord) && !results.contains(checkWord) && wordScorer.getScore(checkWord) > minVal) {
				results.add(checkWord);
				minVal = wordScorer.getScore(checkWord);
			}
		}
		return results;
	}
}
