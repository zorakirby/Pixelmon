package pixelmon.entities.pixelmon;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import pixelmon.entities.pixelmon.helpers.AIHelper;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityBoat;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.World;

public abstract class Entity5Rideable extends Entity4Textures {

	private RidingHelper ridingHelper;

	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	private boolean isMounted;

	private final boolean ridingEnabled = false;

	public Entity5Rideable(World par1World) {
		super(par1World);

	}

	@Override
	protected void init(String name) {
		super.init(name);
		if (baseStats.IsRideable && worldObj.isRemote)
			ridingHelper = new RidingHelper((EntityPixelmon) this, worldObj);
	}

	@Override
	public void jump() {
		super.jump();
	}

	@Override
	public boolean interact(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			if (ridingEnabled) {
				ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
				if (itemstack == null) {
					if (baseStats.IsRideable && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).isIn((EntityPixelmon) this)) {
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
		super.onLivingUpdate();
		if (ridingHelper != null)
			ridingHelper.onLivingUpdate();
	}

	@Override
	public void moveEntity(double d, double d1, double d2) {
		if (ridingHelper != null)
			ridingHelper.moveEntity(d, d1, d2);
		else
			super.moveEntity(d, d1, d2);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5) {
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.worldObj.isRemote && this.riddenByEntity != null) {
			double newPosX = this.posX + this.motionX;
			double newPosY = this.posY;
			double newPosZ = this.posZ + this.motionZ;
			this.setPosition(newPosX, newPosY, newPosZ);
		} else {
			if (this.riddenByEntity != null) {
				this.motionX += this.riddenByEntity.motionX * getMoveSpeed();
				this.motionY += this.riddenByEntity.motionY * getMoveSpeed();
				this.motionZ += this.riddenByEntity.motionZ * getMoveSpeed();
				this.riddenByEntity.setPosition(posX, posY, posZ);
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

		}
		if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
			this.riddenByEntity = null;
		}
	}

	@Override
	public void updateRidden() {
		if (ridingHelper != null)
			ridingHelper.updateRidden();
		else {
			super.updateRidden();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		if (this.riddenByEntity == null)
			super.setPositionAndRotation2(par1, par3, par5, par7, par8, par9);
		else {
			this.motionX = this.velocityX;
			this.motionY = this.velocityY;
			this.motionZ = this.velocityZ;
		}
	}

	public void doMoveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
	}
}
