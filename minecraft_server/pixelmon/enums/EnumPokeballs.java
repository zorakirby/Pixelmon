package pixelmon.enums;

import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(0, 1), GreatBall(1, 1.5), UltraBall(2, 2), MasterBall(3, 255);

	private EnumPokeballs(int index, double ballBonus) {
		this.ballBonus = ballBonus;
	}

	private double ballBonus;
	private int index;

	public double getBallBonus() {
		return ballBonus;
	}
	
	public int getIndex(){
		return index;
	}
}
