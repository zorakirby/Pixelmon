package pixelmon.enums;

public enum EnumTreasureRarity {
	
	/**4/5 chance (.8)*/
	COMMON(.8F),
	
	/**3/5 chance (.6)*/
	UNCOMMON(.6F),
	
	/**2/5 chance (.4)*/
	UNLIKELY(.4F),
	
	/**1/5 chance (.2)*/
	SEMI_RARE(.2F),
	
	/**1/10 chance (.1)*/
	RARE(.1F),
	
	/**1/20 chance (.05)*/
	VERY_RARE(.05F),
	
	/**1/100 chance (.01)*/
	SUPER_RARE(.01F),
	
	/**1/200 chance (.005)*/
	ULTRA_RARE(.005F),
	
	ALWAYS(1F),
	NEVER(0F);
	
	public static final EnumTreasureRarity[] STANDARD_RARITIES = {
		COMMON,
		UNCOMMON,
		UNLIKELY,
		SEMI_RARE,
		RARE,
		VERY_RARE,
		SUPER_RARE,
		ULTRA_RARE
	};
	
	public static final float[] midPoints = 
		{
		UNCOMMON.average(COMMON),
		UNLIKELY.average(UNCOMMON),
		SEMI_RARE.average(UNLIKELY),
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
		case UltraBall : return UNLIKELY.chance;
		case MasterBall : return ULTRA_RARE.chance;
		case PremierBall :  return COMMON.average(UNCOMMON);
		default :	return UNLIKELY.chance;
		}
		}
	
	public float average(EnumTreasureRarity other){
		return (this.chance + other.chance)/2;
	}
	
	public float average(float other){
		return (this.chance + other)/2;
	}
}
