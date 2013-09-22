package pixelmon.battles.status;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class FutureSighted extends StatusBase {
	int turnsToGo = 2;
	boolean missed;
	Attack a;
	public FutureSighted(boolean missed, Attack a) {
		super(StatusType.FutureSight, true, false, false);
		this.missed = missed;
		if (!missed)
		this.a = a;
	}
	public void applyRepeatedEffect(EntityPixelmon entityPixelmon, EntityPixelmon entityPixelmon2) throws Exception {
		
		turnsToGo--;
		if (turnsToGo == 0)
		{
			if (!missed)
			{
			ChatHandler.sendBattleMessage(entityPixelmon.getOwner(), entityPixelmon2.getOwner(), entityPixelmon2.getNickname() + " took the future sight attack!");
			int damage = a.doDamageCalc(entityPixelmon, entityPixelmon2, 1);
			entityPixelmon2.attackEntityFrom(DamageSource.causeMobDamage(entityPixelmon), damage);
			entityPixelmon.status.remove(this);
			return;
			}
			ChatHandler.sendBattleMessage(entityPixelmon.getOwner(), entityPixelmon2.getOwner(), "But it failed!");
			entityPixelmon.status.remove(this);
		}
		return;
	
	}
}
