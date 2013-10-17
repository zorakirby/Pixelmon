package pixelmon.util.testing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

import net.minecraftforge.common.ForgeDirection;

import pixelmon.WorldHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;
import pixelmon.util.geom.Fractal;
import pixelmon.util.geom.ShapeHelper;
import pixelmon.worldGeneration.PathExcavator;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;
import pixelmon.worldGeneration.mysteryDungeon.DungeonEntranceStandard;
import pixelmon.worldGeneration.mysteryDungeon.MysteryDungeonFloor;
import pixelmon.worldGeneration.mysteryDungeon.RoomMarker;

public class TestingCanvas extends Canvas{

	private AbstractDrawable[] drawables;
	private double[] translation = new double[2];
	private double[] scale = {1D, 1D};
	private static boolean defaultDraw = true;
	private final int width, height;
	
	private static Ellipse2D circle = new Ellipse2D.Float(0, 0, 30, 30);
	public static AbstractList2D<Float> circleMap = FunctionHelper.gaussianBlur(new MappedList2D().modifyWithShape(circle, 1F), 3);

	public static TestingCanvas createSimpleScreen(int width, int height, String name){
		TestingCanvas result = new TestingCanvas(width, height);
		result.setPreferredSize(new Dimension(width, height));
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(result);
		frame.pack();
		frame.setVisible(true);
		return result;
	}
	
	public static TestingCanvas createSimpleScreen(String name){
		Dimension screenWH = Toolkit.getDefaultToolkit().getScreenSize();
		TestingCanvas result = new TestingCanvas(screenWH.width, screenWH.height);
		result.setPreferredSize(screenWH);
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(result);
		frame.pack();
		frame.setVisible(true);
		return result;
	}
	
	public TestingCanvas(int width, int height){
		super();
		this.width = width;
		this.height = height;
		this.setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g){
		if(this.drawables!=null){
			Graphics2D g2d = (Graphics2D) g;
			//g2d.scale(.6, .6);
			g2d.translate((int)translation[0], (int)translation[1]);
			g2d.scale(scale[0], scale[1]);
			for(AbstractDrawable drawable : drawables)
				drawable.draw(g2d);
		}
	}

	public void setDrawables(AbstractDrawable...drawables){
		this.drawables = drawables;
	}
	public void setTranslation(double x, double y){
		translation[0] = x;
		translation[1] = y;
	}
	
	public void setScale(double x, double y){
		scale[0] = x;
		scale[1] = y;
	}
}
