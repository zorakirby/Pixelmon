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

	public void onUpdate(){
		parent.rotationYaw = parent.riddenByEntity.rotationYaw;
	}
	
	public void onLivingUpdate() {
		parent.isJumping = ((EntityLiving) parent.riddenByEntity).isJumping;
		parent.motionX = parent.riddenByEntity.motionX * 16;
		parent.motionZ = parent.riddenByEntity.motionZ * 16;

		if (parent.riddenByEntity.motionX > 0) {
			System.out.println("motionX=" + parent.riddenByEntity.motionX);
			System.out.println("motionZ=" + parent.riddenByEntity.motionZ);
		}
	}

	public void updateRidden() {
		this.field_70768_au = this.field_70766_av;
		this.field_70766_av = 0.0F;
		parent.fallDistance = 0.0F;
	}

}