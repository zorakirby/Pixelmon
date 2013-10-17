package pixelmon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import pixelmon.util.ChancedWrapper;
import pixelmon.util.IRandomPicker;
import pixelmon.util.RandomWrapper;
import pixelmon.util.WeightedWrapper;

public class RandomHelper {
	static Random rand = new Random();
	static RandomWrapper randWrap = new RandomWrapper(rand);

	public static int getRandomNumberBetween(int min, int max) {
		return useRandomForNumberBetween(rand, min, max);
	}

	public static float getRandomNumberBetween(float min, float max) {
		return useRandomForNumberBetween(rand, min, max);
	}
	
	public static int useRandomForNumberBetween(Random random, int min, int max){
		return random.nextInt(max-min+1) + min;
	}
	
	public static float useRandomForNumberBetween(Random random, float min, float max){
		return random.nextFloat() * (max - min) + min;
	}
	
	public static float nextFloat(Random random){
		return ((float)random.nextInt(Integer.MAX_VALUE))/((float)Integer.MAX_VALUE-1);
	}
	
	public static float nextFloat(){
		return nextFloat(rand);
	}
	

	
	
	/**
	 * @return The exact same thing as <code>weightedChoice(Collection, Random)</code>, except no 
	 * <code>Random</code> argument is required. Instead, <code>RandomHelper</code>'s own 
	 * static <code>Random</code> is automatically used
	 */
	public static <T> T weightedChoice(Collection<ChancedWrapper<T>> choices, boolean nextForEach){
		return ChancedWrapper.weightedChoice(choices, randWrap, nextForEach);
	}
}
