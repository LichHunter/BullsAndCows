import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		//read user input
		//if input == 0 -> break
		while (true) {
			//read input
			String input = scanner.next();

			//try to convert string to int
			try {
				//get number from string
				int number = Integer.parseInt(input);

				//number == 0 -> break
				if (number == 0) {
					break;
				} else {
					//output number * 10
					System.out.println(number * 10);
				}
			} catch (Exception e) {
				//if cant convert output error
				System.out.println("Invalid user input: " + input);
			}
		}
	}
}