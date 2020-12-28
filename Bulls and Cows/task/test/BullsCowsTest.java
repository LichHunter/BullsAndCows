import bullscows.Main;
import org.hyperskill.hstest.dynamic.input.DynamicTestingMethod;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BullsCowsTest extends StageTest<String> {

	private static void swap(Integer[] input, int a, int b) {
		int tmp = input[a];
		input[a] = input[b];
		input[b] = tmp;
	}

	// base test with 1 digit number
	@DynamicTestingMethod
	CheckResult test1() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();

		String output;
		int gotAnswer = 0;
		output = main.execute("1");

		for (int i = 0; i <= 9; i++) {
			if (main.isFinished()) {
				break; // if game has stopped, stop cycle and start check the results;
			}
			output = main.execute(Integer.toString(i));
			int[] result = getNumOfBullsAndCows(output);
			if (result[0] == 1) {
				gotAnswer++; // if got a bull, count for an amount of answers
			}
		}

		// if we got less or more than 1 answer, the program work is incorrect
		if (gotAnswer != 1) {
			return CheckResult.wrong("The game has no answer or more than one.");
		}

		return CheckResult.correct();
	}

	// standard bulls and cows game
	@DynamicTestingMethod
	CheckResult test2() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("4");

		Integer[] usedNums = getUsedNumbers(main, 4);
		boolean check = getPermutations(main, 4, usedNums);

		if (!check && main.isFinished()) {
			return CheckResult.wrong("The program has finished before the answer was found");
		}

		if (!check) {
			return CheckResult.wrong("The program tried all possible " +
					"combinations of digits and hasn't found the answer.");
		}

		if (!main.isFinished()) {
			return CheckResult.wrong("The program didn't finish after " +
					"the answer was found");
		}

		return CheckResult.correct();
	}

	// max length we can check
	@DynamicTestingMethod
	CheckResult test3() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("6");

		Integer[] usedNums = getUsedNumbers(main, 6);
		boolean check = getPermutations(main, 6, usedNums);

		if (!check && main.isFinished()) {
			return CheckResult.wrong("The program has finished before the answer was found");
		}

		if (!check) {
			return CheckResult.wrong("The program tried all possible " +
					"combinations of digits and hasn't found the answer.");
		}

		if (!main.isFinished()) {
			return CheckResult.wrong("The program didn't finish after " +
					"the answer was found");
		}

		return CheckResult.correct();
	}

	// length limit check
	@DynamicTestingMethod
	CheckResult test4() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("11");

		if (output.toLowerCase().contains("error")) {
			return CheckResult.correct();
		} else {
			return CheckResult.wrong("An error message expected with input \"11\"");
		}
	}

	Integer[] getUsedNumbers(TestedProgram main, int length) {
		Integer[] nums = new Integer[length];
		int[] result;

		int index = 0;
		String output;
		String input;

		for (int i = 0; i < 10; i++) {
			input = new String(new char[length]).replace("\0", Integer.toString(i));
			output = main.execute(input);
			result = getNumOfBullsAndCows(output);

			if (result[0] > 1) {
				throw new WrongAnswer("Seems like " +
						"the calculation of bulls isn't right. " +
						"For the guess \"" + input + "\" there can be 1 bull at max.");
			}

			if (result[0] == 1) {
				nums[index++] = i;
			}

			if (index == length) {
				break;
			}
		}

		if (index != length) {
			throw new WrongAnswer(
					"Output should contain " + length + " bulls " +
							"summarized as every option was tried. Found: " + index
			);
		}

		return nums;
	}

	// permutations one by one
	public boolean getPermutations(TestedProgram main, int length, Integer[] elements) {
		int[] indexes = new int[length];
		for (int i = 0; i < length; i++) {
			indexes[i] = 0;
		}

		String output = main.execute(Arrays.toString(elements).replaceAll("\\[|\\]|, ", ""));
		int[] result = getNumOfBullsAndCows(output);
		if (result[0] == length) {
			return true;
		}

		int i = 0;
		while (i < length) {
			if (indexes[i] < i) {
				swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
				output = main.execute(Arrays.toString(elements).replaceAll("\\[|\\]|, ", ""));
				result = getNumOfBullsAndCows(output);
				if (result[0] == length) {
					return true;
				}
				indexes[i]++;
				i = 0;
			} else {
				indexes[i] = 0;
				i++;
			}
		}
		return false;
	}

	// get number of bulls and cows from user program's output
	int[] getNumOfBullsAndCows(String userString) {
		Matcher nonePattern = Pattern.compile("\\b[nN]one\\b").matcher(userString);
		Matcher cowsPattern = Pattern.compile("\\b\\d [cC]ows?").matcher(userString);
		Matcher bullsPattern = Pattern.compile("\\b\\d [bB]ulls?").matcher(userString);
		Pattern oneNumPattern = Pattern.compile("\\d");

		if (nonePattern.find()) {
			return new int[]{0, 0};
		}

		int[] ans = {0, 0};
		boolean found = false;

		if (bullsPattern.find()) {
			String temp = bullsPattern.group();
			Matcher oneNumBulls = oneNumPattern.matcher(temp);
			oneNumBulls.find();
			ans[0] = Integer.parseInt(oneNumBulls.group());
			found = true;
		}

		if (cowsPattern.find()) {
			String temp = cowsPattern.group();
			Matcher oneNumCows = oneNumPattern.matcher(temp);
			oneNumCows.find();
			ans[1] = Integer.parseInt(oneNumCows.group());
			found = true;
		}

		if (!found) {
			throw new WrongAnswer(
					"Cannot find number of bulls or number of cows or None after the input."
			);
		}

		return ans;
	}
}