import java.math.BigInteger;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		//create Scanner instance
		Scanner scanner = new Scanner(System.in);

		//read four numbers
		String a = scanner.next();
		String b = scanner.next();
		String c = scanner.next();
		String d = scanner.next();

		//convert those numbers to BigInteger
		BigInteger aB = new BigInteger(a);
		BigInteger bB = new BigInteger(b);
		BigInteger cB = new BigInteger(c);
		BigInteger dB = new BigInteger(d);

		//make math operations
		BigInteger result = aB.negate().multiply(bB).add(cB).subtract(dB);
		System.out.println(result.toString());
	}
}