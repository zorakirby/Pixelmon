package pixelmon.util;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.common.collect.TreeMultimap;

import pixelmon.util.FunctionHelper.FunctionType;
import pixelmon.util.testing.Testomatic;

/**
 * A fantastic, wonderful class that functions as though it were a 2-dimensional array of values, except that
 * <i>negative</i> values <u>can also be used!</u>. Compare {@code Object[]} to {@code ArrayList}: effectively,
 * this class could be thought of as:<br>
 * {@code T[]} is to <code>ArrayList{@literal<T>}</code> as {@code T[][]} is to <code>AbstractList2D{@literal<T>}</code>,<br>
 * except that it has way more features. <br>
 * The only current subclass of {@code AbstractList2D} is {@link MappedList2D}, and,
 * being that {@code AbstractList2D} is, well, abstract, {@link MappedList2D} is currently
 * the only instantiable kind of {@code AbstractList2D}.<br>
 * <br>
 * Unfortunately, this class does <i>not</i> implement {@link java.lang.Iterable},
 * because writing an iterator for this was more confusing than one might think, but luckily, is very simple to
 * iterate through. The most effective way to iterate through an {@code AbstractList2D} is as follows:<br>
 * <code> for(int x : xList())<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;for(int z : zList(x))<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i>doSomething...</i><br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br></code>
 * Note that usage of <i>x</i> and <i>z</i> (as opposed to x and y, or i and j, etc.) are due to 
 * {@code AbstractList2D}'s initial purpose (and most prominent usage) of being a 
 * world-generation utility, that is, top-down views of x/z coordinates.<br>
 * <br>
 * Aside from being a way of storing values in two-dimensional indices, {@code AbstractList2D}
 * is loaded with useful features. These features include:<br>
 * (The obvious)<br>
 * -adding a value at a two-dimensional index<br>
 * -removing a value from a two-dimensional index<br>
 * (The extra features)<br>
 * -adding full rectangles of values<br>
 * -adding a checkerboard pattern of values<br>
 * -creating a {@link List} of all values<br>
 * -creating a {@link Set} of all values<br>
 * -retrieving minimum/maximum column/row values<br>
 * -checking to see whether a values is contained at a certain x/z index<br>
 * -checking to see whether two {@code AbstractList2D}s overlap<br>
 * -creation from one-dimensional array<br>
 * -picking a random x/z index<br>
 * -duplication (cloning)<br>
 * -duplication while excluding a certain value<br>
 * -duplication while ignoring all but a certain value<br>
 * -duplication while ignoring all but {@link Comparable} values {@literal (<, >, <=, >=, ==, !=)}<br>
 * -adding/subtracting/multiplying values with <code>AbstractList2D{@literal <Number>}</code><br>
 * -inverting <code>AbstractList2D{@literal <Float>}</code><br>
 * -<b>creation/modifying with {@link Shape}!!!</b><br>
 * -<b>creation/modifying with {@link Line2D Line(s)}!!!</b><br>
 * -combine/intersect with other {@code AbstractList2D}<br>
 * -shifting all values to bottom-left aligned with {@code (0,0)}<br>
 * -saving/loading values to/from a file!!!<br>
 * 
 * 
 * @author Jack
 *
 * @param <T> The type of values being stored
 */
public abstract class AbstractList2D<T> implements IList2D<T>{

	protected int elements = 0;
	protected Integer minX = null, minZ = null, maxX = null, maxZ = null;
	protected Double minVal = null, maxVal = null;

	protected Class valuesClass;

	protected TreeMultimap<Integer, Integer> xToZ = TreeMultimap.create(), zToX = TreeMultimap.create();
	protected ArrayList<T> valuesList = new ArrayList();

	public abstract AbstractList2D createNew();
	public abstract T get(int x, int z);
	/**
	 * Removes the value at <code>(x, z)</code><br>
	 * <i>Note to subclassers:</i> make sure nothing more than the specified element
	 *  is being removed!
	 * @return the value that was removed, or <code>null</code> if there was no 
	 * element at this index.
	 */
	protected abstract T removeImpl(int x, int z);
	protected abstract void putValue(int x, int z, T value);


	public abstract AbstractList2D modifyWithShape(Shape shape, T fill);
	public abstract Set<T> toSet();
	public abstract List<T> toList();


	public void addValue(int x, int z, T value){
		if(minX == null || x < minX)
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
		}
		if(!this.contains(x, z))
			elements++;
		else{
			this.valuesList.remove(get(x, z));
		}
		xToZ.put(x, z);
		zToX.put(z, x);

		this.valuesList.add(value);
		putValue(x, z, value);
	}

	public T remove(int x, int z){
		if(this.contains(x, z)){
			elements--;
			this.valuesList.remove(get(x,z));
		}
		xToZ.remove(x, z);
		zToX.remove(z, x);
		if(xToZ.get(x).isEmpty())
			xToZ.removeAll(x);
		if(zToX.get(z).isEmpty())
			zToX.removeAll(z);
		return removeImpl(x, z);
	}

	public TreeSet<Integer> xList(){
		return new TreeSet(xToZ.keySet());
	}
	public TreeSet<Integer> zList(){
		return new TreeSet(zToX.keySet());
	}
	public TreeSet<Integer> xList(int z){
		return new TreeSet(zToX.get(z));
	}
	public TreeSet<Integer> zList(int x){
		return new TreeSet(xToZ.get(x));
	}

	public Integer minX(){
		return minX;
	}

	public Integer maxX(){
		return maxX;
	}

	public Integer minZ(){
		return minZ;
	}

	public Integer maxZ(){
		return maxZ;
	}

	public Integer rangeX(){
		return (minX == null || maxX == null) ? null : maxX - minX;
	}

	public Integer rangeZ(){
		return (minZ == null || maxZ == null) ? null : maxZ - minZ;
	}

	public Double minVal(){
		return minVal;
	}

	public Double maxVal(){
		return maxVal;
	}


	public int size(){
		//return xToZ.size();
		return elements;
	}

	/**
	 * @return The smallest possible 'Rectangle values' that can fit around 
	 * <code>lists</code> in the order (minX, minZ, maxX, maxZ).
	 */
	public static int[] getBounds(AbstractList2D... lists){
		int xMin = 0, xMax = 0, zMin = 0, zMax = 0;
		for(AbstractList2D list : lists){
			if(list.minX != null && list.minX < xMin)
				xMin = list.minX;
			if(list.maxX != null && list.maxX > xMax)
				xMax = list.maxX;
			if(list.minZ != null && list.minZ < zMin)
				zMin = list.minZ;
			if(list.maxZ != null && list.maxZ > zMax)
				zMax = list.maxZ;
		}
		return new int[]{xMin, zMin, xMax, zMax};
	}

	public boolean contains(int x, int z){
		return this.xToZ.containsEntry(x, z);
	}

	public boolean contains(T value){
		return valuesList.contains(value);
	}

	/**
	 * Nearly the same as {@link #contains(int, int)} except that if there IS a value at
	 * {@code (x, z)}, it will only return true if said value is not {@code null}
	 * @param x
	 * @param z
	 * @return true if there is a value at {@code (x, z)} and it's not {@code null}, false otherwise.
	 */
	public boolean isntNull(int x, int z){
		return this.get(x, z)!= null;
	}

	public boolean containsX(int x){
		return xToZ.containsKey(x);
	}

	public boolean containsZ(int z){
		return zToX.containsKey(z);
	}
	public boolean isEmpty(){
		return elements == 0;
	}

	public boolean overlaps(AbstractList2D another){
		for(int i : this.xList()){
			for(int j : this.zList(i)){
				if(another.contains(i, j))
					return true;
			}
		}
		return false;
	}






	public void addRect(int x, int z, int width, int length, T value, boolean overwrite){
		int dx = Integer.signum(width);
		int dz = Integer.signum(length);
		for(int i = 0; i!= width; i+=dx){
			for(int j = 0; j!= length; j+= dz){
				if(overwrite || !this.contains(i, j))
					this.addValue(x+i, z+j, value);
			}
		}
	}

	public void addRectButSkip(int x, int z, int width, int length, T value, T noOverwrite){
		int dx = Integer.signum(width);
		int dz = Integer.signum(length);
		for(int i = 0; i!= width; i+=dx){
			for(int j = 0; j!= length; j+= dz){
				if(!noOverwrite.equals(this.get(x+i, z+j)))
					this.addValue(x+i, z+j, value);
			}
		}
	}

	public void addCheckerBoard(int x, int z, int width, int length, T value){
		for(int i = 0; i < width; i++)
			for(int j = 0; j < length; j++)
				if(((i+j)&1)==0)
					this.addValue(x+i, z+j, value);
	}

	public AbstractList2D<T> copy(){
		AbstractList2D<T> result = this.createNew();
		for(int i : xList()){
			for(int j : zList(i)){
				result.addValue(i, j, this.get(i, j));
			}
		}
		return result;
	}

	public static <T extends Number> AbstractList2D<T> sumCheckerBoard(AbstractList2D<T> values, T addend){
		AbstractList2D<T> addendCheckers = values.createNew();
		int[] bounds = getBounds(values);
		addendCheckers.addCheckerBoard(bounds[0], bounds[1], (bounds[2]+1)-bounds[0], (bounds[3]+1)-bounds[1], addend);
		add(values, addendCheckers);
		return values;
	}

	public int[] randomPoint(Random random){
		Integer[] xa = xList().toArray(new Integer[0]);
		int x = xa[random.nextInt(xa.length)];
		Integer[] za = zList(x).toArray(new Integer[0]);
		int z = za[random.nextInt(za.length)];
		return new int[]{x, z};
	}

	/**
	 * @return The first value (smallest row index in the smallest column index) in this {@code AbstractList2D}
	 * or {@code null} if there isn't one.
	 */
	public T getOneValue(){
		T result = null;
		for(int i : xList()){
			for(int j : zList(i)){
				result = get(i, j);
				if(result != null)
					return result;
			}
		}
		return null;
	}

	public AbstractList2D<T> fillEvens(T fill){
		for(int i : xList()){
			if((i&1) == 0){
				for(int j : zList(i)){
					if((j&1) == 0)
						addValue(i, j, fill);
				}
			}
		}
		return this;
	}
	
	public AbstractList2D<T> checkerboard(T fill){
		for(int i : xList()){
			for(int j : zList(i)){
				if(((j+i)&1) == 0)
					addValue(i, j, fill);
			}
		}
		return this;
	}

	public int[] getNthIntersection(AbstractList2D other, int n){
		int hits = 0;
		for(Integer i : xList()){
			for(Integer j : zList(i)){
				if(other.contains(i, j))
					hits++;
				if(hits == n)
					return new int[]{i,j};
			}
		}
		return null;
	}

	/** replaces all values in this {@code AbstractList2D} with {@code value}
	 * @param value
	 * @return
	 */
	public AbstractList2D<T> replace(T value){
		for(int i : xList())
			for(int j : zList(i))
				this.addValue(i, j, value);
		return this;
	}

	public <F> AbstractList2D<F> convert(F fillValue){
		AbstractList2D<F> result = this.createNew();
		for(int i : xList())
			for(int j : zList(i))
				result.addValue(i, j, fillValue);
		return result;
	}

	public AbstractList2D<T> recreateWithMinAtZero(){
		AbstractList2D<T> result = this.createNew();
		for(Integer i : this.xList()){
			for(Integer j : this.zList(i)){
				result.addValue(i-this.minX, j-this.minZ, this.get(i, j));
			}
		}
		return result;
	}

	/**
	 * @return This newly-expanded AbstractList2D
	 */
	public AbstractList2D expand(int radius, T value){
		AbstractList2D<T> temp = new MappedList2D();
		for(int x : xList()){
			for(int z : zList(x)){
				for(int i = -radius; i <= radius; i++){
					for(int j = -radius; j <= radius; j++){
						if(!this.contains(x+i, z+j))
							temp.addValue(x+i, z+j, value);
					}
				}
			}
		}
		return this.combine(temp, false);
	}

	public static <T> AbstractList2D<T> fromArray(Class<? extends AbstractList2D> list2Dclass, int x, int z, int width, int length, T[] values){
		AbstractList2D result = null;
		try{
			result = list2Dclass.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int j = 0; j < length; j++){
			for(int i = 0; i < width; i++){
				result.addValue(i, j, values[i + j * width]);
			}
		}
		return result;
	}

	public static  AbstractList2D<Integer> fromArray(Class<? extends AbstractList2D> list2Dclass, int x, int z, int width, int length, int[] values){
		AbstractList2D result = null;
		try{
			result = list2Dclass.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int j = 0; j < length; j++){
			for(int i = 0; i < width; i++){
				result.addValue(i, j, values[i + j * width]);
			}
		}
		return result;
	}

	public static  AbstractList2D<Float> fromArray(Class<? extends AbstractList2D> list2Dclass, int x, int z, int width, int length, float[] values){
		AbstractList2D result = null;
		try{
			result = list2Dclass.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int j = 0; j < length; j++){
			for(int i = 0; i < width; i++){
				result.addValue(i, j, values[i + j * width]);
			}
		}
		return result;
	}

	public static AbstractList2D<Float> modify(AbstractList2D<Float> values, AbstractList2D<Float> result, FunctionHelper.FunctionType type, double... args){
		if(result == null)
			result = values.createNew();
		for(Integer i : values.xList()){
			for(Integer j : values.zList(i)){
				Float newValue; 
				if(type == FunctionType.PERFECTSIN){
					newValue = (float) FunctionHelper.perfectSin(values.get(i, j), args[0], values.minVal, values.maxVal);
				}
				else{
					newValue = (float) type.function(values.get(i, j), args);
				}
				result.addValue(i, j, newValue);
			}
		}
		return result;
	}

	public static <T> AbstractList2D<T> copyWithOnly(AbstractList2D<T> values, T value){
		AbstractList2D<T> result = values.createNew();
		for(Integer x : values.xList()){
			for(Integer z : values.zList(x)){
				T t =  values.get(x, z);
				if(t.equals(value)){
					result.addValue(x, z, t);
				}
			}
		}
		return result;
	}


	public static <T> AbstractList2D<T> copyWithout(AbstractList2D<T> values, T value){
		AbstractList2D<T> result = values.createNew();
		for(Integer x : values.xList()){
			for(Integer z : values.zList(x)){
				T t =  values.get(x, z);
				if(!t.equals(value)){
					result.addValue(x, z, t);
				}
			}
		}
		return result;
	}

	public static <E, T extends Comparable<E>> AbstractList2D<T> filter(AbstractList2D<T> values, E value, BooleanOp op){
		AbstractList2D<T> result = values.createNew();
		for(Integer x : values.xList()){
			Collection<Integer> column = values.zList(x);
			for(Integer z : column){
				T t =  values.get(x, z);
				if(op.op(t, value)){
					result.addValue(x, z, t);
				}
			}
		}
		return result;
	}

	public  <E> AbstractList2D<E> filterConversion(T match, AbstractList2D<E> dest, E value){
		for(Integer x : this.xList()){
			Collection<Integer> column = this.zList(x);
			for(Integer z : column){
				if(match.equals(this.get(x, z))){
					dest.addValue(x, z, value);
				}
			}
		}
		return dest;
	}

	public static AbstractList2D<Float> invert(AbstractList2D<Float> source, AbstractList2D<Float> dest){
		if(dest == null)
			dest = source.createNew();
		for(Integer i : source.xList()){
			for(Integer j : source.zList(i)){
				float value = 1 - source.get(i, j);
				dest.addValue(i, j, value);
			}
		}
		return dest;
	}

	public static AbstractList2D<Integer> add(AbstractList2D<Integer> source, AbstractList2D<Integer> dest, int addend){
		if(dest == null)
			dest = source.createNew();
		for(Integer i : source.xList()){
			for(Integer j : source.zList(i)){
				Integer val = source.get(i, j) + addend;
				dest.addValue(i, j, val);
			}
		}
		return dest;
	}

	public static <T extends Number> void add(AbstractList2D<T> left, AbstractList2D<? extends Number> right){
		for(int i : right.xList())
			for(int j : right.zList(i)){
				T addend1 = left.get(i, j);
				Number addend2 = right.get(i, j);
				if(left.get(i, j)!=null){
					double sum = addend1.doubleValue() + addend2.doubleValue();
					Class<T> numClass = (Class<T>) addend1.getClass();
					T result = null;
					String methodName = null;
					Constructor constructor = null;
					Constructor[] constructors = numClass.getConstructors();
					for(Constructor con : constructors){
						Class[] params = con.getParameterTypes();
						if(params.length > 0 && params[0].isPrimitive() && params[0].getSimpleName()!="boolean" && params[0].getSimpleName()!="char"){
							methodName = params[0].getSimpleName() + "Value";
							break;
						}
					}
					Method getMethod = null;
					try {
						getMethod = Double.class.getMethod(methodName, new Class[0]);
						result = (T) getMethod.invoke(sum);
					} catch (Exception e) {
						e.printStackTrace();
					}
					left.addValue(i, j, result);
				}
			}
	}

	public static AbstractList2D<Float> add(AbstractList2D<Float> source, AbstractList2D<Float> dest, float addend){
		if(dest == null)
			dest = source.createNew();
		for(Integer i : source.xList()){
			for(Integer j : source.zList(i)){
				Float val = source.get(i, j) + addend;
				dest.addValue(i, j, val);
			}
		}
		return dest;
	}

	public static AbstractList2D<Float> mul(AbstractList2D<Float> source, AbstractList2D<Float> dest, float factor){
		if(dest == null)
			dest = source.createNew();
		for(Integer i : source.xList()){
			for(Integer j : source.zList(i)){
				Float val = source.get(i, j)*factor;
				dest.addValue(i, j, val);
			}
		}
		return dest;
	}

	public static AbstractList2D<Float> fromLines(Class<? extends AbstractList2D> list2DClass, Collection<? extends Line2D> lines){
		AbstractList2D result = null;
		try {
			result = list2DClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Line2D line : lines){
			result.combine(fromLine(list2DClass, line), true);
		}
		return result;
	}

	public AbstractList2D<T> combine(AbstractList2D<? extends T> other, boolean overwrite){
		for(Integer i : other.xList()){
			Collection<Integer> column = other.zList(i);
			for(Integer j : column){
				if(overwrite || this.get(i, j) == null){
					this.addValue(i, j, other.get(i, j));
				}
			}
		}
		return this;
	}

	/**
	 * @return This AbstractList2D, now intersected with <code>other</code>
	 */
	public AbstractList2D<T> intersect(AbstractList2D other){
		Collection<Integer> xList = new TreeSet(xList());
		for(int i : xList){
			Collection<Integer> zList = new TreeSet(zList(i));
			for(int j : zList){
				if(!other.contains(i, j))
					remove(i, j);
			}
		}
		return this;
	}

	public static <T> AbstractList2D<T> common(AbstractList2D<T> derive, AbstractList2D... rest){
		AbstractList2D<T> result = derive.copy();
		for(int i = 0; i < rest.length; i++){
			result.intersect(rest[i]);
		}
		return result;
	}


	public static boolean isPointSurrounded(AbstractList2D values, int x, int z){
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				if(i != 0 && j!=0 && values.get(x+i, j+z) == null)
					return false;
			}
		}
		return true;
	}

	public boolean isInBounds(int x, int z){
		return (x >= minX() && x <= maxX() && z >= minZ() && z <= maxZ());
	}

	public static AbstractList2D<Float> fromLine(Class<? extends AbstractList2D> list2DClass, Line2D line){
		AbstractList2D<Float> result = null;
		try {
			result = list2DClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double x0 = line.getX1(), y0 = line.getY1(), x1 = line.getX2(), y1 = line.getY2();
		boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
		if (steep){
			double temp = x0;
			x0 = y0;
			y0 = temp;
			temp = x1;
			x1 = y1;
			y1 = temp;
		}
		if (x0 > x1){
			double temp = x0;
			x0 = x1;
			x1 = temp;
			temp = y0;
			y0 = y1;
			y1 = temp;
		}
		int deltax = (int) (x1 - x0);
		int deltay = (int) Math.abs(y1 - y0);
		int error = deltax / 2;
		int ystep;
		int y = (int) y0;
		ystep = y0 < y1 ? 1 : -1;
		for(int x = (int) x0; x <= x1; x++){
			if(steep)
				result.addValue(y, x, 1F);
			else result.addValue(x, y, 1F);
			error -= deltay;
			if(error < 0){
				y += ystep;
				error += deltax;
			}
		}
		return result;
	}

	public String listIndices(){
		return this.xToZ.toString();
	}


	/**
	 * @return A new, boring, MinimalList2D containing all the values this has,
	 * but since it's a {@code MinimalList2D}, it doesn't have any of the 
	 * awesome methods {@code AbstractList2D} has, so it's not any fun. :(
	 */
	public MinimalList2D<T> makeBoring(){
		MinimalList2D<T> result = new MinimalList2D();
		for(int i : xList())
			for(int j : zList(i))
				result.addValue(i, j, get(i, j));
		return result;
	}
	
	/**
	 * @param img
	 */
	public void imageFill(AbstractList2D<Float> l2d, BufferedImage img){
		Color color = null;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				color = new Color(img.getRGB(i, j));
				float alpha = color.getAlpha()/255F;
				if(alpha == 0)
					continue;
				float lightness = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[2];
				float value = lightness*alpha;
				l2d.addValue(i, j, value);
			}
		}
	}


	/**
	 * Loads values from a file created via {@link #save(OutputStream)} and fills this
	 * {@code AbstractList2D} with them.
	 * @param stream - The {@link InputStream} associated with the file from which this
	 * {@code AbstractList2D} is loading.
	 * @throws IOException Any of the usual Input/Output related exceptions.
	 * @throws ClassCastException The values loaded from the {@code OutputStream} are 
	 * incompatible with this {@code AbstractList2D} 
	 * (for example, an <code>{@literal AbstractList2D<Integer>}</code> is loading from a 
	 * file created by an <code>{@literal AbstractList2D<Boolean>}</code>
	 * @throws ClassNotFoundException Class of a serialized object cannot be found.
	 * @throws java.io.InvalidObjectException Any value in the file is of {@link Enum} type 
	 * (because Enum defines its own {@link Enum#readObject(ObjectInputStream) readObject}
	 * method that always throws an error). May also be thrown for any of the obvious problems
	 * that can occur from deserialization (i.e., somebody moving the entire class to a 
	 * different package will completely screw it over)
	 */

	public void loadFill(InputStream stream)throws IOException, ClassCastException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(stream);
		Object[][] deserial = (Object[][]) in.readObject();
		for(Object[] xzv : deserial){
			Integer i = (Integer) xzv[0];
			Integer j = (Integer) xzv[1];
			Object value = xzv[2];
			this.addValue(i, j, (T) value);
		}
	}


	/**
	 * Writes all the values in this AbstractList2D to a file. There is no guarantee that the
	 * AbstractList2D will be restorable if the class of any of the values is updated/moved.
	 * For this reason, it may be a good idea to use (or first convert the AbstractList2D
	 * into an AbstractList2D of) primitive types and/or Strings to symbolize
	 * different values, or, use {@link java.io.Serializable}, because even changing the order of
	 * a few lines in a method can break deserializing older serialized versions of an object.
	 * <br>
	 * <br>
	 * <b>IMPORTANT</b>: {@link pixelmon.util.testing.Testomatic} contains
	 * {@link pixelmon.util.testing.Testomatic#save(AbstractList2D, String) a method} that simplifies
	 * the saving process, automatically saving the files into
	 * <code>{@literal <MCP folder>}/src/minecraft/pixelmon/util/L2D/</code>. Ideally, a simple, 
	 * temporary method should be written in Testomatic that saves the specific lists using 
	 * that method.<br>
	 * <b>WARNING</b>: {@link Enum} defines its own {@link Enum#readObject(ObjectInputStream) readObject}
	 * method that always throws an error. Do not attempt to serialize Enum values, or they
	 * will cause the resulting file to be un-deserializable. Instead, use primitive types
	 * or Strings to symbolize different Enum values, as is described above.
	 * @param stream
	 */
	public void save(OutputStream stream){
		ObjectOutputStream out = null;
		try{
			out	= new ObjectOutputStream(stream);
			ArrayList<Object[]> tempList = new ArrayList(elements);
			for(int x : xList()){
				for(int z : zList(x)){
					Object[] xzv = new Object[]{x, z, get(x, z)};
					tempList.add(xzv);
				}
			}
			Object[][] serial = tempList.toArray(new Object[0][0]);
			out.writeObject(serial);
		}catch(Exception e){
			e.printStackTrace();
		}finally{ //My first-ever 'finally-block'
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
