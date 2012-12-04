package pixelmon.entities.pixelmon.helpers;

import java.util.Iterator;
import java.util.List;

import pixelmon.entities.pixelmon.EntityFlyingPixelmon;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovementInput;
import net.minecraft.src.Packet5PlayerInventory;
import net.minecraft.src.World;
import net.minecraft.src.WorldServer;
import net.minecraftforge.common.ForgeHooks;

public class RidingHelper {

	EntityPixelmon parent;
	World worldObj;
	private Object field_70768_au;
	private float field_70766_av;
	private int jumpTicks;

	public RidingHelper(EntityPixelmon parent, World worldObj) {
		this.worldObj = worldObj;
		this.parent = parent;
	}

	public double getMountedYOffset() {
		return (double) parent.height * 0.9D;
	}

	public void onUpdate() {
		this.onLivingUpdate();
		double diffPosX = parent.posX - parent.prevPosX;
		double diffPosZ = parent.posZ - parent.prevPosZ;
		float sqDiff = (float) (diffPosX * diffPosX + diffPosZ * diffPosZ);
		float var6 = parent.renderYawOffset;
		float var7 = 0.0F;
		this.field_70768_au = this.field_70766_av;
		float var8 = 0.0F;

		if (sqDiff > 0.0025000002F) {
			var8 = 1.0F;
			var7 = (float) Math.sqrt((double) sqDiff) * 3.0F;
			var6 = (float) Math.atan2(diffPosZ, diffPosX) * 180.0F / (float) Math.PI - 90.0F;
		}

		if (parent.swingProgress > 0.0F) {
			var6 = parent.rotationYaw;
		}

		if (!parent.onGround) {
			var8 = 0.0F;
		}

		this.field_70766_av += (var8 - this.field_70766_av) * 0.3F;

		float var9 = MathHelper.wrapAngleTo180_float(var6 - parent.renderYawOffset);
		parent.renderYawOffset += var9 * 0.3F;
		float var10 = MathHelper.wrapAngleTo180_float(parent.rotationYaw - parent.renderYawOffset);
		boolean var11 = var10 < -90.0F || var10 >= 90.0F;

		if (var10 < -75.0F) {
			var10 = -75.0F;
		}

		if (var10 >= 75.0F) {
			var10 = 75.0F;
		}

		parent.renderYawOffset = parent.rotationYaw - var10;

		if (var10 * var10 > 2500.0F) {
			parent.renderYawOffset += var10 * 0.2F;
		}

		if (var11) {
			var7 *= -1.0F;
		}

		while (parent.rotationYaw - parent.prevRotationYaw < -180.0F) {
			parent.prevRotationYaw -= 360.0F;
		}

		while (parent.rotationYaw - parent.prevRotationYaw >= 180.0F) {
			parent.prevRotationYaw += 360.0F;
		}

		while (parent.renderYawOffset - parent.prevRenderYawOffset < -180.0F) {
			parent.prevRenderYawOffset -= 360.0F;
		}

		while (parent.renderYawOffset - parent.prevRenderYawOffset >= 180.0F) {
			parent.prevRenderYawOffset += 360.0F;
		}

		while (parent.rotationPitch - parent.prevRotationPitch < -180.0F) {
			parent.prevRotationPitch -= 360.0F;
		}

		while (parent.rotationPitch - parent.prevRotationPitch >= 180.0F) {
			parent.prevRotationPitch += 360.0F;
		}

		while (parent.rotationYawHead - parent.prevRotationYawHead < -180.0F) {
			parent.prevRotationYawHead -= 360.0F;
		}

		while (parent.rotationYawHead - parent.prevRotationYawHead >= 180.0F) {
			parent.prevRotationYawHead += 360.0F;
		}
	}

	public void onLivingUpdate() {
		parent.isJumping = ((EntityLiving) parent.riddenByEntity).isJumping;
		parent.motionX = parent.riddenByEntity.motionX;
		parent.motionZ = parent.riddenByEntity.motionZ;
	}

	public void updateRidden() {
		this.field_70768_au = this.field_70766_av;
		this.field_70766_av = 0.0F;
		parent.fallDistance = 0.0F;
	}

}