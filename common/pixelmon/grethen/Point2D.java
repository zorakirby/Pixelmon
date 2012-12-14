package pixelmon.grethen;


public class Point2D implements Cloneable
{

	public int posX;
	public int posY;

	public Point2D(int x, int y)
	{
		posX = x;
		posY = y;
	}

	public Point2D()
	{
		this(0, 0);
	}

	public Point2D changePos(int x, int y)
	{
		return new Point2D(x, y);
	}

	public boolean equals(Object o)
	{
		if(!(o instanceof Point2D))
			return false;
		Point2D p = (Point2D) o;
		return p.posX == posX && p.posY == posY;
	}

	public Point2D clone()
	{
		return new Point2D(posX, posY);
	}

	public String toString()
	{
		return "(" + posX + ", " + posY + ")";
	}
}