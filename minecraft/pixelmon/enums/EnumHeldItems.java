package pixelmon.enums;


public enum EnumHeldItems {
	expShare(false), luckyEgg(false),
	oran, rawst, leppa;
	
	private boolean effectsBattle;
	
	private EnumHeldItems()
	{
		this(true);
	}
	
	private EnumHeldItems(boolean var1)
	{
		effectsBattle = var1;
	}
	
	public boolean getBattleModifier()
	{
		return effectsBattle;
	}
}
