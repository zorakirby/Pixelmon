package pixelmon.util.testing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import pixelmon.util.AbstractList2D;
import pixelmon.util.CommonHelper;
import pixelmon.util.FunctionHelper;
import pixelmon.util.PixelmonDebug;

public abstract class AbstractDrawable{
	protected boolean statedDrawingException = false;
	public abstract void draw(Graphics2D g2d);


	public static class ListingWrapper<T>{
		private Object listing;
		private boolean is2D;
		public ListingWrapper(AbstractList2D<T> list2d){
			this.listing = list2d;
			this.is2D = true;
		}
		public ListingWrapper(Collection<T> list){
			this.listing = list;
			this.is2D = false;
		}
		public ListingWrapper(Map<Integer, T> map){
			this.listing = map;
			this.is2D = false;
		}

		public boolean is2D(){
			return is2D;
		}

		public Object getListing(){
			return listing;
		}
	}

	/**
	 * Draws gray points at all mapping values, with black being a little less than the 
	 * mapping's minimum value, and white being the mapping's max value
	 * @author Jack
	 *
	 */
	public static class IntegerDrawing extends AbstractDrawable{
		ListingWrapper<Integer> points;
		private double min, max;

		public IntegerDrawing(AbstractList2D<Integer> points){
			this.points = new ListingWrapper(points);
			this.min = points.minVal()-.1;
			this.max = points.maxVal();
		}
		public IntegerDrawing(Map<Integer, Integer> points){
			this.points = new ListingWrapper(points);
			this.min = Collections.min(points.values()) - .1;
			this.max = Collections.max(points.values());
		}

		public IntegerDrawing(AbstractList2D<Integer> points, double minDifference){
			this.points = new ListingWrapper(points);
			this.min = points.minVal()-(Math.abs(minDifference));
			this.max = points.maxVal();
			if(this.min >= this.max)
				this.min = this.max -.1;
		}

		public IntegerDrawing(Map<Integer, Integer> points, double minDifference){
			this.points = new ListingWrapper(points);
			this.min = Collections.min(points.values())-(Math.abs(minDifference));
			this.max = Collections.max(points.values());
			if(this.min >= this.max)
				this.min = this.max -.1;
		}

		@Override
		public void draw(Graphics2D g2d) {
			if(points.is2D())
				draw2D(g2d);
			else
				draw1D(g2d);

		}
		public void draw2D(Graphics2D g2d){
			AbstractList2D<Integer> l2d = (AbstractList2D<Integer>) points.getListing();
			for(Integer i : l2d.xList()){
				Collection<Integer> column = l2d.zList(i);
				for(Integer j : column){
					//float height = points.getValue(i, j);
					//g2d.setColor(new Color(height, height, height));
					try{
					float value = (float) FunctionHelper.slider((Integer)l2d.get(i, j), min, max);
					g2d.setColor(new Color(value, value, value));
					g2d.drawRect(i, j, 0, 0);
					}catch(Exception e){
						if(!statedDrawingException){
							statedDrawingException = true;
							System.err.println("An error occured trying to draw this IntegerDrawing");
							System.err.println("The value that was attempted for use with drawing was : " + l2d.get(i, j));
							System.err.println("The class of the points list was " + points.getListing().getClass());
							System.err.println("And just for the heck of it, the blank Object in CommonHelper is " + CommonHelper.THING);
						}
					}
				}
			}
		}

		public void draw1D(Graphics2D g2d){
			Map<Integer, Integer> map = (Map<Integer, Integer>) points.getListing();
			for(Integer i : map.keySet()){
				float value = (float) FunctionHelper.slider(map.get(i), min, max);
				g2d.setColor(new Color(value, value, value));
				g2d.drawRect(i, 0, 0, 0);
			}
		}

	}

	/**
	 * Draws gray points at all mapping values, with the lightness being the value's intensity
	 * from 0 to 1
	 * @author Jack
	 *
	 */
	public static class FloatDrawing extends AbstractDrawable{
		AbstractList2D<Float> points;

		public FloatDrawing(AbstractList2D<Float> points){
			this.points = points;
		}

		@Override
		public void draw(Graphics2D g2d) {
			for(Integer i : points.xList()){
				Collection<Integer> column = points.zList(i);
				for(Integer j : column){
					float height = points.get(i, j);
					g2d.setColor(new Color(height, height, height));
					g2d.drawRect(i, j, 1, 1);
				}
			}
		}

	}

	/**
	 * Draws white lines in the list
	 * @author Jack
	 *
	 */
	public static class LineDrawing extends AbstractDrawable{
		Collection<? extends Line2D> lines;

		public LineDrawing(Collection<? extends Line2D> lines){
			this.lines = lines;
			System.out.println("The LineDrawing from " + PixelmonDebug.prevMethod() + " contains " + lines.size() + " lines.");
		}

		@Override
		public void draw(Graphics2D g2d) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.WHITE);
			for(Line2D line : lines){
				g2d.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());
			}
		}
	}

	/**
	 * Draws white points at all mapping values
	 * @author Jack
	 *
	 */
	public static class NonNullDrawing extends AbstractDrawable{
		private ListingWrapper points;

		public NonNullDrawing(AbstractList2D points){
			this.points = new ListingWrapper(points);
		}

		public NonNullDrawing(Collection<Integer> points){
			this.points = new ListingWrapper(points);
		}

		@Override
		public void draw(Graphics2D g2d) {
			if(points.is2D)
				draw2D(g2d);
			else
				draw1D(g2d);
		}

		public void draw1D(Graphics2D g2d){
			Collection<Integer> list = (Collection<Integer>) points.getListing();
			for(int i : list){
				g2d.setColor(Color.WHITE);
				g2d.drawRect(i, 0, 0, 0);
			}
		}


		public void draw2D(Graphics2D g2d){
			AbstractList2D<?> l2d = (AbstractList2D) points.getListing();
			for(int i : l2d.xList()){
				for(int j : l2d.zList(i)){
					g2d.setColor(Color.WHITE);
					g2d.drawRect(i, j, 0, 0);
				}
			}
		}
	}
	
	public static class Lister extends AbstractDrawable{
		
		protected Collection col;
		protected static final int extension = 3;
		public Lister(Collection col){
			this.col = col;
		}

		@Override
		public void draw(Graphics2D g2d) {
			FontMetrics metrics = g2d.getFontMetrics();
			int y = 0;
			for(Object item : col){
				String describe = item.toString();
				g2d.setColor(Color.CYAN);
				Rectangle2D bounds = metrics.getStringBounds(describe, g2d);
				g2d.fillRoundRect(0, y, (int) bounds.getWidth(), (int) bounds.getHeight()+extension, 10, 10);
				y+=bounds.getHeight();
				g2d.setColor(Color.BLACK);
				g2d.drawString(describe, 0, y);
				y+=extension+2;
			}
		}
	}

}
