package pixelmon.enums;

import net.minecraft.src.mod_Pixelmon;

public enum EnumPokeballs {
	PokeBall(1), GreatBall(1.5), UltraBall(2), MasterBall(255);
	
	private EnumPokeballs(double ballBonus){
		this.ballBonus = ballBonus;
	}
	private double ballBonus;
	
	public double getBallBonus(){
		return ballBonus;
	}
}
