package pixelmon.client;

import java.util.Iterator;
import java.util.List;

import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCamera extends EntityLiving {

	public EntityCamera(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void entityInit() {
		super.entityInit();

	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		if (this.newPosRotationIncrements > 0) {
			double d0 = this.posX + (this.newPosX - this.posX) / (double) this.newPosRotationIncrements;
			double d1 = this.posY + (this.newPosY - this.posY) / (double) this.newPosRotationIncrements;
			double d2 = this.posZ + (this.newPosZ - this.posZ) / (double) this.newPosRotationIncrements;
			double d3 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double) this.rotationYaw);
			this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.newPosRotationIncrements);
			this.rotationPitch = (float) ((double) this.rotationPitch + (this.newRotationPitch - (double) this.rotationPitch)
					/ (double) this.newPosRotationIncrements);
			--this.newPosRotationIncrements;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}

		this.rotationYawHead = this.rotationYaw;
	}

	/**
	 * Moves the entity based on the specified heading. Args: strafe, forward
	 */
	@Override
	public void moveEntityWithHeading(float par1, float par2) {
		double d0;

		d0 = this.posY;
		this.moveFlying(par1, par2, 0.02F);
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

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

	@Override
	public void onEntityUpdate() {
		this.field_70763_ax = this.field_70764_aw;
		this.prevRenderYawOffset = this.renderYawOffset;
		this.prevRotationYawHead = this.rotationYawHead;
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (Minecraft.getMinecraft().renderViewEntity == this && target != null && !target.isDead) {
			System.out.println("Camera Hooked");
			double vecX = target.posX - posX;
			double vecY = target.posY - 0.5f - posY;
			double vecZ = target.posZ - posZ;
			double length = (double) MathHelper.sqrt_double(vecX * vecX + vecZ * vecZ);
			float f2 = (float) (Math.atan2(vecZ, vecX) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) (-(Math.atan2(vecY, length) * 180.0D / Math.PI));
			this.rotationPitch = this.updateRotation(this.rotationPitch, f3, 50.0F);
			this.rotationYaw = this.updateRotation(this.rotationYaw, f2, 50.0F);
			this.lastTickPosX = this.posX;
			this.lastTickPosY = this.posY + (double) this.yOffset;
			this.lastTickPosZ = this.posZ;
		}
	}

	private float updateRotation(float par1, float par2, float par3) {
		float f3 = MathHelper.wrapAngleTo180_float(par2 - par1);

		if (f3 > par3) {
			f3 = par3;
		}

		if (f3 < -par3) {
			f3 = -par3;
		}

		return par1 + f3;
	}

	public Entity target;

	public void pointAt(Entity entity) {
		target = entity;
		if (target != null) {
			prevPosX = posX = target.posX + 3;
			prevPosY = posY = target.posY + 0.5f;
			prevPosZ = posZ = target.posZ;
		}
	}

}
