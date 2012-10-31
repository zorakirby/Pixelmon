package pixelmon.AI;

import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PathEntity;
import net.minecraft.src.PathPoint;

public abstract class AITarget extends EntityAIBase {
	/** The entity that this task belongs to */
	protected Entity7HasAI taskOwner;
	protected float targetDistance;

	/**
	 * If true, EntityAI targets must be able to be seen (cannot be blocked by
	 * walls) to be suitable targets.
	 */
	protected boolean shouldCheckSight;
	private boolean field_75303_a;
	private int field_75301_b;
	private int field_75302_c;
	private int field_75298_g;

	public AITarget(EntityPixelmon par1EntityLiving, float par2, boolean par3) {
		this(par1EntityLiving, par2, par3, false);
		setMutexBits(3);
	}

	public AITarget(Entity7HasAI entity7HasAI, float par2, boolean par3, boolean par4) {
		this.field_75301_b = 0;
		this.field_75302_c = 0;
		this.field_75298_g = 0;
		this.taskOwner = entity7HasAI;
		this.targetDistance = par2;
		this.shouldCheckSight = par3;
		this.field_75303_a = par4;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		if (taskOwner.battleController != null)
			return false;
		EntityLiving var1 = this.taskOwner.getAttackTarget();

		if (var1 == null) {
			return false;
		} else if (!var1.isEntityAlive()) {
			return false;
		} else if (this.taskOwner.getDistanceSqToEntity(var1) > (double) (this.targetDistance * this.targetDistance)) {
			return false;
		} else {
			if (this.shouldCheckSight) {
				if (this.taskOwner.getEntitySenses().canSee(var1)) {
					this.field_75298_g = 0;
				} else if (++this.field_75298_g > 60) {
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.field_75301_b = 0;
		this.field_75302_c = 0;
		this.field_75298_g = 0;
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.taskOwner.setAttackTarget((EntityLiving) null);
	}

	/**
	 * A method used to see if an entity is a suitable target through a number
	 * of checks.
	 */
	protected boolean isSuitableTarget(EntityLiving entity, boolean par2) {
		if (entity == null) {
			return false;
		} else if (!(entity instanceof EntityPixelmon) && !(entity instanceof EntityPlayer))
			return false;
		else if (entity == this.taskOwner) {
			return false;
		} else if (!entity.isEntityAlive()) {
			return false;
		} else if (entity.boundingBox.maxY > this.taskOwner.boundingBox.minY && entity.boundingBox.minY < this.taskOwner.boundingBox.maxY) {

			if (this.taskOwner instanceof EntityPixelmon && ((EntityPixelmon) this.taskOwner).getOwner() != null) {
				if (entity instanceof EntityPixelmon
						&& ((EntityPixelmon) entity).getOwner() == ((EntityPixelmon) this.taskOwner).getOwner()) {
					return false;
				}

				if (entity == ((EntityTameable) this.taskOwner).getOwner()) {
					return false;
				}
			} else if (entity instanceof EntityPlayer && !par2 && ((EntityPlayer) entity).capabilities.disableDamage) {
				return false;
			}

			if (!this.taskOwner.isWithinHomeDistance(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY),
					MathHelper.floor_double(entity.posZ))) {
				return false;
			} else if (this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee(entity)) {
				return false;
			} else {
				if (this.field_75303_a) {
					if (--this.field_75302_c <= 0) {
						this.field_75301_b = 0;
					}

					if (this.field_75301_b == 0) {
						this.field_75301_b = this.func_75295_a(entity) ? 1 : 2;
					}

					if (this.field_75301_b == 2) {
						return false;
					}
				}

				return true;
			}

		} else {
			return false;
		}
	}

	private boolean func_75295_a(EntityLiving par1EntityLiving) {
		this.field_75302_c = 10 + this.taskOwner.getRNG().nextInt(5);
		PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLiving);

		if (var2 == null) {
			return false;
		} else {
			PathPoint var3 = var2.getFinalPathPoint();

			if (var3 == null) {
				return false;
			} else {
				int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
				int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
				return (double) (var4 * var4 + var5 * var5) <= 2.25D;
			}
		}
	}
}
