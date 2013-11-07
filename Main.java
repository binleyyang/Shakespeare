/* Binley Yang
 * CSC 172
 * Shakespeare
 * Email: byang8@u.rochester.edu
 * */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static String sonnet = "sonnets.txt";
	static String control = "control.txt";
	static String dict = "dict.txt";
	private static HashTable sonnetTable;
	private static HashTable controlTable;
	
	public static <E extends Comparable<E>> void main (String[] args) throws IOException {
	
		Scanner scan = new Scanner(System.in);
		Main init = new Main();
		sonnetTable = init.buildConcordance(sonnet);
		controlTable = init.buildConcordance(control);
		
		calculateRatio(sonnetTable, controlTable);
		
		System.out.println("\nEnter the word you want to search for, enter 'escape' to exit.");
		while (scan.hasNext()) {
			String findWord = scan.next();
			if (findWord.equals("escape"))
				break;
			sonnetTable.searchConc(findWord);
		}
		
		/*----------------------------------------------------------------------------------------------------------------*/
		System.out.println("Sorting sonnets and merging them with the lexicon and writing to 'output.txt', please wait: ");
		
		List<E> dictionary = makeListFromTxt(dict);
		List<E> sonnets = makeListFromTxt(sonnet);
		//System.out.println(dictionary.toString());
		//System.out.println(sonnets.toString());
		
		mergesort(sonnets);
		
		merge(sonnets, dictionary);
	}
	
	@SuppressWarnings("resource")
	public HashTable buildConcordance(String str) throws IOException {
		LineNumberReader reader = new LineNumberReader(new FileReader(str));
		String line;
		HashTable table = new HashTable();
		
		while ((line = reader.readLine()) != null) {
			Scanner scan = new Scanner(line);
			while (scan.hasNext()) {
				LinkedList list = new LinkedList();
				list.insert(reader.getLineNumber());
				table.add(new Word(scan.next(), list));
			}
		}
		System.out.println("There are " + table.size() + " many different words in the " + str + " file.");
		return table;
	}
	
	public static void calculateRatio (HashTable one, HashTable two) {
		
		double ratio = (one.size()/one.total())/(two.size()/two.total());

		if (ratio > 1.0)
			System.out.println("The hypothesis is correct as ratio of (SD/SS)/(ED/ES) is " + ratio + " > 1");
		else 
			System.out.println("The hypothesis is incorrect as ratio of (SD/SS)/(ED/ES) is " + ratio + " < 1");
	}
	
	@SuppressWarnings("resource")
	public static <E extends Comparable<E>> void merge(List<E> sonnet, List<E> dict) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		
		ArrayList<E> newList = new ArrayList<E>(sonnet);
		newList.removeAll(dict);
		
		ArrayList<E> noDuplicate = new ArrayList<E>();
		Iterator<E> dupItr = newList.iterator();
		while(dupItr.hasNext()) {
			E word = dupItr.next();
			if (noDuplicate.contains(word) || (((String) word).matches("[ivxlcdm]+")))
				dupItr.remove();
			else
				noDuplicate.add(word);
		}
		
		for (E word : noDuplicate)  {

			bw.write((String) word);
			bw.newLine();
			bw.flush();
			System.out.println(word);
		}
		bw.close();
	}
	
	public static <E extends Comparable<E>> void mergesort(List<E> list) throws IOException {
		if (list.size() > 1) {
			List<E> temp = list.subList(0, list.size()/2);
			
			ArrayList<E> left = new ArrayList<E>(0);
			for (E e : temp)
				left.add(e);
			
			temp = list.subList(list.size()/2, list.size());
			
			ArrayList<E> right = new ArrayList<E>(0);
			for (E e : temp)
				right.add(e);
			
			if (right.size() != 1) 
				mergesort(right);
			if (left.size() != 1)
				mergesort(left);
			
			list.clear();
			list.addAll(mergeSortedLists(left, right));
		}
	}
	
	public static <E extends Comparable<E>> List<E> mergeSortedLists(List<E> leftList, List<E> rightList) throws IOException {
		ArrayList<E> list = new ArrayList<E>();
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		
		while (!leftList.isEmpty() && !rightList.isEmpty()) {
			if ((leftList.get(0)).compareTo(rightList.get(0)) <= 0) {
				
				list.add(leftList.remove(0));
			}
			else {
				list.add(rightList.remove(0));
			}
		}
		 
		while (!leftList.isEmpty()) {
			list.add(leftList.remove(0));
		}
		
		while (!rightList.isEmpty()) {
			list.add(rightList.remove(0));
		}
		
		return list;
	}
	
	@SuppressWarnings({ "resource", "unchecked" })
	public static <E extends Comparable<E>> List<E> makeListFromTxt(String f) throws IOException {
		LineNumberReader read = new LineNumberReader(new FileReader(f));
		List<E> newList = new ArrayList<E>();
		String currentLine;
		
		while((currentLine = read.readLine()) != null) {
			String[] array = currentLine.split("\\s+");
			for (String s : array) {
				s = s.replaceAll("[^A-Za-z0-9']", "").toLowerCase();
				//s = s.replaceAll("\\-\\-", "");
				newList.add((E) s);
			}
		}
				
		return newList;
	}
}
