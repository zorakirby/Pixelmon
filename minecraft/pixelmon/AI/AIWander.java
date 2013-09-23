package pixelmon.AI;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class AIWander extends EntityAIBase {
	private EntityCreature entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;

	public AIWander(EntityCreature par1EntityCreature) {
		this.entity = par1EntityCreature;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (this.entity.getAge() >= 100) {
			return false;
		} else if (this.entity.getRNG().nextInt(120) != 0) {
			return false;
		}else if (entity instanceof EntityPixelmon && !((EntityPixelmon)entity).canMove)
			return false;
		else {
			Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

			if (vec3 == null) {
				return false;
			} else {
				this.xPosition = vec3.xCoord;
				this.yPosition = vec3.yCoord;
				this.zPosition = vec3.zCoord;
				return true;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return !this.entity.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition,
				entity.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
	}
}
