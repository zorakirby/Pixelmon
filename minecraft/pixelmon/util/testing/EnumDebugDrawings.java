package pixelmon.util.testing;

import java.awt.Canvas;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import pixelmon.util.AbstractList2D;
import pixelmon.util.FunctionHelper;
import pixelmon.util.MappedList2D;
import pixelmon.util.geom.Fractal;
import pixelmon.util.geom.ShapeHelper;
import pixelmon.worldGeneration.PathExcavator;
import pixelmon.worldGeneration.WorldGenMysteryDungeon;
import pixelmon.worldGeneration.mysteryDungeon.DungeonEntranceStandard;
import pixelmon.worldGeneration.mysteryDungeon.MysteryDungeonFloor;
import pixelmon.worldGeneration.mysteryDungeon.RoomMarker;

public enum EnumDebugDrawings {
	
	Path_Excavator("Path Excavator"){public void makeDrawing(){FS.pathExcavator();}},
	MBrot("MBrot Fractal"){public void makeDrawing(){FS.MBrot();}},
	Dragon("Dragon Fractal"){public void makeDrawing(){FS.dragon();}},
	Gold_Dragon_Lines ("Line-based Golden Dragon Fractal"){public void makeDrawing(){FS.goldDragonLines();}},
	Gold_Dragon_Points("Point-based Golden Dragon Fractal"){public void makeDrawing(){FS.goldDragonPoints();}},
	Warbled_Circle("Warbled Circle"){public void makeDrawing(){FS.warbledCircle();}},
	Warbled_Window("Warbled Circle Window"){public void makeDrawing(){FS.crazehWarbledCircleWindow();}},
	Mystery_Dungeon_Floor("Single Mystery Dungeon Floor"){public void makeDrawing(){FS.mysteryDungeonFloor();}},
	Complete_Mystery_Dungeon("Complete 10-Floor Mystery Dungeon"){public void makeDrawing(){FS.mysteryDungeonFull();}},
	Room_Edge_Tester("Mystery Dungeon Room-Edge Test"){public void makeDrawing(){FS.roomEdge();}}
	;
	
	public final String name;
	
	EnumDebugDrawings(String name){
		this.name = name;
	}
	
	
	public abstract void makeDrawing();
	
	@Override
	public String toString(){
		return this.name;
	}
	
	protected static class FS{
		
		public static void pathExcavator(){
			PathExcavator excavator = new PathExcavator(0, 10, 20, -16, 0);
			AbstractList2D path = excavator.genPath(new Random(), 0);
			AbstractList2D.sumCheckerBoard(path, -1);
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "Path Excavator");
			canvas.setDrawables(new AbstractDrawable.IntegerDrawing(path));
			canvas.setTranslation(150, 250);
			canvas.setScale(10, 10);
		}	
		
		public static void dungeonCommonStrip(){
			Collection<Integer> ints = MysteryDungeonFloor.getValid1x1RoomStrip(65, 2).keySet();
			Collection<Integer> ints2 = new ArrayList();
			for(int i = 0; i < 65; i++){
				ints2.add(i);
			}
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "Dungeon Column/Row Validity Strip");
			canvas.setDrawables(new AbstractDrawable.NonNullDrawing(ints));
			canvas.setTranslation(50, 50);
			canvas.setScale(10, 10);
		}
		
		public static void roomEdge(){
			RoomMarker room = new RoomMarker(4,4,25,75, 1, false);
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000,700, "Room Edge Test");
			canvas.setDrawables(new AbstractDrawable.IntegerDrawing(room.getAllEdgePoints(false).checkerboard(1)));
			//AbstractList2D<Integer> map = room.getAllEdgePointsForgeDir(false);//.checkerboard(5);
			//map.combine(room.getAllEdgePoints(), false);
			//canvas.drawable = new IDrawable.IntegerDrawing(map);
			canvas.setTranslation(15, 15);
			canvas.setScale(5, 5);
		}
		
		public static void mysteryDungeonFull(){
			int floor = 1;
			StopWatch stopwatch = new StopWatch();
			stopwatch.start();
			Random random = new Random();
			MysteryDungeonFloor[] floors = new MysteryDungeonFloor[10];
			AbstractList2D<Integer>[] floorMaps = WorldGenMysteryDungeon.floorMaps(65, 65, floors, random, true);
			stopwatch.suspend();
			System.out.println(stopwatch.describe());
			
			for(int i = 0; i < 10; i++){
				AbstractList2D<Integer> floorMap = floorMaps[i];
				floorMap.combine(floors[i].getChoosablePoints().convert(1), true);
				if(i == 0){
					DungeonEntranceStandard.entrancesOn4Sides(floors[i], floorMap, random);
				}
				TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "MysteryDungeon - Floor " + floor++);
				canvas.setDrawables(new AbstractDrawable.IntegerDrawing(floorMap));
				canvas.setTranslation(50, 50);
				canvas.setScale(10, 10);
			}
		}
		
		public static void mysteryDungeonFloor(){
			StopWatch stopwatch = new StopWatch();
			stopwatch.start();
			Random rand = new Random();
			MysteryDungeonFloor dungeon = new MysteryDungeonFloor(65, 65, rand, false);
			AbstractList2D<Integer> ints = dungeon.floorMain(new Random(), 2);//.checkerboard(-1);
			stopwatch.suspend();
			System.out.println(stopwatch.describe());
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "Mystery Dungeon");
			canvas.setDrawables(new AbstractDrawable.IntegerDrawing(ints));
			canvas.setTranslation(50, 50);
			canvas.setScale(10, 10);
		}
		
		public static void warbledCircle(){
			Point2D[] circleparts = new Point2D[12];
			Shape warb = ShapeHelper.warbledCircle(64, 5, null, circleparts);
			AbstractList2D<Float> points = new MappedList2D<Float>().modifyWithShape(warb, 1F);
			AbstractList2D<Integer> circlePoints = new MappedList2D();
			for(Point2D p : circleparts)
				circlePoints.addValue((int)p.getX(), (int) p.getY(), 1);
			points = FunctionHelper.sequencedBlur(points, 3, true, false);
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "Warbled circle");
			canvas.setDrawables(new AbstractDrawable.FloatDrawing(points), new AbstractDrawable.IntegerDrawing(circlePoints));
			canvas.setTranslation(100, 100);
		}
		
		public static void crazehWarbledCircleWindow(){
			JOptionPane pane = new JOptionPane("WHOOOAAAAAAA");
			pane.setPreferredSize(new Dimension(300, 300));
			JDialog dialog = pane.createDialog("WAAAAAHHHOOOOOOOO!");
			Path2D warb = ShapeHelper.warbledCircle(200, 20, new Random(), null);
			AffineTransform shift = AffineTransform.getTranslateInstance(150, 150);
			warb.transform(shift);
			dialog.dispose();
			dialog.setUndecorated(true);
			dialog.setShape(warb);
			dialog.setVisible(true);
			
		}

		public static void dragon(){
			TestingCanvas canvas = TestingCanvas.createSimpleScreen("Dragon");
			Testomatic.showConcurrentDialog("Just so you know...", "This may take a couple of seconds", 500L);
			AbstractList2D<Float> dragon = Fractal.dragon(18, true);
			canvas.setDrawables(new AbstractDrawable.FloatDrawing(dragon));
		}
		

		public static void goldDragonLines(){
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "Golden Dragon");
			StopWatch gDragonTimer = new StopWatch("Golden Dragon Timer");
			gDragonTimer.start();
			Collection<Line2D.Float> lines = Fractal.goldDragonLines(1200, 15).getAll();
			gDragonTimer.stop();
			System.out.println(gDragonTimer.describe());
			canvas.setTranslation(350, 500);
			canvas.setScale(.5, .5);
			canvas.setDrawables(new AbstractDrawable.LineDrawing(lines));
		}
		
		public static void goldDragonPoints(){
			TestingCanvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "Golden Dragon 2");
			canvas.setTranslation(300, 700);
			
			AbstractList2D<Float> points = Fractal.goldDragon(400, 15, true);
			canvas.setDrawables(new AbstractDrawable.FloatDrawing(points));
		}

		public static void MBrot(){
			Canvas canvas = TestingCanvas.createSimpleScreen(1000, 700, "FractalTest");
			Fractal.MBrot(canvas, 700);
		}
	}

}
