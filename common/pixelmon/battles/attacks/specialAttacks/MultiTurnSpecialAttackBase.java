package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.attackEffects.EffectParser;
import pixelmon.battles.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.battles.attacks.attackModifiers.ChanceModifier;
import pixelmon.battles.attacks.attackModifiers.ModifierBase;
import pixelmon.battles.attacks.attackModifiers.ModifierType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class MultiTurnSpecialAttackBase extends EffectBase {
		public int turnCount=2;
		public int turnCounter=0;

		private MultiTurnSpecialAttackType mtsatype;
		
		public MultiTurnSpecialAttackBase(MultiTurnSpecialAttackType type, int turnCount) {
			super(EffectType.MultiTurnSpecialAttack, ApplyStage.During, true);
			this.turnCount = turnCount;
			this.mtsatype = type;
		}

		public abstract boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList);
		
		@Override
		public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		}
		
		public abstract boolean cantMiss();
}
