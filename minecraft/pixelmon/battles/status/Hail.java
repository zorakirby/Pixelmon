package pixelmon.battles.status;

import pixelmon.battles.controller.BattleController;
import pixelmon.battles.controller.GlobalStatusController;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Hail extends GlobalStatusBase {
	int turnsToGo;
	public Hail(String name, int turnsToGo) {
		super(name);
		this.turnsToGo = turnsToGo;
	}
	@Override
	public void applyRepeatedEffect(GlobalStatusController global, EntityPixelmon user, EntityPixelmon target)
	{
		if (!user.type.contains(EnumType.Ice))
		{
			user.doBattleDamage(user, user.getMaxHealth()/16);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " was hurt by hail!");
		}
		if (!target.type.contains(EnumType.Ice))
		{
			target.doBattleDamage(target, target.getMaxHealth()/16);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " was hurt by hail!");
		}
	}
	@Override
	public String endOfTurnMessage(BattleController bc)
	{
		turnsToGo--;
		if (turnsToGo == 0)
		{
			bc.globalStatusController.removeGlobalStatus(this);
			return "The hail stopped.";
		}
		return "The hail falls heavily!";
	}
}
