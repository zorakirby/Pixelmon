package pixelmon.AI;

import pixelmon.entities.trainers.EntityTrainer;
import net.minecraft.src.EntityAIBase;

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
