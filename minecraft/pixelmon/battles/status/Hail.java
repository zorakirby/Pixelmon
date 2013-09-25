package pixelmon.battles.status;

import pixelmon.battles.controller.GlobalStatusController;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Hail extends GlobalStatusBase {

	public Hail() {
		super("Hail");
	}
	@Override
	public void applyRepeatedEffect(GlobalStatusController global, EntityPixelmon user, EntityPixelmon target)
	{
		if (!user.type.contains(EnumType.Ice))
		{
			user.doBattleDamage(user, user.getMaxHealth()/16);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " was hurt by hail!");
		}
	}
}
