package pixelmon.enums;

import net.minecraft.src.Item;
import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball.png", "pokeball_flashing.png"), GreatBall(1, 1.5, "greatball.png", "greatball_flashing.png"), 
	UltraBall(2, 2, "ultraball.png", "greatball_flashing.png"), MasterBall(3, 255, "masterball.png", "masterball_flashing.png");

	private EnumPokeballs(int index, double ballBonus, String texture, String flashRedTexture) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.texture = texture;
		this.flashRedTexture = flashRedTexture;
	}

	private double ballBonus;
	private int index;
	private String texture;
	private String flashRedTexture;

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
		return texture;
	}

	public String getFlashRedTexture() {
		return flashRedTexture;
	}
}
