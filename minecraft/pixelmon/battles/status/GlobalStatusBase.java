package pixelmon.battles.status;

import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class GlobalStatusBase {
	
	public boolean hasEndOfMoveEffect;
	public boolean hasInMoveEffect;
	private int turnTick;
	
	public GlobalStatusBase(boolean hasEndOfMoveEffect, boolean hasInMoveEffect)
	{
		this.hasEndOfMoveEffect = hasEndOfMoveEffect;
	}
	
	public boolean hasEndOfMoveEffect()
	{
		return hasEndOfMoveEffect;
	}
	
	public boolean hasInMoveEffect()
	{
		return hasInMoveEffect;
	}
	
	public String endOfTurnMessage()
	{
		return null;
	}
	
	public int getTurnTick()
	{
		return turnTick;
	}
	
	
	public void applyInMoveEffect(GlobalStatusBase[] global, EntityPixelmon user, EntityPixelmon target)
	{}
}
