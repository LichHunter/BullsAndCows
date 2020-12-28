package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int BULL = 0;
    private static int COW = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = generateNumber(scanner.nextInt());
        System.out.println("The random secret number is " + number);
//        String userGuess;
//        do {
//            userGuess = scanner.next();
//        } while (!isCorrectInput(userGuess));
//
//        compare(number, userGuess);
//
//        if (BULL == 0 && COW == 0) {
//            System.out.println("Grade: None");
//        } else {
//            System.out.format("Grade: %d bull(s) and %d cow(s)\n", BULL, COW);
//        }
//        System.out.println("The secret code is " + number);
    }

    private static void compare(String number, String userGuess) {
        for (int i = 0; i < number.length(); i++) {
            for (int j = 0; j < userGuess.length(); j++) {
                if (number.charAt(i) == userGuess.charAt(j)) {
                    if (i == j) {
                        BULL++;
                    } else {
                        COW++;
                    }
                }
            }
        }
    }

    private static boolean isCorrectInput(String num) {
        return Integer.parseInt(num) >= 1000 && Integer.parseInt(num) <= 9999;
    }

    private static String generateNumber(int length) {
        if (length > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            Random random = new Random();

            while (builder.length() != length) {
                if (builder.length() != 0) {
                    int num;
                    do {
                        num = random.nextInt(10);
                    } while (digitIsInArray(builder.toString().toCharArray(), num));

                    builder.append(num);
                } else {
                    int num;
                    do {
                        num = random.nextInt(10);
                    } while (num == 0);

                    builder.append(num);
                }
            }

            return builder.toString();
        }
    }

    private static boolean digitIsInArray(char[] digits, int num) {
        for (char digit : digits) {
            if (String.valueOf(digit).equals(String.valueOf(num))) return true;
        }

        return false;
    }
}
