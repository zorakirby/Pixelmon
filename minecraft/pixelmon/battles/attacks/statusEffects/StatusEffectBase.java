package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class StatusEffectBase extends EffectBase {

	public StatusEffectType type;

	private boolean hasRepeatedEffect = false;
	private boolean hasInMoveEffect = false;
	private boolean hasPreMoveEffect = false;

	public StatusEffectBase(StatusEffectType type, boolean hasRepeatedEffect, boolean hasPreMoveEffect, boolean hasInMoveEffect) {
		super(EffectType.Status, ApplyStage.End, false);
		this.hasRepeatedEffect = hasRepeatedEffect;
		this.hasPreMoveEffect = hasPreMoveEffect;
		this.hasInMoveEffect = hasInMoveEffect;
		this.type = type;
	}

	@Override
	public abstract void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList);

	public boolean hasRepeatedEffect() {
		return hasRepeatedEffect;
	}

	public boolean hasPreMoveEffect() {
		return hasPreMoveEffect;
	}

	public boolean hasInMoveEffect() {
		return hasInMoveEffect;
	}

	public boolean stopsSwitching() {
		return false;
	}

	public boolean clearsOnBattleEnd() {
		return true;
	}

	public void applyRepeatedEffect(EntityPixelmon entityPixelmon, EntityPixelmon entityPixelmon2) {
	}

	public boolean canAttackThisTurn(EntityPixelmon pixelmon1, EntityPixelmon pixelmon2) {
		return true;
	}

	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		return false;
	}

	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) {
		return damage;
	}

	public boolean stopsStatusChange() {
		return false;
	}

	public void turnTick(EntityPixelmon pixelmon1, EntityPixelmon target) {
	}

	public boolean cantMiss() {
		return false;
	}

	public boolean pokemon1MovesFirst(EntityPixelmon user, EntityPixelmon target) {
		return false;
	}

	public void writeToNBT(int i, NBTTagCompound nbt) {
		if (!clearsOnBattleEnd()) {
			nbt.setInteger("Status" + i, type.index);
		}
	}
}
