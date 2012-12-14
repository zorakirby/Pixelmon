package pixelmon;

import java.util.Random;

public class RandomHelper {
	public static int getRandomNumberBetween(int i, int i1) {
		Random rand = new Random();
		int number = 0;
		for (int i2 = -1; !(i2 >= i && i2 <= i1);) {
			i2 = number = rand.nextInt(i1 + 1);
		}
		return number;
	}
}
