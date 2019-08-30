import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListOfWords {
	private HashMap<String, OccuringWord> rows;
	
	/*
	 * Initializes the hashmap
	 */
	public ListOfWords() {
		rows = new HashMap<String, OccuringWord>();
	}
	
	/*
	 * Creates a lowercase version of newWord to avoid multiple entries of the same word in the table
	 * 
	 * Adds a word to the hashmap if it doesn't already exist in the map
	 * 
	 * Otherwise it adds the current document to that entry in the map (if necessary)
	 * and adds the sentence to that entry (again, if necessary)
	 * Finally, increments the word counter 		
	 */
	public void addWord(String newWord, String docName, String sentence) {
		String word = newWord.toLowerCase();
		if(rows.get(word) == null) {
			rows.put(word, new OccuringWord(word, docName, sentence));
		} else {
			if(!rows.get(word).getDocName().contains(docName))
				rows.get(word).addDoc(docName);
			if(!rows.get(word).getSentences().contains(sentence))
				rows.get(word).addSentence(sentence);	
			rows.get(word).incrementCount();
		}
	}
	
	/*
	 * Sorts the hashmap in order of frequency of the word, then ouputs HTML code to make a table with the data
	 */
	public String toHTML() {
		String result = "<!DOCTYPE html>\n<html>\n<head>\n<style>\ntable, th, td {border: 1px solid black;}\n</style>\n</head>\n<body>\n"
				+ "<table>\n<tr>\n<th>Word</th>\n<th>Documents</th>\n<th>Sentence containing the word</th>\n";
		List<OccuringWord> sortedRows = new ArrayList<OccuringWord>(rows.values());
		Collections.sort(sortedRows);
		for(OccuringWord word : sortedRows) {
			result += ("<tr>" + word.toHTML() + "</tr>\n");	
		}
		result += "</table>\n</body>\n</html>";
		return result;
	}
}
