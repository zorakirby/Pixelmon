package pixelmon.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * List-specific ChancedWrapper for programming simplification.
 *
 * @param <T>
 */
public class ChancedCollection<T> extends ChancedWrapper<Collection<T>> implements Collection<T>{

	
	public ChancedCollection(Collection<T> object, float chance) {
		super(object, chance);
	}
	
	public ChancedCollection(Collection<T> object, Random rand, float chance) {
		super(object, rand, chance);
	}

	@Override
	public boolean add(T e) {
		return object.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return object.addAll(c);
	}

	@Override
	public void clear() {
		object.clear();
	}

	@Override
	public boolean contains(Object o) {
		return object.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return object.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return object.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return object.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return object.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return object.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return object.retainAll(c);
	}

	@Override
	public int size() {
		return object.size();
	}

	@Override
	public Object[] toArray() {
		return object.toArray();
	}

	@Override
	public <E> E[] toArray(E[] a) {
		return object.toArray(a);
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName() + "(Chance = " + (maxChance - minChance) + ", " + object.getClass().getSimpleName() + "[" + object.size() + "])";
	}
	
	@Override
	public String describeObject(){
		String base = "00000000000";
		String vals = Integer.toString(this.object.size());
		return base.substring(0, base.length()-vals.length())+vals;
	}



}
