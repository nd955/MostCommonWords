import java.util.ArrayList;

public class OccuringWord implements Comparable<OccuringWord> {
	private String word;
	private ArrayList<String> docs;
	private ArrayList<String> sentences;
	private int count;
	
	/*
	 * Initializes with the word
	 * Then initializes the document, the containing sentence, and sets count to one
	 */
	public OccuringWord(String word, String docName, String containingSentence) {
		this.word = word;
		docs = new ArrayList<String>();
		docs.add(docName);
		sentences = new ArrayList<String>();
		sentences.add(containingSentence);
		count = 1;
	}
	
	public String getWord() {
		return word;
	}
	public ArrayList<String> getDocName() {
		return docs;
	}
	public ArrayList<String> getSentences() {
		return sentences;
	}
	public int getCount() {
		return count;
	}
	
	/*
	 * Adds the document to the list of documents
	 */
	public void addDoc(String docName) {
		docs.add(docName);
	}
	/*
	 * Adds the sentence to the list of sentences
	 */
	public void addSentence(String containingSentence) {
		sentences.add(containingSentence);
	}
	/*
	 * Increments the word count
	 */
	public void incrementCount() {
		count++;
	}
	
	/*
	 * Outputs the table data for each row in the HTML table
	 */
	public String toHTML() {
		return ("<td>" + word + "</td>\n<td>" + docs.toString() + "</td>\n<td>" + organizeSentences() + "</td>");
	}
	/*
	 * Makes the sentences more spaced out and readable
	 */
	private String organizeSentences() {
		String result = "";
		for(String currentSentence : sentences) {
			result += ("<div>" + currentSentence + "</div>\n");
		}
		return result;
	}

	
	/*
	 * Compares based on the count of the word, with higher count getting higher precedence
	 */
	@Override
	public int compareTo(OccuringWord otherWord) {
		if(count > otherWord.getCount()) 
			return -1;
		else if(count < otherWord.getCount())
			return 1;
		else
			return 0;
	}
}
