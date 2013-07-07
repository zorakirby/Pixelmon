package pixelmon.battles.status;

import pixelmon.entities.pixelmon.EntityPixelmon;

public class Rainy extends GlobalStatusBase {

	public Rainy(boolean hasEndOfMoveEffect, boolean hasInMoveEffect) {
		super(false, true);
	}
	
	@Override
	public String endOfTurnMessage()
	{
		return "It is raining heavily!";
	}
	
	@Override
	public void applyInMoveEffect(GlobalStatusBase[] global, EntityPixelmon user, EntityPixelmon target)
	{
		
	}

}
