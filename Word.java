/* Binley Yang
 * CSC 172
 * Shakespeare
 * Email: byang8@u.rochester.edu
 * */

public class Word {
	
	private String word;
	private LinkedList lineNumbers;
	
	public Word (String word, LinkedList lineNumbers) {
		this.word = word;
		this.lineNumbers = lineNumbers;
	}
	
	public String toString() {
		return word;
	}
	
	public String getContent() {
		return word;
	}
	
	public String getLineNumbers() {
		return lineNumbers.toString();
	}
	
	public LinkedList getLines() {
		return lineNumbers;
	}
	
	public void addLineNumbers(int line) {
		lineNumbers.insert(line);
	}
	
	public void removeNonAlphaNum() {
		word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	}
}
