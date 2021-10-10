package me.scill.siriusenchants.utils;

import java.util.Random;

public final class RandomUtil {

	private RandomUtil() {}
	private final static Random random = new Random();

	/**
	 * Gets a random number.
	 *
	 * @param num any integer
	 * @return random number
	 */
	public static int random(int num) {
		return random.nextInt(num);
	}

	/**
	 * Gets a random number between two integers.
	 *
	 * @param highNum high integer
	 * @param lowNum low integer
	 * @return random number
	 */
	public static int random(int highNum, int lowNum) {
		return random.nextInt(highNum - lowNum) + lowNum;
	}

	/**
	 * Gets a random number between two doubles.
	 *
	 * @param highNum high double
	 * @param lowNum low double
	 * @return random number
	 */
	public static double random(double highNum, double lowNum) {
		return (random.nextDouble() * (highNum - lowNum)) + lowNum;
	}

	/**
	 * @param num any integer
	 * @return true if num is higher than a random number 1-100
	 */
	public static boolean chance(int num) {
		return (random(100) + 1) <= num;
	}
}
