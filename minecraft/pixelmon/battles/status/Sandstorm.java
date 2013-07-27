package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.controller.BattleController;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Sandstorm extends GlobalStatusBase {
	int turnsToGo = 10;
	public Sandstorm(String name) {
		super("Sandstorm");
		
	}
	@Override
	public String endOfTurnMessage(BattleController bc)
	{
		turnsToGo--;
		if (turnsToGo == 0)
		{
			bc.removeGlobalStatus(this);
			return "The sandstorm subsided";
		}
		return "The sandstorm rages harshly!";
	}
	
	@Override
	public void applyRepeatedEffect(ArrayList<GlobalStatusBase> global, EntityPixelmon user, EntityPixelmon target)
	{
		if (!user.type.contains(EnumType.Ground) && !user.type.contains(EnumType.Rock) && !user.type.contains(EnumType.Steel))
		{
			user.attackEntityFrom(DamageSource.causeMobDamage(target), user.getMaxHealth()/16);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " was buffeted by the sandstorm!");
		}
		if (!target.type.contains(EnumType.Ground) && !target.type.contains(EnumType.Rock) && !target.type.contains(EnumType.Steel))
		{
			target.attackEntityFrom(DamageSource.causeMobDamage(user), user.getMaxHealth()/16);
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getNickname() + " was buffeted by the sandstorm!");
		}
	}
	
	
}
