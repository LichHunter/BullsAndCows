package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int BULL = 0;
    private static int COW = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = generateNumber();
        String userGuess;
        do {
            userGuess = scanner.next();
        } while (!isCorrectInput(userGuess));

        compare(number, userGuess);

        if (BULL == 0 && COW == 0) {
            System.out.println("Grade: None");
        } else {
            System.out.format("Grade: %d bull(s) and %d cow(s)\n", BULL, COW);
        }
        System.out.println("The secret code is " + number);
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

    private static String generateNumber() {
        return String.valueOf(new Random().nextInt(8999) + 1000);
    }
}
