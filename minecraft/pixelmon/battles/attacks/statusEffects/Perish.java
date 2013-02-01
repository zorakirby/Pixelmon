package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import net.minecraft.util.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Perish extends StatusEffectBase {

	public Perish() {
		super(StatusEffectType.Perish, false, false, false);

	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) throws Exception{
		if (checkChance()) {
			for (StatusEffectBase a : target.status)
				if (a.type == StatusEffectType.Perish) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), target.getName() + " has already heard the song!");
					return;
				}
			for (StatusEffectBase b : user.status)
				if (b.type == StatusEffectType.Perish) {
					ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getOwner() + " has already heard the song!");
					return;
				}

			user.battleVariables.set(type, 0);
			user.status.add(new Perish());
			ChatHandler.sendBattleMessage(user.getOwner(), user.getName() + " heard the Perish Song!");
			target.battleVariables.set(type, 0);
			target.status.add(new Perish());
			ChatHandler.sendBattleMessage(target.getOwner(), target.getName() + " heard the Perish Song!");
		}

	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) throws Exception{

		if (user.battleVariables.get(type) == 3) {
			if (!target.isDead) {
				target.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
				ChatHandler.sendBattleMessage(target.getOwner(), user.getOwner(), "Perish Song struck " + target.getName() + "!");
			}
			if (!user.isDead) {
				user.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
				ChatHandler.sendBattleMessage(target.getOwner(), user.getOwner(), "Perish Song struck " + user.getName() + "!");
			}
		}
		user.battleVariables.increment(type);

	}

}
