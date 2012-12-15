package pixelmon.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import pixelmon.entities.pixelmon.Entity7HasAI;

public class AIMoveTowardsTarget extends EntityAIBase {
	private Entity7HasAI theEntity;
	private EntityLiving targetEntity;
	private double movePosX;
	private double movePosY;
	private double movePosZ;
	private float field_75425_f;
	private float field_75426_g;

	public AIMoveTowardsTarget(Entity7HasAI par1EntityCreature, float par2, float par3) {
		this.theEntity = par1EntityCreature;
		this.field_75425_f = par2;
		this.field_75426_g = par3;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (theEntity.battleController != null)
			return false;
		this.targetEntity = theEntity.getAttackTarget();

		if (this.targetEntity == null) {
			return false;
		} else if (targetEntity.getDistanceSqToEntity(theEntity) > (double) (field_75426_g * field_75426_g)) {
			return false;
		} else {
			Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(theEntity, 16, 7, this.theEntity.worldObj.getWorldVec3Pool().getVecFromPool(targetEntity.posX, targetEntity.posY, targetEntity.posZ));

			if (var1 == null) {
				return false;
			} else {
				this.movePosX = var1.xCoord;
				this.movePosY = var1.yCoord;
				this.movePosZ = var1.zCoord;
				return true;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
//		if (theEntity.battleController != null)
//			return false;
//		return !(theEntity.getNavigator().noPath() && targetEntity.isEntityAlive() && targetEntity.getDistanceSqToEntity(theEntity) < (double) (field_75426_g * field_75426_g));
		return false;
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.targetEntity = null;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.field_75425_f);
	}
}
