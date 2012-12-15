package pixelmon.AI;

import net.minecraft.entity.ai.EntityAIBase;
import pixelmon.entities.pixelmon.Entity7HasAI;

public class AIIsInBattle extends EntityAIBase {

	Entity7HasAI pixelmon;

	public AIIsInBattle(Entity7HasAI entity7HasAI) {
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
