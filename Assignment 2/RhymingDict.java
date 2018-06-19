import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RhymingDict {

	// Given a pronunciation, get the rhyme group
	// get the more *heavily emphasized vowel* and following syllables
	// For "tomato", this is "-ato", and not "-omato", or "-o"
	// Tomato shares a rhyming group with "potato", but not "grow"
	private static String getRhymeGroup(String line) {

		int firstSpace = line.indexOf(" ");

		String pronunciation = line.substring(firstSpace + 1, line.length());

		int stress0 = pronunciation.indexOf("0");
		int stress1 = pronunciation.indexOf("1");
		int stress2 = pronunciation.indexOf("2");

		if (stress2 >= 0)
			return pronunciation.substring(stress2 - 2, pronunciation.length());
		if (stress1 >= 0)
			return pronunciation.substring(stress1 - 2, pronunciation.length());
		if (stress0 >= 0)
			return pronunciation.substring(stress0 - 2, pronunciation.length());

		// No vowels at all? ("hmmm", "mmm", "shh")
		return pronunciation;
	}

	private static String getWord(String line) {
		int firstSpace = line.indexOf(" ");

		String word = line.substring(0, firstSpace);

		return word;
	}

	// Load the dictionary
	private static String[] loadDictionary() {
		// Load the file and read it

		String[] lines = null; // Array we'll return holding all the lines of
								// the dictionary

		try {
			String path = "cmudict/cmudict-short.dict";
			// Creating an array of strings, one for each line in the file
			lines = new String(Files.readAllBytes(Paths.get(path))).split("\\r?\\n");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return lines;
	}

	public static void main(String []args) {


		String[] dictionaryLines = loadDictionary();

		/* This code is in here to help you test MyLinkedList without having to mess around with the dictionary. 
		   Feel free to change this test code as you're testing your linked list. But be sure to comment this code
		   out when you submit it.
		*/
		/*MyLinkedList testList = new MyLinkedList(); 
		testList.add(0, "hello");
		testList.add(1, "world");
		testList.add(2, "!");
		System.out.println(testList);
		System.out.println("index 2 = " + testList.get(2));
		System.out.println("world at index " + testList.find("world"));
		System.out.println("hello at index " + testList.find("hello"));
		System.out.println("! at index " + testList.find("!"));
		System.out.println("wow at index " + testList.find("wow"));
		testList.remove(2);
		System.out.println(testList);
		testList.remove(0);
		System.out.println(testList);
		testList.remove(0);
		System.out.println(testList);
		System.out.println("hello at index " + testList.find("hello"));*/


		// List of rhyme groups. The items in this linked list will be RhymeGroupWords. 
		ListInterface rhymeGroups = new MyLinkedList();

		/* TODO: Add in your code to load the dictionary into your linked lists. Remember that rhymeGroups is a 
		   list of RhymeGroupWords. Inside each of this objects is another linked list which is a list of words within the same
		   rhyme group. I would recommend first getting this working with MyLinkedList for both lists (rhyme groups and 
		   word lists) then get it working using MySortedLinkedList for the word groups. */
		//Linked list to store unique rhyme groups
		//ListInterface oldRhymeGroup = new MyLinkedList();
		//int k = 0;
		
		int index = 0;
		//iterate through dictionary
		for(int i = 0; i < dictionaryLines.length; i++){
			String word = getWord(dictionaryLines[i]);
			String rg = getRhymeGroup(dictionaryLines[i]);
			if(rhymeGroups.size() == 0){
				ListInterface wordList = new MyLinkedList();
				RhymeGroupWords obj = new RhymeGroupWords(rg, wordList);
				obj.getWordList().add(0, word);
				
				rhymeGroups.add(0, obj);
				index++;
				
				
				continue;
			}
			int size = 0;
			
			//iterate through rhymeGroups linked list to see if current rhyme group is new 
			Boolean done = false;
			for(int j = 0; j < rhymeGroups.size();  j++){
				
				RhymeGroupWords rgw = (RhymeGroupWords) rhymeGroups.get(j);
				
				ListInterface wl = rgw.getWordList();
				//compare rg
				
				if(rgw.getRhymeGroup().equals(rg)){
					wl.add(0, word);
					index++;
					break;
					
					
				}		
				//Signal that loop is DONE
				if((j+ 1) == rhymeGroups.size()){
					done = true;
				}
				
									
				}
			if(done == true){
				ListInterface wordList = new MyLinkedList();
				RhymeGroupWords obj = new RhymeGroupWords(rg, wordList);
				(obj.getWordList()).add(index, word);
				rhymeGroups.add(0, obj);
				index++;
				done = false;
			}
			continue;
				
				
		}
		


	/* End TODO for adding dictionary in rhymeGroups. */

	// This code prints out the rhyme groups that have been loaded above.

	for(

	int i = 0;i<rhymeGroups.size();i++)
	{
		RhymeGroupWords rg = (RhymeGroupWords) rhymeGroups.get(i);
		System.out.print(rg.getRhymeGroup() + ": ");
		System.out.println(rg.getWordList());
	}

	/*
	 * TODO: Add the code here to iterate through pairs of arguments, testing to
	 * see if they are in the same rhyme group or not.
	 */

	for(int i = 0;i<args.length;i+=2){
		if (args[i + 1] == null) {
			break;
		} 
		else {
			System.out.print(args[i] + " and " + args[i + 1]);
			String rg1= "";
			String rg2 = "";
			for (int j = 0; j < (rhymeGroups).size(); j++) {
				RhymeGroupWords temp = (RhymeGroupWords) rhymeGroups.get(j);
				for(int k = 0; k < temp.getWordList().size(); k++){
					String test = (String) temp.getWordList().get(k);
					if(args[i].equals(test)){
						rg1 = temp.getRhymeGroup();
					}
					else if(args[i + 1].equals(test)){
						rg2 = temp.getRhymeGroup();
					}
				}
				
			}
			if(rg1.equals(rg2)){
				System.out.println(" rhyme");
			}
			else{
				System.out.println(" don't rhyme");
			}
		}
	}

}
}