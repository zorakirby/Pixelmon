package pixelmon.util.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.swing.Timer;

import pixelmon.util.PixelmonDebug;

/**
 * Class mainly for gauging how efficient certain code is.
 */
public class StopWatch extends org.apache.commons.lang3.time.StopWatch{
	private static Field runningStateField, splitStateField;
	private String timerName;
	private static int id = 0;
	
	static{
		try {
			runningStateField =
	org.apache.commons.lang3.time.StopWatch.class.getDeclaredField("runningState");
			runningStateField.setAccessible(true);
			splitStateField =
	org.apache.commons.lang3.time.StopWatch.class.getDeclaredField("splitState");
			splitStateField.setAccessible(true);
					
		} catch (Exception cannotHappen) {
		}
	}
	/**
	 * Constructs a new {@code StopWatch} with the name being {@link PixelmonDebug#prevMethod() the method calling this constructor}
	 */
	public StopWatch(){
			this.timerName = PixelmonDebug.prevMethod();
	}
	
	public StopWatch(String name){
		this.timerName = name;
	}

	public String identify(){
		return "The Stopwatch \"" + timerName + "\" ";
	}
	
	public int getRunningState(){
		try {
			return runningStateField.getInt(this);
		} catch (Exception cannotHappen) {}
		return -1;
	}
	
	public int getSplitState(){
		try {
			return splitStateField.getInt(this);
		} catch (Exception cannotHappen) {}
		return -1;
	}
	public String describe(){
		String result = identify();
		switch(this.getRunningState()){
		case 0 : return result + "has not been started yet.";
		case 1 : return result + "is currently running.";
		case 2 : return result + "is stopped at " + this.getTime() + " milliseconds.";
		case 3 : return result + "is suspended at " + this.getTime() + " milliseconds.";
		
		//cannot happen
		default : return result + "is taking a monster mucus dump in the bathrooms at Burger King.";
		}
	}
}
