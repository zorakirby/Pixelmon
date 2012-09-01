package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class Frustration extends SpecialAttackBase {

	public Frustration() {
		super(SpecialAttackType.Frustration, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Friendship not fully implemented yet, using basePower of 50");
		a.basePower = 50;
		return false;
	}

}
