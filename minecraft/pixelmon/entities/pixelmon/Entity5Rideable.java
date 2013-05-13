package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import pixelmon.api.events.EventType;
import pixelmon.api.events.PixelmonEventHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.helpers.AIHelper;
import pixelmon.entities.pixelmon.helpers.PlayerRiding;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.enums.EnumPokemon;
import pixelmon.storage.PixelmonStorage;

public abstract class Entity5Rideable extends Entity4Textures {

	public static ArrayList<PlayerRiding> PlayerRidingList = new ArrayList<PlayerRiding>();
	private PlayerRiding playerRiding;

	private RidingHelper ridingHelper;

	public boolean isFlying = false;

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
			if (PixelmonConfig.allowRiding) {
				ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
				if (itemstack == null) {
					if (baseStats.isRideable && belongsTo(player)) {
						if (ridingHelper == null)
							ridingHelper = new RidingHelper((EntityPixelmon) this, worldObj);
						if (riddenByEntity != null) {
							player.mountEntity(this);
							((EntityPixelmon) this).aiHelper = new AIHelper(getName(), (EntityPixelmon) this, tasks);
						} else {
							player.mountEntity(this);
							tasks.taskEntries.clear();
						}
						PixelmonEventHandler.fireEvent(EventType.RidePokemon);
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
				double var9 = this.posY;
				this.moveFlying(par1, par2, jumpMovementFactor);
				this.moveEntity(this.motionX, this.motionY, this.motionZ);
				this.motionX *= 0.900000011920929D;
				this.motionY *= 0.900000011920929D;
				this.motionZ *= 0.900000011920929D;
				// this.motionY -= 0.02D;

				if (this.isCollidedHorizontally
						&& this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + var9, this.motionZ)) {
					this.motionY = 0.30000001192092896D;
				}

				movementHandled = true;
			}
		}
		if (baseStats != null && baseStats.canFly && !movementHandled) {
			double var9;
			if (!this.isInWater() && !this.handleLavaMovement()) {
				float var3 = 0.91F;

				if (this.onGround) {
					var3 = 0.54600006F;
					int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1,
							MathHelper.floor_double(this.posZ));

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
					int var6 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1,
							MathHelper.floor_double(this.posZ));

					if (var6 > 0) {
						var3 = Block.blocksList[var6].slipperiness * 0.91F;
					}
				}

				this.moveEntity(this.motionX, this.motionY, this.motionZ);

				if (this.worldObj.isRemote
						&& (!this.worldObj.blockExists((int) this.posX, 0, (int) this.posZ) || !this.worldObj.getChunkFromBlockCoords((int) this.posX,
								(int) this.posZ).isChunkLoaded)) {
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

				this.prevLimbYaw = this.limbYaw;
				var9 = this.posX - this.prevPosX;
				double var12 = this.posZ - this.prevPosZ;
				float var11 = MathHelper.sqrt_double(var9 * var9 + var12 * var12) * 4.0F;

				if (var11 > 1.0F) {
					var11 = 1.0F;
				}

				this.limbYaw += (var11 - this.limbYaw) * 0.4F;
				this.limbSwing += this.limbYaw;
				movementHandled = true;

			}

		}
		if (!movementHandled)
			super.moveEntityWithHeading(par1, par2);
	}

	@Override
	public void onLivingUpdate() {
		if (riddenByEntity != null) {
			if (ridingHelper == null) {
				riddenByEntity.mountEntity(this);
				super.onLivingUpdate();
				return;
			}
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
					moveForward += 5 * (float) playerRiding.acceleration * 500f / (float) stats.Speed;
				} else if (isInWater() && baseStats.canSurf) {
					moveForward += 30 * (float) playerRiding.acceleration * 500f / (float) stats.Speed;
				} else {
					moveForward += playerRiding.acceleration;
				}
				rotationYaw += (float) playerRiding.rotation * 1.25f;
				playerRiding.rotation = 0;
				playerRiding.acceleration = 0;
				if (!isInWater() && playerRiding.jump > 0) {
					if (onGround) {
						motionY = 0.5f;
						jump();
					} else if (baseStats.canFly) {
						motionY += 0.02f;
						isFlying = true;
					}
				} else if (isInWater()) {
					if (playerRiding.jump > 0) {
						if (baseStats.canSurf)
							motionY += 0.03f;
						else
							motionY += 0.1f;
					}

					else if (playerRiding.jump < 0)
						motionY -= 0.03f;
				}
				playerRiding.jump = 0;
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
		if (riddenByEntity != null && ridingHelper != null)
			ridingHelper.onUpdate();
	}

	@Override
	public void moveEntity(double d, double d1, double d2) {
		super.moveEntity(d, d1, d2);
	}

	@Override
	public void updateRidden() {
		super.updateRidden();
		if (ridingHelper != null)
			ridingHelper.updateRidden();
	}

	EnumPokemon ep;

	// provided to allow these values to be shifted in the debug process to work
	// out ideal riding values
	private float debugOffsetX = 0, debugOffsetY = 0, debugOffsetZ = 0;

	@Override
	public void updateRiderPosition() {
		debugOffsetX = 0f;
		debugOffsetY = 0f;
		debugOffsetZ = 0f;
		if (this.riddenByEntity != null) {
			try {
				Vec3 vec = Vec3.createVectorHelper((debugOffsetX + baseStats.ridingOffsetX) * getScale() * getScaleFactor(), 0,
						(debugOffsetZ + baseStats.ridingOffsetZ) * getScale() * getScaleFactor());
				vec.rotateAroundY(-(this.renderYawOffset) * (float) Math.PI / 180.0f);
				// System.out.println(rotationYaw +" " + renderYawOffset);
				double var1 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
				double var3 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
				if (ep == null)
					ep = EnumPokemon.get(getName());
				this.riddenByEntity.setPosition(this.posX + var1 + vec.xCoord, this.posY
						+ (this.getMountedYOffset() + baseStats.ridingOffsetY + height + debugOffsetY) * getScale() * getScaleFactor(), this.posZ + var3
						+ vec.zCoord);
			} catch (Exception e) {
				riddenByEntity.mountEntity(this);
			}
		}
	}

	public void doMoveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
	}

	public void unloadEntity() {
		if (riddenByEntity != null) {
			riddenByEntity.mountEntity(this);
			((EntityPixelmon) this).aiHelper = new AIHelper(getName(), (EntityPixelmon) this, tasks);
		}
	}
}
