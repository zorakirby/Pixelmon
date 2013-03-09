package pixelmon.battles.attacks.specialAttacks;

import java.util.Random;

public class ChanceModifier extends ModifierBase{

	public ChanceModifier(int... values) {
		super(ModifierType.Chance);
		this.value = values[0];
	}
	
	public boolean RollChance(){
		if ((new Random()).nextInt(100) < value)
			return true;
		else
			return false;
	}
}
