package pixelmon.battles.attacks.specialAttacks.modifiers;

public abstract class ModifierBase {
	public ModifierType type;
	public float value;
	public float value2;
	
	public ModifierBase(ModifierType type){
		this.type = type;
	}
	
	
}
