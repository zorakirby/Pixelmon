package pixelmon.enums.heldItems;


public enum EnumHeldItems {
	expShare(false, false), luckyEgg(false, false),
	oran, rawst, leppa, choiceItem(false, true), everStone
	
	;
	
	private boolean affectsBattle;
	private boolean usableInBattle;
	
	private EnumHeldItems()
	{
		this(true, false);
	}
	
	private EnumHeldItems(boolean usableInBattle, boolean affectsBattle)
	{
		this.usableInBattle = usableInBattle;
		this.affectsBattle = affectsBattle;
	}
	
	public boolean getUsableInBattle()
	{
		return usableInBattle;
	}

	public boolean getAffectsBattle() {
		return affectsBattle;
	}
}
