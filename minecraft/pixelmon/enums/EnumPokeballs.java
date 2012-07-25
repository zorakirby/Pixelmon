package pixelmon.enums;

import net.minecraft.src.Item;
import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(0, 1, mod_Pixelmon.pokeBall), GreatBall(1, 1.5, mod_Pixelmon.greatBall), UltraBall(2, 2, mod_Pixelmon.ultraBall), MasterBall(3, 255, mod_Pixelmon.masterBall);

	private EnumPokeballs(int index, double ballBonus, Item item) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.item = item;
	}

	private double ballBonus;
	private int index;
	private Item item;

	public double getBallBonus() {
		return ballBonus;
	}
	
	public int getIndex(){
		return index;
	}
	
	public Item getItem(){
		return item;
	}
}
