import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * @author Neale Donovan
 * 
 * Takes in an array of text files, parses through the files sentence by sentence 
 * and word by word to determine the most frequently used words across the array
 * of text files.
 * 
 * Outputs an HTML file containing a table of each word used across all documents 
 * sorted by the word's frequency. Each table row contains the word, the documents
 * the word is found in, and each sentence that the word was found.
 * 
 */

public class Parser {
	private String wordDelimiter;
	private String sentenceDelimiter;
	private ListOfWords wordList;
	
	/*
	 * Default constructor for the parser
	 * wordDelimiter accounts for one or none of the following symbols so the same word isn't presented twice
	 * sentenceDelimiter accounts for exactly one end mark of a sentence
	 */
	public Parser() {
		wordDelimiter = "[,;:\"]?\\s+[\"]?";
		sentenceDelimiter = "[.!?]\\s+";
		wordList = new ListOfWords();
	}

	public ListOfWords getListOfWords() {
		return wordList;
	}
	
	/*
	 * Parses through an array of text files
	 */
	public void parseAllFiles(File[] listOfFiles) throws FileNotFoundException {
		for(File currentFile : listOfFiles) {
			parseText(currentFile);
		}
	}
	/*
	 * Parses through a given text file and passes sentences to be parsed
	 */
	private void parseText(File document) throws FileNotFoundException {
		Scanner documentScanner = new Scanner(document);
		documentScanner.useDelimiter(sentenceDelimiter);
		while(documentScanner.hasNext()) {
			parseSentence(documentScanner.next(), document.getName());
		}
		documentScanner.close();
	}
	/*
	 * Parses a sentence into words and adds them to wordList
	 */
	private void parseSentence(String sentence, String docName) {
		Scanner sentenceScanner = new Scanner(sentence);
		sentenceScanner.useDelimiter(wordDelimiter);
		while(sentenceScanner.hasNext()) {
			wordList.addWord(sentenceScanner.next(), docName, sentence);
		}
		sentenceScanner.close();
	}
	
	/*
	 * Writes sorted list to an HTML file
	 */
	public void writeToHTML() throws IOException {
		FileWriter fWriter = new FileWriter("src\\MostCommonlyUsedWords.html");
		BufferedWriter bWriter = new BufferedWriter(fWriter);
	    bWriter.write(getListOfWords().toHTML());
	    bWriter.close();
	    fWriter.close();
	}
	
	
	
	public static void main(String[] args) throws IOException {
		File doc1 = new File("src\\doc1.txt");
		File doc2 = new File("src\\doc2.txt");
		File doc3 = new File("src\\doc3.txt");
		File doc4 = new File("src\\doc4.txt");
		File doc5 = new File("src\\doc5.txt");
		File doc6 = new File("src\\doc6.txt");
		File[] allDocs = {doc1, doc2, doc3, doc4, doc5, doc6};
		Parser docParser = new Parser();
		docParser.parseAllFiles(allDocs);
		docParser.writeToHTML();
	}
}
