package pixelmon.util.geom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @see <a href="http://en.wikipedia.org/wiki/Lindenmayer_system">L-System (Wikipedia)</a>
 * @author ZoraKirby
 */
public class LSystem {

	public ArrayList<Character> vars = new ArrayList();
	private HashMap<Character, String> rules = new HashMap();
	public String start;

	/**NONE OF THE VARIABLES SHOULD CONTAIN A NUMBER AS A CHARACTER.(example '1', '2', etc..) THIS WILL CAUSE THE OUTPUT STRING TO BE WRONG.
	 * @param vars - Variable names to be used for replacing
	 * @param start - the Starting String
	 * @param rules - Replacement rules. Rules should start with the character you want to replace, followed by any character (ideally an '='), and then the string that will be used in the replacement
	 */
	public LSystem(char[] vars, String start, String... rules){

		for(char c2 : vars){
			this.vars.add(c2);
		}
		this.start = start;
		for(String rule : rules){
			parseRules(rule);
		}
	}
	
	private void parseRules(String rule){
		char key = rule.charAt(0);
		String val = rule.substring(2);
		for(Character c : vars)
			val.replaceAll(""+c, vars.indexOf(c) +  "");
		rules.put(key, val);
	}
	
	public String generate(int iterations){
		String result = this.start + "";
		for(int i = 0; i < iterations; i++){
			for(Character c : vars){
				result = result.replaceAll(c+"", vars.indexOf(c)+"");
			}
			for(int j = 0; j < vars.size(); j++){
				result = result.replaceAll(j+"", rules.get(vars.get(j)));
			}
		}
		return result;
	}
}
