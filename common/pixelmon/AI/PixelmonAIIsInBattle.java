package pixelmon.AI;

import pixelmon.battles.BattleRegistry;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.EntityAIBase;

public class PixelmonAIIsInBattle extends EntityAIBase {

	EntityPixelmon pixelmon;

	public PixelmonAIIsInBattle(EntityPixelmon p) {
		setMutexBits(1);
		pixelmon = p;
	}

	@Override
	public boolean shouldExecute() {
		return pixelmon.battleController != null;
	}

	@Override
	public boolean continueExecuting() {
		return pixelmon.battleController != null;
	}

}
