package pixelmon.AI;

import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.RandomPositionGenerator;
import net.minecraft.src.Vec3;

public class PixelmonAIMoveTowardsTarget extends EntityAIBase {
	private PixelmonEntityHelper theEntity;
	private PixelmonEntityHelper targetEntity;
	private double movePosX;
	private double movePosY;
	private double movePosZ;
	private float field_75425_f;
	private float field_75426_g;

	public PixelmonAIMoveTowardsTarget(PixelmonEntityHelper par1EntityCreature, float par2, float par3) {
		this.theEntity = par1EntityCreature;
		this.field_75425_f = par2;
		this.field_75426_g = par3;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (theEntity.bc != null)
			return false;
		this.targetEntity = ((IHaveHelper) ((EntityLiving) this.theEntity.getEntity()).getAttackTarget()).getHelper();

		if (this.targetEntity == null) {
			return false;
		} else if (((EntityLiving) this.targetEntity.getEntity()).getDistanceSqToEntity(((EntityLiving) this.theEntity.getEntity())) > (double) (this.field_75426_g * this.field_75426_g)) {
			return false;
		} else {
			Vec3 var1 = RandomPositionGenerator.findRandomTargetBlockTowards(((EntityCreature) this.theEntity.getEntity()), 16, 7,
					Vec3.getVec3Pool().getVecFromPool(this.targetEntity.getXPos(), this.targetEntity.getYPos(), this.targetEntity.getZPos()));

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
		if (theEntity.bc != null)
			return false;
		return !((EntityLiving) this.theEntity.getEntity()).getNavigator().noPath() && ((EntityLiving) this.targetEntity.getEntity()).isEntityAlive()
				&& ((EntityLiving) this.targetEntity.getEntity()).getDistanceSqToEntity(((EntityLiving) this.theEntity.getEntity())) < (double) (this.field_75426_g * this.field_75426_g);
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
		((EntityLiving) this.theEntity.getEntity()).getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.field_75425_f);
	}
}
