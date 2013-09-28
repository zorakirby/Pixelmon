package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class BellyDrum extends SpecialAttackBase {

	public BellyDrum() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
			if (user.getHealth() > user.getMaxHealth()/2)
			{
			user.doBattleDamage(user, user.getMaxHealth()/2);
			while (user.battleStats.getAttackModifier() < 6)
				user.battleStats.IncreaseAttack(1);
			}
			else
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "But the move failed!");
			return true;
	}

}
