/**
 * 
 */
package com.wojewodka.algoritmics.ex2;

import com.wojewodka.algoritmics.timer.AlgorithmTimer;

/**
 * @author Emil Wojewódka
 *
 * @since 26 wrz 2018
 */
public class AbsoluteValueCheckerRunnable {

	public static void main(String[] args) throws Exception {
		int result = new AlgorithmTimer<Integer>() {
			
			@Override
			protected Integer execute() throws Exception {
				return check(25, 2, -10, -6);
			}
		}.runAlgorithm();
		System.out.println("Absolutna suma liczb ujemnych jest "
				+ (result == 0 ? "równa" : result > 1 ? "mniejsza" : "większa") + " niż suma liczba dodatnich");
	}

	/**
	 * Return 0 if sums are equals, return -1 if sum of positive is bigger, and 1 if
	 * sum absolute of negative are bigger.
	 * 
	 * @param positiveA
	 * @param positiveB
	 * @param negativeA
	 * @param negativeB
	 * @return
	 */
	private static int check(int positiveA, int positiveB, int negativeA, int negativeB) {
		// Check variables are correct.
		Asserts.greaterThanZero(positiveA);
		Asserts.greaterThanZero(positiveB);
		Asserts.lessThanZero(negativeA);
		Asserts.lessThanZero(negativeB);

		// sum negative and multiple by -1, so result'll be on plus.
		int absoluteNegativeSum = (negativeA + negativeB) * -1;
		int positiveSum = positiveA + positiveB;
		// Print info
		System.out.println("Absolutna suma liczb ujemnych: " + absoluteNegativeSum);
		System.out.println("Suma liczb dodatnich: " + positiveSum);
		return absoluteNegativeSum == positiveSum ? 0 : absoluteNegativeSum > positiveSum ? 1 : -1;
	}

	static class Asserts {
		// Return false if value is 0
		static void greaterThanZero(int value) {
			if (value > 0)
				return;
			throw new IllegalArgumentException("Value [" + value + "] is lesser than zero.");
		}

		static void lessThanZero(int value) {
			if (value < 0)
				return;
			throw new IllegalArgumentException("Value [" + value + "] is greater than zero.");
		}

	}

}
