package pixelmon.AI;

import pixelmon.entities.EntityCamera;
import net.minecraft.src.EntityAIBase;

public class AILookAtTarget extends EntityAIBase {

	EntityCamera camera;

	public AILookAtTarget(EntityCamera camera) {
		this.camera = camera;
	}

	@Override
	public boolean shouldExecute() {
		if (camera.getAttackTarget() != null)
			return true;
		return false;
	}

	@Override
	public boolean continueExecuting() {
		return camera.getAttackTarget() != null;
	}

	@Override
	public void updateTask() {
		camera.getLookHelper().setLookPositionWithEntity(
				camera.getAttackTarget(), 30, 30);
	}
}
