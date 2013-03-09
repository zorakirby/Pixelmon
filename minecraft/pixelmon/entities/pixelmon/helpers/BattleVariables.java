package pixelmon.entities.pixelmon.helpers;

import java.util.HashMap;

public class BattleVariables {
	private HashMap<Object, Integer> variables = new HashMap<Object, Integer>();
	private HashMap<Object, Boolean> booleans = new HashMap<Object, Boolean>();

	public int get(Object type) {
		if (variables.containsKey(type))
			return variables.get(type);
		return -1;
	}

	public void set(Object type, Integer value) {
		variables.put(type, value);
	}

	public void increment(Object type) {
		variables.put(type, variables.get(type) + 1);
	}

	public void decrement(Object type) {
		variables.put(type, variables.get(type) - 1);
	}

	public void setBoolean(Object type, Boolean value) {
		booleans.put(type, value);
	}

	public boolean getBoolean(Object type) {
		if (booleans.containsKey(type))
			return booleans.get(type);

		return false;
	}
}
