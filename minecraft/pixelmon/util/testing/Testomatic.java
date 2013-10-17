package pixelmon.util.testing;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;

import java.awt.Dialog;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import pixelmon.RandomHelper;
import pixelmon.enums.EnumTreasureRarity;
import pixelmon.util.ChancedWrapper;
import pixelmon.util.PixelmonDebug;

public abstract class Testomatic {
	
	public static void main(String[] args){
		//debugDrawing();
		debugChancedWrapper();
		//debugNextFloatNrml();
	}
	
	private static void debugDrawing(){
		Object choice = JOptionPane.showInputDialog(null, "Choose a Drawing Option", "Which Drawing?", QUESTION_MESSAGE, null, EnumDebugDrawings.values(), null);
		if(choice != null && choice instanceof EnumDebugDrawings){
			((EnumDebugDrawings)choice).makeDrawing();
		}
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
