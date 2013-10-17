package pixelmon.util.geom;

import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Random;

import pixelmon.RandomHelper;
import pixelmon.WorldHelper;

import net.minecraftforge.common.ForgeDirection;
import static net.minecraftforge.common.ForgeDirection.*;


public abstract class ShapeHelper {
	
	private static Random RANDOM = new Random();
	
	
	public static Ellipse2D.Float centeredEllipse(float width, float length){
		return new Ellipse2D.Float(-width/2, -length/2, width, length);
	}
	
	/**
	 * @param diameter - base diameter of the circlesque function.
	 * @param deviation - actual min/max offset value for random
	 * @param rand - The <code>Random</code> object to be used for random number generation,
	 * or <code>null</code> to use ShapeHelper's own static <code>Random</code>
	 * @param points - Point2D array to store resulting circle points and handles.
	 * If {@code Points} is {@code null} or does not have at least 12 elements, no
	 * errors will be thrown, but... well, you know.
	 * @return
	 */
	public static Path2D warbledCircle(float diameter, float deviation, Random rand, Point2D[] points){
		Path2D result = new Path2D.Float();
		Point2D.Float[] circlePoints = new Point2D.Float[4]; //CCW
		Point2D.Float[] handles1 = new Point2D.Float[4]; //CCW
		Point2D.Float[] handles2 = new Point2D.Float[4];
		for(int i = 0; i < 4; i++){
			int next = (i + 1) > 3 ? 0 : i+1;
			float x = WorldHelper.NWSE[i].offsetX * diameter * .5F;
			float z = WorldHelper.NWSE[i].offsetZ * diameter * .5F;
			
			circlePoints[i] = (Point2D.Float) pointNear(x, z, deviation, rand);
			
			float handleOffsetX = WorldHelper.NWSE[next].offsetX*.276F*diameter;
			float handleOffsetZ = WorldHelper.NWSE[next].offsetZ*.276F*diameter;
			
			Point2D.Float base = (Float) pointNear(handleOffsetX, handleOffsetZ, deviation*.25F, rand);
			
			handles1[i] = new Point2D.Float(circlePoints[i].x+base.x, circlePoints[i].y + base.y);
			handles2[i] = new Point2D.Float(circlePoints[i].x-base.x, circlePoints[i].y - base.y);
		}
		for(int i = 0; i < 4; i++){
			int next = (i + 1) > 3 ? 0 : i+1;
			CubicCurve2D.Float curve = curveFrom4Points(circlePoints[i], handles1[i], handles2[next], circlePoints[next]);
			result.append(curve, true);
			try{
				points[i] = circlePoints[i];
				points[i+4] = handles1[i];
				points[i+8] = handles2[i];
			}catch(Exception e){}
		}
		return result;
	}
	
	
	public static Point2D pointNear(float x, float z, float deviation, Random rand){
		if(rand == null)
			rand = RANDOM;
		x += RandomHelper.useRandomForNumberBetween(rand, -deviation, deviation);
		z += RandomHelper.useRandomForNumberBetween(rand, -deviation, deviation);
		return new Point2D.Float(x, z);
	}
	
	public static CubicCurve2D.Float curveFrom4Points(Point2D.Float start, Point2D.Float ctrl1, Point2D.Float ctrl2, Point2D.Float end){
		return new CubicCurve2D.Float(start.x, start.y, ctrl1.x, ctrl1.y, ctrl2.x, ctrl2.y, end.x, end.y);
	}

}
