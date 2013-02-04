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
	public abstract void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList)throws Exception;

	public boolean hasRepeatedEffect() throws Exception{
		return hasRepeatedEffect;
	}

	public boolean hasPreMoveEffect() throws Exception{
		return hasPreMoveEffect;
	}

	public boolean hasInMoveEffect() throws Exception{
		return hasInMoveEffect;
	}

	public boolean stopsSwitching() throws Exception{
		return false;
	}

	public boolean clearsOnBattleEnd() throws Exception{
		return true;
	}

	public void applyRepeatedEffect(EntityPixelmon entityPixelmon, EntityPixelmon entityPixelmon2)throws Exception {
	}

	public boolean canAttackThisTurn(EntityPixelmon pixelmon1, EntityPixelmon pixelmon2) throws Exception{
		return true;
	}

	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a)throws Exception {
		return false;
	}

	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) throws Exception{
		return damage;
	}

	public boolean stopsStatusChange() throws Exception{
		return false;
	}

	public void turnTick(EntityPixelmon pixelmon1, EntityPixelmon target) throws Exception{
	}

	public boolean cantMiss(EntityPixelmon user) throws Exception{
		return false;
	}

	public boolean participantMovesFirst(EntityPixelmon user, EntityPixelmon target)throws Exception {
		return false;
	}

	public void writeToNBT(int i, NBTTagCompound nbt) throws Exception{
		if (!clearsOnBattleEnd()) {
			nbt.setInteger("Status" + i, type.index);
		}
	}
}
