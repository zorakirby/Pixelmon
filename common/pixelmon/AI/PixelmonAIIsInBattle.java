package pixelmon.AI;

import pixelmon.battles.BattleRegistry;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.EntityAIBase;

public class PixelmonAIIsInBattle extends EntityAIBase {

	Entity7HasAI pixelmon;

	public PixelmonAIIsInBattle(Entity7HasAI entity7HasAI) {
		setMutexBits(1);
		pixelmon = entity7HasAI;
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
