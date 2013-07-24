package pixelmon.battles.status;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class GlobalStatusBase {
	private String name;
	public GlobalStatusBase(String name)
	{
		this.name = name;
	}
	public String endOfTurnMessage(ArrayList<GlobalStatusBase> list)
	{
		return "";
	}
	public void applyInMoveEffect(EntityPixelmon user, EntityPixelmon target, Attack a)
	{}
	
	public void applyRepeatedEffect(ArrayList<GlobalStatusBase> global, EntityPixelmon user, EntityPixelmon target)
	{}
	
	public String getName()
	{
		return name;
	}
}
