package pixelmon.AI;

import net.minecraft.entity.ai.EntityAIBase;
import pixelmon.entities.npcs.EntityTrainer;

public class AITrainerInBattle extends EntityAIBase {
	EntityTrainer trainer;

	public AITrainerInBattle(EntityTrainer trainer) {
		setMutexBits(1);
		this.trainer = trainer;
	}

	@Override
	public boolean shouldExecute() {
		return trainer.releasedPokemon != null;
	}

	@Override
	public boolean continueExecuting() {
		return trainer.releasedPokemon != null;
	}

}
