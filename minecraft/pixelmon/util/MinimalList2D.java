package pixelmon.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * A <b>reaaaaaalllllllly boring</b> and <b> not fun at all</b> implementation
 * of {@link IList2D}. It lacks all of the <b>really fun</b> and <b>unique</b>
 * methods found in {@link AbstractList2D}, as well as any friend, or a social
 * life. {@link AbstractList2D} only agreed to work together with
 * {@code MinimalList2D}, because it would be really helpful: <i>if 
 * {@code AbstractList2D} could have it any other way, she would <b>pretend
 * {@code MinimalList2D} did not exist</b> because he's no fun</i><br><br>
 * 
 * You see, {@code MinimalList2D} lives in this surprisingly clean, 
 * tiny little 3-room apartment. You may wonder why exactly the apartment 
 * is so neat and tidy, but that's because he barely has anything with which 
 * to make a mess. In the living room is a white couch of such simple design, 
 * you'd think it was just a big box with a bit of cushioning. The same goes 
 * for his bed. In his kitchen, you'd find a few cupboards, but after peering
 * into these, you'd discover only a few boxes of bran flakes and ramen noodles. 
 * Yes, he has a refrigerator, but inside, only a gallon of milk for his 
 * cereal, and an apple or two. MinimalList2D is not very likeable, but he
 * has his uses.<br><br>
 * 
 * His resume reads:<br>
 * <i>I know how to store values into 2D indices. I'm no good at all that
 * fancy stuff that AbstractList2D can do, but sometimes, extra features mean
 * taking up too much time or resources to do something.</i>
 * <br>
 * That's why {@link AbstractList2D} hates {@code MinimalList2D}. Well, that, and
 * {@code MinimalList2D} now works at {@link AbstractList2D}'s second job, which is
 * at {@link pixelmon.worldGeneration.WorldGenFloodDrain WorldGenFloodDrain}.
 * 
 * @author Jack
 *
 * @param <T>
 */
public class MinimalList2D<T> implements IList2D<T>{
	
	
	/**
	 * Since this class is a minimalistic implementation of {@link IList2D}
	 * the order of elements isn't important, so no {@link java.util.TreeSet TreeSet}.
	 */
	protected HashMap<Integer, HashMap<Integer, T>> values = new HashMap();
	protected int elements = 0;
	//Screw this extra min/max stuff. It doesn't fit with the minimalist concept
	//of MinimalList2D, and plus, it's almost fun!
	//protected Integer minX = null, minZ = null, maxX = null, maxZ = null;
	//protected Double minVal = null, maxVal = null;
	
	public MinimalList2D(){
		
	}

	@Override
	public boolean containsValue(int x, int z) {
		return this.values.containsKey(x) && this.values.get(x) != null && this.values.get(x).containsKey(z);
	}

	@Override
	public void addValue(int x, int z, T value) {
		/*if(minX == null || x < minX)
			minX = x;
		if(maxX == null || x > maxX)
			maxX = x;
		if(minZ == null || z < minZ)
			minZ = z;
		if(maxZ == null || z > maxZ)
			maxZ = z;
		if(value instanceof Number){
			double numValue = ((Number) value).doubleValue();
			if(minVal == null || numValue < minVal)
				minVal = numValue;
			if(maxVal == null || numValue > maxVal)
				maxVal = numValue;
		}*/
		if(!this.containsValue(x, z))
			elements++;
		if(values.get(x) == null)
			values.put(x, new HashMap());
		values.get(x).put(z, value);
	}

	@Override
	public T get(int x, int z) {
		if(values.get(x) == null)
			return null;
		return values.get(x).get(z);
	}

	@Override
	public T remove(int x, int z) {
		if(values.get(x) == null || values.get(x).get(z) == null)
			return null;
		return values.get(x).remove(z);
	}

	@Override
	public Set<Integer> xList(){
		return values.keySet();
	}

	@Override
	public Set<Integer> zList(int x) {
		HashMap map = values.get(x);
		return map == null ? new HashSet<Integer>() : map.keySet();
	}
	
	/**
	 * @param abstractList2DClass - An instantiable class that implements 
	 * {@code AbstractList2D}.
	 * @return A new, fun, awesome, but completely separate 
	 * {@code AbstractList2D} containing all the values this does, but since
	 * it's an {@code AbstractList2D}, it has all those awesome, fun methods
	 * that {@code MinimalList2D} doesn't have. Please note that all that the
	 * {@code MinimalList2D} that calls this does is <i>tell some type of
	 * {@code AbstractList2D} to create itself, and send its data to the new
	 * {@code AbstractList2D}; </b></i>since {@code MinimalList2D} is still
	 * unaffiliated with any of the fun methods contained within
	 * {@code AbstractList2D}, it still <b> isn't any fun!
	 * 
	 */
	public <E extends AbstractList2D> AbstractList2D<T> makeFun(Class<E> abstractList2DClass){
		AbstractList2D<T> result = null;
		try {
			result = abstractList2DClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i : xList())
			for(int j : zList(i))
				result.addValue(i, j, get(i, j));
		
		return result;
	}
	

}
