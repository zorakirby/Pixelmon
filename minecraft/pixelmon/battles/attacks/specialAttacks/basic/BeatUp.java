package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.status.StatusBase;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.items.heldItems.ChoiceItem;
import pixelmon.storage.PlayerStorage;

public class BeatUp extends SpecialAttackBase {

	public BeatUp() {
		super(ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {

		PlayerStorage storage = user.getStorage();
		for (NBTTagCompound p : storage.partyPokemon) {
			if (!isFainted(p) && !hasVolatileStatus(p)) {
				doAttack(user, target, a);
			}
		}

		return true;
	}

	private void doAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {

		double stab = 1;
		if (user.baseStats.type1 == EnumType.Dark || user.baseStats.type2 == EnumType.Dark)
			stab = 1.5;

		double critical = Attack.calcCriticalHit(null);
		double rand = 1;
		double modifier = stab * 1 * critical; // Add in
												// Type/Weakness/Resistance
												// in again.
		double attack = 0, defense = 0, Level = (double) (user.getLvl().getLevel());

		attack = ((double) user.stats.Attack) * ((double) user.battleStats.getAttackModifier()) * 0.01;
		defense = ((double) target.stats.Defence) * ((double) target.battleStats.getDefenceModifier()) * 0.01;
		if (ItemHeld.isItemOfType(user.getHeldItem(), EnumHeldItems.choiceItem)) {
			attack = ((ChoiceItem) user.getHeldItem().getItem()).affectAttack(attack);
		}

		double DamageBase = (((((((2 * Level) / 5) + 2) * attack * (user.baseStats.attack / 10 + 5)) * 0.02) / defense) + 2);

		// Split up so they can be monitored while debugging.
		double Damage = DamageBase * modifier * 0.85;

		for (int i = 0; i < target.status.size(); i++) {
			StatusBase e = target.status.get(i);
			try {
				Damage = e.adjustDamage(a, Damage, user, target, critical);
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error in adjustDamage for " + e.getClass().toString() + " for Beat Up.");
					System.out.println(exc.getStackTrace());
				}
			}
		}
		target.doBattleDamage(user, (int)Damage);
	}

	private boolean hasVolatileStatus(NBTTagCompound nbt) {
		return nbt.getShort("EffectCount") > 0;
	}

	private boolean isFainted(NBTTagCompound nbt) {
		if (nbt != null) {
			if (nbt.getBoolean("IsFainted"))
				return true;
			if (nbt.getShort("Health") <= 0)
				return true;
		}
		return false;
	}

}
