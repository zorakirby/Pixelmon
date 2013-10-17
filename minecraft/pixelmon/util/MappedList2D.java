package pixelmon.util;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;


/**
 * {@link TreeMap}-based implementation of {@link AbstractList2D}. This handles 2D
 * indices as a {@code TreeMap} of {@code TreeMaps}. <br>
 * <br>
 * Since {@code TreeMap} keeps its keys in order from least to greatest, and the keys 
 * being used here are all integers, the values are also all stored (programmatically)
 * from least to greatest. This also means, of course, that any method that simply 
 * iterates through a {@code MappedList2D} is also guaranteed to look through the 
 * values in order, whereas a theoretical {@link java.util.HashMap HashMap}-based 
 * implementation would not.
 * @author Jack
 *
 * @param <T> The type of values being stored
 */
public class MappedList2D<T> extends AbstractList2D<T>{
	
	protected TreeMap<Integer, TreeMap<Integer, T>> values = new TreeMap();

	public MappedList2D(){
		
	}
	
	public MappedList2D(int width, int length, T value){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < length; j++){
				this.addValue(i, j, value);
			}
		}
	}
	
	@Override
	public AbstractList2D createNew() {
		return new MappedList2D();
	}

	@Override
	public void putValue(int x, int z, T value) {
		if(values.get(x) == null)
			values.put(x, new TreeMap());
		values.get(x).put(z, value);
	}
	
	@Override
	public T removeImpl(int x, int z){
		if(values.get(x) == null || values.get(x).get(z) == null)
			return null;
		return values.get(x).remove(z);
	}
	

	@Override
	public T get(int x, int z) {
		if(values.get(x) == null)
			return null;
		return values.get(x).get(z);
	}

	
	public AbstractList2D modifyWithShape(Shape shape, T fill){
		Rectangle2D bbox = shape.getBounds2D();
		for(int i = (int) bbox.getMinX() - 1; i < bbox.getMaxX(); i++){
			for(int j = (int) bbox.getMinY() - 1; j < bbox.getMaxY(); j++){
				if(shape.contains(i, j)){
					this.addValue(i, j, fill);
				}
			}
		}
		return this;
	}

	@Override
	public Set<T> toSet() {
		HashSet result = new HashSet();
		for(TreeMap<Integer,T> map : values.values())
			result.addAll(map.values());
		return result;
	}

	@Override
	public List<T> toList() {
		ArrayList<T> result = new ArrayList();
		for(TreeMap<Integer,T> map : values.values())
			result.addAll(map.values());
		return result;
	}

	
}
