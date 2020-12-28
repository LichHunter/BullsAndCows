package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length: ");
        String number = generateNumber(scanner.nextInt());
        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            int bull = 0;
            int cow = 0;

            System.out.println("Turn " + turn);
            String userGuess = scanner.next();

            for (int i = 0; i < number.length(); i++) {
                for (int j = 0; j < userGuess.length(); j++) {
                    if (number.charAt(i) == userGuess.charAt(j)) {
                        if (i == j) {
                            bull++;
                        } else {
                            cow++;
                        }
                    }
                }
            }

            if (number.equals(userGuess)) {
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

    private static String generateNumber(int length) {
        if (length > 10) {
            System.out.format("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
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
