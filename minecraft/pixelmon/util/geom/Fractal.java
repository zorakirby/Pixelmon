package pixelmon.util.geom;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.testing.StopWatch;

import net.minecraftforge.common.ForgeDirection;
import static pixelmon.util.FunctionHelper.PHI;

public class Fractal {
	
	public static final double
	// this is the symbol used in equations that looks like a power button symbol, with the straight part extended further.
	r = 0.7427429446246816,
	angA = -0.5741054276517762,
	angB = 0.8200600925461135;
	private static final AffineTransform
	goldScale1 = AffineTransform.getScaleInstance(r, r),
	goldScale2 = AffineTransform.getScaleInstance(r*r, r*r),
	goldLeft = AffineTransform.getRotateInstance(angA),
	goldRight = AffineTransform.getRotateInstance(angB),
	
	left45 = AffineTransform.getRotateInstance(Math.toRadians(-45)),
	right45 = AffineTransform.getRotateInstance(Math.toRadians(45)),
	scaleBetween45 = AffineTransform.getScaleInstance(.707, .707);
	
	protected final static LSystem dragon = new LSystem(new char[]{'X','Y'}, "FX", "X=X+YF", "Y=FX-Y");
	public static HashMap<Integer, AbstractList2D<Float>> usedDragons = new HashMap();
	public static MappedList2D<AbstractList2D<Float>> goldDragons = new MappedList2D();
	
	
	/**
	 * Preload important fractals here, because most, if not all, take a decent amount of 
	 * time to calculate.
	 */
	public static void preloadFractals(){
		System.out.println("[Pixelmon] Preloading fractals.");
		StopWatch s = new StopWatch();
		s.start();
		try{
		URL fractalURL = Fractal.class.getResource("/pixelmon/util/L2D/fractals");
		File fractalFolder = new File(fractalURL.toURI());
		File[] fractalFiles = fractalFolder.listFiles();
		for(File subFile : fractalFiles){
			if(subFile.getAbsolutePath().endsWith(".ini")){
				parseFractalFile(fractalFolder, subFile);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		s.stop();
		System.out.println(s.describe());
		System.out.println("[Pixelmon] Fractal preloading finished!");
	}
	
	protected static void parseFractalFile(File folder, File file){
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}catch(Exception e){
			e.printStackTrace();
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		String line = "";
		try{
		while((line = reader.readLine())!=null){
			String[] params = line.split(" ");
			if(params[0].equalsIgnoreCase("@subfolder")){
				folder = new File(folder, params[1]);
			}
			else if(params[0].equalsIgnoreCase("@dragon")){
				int iters = Integer.parseInt(params[1]);
				File dragonFile = new File(folder, params[2]);
				AbstractList2D<Float> dragon = new MappedList2D();
				dragon.loadFill(new FileInputStream(dragonFile));
				usedDragons.put(iters, dragon);
			}
		}
		}catch(Exception e){
			System.err.println("Error parsing fractal file : " + file.getAbsolutePath());
			System.err.println("The line was : \"" + line + "\"");
			e.printStackTrace();
		}
	}
	
	/**
	 * ...Yeah this takes at least a minute to finish.
	 * @param canvas
	 * @param widthHeight
	 */
	public static void MBrot(Canvas canvas, int widthHeight)
    {
		Graphics2D g2d = (Graphics2D) canvas.getGraphics();
		g2d.translate(100D, 0D);
		g2d.scale(.4, .4);
        float epsilon = 0.001F; // The step size across the X and Y axis
        float x;
        float y;
        int maxIterations = 1000; // increasing this will give you a more detailed fractal
        int maxColors = 0xffffff; // Change as appropriate for your display.

        Complex z;
        Complex c;
        int iterations;
        for(x=-2; x<=2; x+= epsilon)
        {
            for(y=-2; y<=2; y+= epsilon)
            {
                iterations = 0;
                c = new Complex(x, y);
                z = new Complex(0,0);
                boolean bounded = true;
                while(iterations < maxIterations)
                {
                    z = z.sqr().add(c);
                    iterations++;
                    if(z.abs() >= 2){
                    	bounded = false;
                    	break;
                    }
                }
                if(bounded){
                	int xint = (int) (widthHeight * ((z.x+4f)/4f));
                	int yint = (int) (widthHeight * ((z.i+4f)/4f));
                	g2d.setColor(Color.WHITE);
                	g2d.drawRect(xint, yint, 1, 1);
                }
            }
        }
    }
	
	public static AbstractList2D<Float> dragonLSystem(int iterations, boolean blur){
		if(usedDragons.containsKey(iterations)){
			return usedDragons.get(iterations);
		}
		String commands = dragon.generate(iterations);
		AbstractList2D<Float> result = new MappedList2D();
		Point2D.Float prev = new Point2D.Float(0,0);
		ForgeDirection[] eswn = {EAST, SOUTH, WEST, NORTH};
		int currentDirection = 0;
		for(char command : commands.toCharArray()){
			switch(command){
			case 'F' : {
				int x = (int) (eswn[currentDirection%4].offsetX + prev.x);
				int z = (int) (eswn[currentDirection%4].offsetZ + prev.y);
				Point2D.Float newPoint = new Point2D.Float(x, z);
				prev = newPoint;
				result.addValue(x, z, 1F);
			} break;
			case '+' : currentDirection+=1;break;
			case '-' : currentDirection-=1;break;
			}
			if(currentDirection < 0)
				currentDirection += (currentDirection + 4)%4;
		}
		if(blur){
			result = FunctionHelper.sequencedBlur(result, 4, true, false, false).recreateWithMinAtZero();
		}
		usedDragons.put(iterations, (MappedList2D<Float>) result);
		return result;

	}
	
	public static AbstractList2D<Float> goldDragon(int startSize, int iterations, boolean blur){
		if(goldDragons.get(startSize, iterations) != null){
			return goldDragons.get(startSize, iterations);
		}
		Collection<Line2D.Float> lines = goldDragonLines(startSize, iterations).getAll();
		AbstractList2D<Float> result = AbstractList2D.fromLines(MappedList2D.class, lines);
		if(blur)
			result = FunctionHelper.sequencedBlur(result, 2, true, false, true, true, false);
		goldDragons.addValue(startSize, iterations, result);
		return result;
	}
	
	public static AbstractList2D<Float> dragon(int startSize, int iterations, boolean blur){
		Collection<Line2D.Float> lines = dragonLines(startSize, iterations).getAll();
		AbstractList2D<Float> result = AbstractList2D.fromLines(MappedList2D.class, lines);
		if(blur)
			result = FunctionHelper.sequencedBlur(result, 2, true, false, true, true, false);
		return result;
	}
	
	
	public static ShapeHolder<Line2D.Float> goldDragonLines(int startingSize, int iterations){
		Point2D start = new Point2D.Float(0, 0);
		Point2D end = new Point2D.Float(startingSize, 0);
		float newLength = (float) (startingSize*r);
		Point2D mid = new Point2D.Float(newLength, 0);
		mid = goldLeft.transform(mid, null);
		Line2D a = new Line2D.Float(start, mid);
		Line2D b = new Line2D.Float(mid, end);
		ShapeHolder<Line2D.Float> result = new ShapeHolder(a, b);
		for(int i = 0; i <= iterations; i++){
			for(ShapeHolder<Line2D.Float> holder : result.getInnermostHolders()){
				replaceForGoldDragon(holder);
			}
		}
		return result;
	}
	
	public static ShapeHolder<Line2D.Float> dragonLines(int startingSize, int iterations){
		Point2D start = new Point2D.Float(0, 0);
		Point2D end = new Point2D.Float(startingSize, 0);
		float newLength = startingSize * 0.707F;
		Point2D mid = new Point2D.Float(newLength, 0);
		mid = left45.transform(mid, null);
		Line2D a = new Line2D.Float(start, mid);
		Line2D b = new Line2D.Float(mid, end);
		ShapeHolder<Line2D.Float> result = new ShapeHolder(a, b);
		for(int i = 0; i <= iterations; i++){
			for(ShapeHolder<Line2D.Float> holder : result.getInnermostHolders()){
				replaceForDragon(holder);
			}
		}
		return result;
		
	}

	private static void replaceForDragon(ShapeHolder<Line2D.Float> part){
		Line2D.Float line1 = (Line2D.Float) part.getA();
		Line2D.Float line2 = (Line2D.Float) part.getB();
		
		AffineTransform move = AffineTransform.getTranslateInstance(-line1.x1, -line1.y1);
		Point2D.Float mid1 = new Point2D.Float(line1.x2, line1.y2);
		move.transform(mid1, mid1);
		scaleBetween45.transform(mid1, mid1);
		left45.transform(mid1, mid1);
		try {
			move.invert();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		move.transform(mid1, mid1);
		Line2D.Float line1a = new Line2D.Float(line1.x1, line1.y1, mid1.x, mid1.y);
		Line2D.Float line1b = new Line2D.Float(mid1.x, mid1.y, line1.x2, line1.y2);
		
		move = AffineTransform.getTranslateInstance(-line2.x1, -line2.y1);
		Point2D.Float mid2 = new Point2D.Float(line2.x2, line2.y2);
		move.transform(mid2, mid2);
		scaleBetween45.transform(mid2, mid2);
		right45.transform(mid2, mid2);
		try {
			move.invert();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		move.transform(mid2, mid2);
		Line2D.Float line2a = new Line2D.Float(line2.x1, line2.y1, mid2.x, mid2.y);
		Line2D.Float line2b = new Line2D.Float(mid2.x, mid2.y, line2.x2, line2.y2);
		
		ShapeHolder<Line2D.Float> holderA = new ShapeHolder<Line2D.Float>(line1a, line1b);
		ShapeHolder<Line2D.Float> holderB = new ShapeHolder<Line2D.Float>(line2a, line2b);
		part.setA(holderA);
		part.setB(holderB);
	}

	private static void replaceForGoldDragon(ShapeHolder<Line2D.Float> part){
		Line2D.Float line1 = (Line2D.Float) part.getA();
		Line2D.Float line2 = (Line2D.Float) part.getB();
		
		AffineTransform move = AffineTransform.getTranslateInstance(-line1.x1, -line1.y1);
		Point2D.Float mid1 = new Point2D.Float(line1.x2, line1.y2);
		move.transform(mid1, mid1);
		goldScale1.transform(mid1, mid1);
		goldLeft.transform(mid1, mid1);
		try {
			move.invert();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		move.transform(mid1, mid1);
		Line2D.Float line1a = new Line2D.Float(line1.x1, line1.y1, mid1.x, mid1.y);
		Line2D.Float line1b = new Line2D.Float(mid1.x, mid1.y, line1.x2, line1.y2);
		
		move = AffineTransform.getTranslateInstance(-line2.x1, -line2.y1);
		Point2D.Float mid2 = new Point2D.Float(line2.x2, line2.y2);
		move.transform(mid2, mid2);
		goldScale2.transform(mid2, mid2);
		goldRight.transform(mid2, mid2);
		try {
			move.invert();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		move.transform(mid2, mid2);
		Line2D.Float line2a = new Line2D.Float(line2.x1, line2.y1, mid2.x, mid2.y);
		Line2D.Float line2b = new Line2D.Float(mid2.x, mid2.y, line2.x2, line2.y2);
		
		ShapeHolder<Line2D.Float> holderA = new ShapeHolder<Line2D.Float>(line1a, line1b);
		ShapeHolder<Line2D.Float> holderB = new ShapeHolder<Line2D.Float>(line2a, line2b);
		part.setA(holderA);
		part.setB(holderB);
	}
	

        
        

}
