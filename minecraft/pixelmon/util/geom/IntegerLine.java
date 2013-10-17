package pixelmon.util.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class IntegerLine extends Line2D implements Serializable {

	public int x1, y1, x2, y2;

    /**
     * Constructs and initializes a Line with coordinates (0, 0) -> (0, 0).
     * @since 1.2
     */
    public IntegerLine() {
    }

    public IntegerLine(int x1, int y1, int x2, int y2) {
        setLine(x1, y1, x2, y2);
    }
    
    public IntegerLine(int[] p1, int[] p2){
    	setLine(p1[0], p1[1], p2[0], p2[1]);
    }
    
    public IntegerLine(int[] p1, int x2, int y2){
    	setLine(p1[0], p1[1], x2, y2);
    }

    public double getX1() {
        return (double) x1;
    }

    public double getY1() {
        return (double) y1;
    }

    public double getX2() {
        return (double) x2;
    }

    public double getY2() {
        return (double) y2;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public void setLine(double x1, double y1, double x2, double y2) {
        this.x1 = (int) x1;
        this.y1 = (int) y1;
        this.x2 = (int) x2;
        this.y2 = (int) y2;
    }

    public void setLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle2D getBounds2D() {
        float x, y, w, h;
        if (x1 < x2) {
            x = x1;
            w = x2 - x1;
        } else {
            x = x2;
            w = x1 - x2;
        }
        if (y1 < y2) {
            y = y1;
            h = y2 - y1;
        } else {
            y = y2;
            h = y1 - y2;
        }
        return new Rectangle2D.Float(x, y, w, h);
    }

	@Override
	public Point2D getP1() {
		return new Point2D.Float(x1, y1);
	}

	@Override
	public Point2D getP2() {
		return new Point2D.Float(x2, y2);
	}
}
