package pixelmon.util;

import java.util.Set;

public interface IList2D<T>{
	
	public boolean containsValue(int x, int z);
	public void addValue(int x, int z, T value);
	public T get(int x, int z);
	public T remove(int x, int z);
	public Set<Integer> xList();
	public Set<Integer> zList(int x);

}
