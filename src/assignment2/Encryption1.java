package assignment2;

/**Encryption1 Class
* 
* Encrypts a single word according to the rules:
* Word is either all letters or all digits; no special characters.
* Letters are converted to lowercase.
* Single letter - next letter with wraparound (a-b, z-a).
* All digits - number + 2501.
* Even-length letters- swap every neighboring pair.
* Odd-length letters- swap first and last letters.
* 
* Copyright 2025 Howard Community College
* 
* @author Mario Pallares
* @version 1.0
*/

import java.util.Scanner;

public class Encryption1 {

	public static String encryptWord(String word) {
		if (word == null || word.isEmpty())
			return "";

		if (word.matches("\\d+")) {
			try {
				long val = Long.parseLong(word);
				return Long.toString(val + 2501);
			} catch (NumberFormatException nfe) {
				java.math.BigInteger bi = new java.math.BigInteger(word);
				return bi.add(java.math.BigInteger.valueOf(2501)).toString();
			}
		} else if (word.matches("[A-Za-z]+")) {
			String lower = word.toLowerCase();
			int len = lower.length();

			if (len == 1) {
				char c = lower.charAt(0);
				return (c == 'z') ? "a" : String.valueOf((char) (c + 1));
			}

			if (len % 2 == 0) {
				StringBuilder sb = new StringBuilder(len);
				for (int i = 0; i < len; i += 2) {
					char a = lower.charAt(i);
					char b = lower.charAt(i + 1);
					sb.append(b).append(a);
				}
				return sb.toString();
			} else {
				char[] chars = lower.toCharArray();
				char tmp = chars[0];
				chars[0] = chars[len - 1];
				chars[len - 1] = tmp;
				return new String(chars);
			}
		} else {
			throw new IllegalArgumentException("Invalid word: '" + word
					+ "'. Words must contain only letters OR only digits; no special characters.");
		}
	} // End of encryptWord method

	/**
	 * Main method: Encrypt each word from the line individually and concatenate all
	 * the encrypted words together to form the encrypted text.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				System.out.println("Enter text below:");
				String line = scanner.nextLine();

				StringBuilder result = new StringBuilder();
				if (line != null && !line.trim().isEmpty()) {
					String[] words = line.trim().split("\\s+");
					for (int i = 0; i < words.length; i++) {
						if (i > 0)
							result.append(' ');
						result.append(encryptWord(words[i]));
					}
				}

				System.out.println(result.toString());
				System.out.print("Would you like to encrypt more text (yes/no)? ");
				String again = scanner.nextLine().trim().toLowerCase();
				if (!(again.startsWith("y"))) {
					break;
				}
				System.out.println();
			}
		} finally {
			scanner.close();
		}
	} //End of main method
}//End of Encryption1 class
