package pixelmon;

import java.util.Random;

public class RandomHelper {
	static Random rand = new Random();

	/**
     * Returns a random int between min and max inclusive
     */
	public static int getRandomNumberBetween(int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}

	public static float getRandomNumberBetween(float min, float max) {
		return rand.nextFloat() * (max - min) + min;
	}
}
