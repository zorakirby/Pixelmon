package pixelmon.enums;

public enum EnumRodType {
	OldRod("oldrod", 170),
	GoodRod("goodrod", 60),
	SuperRod("superrod", 0);
	
	public String textureName;
	public int rarityThreshold;
	private EnumRodType(String textureName, int rarityThreshold){
		this.textureName = textureName;
		this.rarityThreshold = rarityThreshold;
	}
}
