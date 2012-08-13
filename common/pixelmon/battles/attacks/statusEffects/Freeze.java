package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;

import net.minecraft.src.ModLoader;

public class Freeze extends StatusEffectBase {

	public Freeze() {
		super(StatusEffectType.Freeze, false, true, false);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Freeze) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already frozen!");
					return;
				}
			if (target.getType().contains(EnumType.Ice)){
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), "no effect!");
				return;
			}
			target.status.add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has been frozen solid");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");

	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (RandomHelper.getRandomNumberBetween(0, 100) <= 20) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " breaks free from the ice!");
			user.status.remove(this);
			return true;
		} else {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is frozen solid!");
			return false;
		}
	}

	@Override
	public boolean stopsIncomingAttack(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		return false;
	}

	public boolean ClearsOnBattleEnd() {
		return false;
	}
}
