package pixelmon.enums;

import net.minecraft.src.Item;
import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball"), GreatBall(1, 1.5, "greatball"), UltraBall(2, 2, "ultraball"), MasterBall(3, 255, "masterball");

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
	}

	private double ballBonus;
	private int index;
	private String filenamePrefix;

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

	public String getTexture() {
		return filenamePrefix + ".png";
	}

	public String getFlashRedTexture() {
		return filenamePrefix + "_flashing.png";
	}

	public String getCaptureTexture() {
		return filenamePrefix + "_captured.png";
	}
}
