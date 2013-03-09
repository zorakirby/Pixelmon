package pixelmon.battles.attacks.specialAttacks;

public abstract class ModifierBase {
	public ModifierType type;
	public float value;
	
	public ModifierBase(ModifierType type){
		this.type = type;
	}
	
	
}
