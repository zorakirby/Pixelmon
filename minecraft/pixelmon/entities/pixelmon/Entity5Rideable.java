package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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

	int initAir = 0;

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
							initAir = player.getAir();
						}
						PixelmonEventHandler.fireEvent(EventType.RidePokemon, player);
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
	public void moveEntityWithHeading(float strafe, float forward) {
		boolean movementHandled = false;

		if (riddenByEntity != null && baseStats != null) {
			this.prevRotationYaw = this.rotationYaw;// =
													// this.riddenByEntity.rotationYaw;
			this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			strafe = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F;
			rotationYaw -= strafe * 10;
			strafe = 0;
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			forward = ((EntityLivingBase) this.riddenByEntity).moveForward;
		}

		if (riddenByEntity != null && baseStats != null && (baseStats.canSurf || !baseStats.canSurfSet) && this.inWater) {
			if (!baseStats.canSurfSet) {
				baseStats.canSurf = DatabaseMoves.CanLearnAttack(baseStats.id, "Surf");
				baseStats.canSurfSet = true;
			}
			if (baseStats.canSurf) {
				double var9 = this.posY;
				this.moveFlying(strafe, forward, jumpMovementFactor);
				this.moveEntity(this.motionX, this.motionY, this.motionZ);

				if (this.isCollidedHorizontally
						&& this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + var9, this.motionZ)) {
					this.motionY = 0.30000001192092896D;
				}

				this.motionY *= 0.9800000190734863D;
				this.motionX *= (double) 0.96f;
				this.motionZ *= (double) 0.96f;

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
						var5 = getRideSpeed();
					} else {
						var5 = 1;
					}

					var5 *= var8;
				} else {
					var5 = getRideSpeed();
				}

				this.moveFlying(strafe, forward, var5);
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
					this.motionY -= 0.02D;
				}

				this.motionY *= 0.9800000190734863D;
				this.motionX *= (double) var3;
				this.motionZ *= (double) var3;

				this.prevLimbSwingAmount = this.limbSwingAmount;
				var9 = this.posX - this.prevPosX;
				double var12 = this.posZ - this.prevPosZ;
				float var11 = MathHelper.sqrt_double(var9 * var9 + var12 * var12) * 4.0F;

				if (var11 > 1.0F) {
					var11 = 1.0F;
				}

				this.limbSwingAmount += (var11 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				movementHandled = true;

			}

		}
		if (!movementHandled) {
			if (riddenByEntity != null && baseStats != null)
				moveEntityRidden(strafe, forward);
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	private void moveEntityRidden(float strafe, float forward) {
		double d0;

		if (this.isInWater()) {
			d0 = this.posY;
			this.moveFlying(strafe, forward, 0.05F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.800000011920929D;
			this.motionY *= 0.800000011920929D;
			this.motionZ *= 0.800000011920929D;
			this.motionY -= 0.019D;

			if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ)) {
				this.motionY = 0.30000001192092896D;
			}
		} else if (this.handleLavaMovement()) {
			d0 = this.posY;
			this.moveFlying(strafe, forward, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.6D;
			this.motionY *= 0.6D;
			this.motionZ *= 0.6D;
			this.motionY -= 0.02D;

			if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ)) {
				this.motionY = 0.30000001192092896D;
			}
		} else {
			float f2 = 0.91F;

			if (this.onGround) {
				f2 = 0.54600006F;
				int i = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1,
						MathHelper.floor_double(this.posZ));

				if (i > 0) {
					f2 = Block.blocksList[i].slipperiness * 0.91F;
				}
			}

			float f3 = 0.16277136F / (f2 * f2 * f2);
			float f4;

			if (this.onGround) {
				f4 = getRideSpeed() * f3;
			} else {
				f4 = getRideSpeed() * 0.6f;
			}

			this.moveFlying(strafe, forward, f4);
			f2 = 0.91F;

			if (this.onGround) {
				f2 = 0.54600006F;
				int j = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1,
						MathHelper.floor_double(this.posZ));

				if (j > 0) {
					f2 = Block.blocksList[j].slipperiness * 0.91F;
				}
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			if (this.worldObj.isRemote
					&& (!this.worldObj.blockExists((int) this.posX, 0, (int) this.posZ) || !this.worldObj.getChunkFromBlockCoords((int) this.posX,
							(int) this.posZ).isChunkLoaded)) {
				if (this.posY > 0.0D) {
					this.motionY = -0.1D;
				} else {
					this.motionY = 0.0D;
				}
			} else {
				this.motionY -= 0.04D;
			}

			this.motionY *= 0.9800000190734863D;
			this.motionX *= (double) f2;
			this.motionZ *= (double) f2;
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		d0 = this.posX - this.prevPosX;
		double d1 = this.posZ - this.prevPosZ;
		float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	private float getRideSpeed() {
		return 0.08f + 0.05f * stats.Speed / 500f;
	}

	@Override
	public void onLivingUpdate() {
		if (riddenByEntity != null) {
			if (ridingHelper == null) {
				riddenByEntity.mountEntity(this);
				super.onLivingUpdate();
				return;
			}
			if (baseStats.canSurf) {
				if (riddenByEntity.isInWater() && riddenByEntity instanceof EntityPlayer) {
					((EntityPlayer) riddenByEntity).addPotionEffect((new PotionEffect(Potion.nightVision.getId(), 10, 0)));
				}
				riddenByEntity.setAir(initAir);
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
				if (!isInWater() && playerRiding.jump > 0) {
					if (onGround) {
						jump();
						motionY *= 1f + stats.Speed / 500f;
					} else if (baseStats.canFly) {
						motionY += 0.04f + 0.06f * stats.Speed / 500f;
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
				Vec3 vec = Vec3.createVectorHelper((debugOffsetX + baseStats.ridingOffsets.standing.x) * getPixelmonScale() * getScaleFactor(), 0,
						(debugOffsetZ + baseStats.ridingOffsets.standing.z) * getPixelmonScale() * getScaleFactor());
				vec.rotateAroundY(-(this.renderYawOffset) * (float) Math.PI / 180.0f);
				// System.out.println(rotationYaw +" " + renderYawOffset);
				double var1 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
				double var3 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
				if (ep == null)
					ep = EnumPokemon.get(getName());
				this.riddenByEntity.setPosition(this.posX + var1 + vec.xCoord, this.posY
						+ (this.getMountedYOffset() + baseStats.ridingOffsets.standing.y + height + debugOffsetY) * getPixelmonScale() * getScaleFactor(),
						this.posZ + var3 + vec.zCoord);
			} catch (Exception e) {
				riddenByEntity.mountEntity(this);
			}
		}
	}

	public void doMoveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
	}

	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}

	public void unloadEntity() {
		if (riddenByEntity != null) {
			riddenByEntity.mountEntity(this);
			((EntityPixelmon) this).aiHelper = new AIHelper(getName(), (EntityPixelmon) this, tasks);
		}
	}

}
