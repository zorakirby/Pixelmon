package pixelmon.entities.pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.helpers.AIHelper;
import pixelmon.entities.pixelmon.helpers.PlayerRiding;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.enums.EnumType;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityBoat;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.World;

public abstract class Entity5Rideable extends Entity4Textures {

	public static ArrayList<PlayerRiding> PlayerRidingList = new ArrayList<PlayerRiding>();
	private PlayerRiding playerRiding;

	private RidingHelper ridingHelper;

	public boolean isFlying = false;

	private final boolean ridingEnabled = true;

	public Entity5Rideable(World par1World) {
		super(par1World);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		if (baseStats.isRideable && worldObj.isRemote)
			ridingHelper = new RidingHelper((EntityPixelmon) this, worldObj);
	}

	@Override
	public void jump() {
		super.jump();
	}

	@Override
	protected void fall(float f) {
		if (baseStats != null && baseStats.canFly)
			return;
		super.fall(f);
	}

	@Override
	protected boolean isAIEnabled() {
		return riddenByEntity == null;
	}

	@Override
	protected void updateEntityActionState() {
	}

	@Override
	public boolean interact(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			if (ridingEnabled) {
				ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
				if (itemstack == null) {
					if (baseStats.isRideable && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).isIn((EntityPixelmon) this)) {
						if (ridingHelper == null)
							ridingHelper = new RidingHelper((EntityPixelmon) this, worldObj);
						if (riddenByEntity != null) {
							player.mountEntity(this);
							((EntityPixelmon) this).aiHelper = new AIHelper(getName(), (EntityPixelmon) this, tasks);
							player.setPosition(posX, posY, posZ);
						} else {
							player.mountEntity(this);
							tasks.taskEntries.clear();
						}
						return true;
					}
					return false;
				}
			}
		}
		return super.interact(player);
	}

	@Override
	public double getMountedYOffset() {
		if (ridingHelper != null)
			return ridingHelper.getMountedYOffset();
		else
			return super.getMountedYOffset();
	}

	/**
	 * Moves the entity based on the specified heading. Args: strafe, forward
	 */
	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		boolean movementHandled = false;
		if (riddenByEntity != null && baseStats != null && (baseStats.canSurf || !baseStats.canSurfSet) && this.inWater) {
			if (!baseStats.canSurfSet) {
				baseStats.canSurf = DatabaseMoves.CanLearnAttack(getName(), "Surf");
				baseStats.canSurfSet = true;
			}
			if (baseStats.canSurf) {
				movementHandled = true;
			}
		}
		if (baseStats != null && baseStats.canFly) {
			double var9;
			if (!this.isInWater() && !this.handleLavaMovement()) {
				float var3 = 0.91F;

				if (this.onGround) {
					var3 = 0.54600006F;
					int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

					if (var4 > 0) {
						var3 = Block.blocksList[var4].slipperiness * 0.91F;
					}
				}

				float var8 = 0.16277136F / (var3 * var3 * var3);
				float var5;

				if (this.onGround) {
					if (this.isAIEnabled()) {
						var5 = this.getAIMoveSpeed();
					} else {
						var5 = this.landMovementFactor;
					}

					var5 *= var8;
				} else {
					if (baseStats.canFly)
						var5 = 0.1f;
					else
						var5 = this.jumpMovementFactor;
				}

				this.moveFlying(par1, par2, var5);
				var3 = 0.91F;

				if (this.onGround) {
					var3 = 0.54600006F;
					int var6 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

					if (var6 > 0) {
						var3 = Block.blocksList[var6].slipperiness * 0.91F;
					}
				}

				this.moveEntity(this.motionX, this.motionY, this.motionZ);

				if (this.worldObj.isRemote
						&& (!this.worldObj.blockExists((int) this.posX, 0, (int) this.posZ) || !this.worldObj.getChunkFromBlockCoords((int) this.posX, (int) this.posZ).isChunkLoaded)) {
					if (this.posY > 0.0D) {
						this.motionY -= 0.01D;
					} else {
						this.motionY = 0.0D;
					}
				} else {
					this.motionY -= 0.01D;
				}

				this.motionY *= 0.9800000190734863D;
				this.motionX *= (double) var3;
				this.motionZ *= (double) var3;

				this.prevLegYaw = this.legYaw;
				var9 = this.posX - this.prevPosX;
				double var12 = this.posZ - this.prevPosZ;
				float var11 = MathHelper.sqrt_double(var9 * var9 + var12 * var12) * 4.0F;

				if (var11 > 1.0F) {
					var11 = 1.0F;
				}

				this.legYaw += (var11 - this.legYaw) * 0.4F;
				this.legSwing += this.legYaw;
				movementHandled = true;

			}

		}
		if (!movementHandled)
			super.moveEntityWithHeading(par1, par2);
	}

	@Override
	public void onLivingUpdate() {
		// if (baseStats != null && baseStats.canFly)
		//
		// else
		if (riddenByEntity != null) {
			ridingHelper.onLivingUpdate();
			moveForward *= 0.4f;
			if (moveForward > -0.1 && moveForward < 0.1)
				moveForward = 0;
			if (playerRiding == null) {
				for (int i = 0; i < PlayerRidingList.size(); i++) {
					if (PlayerRidingList.get(i).player == riddenByEntity)
						playerRiding = PlayerRidingList.get(i);
				}
			}
			if (playerRiding != null) {
				if (isFlying) {
					moveForward += 4 * playerRiding.acceleration;
				} else {
					moveForward += playerRiding.acceleration;
				}
				rotationYaw += playerRiding.rotation;
				playerRiding.rotation = 0;
				playerRiding.acceleration = 0;
				if (playerRiding.jump > 0) {
					if (onGround) {
						motionY = 0.5f;
						jump();
					} else if (baseStats.canFly) {
						motionY += 0.02f;
						isFlying = true;
					}
					playerRiding.jump = 0;
				}
				if (onGround && isFlying)
					isFlying = false;
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		// if (baseStats != null && baseStats.canFly)
		// ((Entity)this).onEntityUpdate();
		// else
		super.onUpdate();
		if (riddenByEntity != null)
			ridingHelper.onUpdate();
	}

	@Override
	public void moveEntity(double d, double d1, double d2) {
		super.moveEntity(d, d1, d2);
	}

	@Override
	public void updateRidden() {
		super.updateRidden();
		ridingHelper.updateRidden();
	}

	@Override
	public void updateRiderPosition() {
		if (this.riddenByEntity != null) {
			double var1 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double var3 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + var1, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
		}
	}

	public void doMoveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
	}
}
