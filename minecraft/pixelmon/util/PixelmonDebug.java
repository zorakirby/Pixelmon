package pixelmon.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 *  <tr>
 *    <td></td>
 *    <td ALIGN=CENTER><em>Hey,</em></td>
 *    <td ALIGN=CENTER><em>Look!</em></td>
 *  </tr>
 *  <tr>
 *    <td><b>Row1</b></td>
 *    <td>This</td>
 *    <td>Is</td>
 *  </tr>
 *  <tr>
 *    <td><b>Row2</b></td>
 *    <td>How</td>
 *    <td>Tables</td>
 *  </tr>
 *  <tr>
 *    <td><b>Row3</b></td>
 *    <td>Are</td>
 *    <td>Made!</td>
 *  </tr>
 * </table>
 *
 */
public class PixelmonDebug {
	
	/**
	 * ... Something something something<br>
	 * <b>DEBUG!!!</b><br><br><br>
	 * No, seriously, what is this for?
	 */
	public static void debugKeyFunction() {

	}
	
	public static String listObjs(Object...obs ){
		String result = "(";
		for(int i = 0; i < obs.length; i++){
			result += obs[i];
			if(i+1 != obs.length)
				result+= ", ";
		}
		result+= ")";
		return result;
	}
	
	/**
	 * example:<br>
	 * for <code>width = 5, length = 4, obs.length = 20
	 * <br><br>
	 * <table BORDER CELLPADDING=0 CELLSPACING=0>
	 *  <tr>
	 *    <td ALIGN=CENTER><em>0</em></td> <td ALIGN=CENTER><em>1</em></td> <td ALIGN=CENTER><em>2</em></td> <td ALIGN=CENTER><em>3</em></td> <td ALIGN=CENTER><em>4</em></td>
	 *  </tr>
	 *  <tr>
	 *    <td ALIGN=CENTER><em>5</em></td> <td ALIGN=CENTER><em>6</em></td> <td ALIGN=CENTER><em>7</em></td> <td ALIGN=CENTER><em>8</em></td> <td ALIGN=CENTER><em>9</em></td>
	 *  </tr>
	 *  <tr>
	 *    <td ALIGN=CENTER><em>10</em></td> <td ALIGN=CENTER><em>11</em></td> <td ALIGN=CENTER><em>12</em></td> <td ALIGN=CENTER><em>13</em></td> <td ALIGN=CENTER><em>14</em></td>
	 *  </tr>
	 *  <tr>
	 *    <td ALIGN=CENTER><em>15</em></td> <td ALIGN=CENTER><em>16</em></td> <td ALIGN=CENTER><em>17</em></td> <td ALIGN=CENTER><em>18</em></td> <td ALIGN=CENTER><em>19</em></td>
	 *  </tr>
	 * </table>
	 * </code>
	 * @param ints
	 * @param width
	 * @param length
	 */
	public static void printArrayIn2D(Object[] obs, int width, int length){
		System.out.println();
		System.out.println("2D array start");
		for(int j = 0; j < length; j++){
			Object[] row = new Object[width];
			for(int i = 0; i < width; i++){
				row[i] = obs[i + j * width];
			}
			System.out.println(listObjs(row));
		}
		System.out.println("2D array end");
	}
	
	/**
	 * @return A description of <u>the method from which</u> <i><b>the method calling this method</b></i> was called. There is no whitespace at the beginning
	 * or end of the returned String.
	 */
	public static String prevMethod(){
		try {
			StackTraceElement[] elements = Thread.currentThread().getStackTrace();
			int i = Math.min(3, elements.length - 1);
			StackTraceElement stackElement = elements[i];
			return describeStackElement(stackElement);
		} catch (Exception shouldntHappen) {}
		return "?";
	}
	
	public static String describeStackElement(StackTraceElement stackElement){
		try {
			return Class.forName(stackElement.getClassName()).getSimpleName()+"."+stackElement.getMethodName()+"(line " + stackElement.getLineNumber() + ")";
		} catch (ClassNotFoundException shouldntHappen) {
			return null;
		}
	}
	
	public static void printStackElements(int traceLines){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		System.out.println("[Begin stack trace]");
		for(int i = 1; i <= traceLines && i < elements.length; i++){
			System.out.println(describeStackElement(elements[i]));
		}
		System.out.println("[End stack Trace]");
	}
	
	public static boolean startsWithVowel(String string){
		return string.matches("(?i)[aeiou].*");
	}
	
	public static String upperCaseFirstChar(String string){
		return string.substring(0, 1).toUpperCase()+string.substring(1);
	}
	
	public static String doesFloatArrayRepeat(float[] list){
		int i0, i1, k;
		for(i0 = 0; i0 < list.length; i0++){
			float a = list[i0];
			for(i1 = i0+1; i1 < list.length; i1++){
				float b = list[i1];
				if(a == b){
					boolean worked = true;
					for(k = 0; k <= i1-i0; k++){
						if(list[i0+k] == list[i1+k])
							continue;
						else{
							worked = false;
							break;
						}
					}
					if(worked){
						return "Uh, yeah. The Float array repeats, starting at " + i0 + " and repeats every" + (i1-i0) + "elements";
					}
				}
			}
		}
		return "Nope, the Float array does not repeat";
	}

}
