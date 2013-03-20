package pixelmon.battles.attacks.specialAttacks.statusAppliers;

import pixelmon.battles.attacks.EffectBase;
import pixelmon.battles.attacks.Value;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class StatusApplierBase extends EffectBase {

	public StatusApplierBase(Value... values) {
		super(ApplyStage.End, false);
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) throws Exception {
		return false;
	}
}
