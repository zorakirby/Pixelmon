package pixelmon.util;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeDirection;

public class FunctionHelper {
	
	
	/**
	 * <i>phi</i>, the golden ratio
	 */
	public static final double PHI =1.618033988749895; //(1D + Math.sqrt(5))/2D;

	/**
	 * 
	 * @param x : independent variable
	 * @param a : sort of 'stretches' the function.
	 * @param b : hard to explain. if positive, function increases, if negative, decreases
	 * @param c : the ceiling, max, horizontal asymptote, or whatever name ya' wanna give it.
	 * <br>
	 * <b>Note:</b> the center point(inflection point) = [(ln a)/b, c/2]<br>
	 * <b>Check Out:</b> "http://www.cs.xu.edu/math/math120/01f/logistic.pdf" to learn more about logistic functions (copy into web browser if you want to read it online, or ctrl+click to download directly)
	 */
	public static double logistic(double x, double a, double b, double c){
		return c / (1 + a * Math.pow(Math.E, - b * x));
	}
	
	
	
	/**
	 * Sine-Wave function
	 * @param x : independent variable
	 * @param a : multiples of this number will be (or EXTREEEEEMELY CLOSE TO) 0; so if a = 5, then zeros will be at 5, 10, 15, 20...
	 * @param shift : shifts function to the left (if positive) or right (if negative)
	 * @param scale : multiplies the output. If it's 1 (so no scale) the min/max will be -1, 1.
	 */
	public static double sin(double x, double a, double shift, double scale){
		return scale * Math.sin((float) ((x+shift)/ a * Math.PI));
	}
	
	public static double slider(double x, double min, double max){
		return clampDouble((x - min) / (max - min), 0D, 1D);
	}
	
	public static double inverseSlider(double x, double min, double max){
		return x*(max-min) + min;
	}
	
	public static double clampDouble(double in, double min, double max){
		return in < min ? min : (in > max ? max : in);
	}
	
	
	/**Don't use this method unless you don't want to worry about bitmasking. 
	 * It's way easier to just type "&-2" in your code.<br>
	 * The reason for the usage of '-2' is because in binary, the bit
	 * pattern of -2 is all 1's except for 0x1:<br>
	 * <code>-2 = 11111111111111111111111111111110</code>
	 * @return the same number with <code>0x1</code> turned off (returns the input number if it's even, or the input number-1 if odd)
	 */
	public static int even(int i){
		return i & -2;
	}
	
	public static double perfectSin(double x, double length, double minIN, double maxIN){
		System.out.print("Min = " + minIN + ", max = " + maxIN);
		double input = slider(x, minIN, maxIN);
		double result =  sin(input, length, length*.5, 1);
		System.out.println(", In = " + x + ", input = " + input +", result = " +result);
		return result;
	}
	
	
	/**
	 * Pythagorean theorem. Derp.
	 */
	public static double dist(double x1, double y1, double x2, double y2){
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}
	
	
	
	public static HashMap<Integer, HashMap<Integer, Double>> weirdBlur(HashMap<Integer, HashMap<Integer,Double>> points, int radius){
		for(Integer i : points.keySet()){
			HashMap<Integer, Double> column = points.get(i);
			for(Integer j : column.keySet()){
				Double point = column.get(j);
				double average = 0;
				float div = 0;
				for(int x = -radius; x <= radius; x++){
					for(int y = -radius; y <= radius; y++){
						div++;
						Double add = null;
						try{
							add = points.get(i+x).get(j+y);
						}catch(NullPointerException e){
							add = 0D;
						}
						//System.out.println(add);
						average+= add!=null ? add : 0;
					}
				}
				System.out.println(average);
				average/= div;
				points.get(i).put(j, average);
			}
		}
		return points;
	}
	
	public static AbstractList2D<Float> boxBlur(AbstractList2D<Float> points, int radius){
		return allBlur(points, radius, true);
	}
	
	public static AbstractList2D<Float> limitedBlur(AbstractList2D<Float> points, int radius){
		return allBlur(points, radius, false);
	}
	

	
	
	private static AbstractList2D<Float> allBlur(AbstractList2D<Float> points, int radius, boolean nulls){
		AbstractList2D result = points.createNew();
		for(Integer i : points.xList()){
			Collection<Integer> column = points.zList(i);
			for(Integer j : column){
				Float point = points.get(i, j);
				float average = 0F;
				float div = 0;
				for(int x = -radius; x <= radius; x++){
					for(int y = -radius; y <= radius; y++){
						//if(dist(0, 0, x, y)>radius)
						//	continue;
						Float add = points.get(x+i, y+j);
						if(nulls || add!=null){
							average+= add!=null ? add : 0;
							div++;
						}
					}
				}
				average/= div;
				result.addValue(i, j, average);
			}
		}
		return result;
	}
	
	public static AbstractList2D<Float> gaussianBlur(AbstractList2D<Float> points, int radius){
		AbstractList2D<Float> result = points;
		for(int i = 0; i < 3; i++){
			result = boxBlur(result, radius);
		}
		return result;
	}
	
	/**
	 * @param limits - Sequence of booleans telling whether or not each blur should be limited to 
	 * only the values in <code>points</code> or also factor-in values that do not exist 
	 * in <code>points</code>. That is to say, coordinates that are within the radius, but do not 
	 * exist within <code>points</code> are considered to be '0' and are used in calculating the 
	 * average, as opposed to ignoring them.
	 * @return
	 */
	public static AbstractList2D<Float> sequencedBlur(AbstractList2D<Float> points, int radius, boolean... extendBeyond){
		for(int i = 0; i < extendBeyond.length; i++){
			points = allBlur(points, radius, extendBeyond[i]);
		}
		return points;
	}	
	
	public enum FunctionType{
		LOG,
		SIN,
		PERFECTSIN
		;
		
		public double function(double input, double...args){
			switch(this){
			case LOG : return FunctionHelper.logistic(input, args[0], args[1], args[2]);
			case SIN : return FunctionHelper.sin(input, args[0], args[1], args[2]);
			case PERFECTSIN : return FunctionHelper.perfectSin(input, args[0], args[1], args[2]);
			default : return Double.NEGATIVE_INFINITY;
			}
		}
	}
	

	
	
}
