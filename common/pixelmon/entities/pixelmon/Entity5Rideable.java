package pixelmon.entities.pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import pixelmon.entities.pixelmon.helpers.AIHelper;
import pixelmon.entities.pixelmon.helpers.PlayerRiding;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
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
						motionY += 0.1f;
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
