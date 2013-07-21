package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class GlobalStatusBase {
	
//	private boolean hasEndOfMoveEffect;
//	private boolean hasInMoveEffect;
	private int turnTick;
	
	
	public GlobalStatusBase(boolean hasEndOfMoveEffect, boolean hasInMoveEffect)
	{
//		this.hasEndOfMoveEffect = hasEndOfMoveEffect;
	}
	
/*	public boolean hasEndOfMoveEffect()
	{
		return hasEndOfMoveEffect;
	}
	
	public boolean hasInMoveEffect()
	{
		return hasInMoveEffect;
	}
*/	
	public String endOfTurnMessage(ArrayList<GlobalStatusBase> list)
	{
		return "";
	}
	
	public int getTurnTick()
	{
		return turnTick;
	}
	
	
	
	public void applyInMoveEffect(EntityPixelmon user, EntityPixelmon target, Attack a)
	{}
	
	public void applyRepeatedEffect(ArrayList<GlobalStatusBase> global, EntityPixelmon user, EntityPixelmon target)
	{}
}
