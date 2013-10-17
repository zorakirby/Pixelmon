package pixelmon.util;

public enum BooleanOp{
	EQUALS {public boolean op0(int compare){return compare == 0;}},
	LESS {public boolean op0(int compare){return compare < 0;}}, 
	GREATER{public boolean op0(int compare){return compare > 0;}}, 
	LEQUAL {public boolean op0(int compare){return compare <= 0;}},
	GREQUAL {public boolean op0(int compare){return compare >= 0;}},
	NOTEQUAL {public boolean op0(int compare){return compare != 0;}};
	
	public <E, T extends Comparable<E>> boolean op(T left, E right){
		return op0(left.compareTo(right));
	}
	protected abstract boolean op0(int compare);
}
