package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int codeLength = 0;
		int rangeOfCharacters = 0;

		try {
			System.out.println("Please, enter the secret code's length: ");
			codeLength = scanner.nextInt();
			System.out.println("Please, enter the number of possible symbols in the code: ");
			rangeOfCharacters = scanner.nextInt();

			if (codeLength > rangeOfCharacters) {
				System.out.format("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n"
						, codeLength, rangeOfCharacters);
				System.exit(1);
			} else if (codeLength == 0 || rangeOfCharacters == 0) {
				System.out.println("Error: code length and number of possible symbols must be greater than 0");
				System.exit(1);
			} else if (rangeOfCharacters > 36) {
				System.out.format("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", rangeOfCharacters);
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getCause() + "isn't a valid number");
			System.exit(1);
		}

		char[] charLibrary = createLibrary(rangeOfCharacters);
		String code = generateCode(codeLength, charLibrary);

		System.out.print("The secret code is prepared: ");
		for (char c : code.toCharArray()) {
			System.out.print("*");
		}
		System.out.print("(0-");
		if (rangeOfCharacters < 10) {
			System.out.print(charLibrary[charLibrary.length - 1] + ").");
		} else if (rangeOfCharacters == 10) {
			System.out.print("9).");
		} else {
			System.out.print("9, a-" + charLibrary[charLibrary.length - 1] + ").");
		}
		System.out.println();
		System.out.println("Okay, let's start a game!");

		int turn = 1;
		while (true) {
			int bull = 0;
			int cow = 0;

			System.out.println("Turn " + turn);
			String userGuess = scanner.next();

			if (userGuess.length() > code.length() || userGuess.length() < code.length()) {
				System.out.println("Error: answer can't be shorter or bigger than code.");
				System.exit(1);
			}

			for (int i = 0; i < code.length(); i++) {
				for (int j = 0; j < userGuess.length(); j++) {
					if (code.charAt(i) == userGuess.charAt(j)) {
						if (i == j) {
							bull++;
						} else {
							cow++;
						}
					}
				}
			}

			if (code.equals(userGuess)) {
				System.out.format("Grade: %d bull(s) and %d cow(s)\n", bull, cow);
				System.out.println("Congratulations! You guessed the secret code.");
				break;
			} else if (bull == 0 && cow == 0) {
				System.out.println("Grade: None");
			} else {
				System.out.format("Grade: %d bull(s) and %d cow(s)\n", bull, cow);
			}

			turn++;
		}
	}

	private static String generateCode(int length, char[] chars) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();

		while (builder.length() != length) {
			char c;
			do {
				c = chars[random.nextInt(chars.length)];
			} while (isCharInCode(builder.toString().toCharArray(), c));

			builder.append(c);
		}

		return builder.toString();
	}

	private static boolean isCharInCode(char[] chars, char c) {
		for (char ch : chars) {
			if (ch == c) return true;
		}

		return false;
	}

	private static char[] createLibrary(int size) {
		char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't',
				'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
		Arrays.sort(chars);
		char[] result = new char[size];
		if (size >= 0) System.arraycopy(chars, 0, result, 0, size);

		return result;
	}
}
