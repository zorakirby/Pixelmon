package pixelmon.util;

import java.util.Random;

public class RandomWrapper implements IRandomPicker{

	Random random;
	
	public RandomWrapper(Random rand){
		this.random = rand;
	}
	
	public RandomWrapper(long seed){
		this.random = new Random(seed);
	}
	
	public RandomWrapper(){
		this.random = new Random();
	}
	@Override
	public float nextFloat() {
		return random.nextFloat();
	}

	@Override
	public int getNextInt(int n) {
		return random.nextInt(n);
	}

}
