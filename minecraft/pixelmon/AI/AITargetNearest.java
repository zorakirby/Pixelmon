package pixelmon.AI;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AITargetNearest extends AITarget {
	EntityLivingBase targetEntity;
	private TargetSorter field_48387_g;

	public AITargetNearest(Entity7HasAI entity7HasAI, float par3, boolean par5) {
		this(entity7HasAI, par3, par5, false);
	}

	public AITargetNearest(Entity7HasAI entity7HasAI, float par3, boolean par5, boolean par6) {
		super(entity7HasAI, par3, par5, par6);
		this.targetDistance = par3;
		this.field_48387_g = new TargetSorter(this, entity7HasAI);
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (taskOwner.battleController != null)
			return false;

		EntityPlayer var1 = this.taskOwner.worldObj.getClosestPlayerToEntity(this.taskOwner, (double) this.targetDistance);
		if (this.isSuitableTarget(var1, true)) {
			this.targetEntity = var1;
			return true;
		}

		List var5 = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityPixelmon.class,
				this.taskOwner.boundingBox.expand((double) this.targetDistance, 4.0D, (double) this.targetDistance));
		Collections.sort(var5, this.field_48387_g);
		Iterator var2 = var5.iterator();

		while (var2.hasNext()) {
			Entity var3 = (Entity) var2.next();
			EntityLiving var4 = (EntityLiving) var3;

			if (this.isSuitableTarget(var4, true)) {
				this.targetEntity = var4;
				return true;
			}
		}

		return false;

	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}

}
