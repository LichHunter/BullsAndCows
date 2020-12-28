import bullscows.Main;
import org.hyperskill.hstest.dynamic.input.DynamicTestingMethod;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.hyperskill.hstest.testing.expect.Expectation.expect;

public class BullsCowsTest extends StageTest<String> {

	private static List<Integer> stringToArrayOfNumbers(String array) {
		return Arrays.stream(array.split(""))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	// basic test case
	@DynamicTestingMethod
	CheckResult test1() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("4").toLowerCase().trim();

		return outputCheck(output, 4);
	}

	@DynamicTestingMethod
	CheckResult test2() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("1").toLowerCase().trim();

		return outputCheck(output, 1);
	}

	// test of incorrect input
	@DynamicTestingMethod
	CheckResult test4() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("11").toLowerCase().trim();

		return outputCheck(output, 11);
	}

	@DynamicTestingMethod
	CheckResult test5() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("6").toLowerCase().trim();

		return outputCheck(output, 6);
	}

	@DynamicTestingMethod
	CheckResult test6() {
		TestedProgram main = new TestedProgram(Main.class);
		main.start();
		String output = main.execute("3").toLowerCase().trim();

		return outputCheck(output, 3);
	}

	CheckResult outputCheck(String source, int length) {

		if (length > 10) {
			if (source.toLowerCase().contains("error")) {
				return CheckResult.correct();
			} else {
				return CheckResult.wrong("An error message expected with input " +
						"\"" + length + "\"");
			}
		}

		List<Integer> integers = expect(source).toContain(1).integers();
		source = "" + integers.get(0);

		if (source.length() != length) {
			return CheckResult.wrong("The output number of your program has " +
					"an incorrect length (found " + source.length() + ")");
		}

		List<Integer> temp = stringToArrayOfNumbers(source);
		temp = new ArrayList<>(new LinkedHashSet<>(temp));

		if (temp.toArray().length != source.length()) {
			return CheckResult.wrong("Digits in the generated number are not unique.");
		}

		return CheckResult.correct();
	}
}