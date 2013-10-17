package pixelmon.enums;

public enum EnumTreasureRarity {
	
	/**2/5 chance*/
	COMMON(.4F),
	
	/**1/10 chance*/
	UNCOMMON(.1F),
	
	/**1/20 chance*/
	SEMI_RARE(.05F),
	
	/**1/50 chance*/
	RARE(.02F),
	
	/**1/100 chance*/
	VERY_RARE(.01F),
	
	/**1/200 chance*/
	SUPER_RARE(.005F),
	
	/**1/1000 chance*/
	ULTRA_RARE(.001F),
	
	ALWAYS(1F),
	NEVER(0F);
	
	public static final EnumTreasureRarity[] STANDARD_RARITIES = {
		COMMON,
		UNCOMMON,
		SEMI_RARE,
		RARE,
		VERY_RARE,
		SUPER_RARE,
		ULTRA_RARE
	};
	
	public static final float[] midPoints = 
		{
		UNCOMMON.average(COMMON),
		SEMI_RARE.average(UNCOMMON),
		RARE.average(SEMI_RARE),
		VERY_RARE.average(RARE),
		SUPER_RARE.average(VERY_RARE),
		ULTRA_RARE.average(SUPER_RARE)		
		};
	
	public final float chance;
	
	EnumTreasureRarity(float chance){
		this.chance = chance;
	}
	
	public static EnumTreasureRarity round(float chance){
		for(int i = 0; i < midPoints.length; i++){
			if(chance >= midPoints[i])
				return values()[i];
		}
		return ULTRA_RARE;
	}
	
	public static float estimateRarity(EnumPokeballs ballType){
		switch(ballType){
		case PokeBall : return COMMON.chance;
		case GreatBall : return UNCOMMON.chance;
		case UltraBall : return SEMI_RARE.chance;
		case MasterBall : return ULTRA_RARE.chance;
		case PremierBall :  return COMMON.average(UNCOMMON);
		default :	return COMMON.average(RARE);
		}
		}
	
	public float average(EnumTreasureRarity other){
		return (this.chance + other.chance)/2;
	}
}
