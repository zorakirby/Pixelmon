package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class Cursed extends StatusEffectBase {

	public Cursed() {
		super(StatusEffectType.Cursed, true, false, false);
	
	}
	
	@Override
	public void applyRepeatedEffect(EntityPixelmon user, EntityPixelmon target) {
		if(checkChance()) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is afflicted by the curse!");
		user.attackEntityFrom(DamageSource.causeMobDamage(user), (int) (((float) user.getMaxHealth()) / 4));
	}
	}
	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			ArrayList<String> attackList) {
		
	}

}