package pixelmon.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import net.minecraft.util.MathHelper;

import pixelmon.RandomHelper;

public class ChancedWrapper<T> implements Comparable<ChancedWrapper>{
	
	public T object;
	public float minChance, maxChance;
	
	private static final String illegalChance = "The ChancedWrapper from %s wrapping %s must be initialized with a chance value between 0.0f and 1.0f. The value in this case, however, was %s";
	
	/**
	 * The chance for this {@code ChancedWrapper} to be picked is 
	 * between 0 and {@code chance}. Therefore, a random float value simply
	 * needs to be less than {@code chance} in order for this
	 * {@code ChancedWrapper} to be picked.
	 * @param object - The object associated with this ChancedWrapper.
	 * @param chance - the likelyhood this will be picked, <b>between 0 and 1</b>.If it's less than
	 * 0 or greater than 1, it will automatically be set to 0 or 1, respectively.
	 */
	public ChancedWrapper(T object, float chance){
		if(chance < 0 || chance > 1)
			throw badChanceArg(object, chance);
		this.object = object;
		this.minChance = 0;
		this.maxChance = chance;
	}
	
	/**
	 * The 'chance interval' for this {@code ChancedWrapper} to be picked is randomly
	 * "moved" between 0 and 1. Think of it as taking a board game spinner and moving the 
	 * differently colored sectors randomly: the chance does not change, but the spinner needs to 
	 * land on a different area.
	 * @param object - The object associated with this ChancedWrapper.
	 * @param rand - a thing that generates random values. I bet you didn't know that.
	 * @param chance - the likelyhood this will be picked, <b>between 0 and 1</b>. If it's less than
	 * 0 or greater than 1, it will automatically be set to 0 or 1, respectively.
	 */
	public ChancedWrapper(T object, Random rand, float chance){
		chance = MathHelper.clamp_float(chance, 0F, 1F);
		this.object = object;
		this.minChance = RandomHelper.useRandomForNumberBetween(rand, 0F, 1F-chance);
		this.maxChance = minChance+chance;
	}
	
	/**
	 * whether or not {@code a} is between {@link #minChance}(inclusive) and
	 * {@link #maxChance}(exclusive).
	 */
	public boolean chosen(float a){
		boolean result = this.minChance <= a && a < this.maxChance;
		return result;
	}
	
	public boolean chosen(Random random){
		return chosen(random.nextFloat());
	}
	
	/**
	 * simple static version of {@link #chosen(float)}. This behaves as though a {@code ChancedWrapper}
	 * was constructed via {@link #ChancedWrapper(Object, Random, float)} and then had
	 * {@link #chosen(float)} called on it;
	 */
	public static boolean chosen(float chance, Random random){
		chance = MathHelper.clamp_float(chance, 0F, 1F);
		float minChance = RandomHelper.useRandomForNumberBetween(random, 0F, 1F-chance);
		float maxChance = minChance+chance;
		float a = random.nextFloat();
		return minChance <= a && a < maxChance;

		
	}
	
	
	protected static IllegalArgumentException badChanceArg(Object attemptedObj, float badArg){
		return new IllegalArgumentException(String.format(illegalChance, PixelmonDebug.prevMethod(), attemptedObj, badArg));
	}
	
	/**
	 * @param nextForEach - whether or not a new float value should be generated
	 * for each ChancedWrapper. If many of the {@code ChancedWrappers} in
	 * {@code choices} were constructed via {@link #ChancedWrapper(Object, float)}
	 * it may be a better idea to set {@code nextForEach} to true.
	 * @return A randomly chosen Object in the list of choices, based on item weight. The likelyhood for
	 * an item to be picked is its itemWeight. Unlike <code>WeightedRandom.getRandomItem</code>,
	 * this method does not take into account the total weight of the input Collection, instead each item is given 
	 * a chance to be added to a preliminary list, then picks one from the preliminary list with equal chance.
	 * This offers a more balanced, easy-to-use random result chooser than WeightedRandom, especially 
	 * considering that the chances for everything with WeightedRandom change significantly if
	 * anything is added to the collection from which WeightedRandom has to choose.
	 * If nothing was picked, <code>null</code> is returned.
	 */
	public static <T, E extends ChancedWrapper<T>> E weightedChoice(Collection<E> choices, Random random, boolean nextForEach){
		ArrayList<E> preliminary = new ArrayList();
		float next = random.nextFloat();
		for(E choice : choices){
			if(nextForEach)
				next = random.nextFloat();
			boolean chosen = choice.chosen(next);
			if(chosen){
				preliminary.add(choice);
				//System.out.println("Adding " + choice + " to preliminary list");
			}
		}
		//System.out.println("Preliminary size is " + preliminary.size());
		E result =  preliminary.size() == 0 ? null : preliminary.get(random.nextInt(preliminary.size()));
		if(result != null){
		//System.out.println("Yes, result was " + result);
		}
		return result;
	}
	
	/**
	 * uses an {@link IRandomPicker} instance instead
	 * @param random - An IRandomPicker instance. A primary example would be 
	 * {@link pixelmon.worldGeneration.GenLayerAddRareBiome GenLayerAddRareBiome},
	 * which has a built-in {@code nextInt()} method from extending 
	 * {@link net.minecraft.world.gen.layer.GenLayer GenLayer}.
	 */
	public static <T> T weightedChoice(Collection<ChancedWrapper<T>> choices, IRandomPicker random, boolean nextForEach){
		ArrayList<T> preliminary = new ArrayList();
		float next = random.nextFloat();
		for(ChancedWrapper<T> choice : choices){
			if(nextForEach)
				next = random.nextFloat();
			if(choice.chosen(next))
				preliminary.add(choice.object);
		}
		T result =  preliminary.size() == 0 ? null : preliminary.get(random.getNextInt(preliminary.size()));
		return result;
	}
	
	public float chance(){
		return this.maxChance-this.minChance;
	}
	
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName() + "(chance = " + (maxChance-minChance) + "object = " + object + ")";
	}
	
	public String describeObject(){
		return this.object.toString();
	}

	/**
	 * {@inheritDoc}
	 * Sorts by Class, then chance, then:<br>
	 * -If both {@code ChancedWrapper}s' objects are of type {@link Comparable}, by comparing the two<br>
	 * -Otherwise, by {@link #describeObject() object description}.<br><br>
	 * If, at this point, the two{@code ChancedWrapper}s have still not been differentiated, compares 
	 * this {@code ChancedWrapper}'s hashCode to the other's.
	 */
	@Override
	public int compareTo(ChancedWrapper o) {
		int classCompare = this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
		if(classCompare!=0)
			return classCompare;
		int chanceCompare = (int) Math.signum(this.chance() - o.chance());
		if(chanceCompare!=0)
			return chanceCompare;
		if(this.object instanceof Comparable && o.object instanceof Comparable){
			Comparable c0 = (Comparable) this.object;
			Comparable c1 = (Comparable) o.object;
			try{
			return c0.compareTo(c1);
			}catch(Exception e){}
		}
		int objectCompare = this.describeObject().compareTo(o.describeObject());
		if(objectCompare!=0)
			return objectCompare;
		return(this.hashCode() - o.hashCode());
	}
	
	



}
