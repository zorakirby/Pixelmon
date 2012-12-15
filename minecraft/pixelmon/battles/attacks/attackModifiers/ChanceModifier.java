package pixelmon.battles.attacks.attackModifiers;

import java.util.Random;

public class ChanceModifier extends ModifierBase{

	public ChanceModifier(int value) {
		super(ModifierType.Chance);
		this.value = value;
	}
	
	public boolean RollChance(){
		if ((new Random()).nextInt(100) < value)
			return true;
		else
			return false;
	}
}
