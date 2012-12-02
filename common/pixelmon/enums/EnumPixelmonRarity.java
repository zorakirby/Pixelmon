package pixelmon.enums;

public enum EnumPixelmonRarity {

	Common,
	Uncommon,
	Rare,
	SRare,
	Legend,
	Unknown;
	public static EnumPixelmonRarity EnumPixelmonRarityLevel(int Rarity){
		if(Rarity == 1)
		{
			return Legend;
		} else if(Rarity >= 2 && Rarity <= 49)
		{
			return SRare;
		} else if(Rarity >= 50 && Rarity <= 99)
		{
			return Rare;
		} else if(Rarity >= 100 && Rarity <= 149)
		{
			return Uncommon;
		} else if(Rarity >= 150)
		{
			return Common;
		} 
		return Unknown;
	}
}