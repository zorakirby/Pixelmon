package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;

public class HornDrill extends SpecialAttackBase {

	public HornDrill(SpecialAttackType type, ApplyStage a, boolean persists) {
		super(SpecialAttackType.HornDrill, ApplyStage.During, false);
		}

		public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "It's a one-hit-KO!");
			target.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
			return true;
		}
		
		public boolean hasSpecialAccuracyEffect() {
			return true;
		}

		public double getAccuracy(EntityPixelmon user, EntityPixelmon target) {
			if (target.getLvl().getLevel() > user.getLvl().getLevel()) return 0;
			
			return (user.getLvl().getLevel() - target.getLvl().getLevel()) + 30;
		}

}
