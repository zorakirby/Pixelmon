package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.FutureSighted;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FutureSight extends SpecialAttackBase {
	boolean missed = false;
	public FutureSight() {
		super(ApplyStage.During, false);
	}
	Attack e;
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		if (user.hasStatus(StatusType.FutureSight))
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "But it failed!");
		else
		{
			user.status.add(new FutureSighted(missed, a));
			ChatHandler.sendBattleMessage(user.getOwner(),  target.getOwner(), user.getNickname() + " foresaw an attack!");
		}
		return true;
	}
	@Override
	public void ApplyMissEffect(EntityPixelmon user, EntityPixelmon target) throws Exception {
		if (user.hasStatus(StatusType.FutureSight))
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "But it failed!");
		else
		{
			missed = true;
			user.status.add(new FutureSighted(missed, e));
		}
	}

}
