package pixelmon.AI;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;

public class AIFlyingPersistent extends EntityAIBase {

	EntityPixelmon pixelmon;
	int ticksToChangeDirection = 0;
	int ticksToChangeSpeed = 0;
	float movespeed = 1f;

	public AIFlyingPersistent(EntityPixelmon pixelmon) {
		this.pixelmon = pixelmon;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public boolean continueExecuting() {
		return true;
	}

	@Override
	public void updateTask() {
		ticksToChangeDirection--;
		ticksToChangeSpeed--;
		if (ticksToChangeDirection <= 0) {
			pickDirection();
			ticksToChangeDirection = 100 + pixelmon.getRNG().nextInt(1000);
		}
		if (ticksToChangeSpeed <= 0) {
			pickSpeed();
			ticksToChangeSpeed = 100 + pixelmon.getRNG().nextInt(500);
		}
		pixelmon.moveEntityWithHeading(0, movespeed);
		super.updateTask();
	}

	public void pickDirection() {
		pixelmon.rotationYaw += pixelmon.getRNG().nextInt(90) - 30;
	}

	public void pickSpeed() {
		movespeed = pixelmon.getRNG().nextFloat() * 0.3f + 0.5f;
	}
}
