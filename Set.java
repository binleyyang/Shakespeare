/* Binley Yang
 * CSC 172
 * Shakespeare
 * Email: byang8@u.rochester.edu
 * */


@SuppressWarnings("hiding")
public interface Set<Word> extends Iterable<Word> {
	
	public boolean add(Word x);
	public boolean remove(Word x);
	public boolean contains(Word x);
	public void printHT(HashTable ht);
	public void searchConc(String s);
	public void clear();
	public double size();
	public int total();
}
