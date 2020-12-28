import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println(generatePassword());
	}

	private static String generatePassword() {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		StringBuilder builder;

		int uppercase = scanner.nextInt();
		int lowercase = scanner.nextInt();
		int digits = scanner.nextInt();
		int symbols = scanner.nextInt();

		char[] letters =
				{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
		char[] digitsArray = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		char[] chars =
				{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x'
						, 'c', 'v', 'b', 'n', 'm', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

		do {
			builder = new StringBuilder();
			int i = 0;
			int tmpUppercase = uppercase;
			int tmpLowercase = lowercase;
			int tmpDigits = digits;

			while (i < symbols) {
				int generated = random.nextInt(3);

				if (generated == 0 && tmpUppercase > 0) {
					builder.append(Character.toUpperCase(letters[random.nextInt(letters.length)]));
					tmpUppercase--;
					i++;
				} else if (generated == 1 && tmpLowercase > 0) {
					builder.append(letters[random.nextInt(letters.length)]);
					tmpLowercase--;
					i++;
				} else if (generated == 2 && tmpDigits > 0) {
					builder.append(digitsArray[random.nextInt(digitsArray.length)]);
					tmpDigits--;
					i++;
				} else if (tmpUppercase == 0 && tmpLowercase == 0 && tmpDigits == 0) {
					builder.append(chars[random.nextInt(chars.length)]);
					i++;
				}
			}
		} while (!checkPassword(builder.toString().toCharArray(), uppercase, lowercase, digits));

		return builder.toString();
	}

	private static boolean checkPassword(char[] array, int uppercase, int lowercase, int digits) {
		int uppercaseCounter = 0;
		int lowercaseCounter = 0;
		int digitsCounter = 0;

		for (char c : array) {
			if (Character.isUpperCase(c)) {
				uppercaseCounter++;
			} else if (Character.isLowerCase(c)) {
				lowercaseCounter++;
			} else {
				digitsCounter++;
			}
		}

		for (int i = 0; i < array.length; i++) {
			if ((i != 0 && i != array.length - 1) && array[i] == array[i - 1]) return false;
		}

		return uppercase <= uppercaseCounter && lowercase <= lowercaseCounter && digits <= digitsCounter;
	}
}