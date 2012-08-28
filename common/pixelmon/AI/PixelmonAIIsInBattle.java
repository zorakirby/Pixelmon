package pixelmon.AI;

import pixelmon.battles.BattleRegistry;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.EntityAIBase;

public class PixelmonAIIsInBattle extends EntityAIBase {

	PixelmonEntityHelper pixelmon;

	public PixelmonAIIsInBattle(PixelmonEntityHelper p) {
		setMutexBits(1);
		pixelmon = p;
	}

	@Override
	public boolean shouldExecute() {
		return pixelmon.bc != null;
	}

	@Override
	public boolean continueExecuting() {
		return pixelmon.bc != null;
	}

}
