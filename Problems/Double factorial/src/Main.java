import java.math.BigInteger;

class DoubleFactorial {
	public static BigInteger calcDoubleFactorial(int n) {
		//if n == 0 -> return 0
		if (n == 0 || n == 1) return BigInteger.ONE;

		//result BigInteger which we will return
		BigInteger result = BigInteger.ONE;
		//convert number to BigInteger
		BigInteger number = BigInteger.valueOf(n);

		do {
			//multiply result by temp
			result = result.multiply(number);
			//get number by 2 smaller than current
			number = number.subtract(BigInteger.TWO);

			//if number <= 0 -> break
		} while (!number.equals(BigInteger.ZERO) && !number.equals(BigInteger.ONE));

		return result;
	}
}