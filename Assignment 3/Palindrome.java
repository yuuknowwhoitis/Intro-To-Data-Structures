import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary();

	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

	//return string that is reverse of the combined input strings
	public static String stackToReverseString(MyStack stack) {
		/*
		 * TODO 3
		 */
		MyStack temp = new MyStack();
		int i = 0;
		String x = "";
		while (stack.list.size != 0) {
			temp.push(stack.pop());
		}
		while (temp.list.size != 0) {
			String rem = "";
			rem = (String) temp.pop();
			x += rem + " ";
			stack.push(rem);
		}

		return x;

		/*
		 * TODO 3
		 */
	}

	//returns string that is reversed and only contains alphanumeric characters
	public static String reverseStringAndRemoveNonAlpha(String text) {
		/*
		 * TODO 4
		 */
		MyStack stack = new MyStack();
		String temp = "";
		for (int i = 0; i < text.length(); i++) {
			Character word = new Character(text.charAt(i));
			if (Character.isAlphabetic(word)) {
				stack.push(word);
			} else {
				continue;
			}
		}
		while (stack.list.size != 0) {
			temp += stack.pop();
		}

		return temp;

		/*
		 * TODO 4
		 */
	}

	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {
		/*
		 * TODO 5: Implement "explorePalindrome"
		 */

		text = text.toLowerCase();
		//make a stack and queue
		MyStack stack = new MyStack();
		MyQueue que = new MyQueue();
		boolean is = false;
		//adds values to stack and queue
		for (int i = 0; i < text.length(); i++) {
			Character c = new Character(text.charAt(i));
			if (Character.isAlphabetic(c)) {
				stack.push(c);
				que.enqueue(c);
			} else {
				continue;
			}
		}
		//compare one character at a time, if does not match then return false
		//if it makes through the loop, return true
		while (!stack.isEmpty()) {
			Character sTemp = (Character) stack.pop();
			Character qTemp = (Character) que.dequeue();
			if (sTemp.equals(qTemp)) {
				continue;
			} else {
				return false;
			}
		}
		return true;

		/*
		 * TODO 5: Implement "explorePalindrome"
		 */

	}

	//find out what endings would make text a palindrome
	public static void explorePalindrome(String text) {

		/*
		 * TODO 6: Implement "explorePalindrome" & helper function
		 */
		MyStack stack = new MyStack();
		String newText = text.toLowerCase();
		newText = reverseStringAndRemoveNonAlpha(newText);
		decomposeText(text, newText, 0, stack);

		/*
		 * TODO 6
		 */

	}

	//recursive method for explorePalindrome
	public static void decomposeText(String ogText, String textToDecomp, int index, MyStack decomp) {
		//base case if the index equals the length of string
		if (index == textToDecomp.length()) {
			System.out.println(ogText + ": " + stackToReverseString(decomp));
		} else {
			String[] words = getWords(textToDecomp, index);

			for (int i = 0; i < words.length; i++) {
				String word = words[i];

				int len = word.length();
				decomp.push(word);
				decomposeText(ogText, textToDecomp, index + len, decomp);
				decomp.pop();
			}

		}

	}

	// This function looks at the arguments that are passed
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = { "A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami" };
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}
			} else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}