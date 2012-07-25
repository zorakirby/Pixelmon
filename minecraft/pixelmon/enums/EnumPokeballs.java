package pixelmon.enums;

import net.minecraft.src.Item;
import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(0, 1), GreatBall(1, 1.5), UltraBall(2, 2), MasterBall(3, 255);

	private EnumPokeballs(int index, double ballBonus) {
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
		if (index ==0) return mod_Pixelmon.pokeBall;
		if (index ==1) return mod_Pixelmon.greatBall;
		if (index ==2) return mod_Pixelmon.ultraBall;
		if (index ==3) return mod_Pixelmon.masterBall;
		return  mod_Pixelmon.pokeBall;
	}
}
