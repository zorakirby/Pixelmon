package pixelmon.grethen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MatrixTable<T> implements Iterable<T>
{

	private int rows;
	private int columns;
	private int shift;
	private Object[][] matrix;
	private HashMap<T, Entry<Integer, Integer>> map;

	public MatrixTable(int r, int c, int s)
	{
		rows = r;
		columns = c;
		if(shift < 0)
		{
			System.err.println("Matrix shift cannot be less than 0!");
			shift = 0;
		} else
			shift = s;
		matrix = new Object[rows][columns];
		map = new HashMap<T, Entry<Integer, Integer>>();
	}

	public MatrixTable(int r, int c)
	{
		this(r, c, 1);
	}

	public void put(int row, int column, T value)
	{
		matrix[row - shift][column - shift] = value;
		map.put(value, new Entry<Integer, Integer>(column, row));
	}

	public T get(int row, int column)
	{
		return matrixData(row, column);
	}

	public int rows()
	{
		return rows;
	}

	public int columns()
	{
		return columns;
	}

	public void fillEmptyWith(T value)
	{
		for(int y = shift; y < rows; y++)
			for(int x = shift; x < columns; x++)
				if(get(y, x) == null)
					put(y, x, value);
	}

	public void remove(int row, int column)
	{
		map.remove(matrixData(row, column));
		matrix[row - shift][column - shift] = null;
	}

	public Point2D getCoordOf(T value)
	{
		Entry<Integer, Integer> e = map.get(value);
		if(e != null)
			return new Point2D(e.getValue2(), e.getValue1());
		return null;
	}

	@SuppressWarnings("unchecked")
	private T matrixData(int row, int column)
	{
		return (T) matrix[row - shift][column - shift];
	}

	public void expand(int numRows, int numCols)
	{
		if(numRows <= 0)
		{
			System.err.println("Cannot expand MatrixTable by " + numRows + " rows!");
			return;
		}
		if(numCols <= 0)
		{
			System.err.println("Cannot expand MatrixTable by " + numCols + " columns!");
			return;
		}
		Object[][] temp = new Object[rows + numRows][columns + numCols];
		for(T ob : this)
		{
			Entry<Integer, Integer> e = map.get(ob);
			if(e != null)
				temp[e.getValue2() - shift][e.getValue1() - shift] = ob;
		}
		matrix = temp;
		rows += numRows;
		columns += numCols;
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for(Object[] oa : matrix)
		{
			s.append("[");
			for(Object ob : oa)
				s.append(ob + " ");
			s.deleteCharAt(s.lastIndexOf(" "));
			s.append("]\n");
		}
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	public Iterator<T> iterator()
	{
		ArrayList<T> a = new ArrayList<T>();
		for(Object[] o : matrix)
			for(Object obj : o)
				a.add((T) obj);
		return a.iterator();
	}

}

class Entry<T, U>
{
	private T value1;
	private U value2;

	public Entry(T val1, U val2)
	{
		value1 = val1;
		value2 = val2;
	}


	public T getValue1()
	{
		return value1;
	}

	public void setValue1(T value1)
	{
		this.value1 = value1;
	}

	public U getValue2()
	{
		return value2;
	}

	public void setValue2(U value2) {
		this.value2 = value2;
	}

	@SuppressWarnings("rawtypes")
	public boolean equals(Object o)
	{
		if(!(o instanceof Entry))
			return false;
		Entry e = (Entry) o;
		if(e.value1.getClass() != value1.getClass() && e.value2 != value1.getClass())
			return false;
		return value1.equals(e.value1) && value2.equals(e.value2);
	}

}