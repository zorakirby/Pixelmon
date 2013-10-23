package pixelmon.util.testing;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;


import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pixelmon.RandomHelper;
import pixelmon.enums.EnumTreasureRarity;
import pixelmon.util.AbstractList2D;
import pixelmon.util.ChancedWrapper;
import pixelmon.util.MappedList2D;
import pixelmon.util.PixelmonDebug;
import pixelmon.util.geom.Fractal;
import pixelmon.worldGeneration.mysteryDungeon.DungeonExtraTreasure;

public abstract class Testomatic {
	
	public static void main(String[] args){
		//debugRareTreasure();
		//saveDragons2();
		//loadDragon();
		//System.out.println(Testomatic.class.getName());
		//debugDrawing();
		//debugChancedWrapper();
		//debugNextFloatNrml();
	}
	
	/**
	 * Must be called {@link pixelmon.Pixelmon#postInitialized() after Pixelmon has finished Post-Init),
	 * otherwise... nothing will really happen.
	 */
	public static void debugRareTreasure(){
		Collection<ChancedWrapper> rareStuff = DungeonExtraTreasure.getEverything();
		Explorer.showSimpleExplorer(rareStuff, "Rare Items Debug");
	}
	
	/**
	 * @return A really simple ArrayList of all the letters in the alphabet, for easy
	 * testing with Collections/Lists
	 */
	public static ArrayList<Character> arrayListAZ(){
		ArrayList<Character> result = new ArrayList();
		for(int i = 0; i < 26; i++){
			result.add((char) ('A'+i));
		}
		return result;
	}
	
	public static void saveDragons2(){
		int amt = 0;
		int[] sizes = {95, 106, 127, 255, 383, 426, 511};
		for(int i = 12; i <= 18; i++){
			int size = sizes[i-12];
			AbstractList2D dragon = Fractal.dragon(size, i, true);
			System.out.println(i + ", " + dragon.minX() + " - " + dragon.maxX());
			if(saveL2D(dragon, "fractals/dragon_"+size + "_" + i)){
				amt++;
			}
		}
		System.out.println("Done! Successfully saved " + amt + " AbstractList2D's");
	}
	
	public static void saveDragons(){
		int amt = 0;
		for(int i = 12; i <= 18; i++){
			AbstractList2D dragon = Fractal.dragonLSystem(i, true);
			System.out.println(i + ", " + dragon.minX() + " - " + dragon.maxX());
			if(saveL2D(dragon, "fractals/dragon_"+i)){
				amt++;
			}
		}
		System.out.println("Done! Successfully saved " + amt + " AbstractList2D's");
	}
	
	public static void loadDragon(){
		File load = new File("D:/apps/MinecraftModding/MCP-1.6NEW/jars/dragon0.L2D");
		AbstractList2D<Float> result = new MappedList2D();
		try{
			result.loadFill(new FileInputStream(load));
		} catch (Exception e) {
			e.printStackTrace();
		}
		TestingCanvas canvas = TestingCanvas.createSimpleScreen("loadedDragon");
		canvas.setDrawables(new AbstractDrawable.FloatDrawing(result));
	}
	
	private static void debugDrawing(){
		Object choice = JOptionPane.showInputDialog(null, "Choose a Drawing Option", "Which Drawing?", QUESTION_MESSAGE, null, EnumDebugDrawings.valuesByString(), null);
		if(choice != null && choice instanceof EnumDebugDrawings){
			((EnumDebugDrawings)choice).makeDrawing();
		}
	}
	
	/**
	 * @return The values in {@code enumClass}, sorted by {@link Enum#name name}.
	 */
	public static <T extends Enum> T[] valuesByName(Class<T> enumClass){
		Method valsMethod = null;
		try {
			valsMethod = enumClass.getDeclaredMethod("values", new Class[0]);
			T[] vals = (T[]) valsMethod.invoke(null);
			TreeMap<String, T> temp = new TreeMap();
			for(T value : vals){
				temp.put(value.toString(), value);
			}
			return temp.values().toArray(vals);
		} catch (Exception nope) {
		}
		return null;
	}
	
	private static void debugChancedWrapper(){
		HashSet<ChancedWrapper> stuff = new HashSet();
		Random random = new Random();
		for(int i = 0; i < 100; i++){
			ChancedWrapper<Character> blah = new ChancedWrapper(Character.valueOf((char) ('A'+i)), random, random.nextFloat());
			stuff.add(blah);
		}
		ChancedWrapper w = ChancedWrapper.weightedChoice(stuff, random, true);
		System.out.println(w.object);
	}
	
	private static void debugNextFloat(){
		double iters = 20000;
		double hits = 0;
		for(int i = 0; i < iters; i++){
			float f = RandomHelper.nextFloat();
			boolean ultraRare = f <= EnumTreasureRarity.ULTRA_RARE.chance;
			if(ultraRare){
			System.out.println("Next Float #" + i + " : " + f);
			hits++;
			}
		}
		System.out.println("Ultra rare = " + (hits/iters));
	}
	
	private static void debugNextFloatNrml(){
		int iters = 20000;
		double hits = 0;
		Random random = new Random();
		float[] allGenerated = new float[iters];
		for(int i = 0; i < iters; i++){
			float f = random.nextFloat();
			System.out.println(f);
			allGenerated[i] = f;
			boolean ultraRare = f <= EnumTreasureRarity.ULTRA_RARE.chance;
			if(ultraRare){
			//System.out.println("Next Float #" + i + " : " + f);
			hits++;
			}
		}
		System.out.println("Ultra rare = " + (hits/iters));
		System.out.println(PixelmonDebug.doesFloatArrayRepeat(allGenerated));
	}
	
	public static File MCPFolder(){
		File mcpFolder = new File(".");
		String path = mcpFolder.getAbsolutePath();
		for(int i = 0; i < 3; i++)
			path = path.substring(0, path.lastIndexOf("\\"));
		return new File(path);
	}
	
	
	/**
	 * Saves the values of an {@link AbstractList2D} to a file in the following directory:<br>
	 * <code>"{@literal <MCP folder>}/src/minecraft/pixelmon/util/L2D/"</code><br>
	 * @param l2d - The AbstractList2D to be saved
	 * @param fileName - The resulting file's name. The saved file's extension will be ".L2D",
	 * regardless of what {@code fileName} actually ends with.
	 * @return true if nothing went wrong, false otherwise
	 */
	private static boolean saveL2D(AbstractList2D l2d, String fileName){
		File saveTo = Testomatic.MCPFolder();
		int end = fileName.lastIndexOf(".");
		if(end != -1)
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		fileName+=".L2D";
		saveTo = new File(saveTo, "src/minecraft/pixelmon/util/L2D/" + fileName);
		String folderName = saveTo.getAbsolutePath();
		folderName = folderName.substring(0, folderName.lastIndexOf("\\"));
		File folder = new File(folderName);
		try{
			if(folder.mkdirs())
				System.out.println("Creating directory : " + folder.getAbsolutePath());
			if(saveTo.createNewFile())
				System.out.println("Creating file : " + saveTo.getAbsolutePath());
			System.out.println("Saving " + l2d.size() + " AbstractList2D values to " + saveTo.getAbsolutePath());
			FileOutputStream out = new FileOutputStream(saveTo);
			l2d.save(out);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	/**
	 * @param title {@code Object} whose {@link Object#toString() toString()} result will be
	 * the title of the dialog box.
	 * @param message {@code Object} whose {@link Object#toString() toString()} result will be
	 * the message of the dialog box.
	 * @param duration - The duration, in milliseconds, the dialog box will be shown before going away.
	 */
	public static void showConcurrentDialog(Object title, Object message, final long duration){
		JOptionPane fyi = new JOptionPane(message);
		fyi.setBounds( 500 , 250 , 200 , 20 );
		final JDialog dialog = fyi.createDialog(title.toString());

		Thread thread = new Thread(){@Override public void run(){
			dialog.setEnabled(false);
			dialog.setModalityType(Dialog.ModalityType.MODELESS);
			dialog.setVisible(true);
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dialog.setVisible(false);
			dialog.dispose();
		}};
		thread.start();
	}

}
