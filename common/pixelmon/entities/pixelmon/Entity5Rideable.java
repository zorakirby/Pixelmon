package pixelmon.entities.pixelmon;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelBase;
import net.minecraft.src.World;

public abstract class Entity5Rideable extends Entity4Textures {

	private RidingHelper ridingHelper;

	@SideOnly (Side.CLIENT)
	private double velocityX;
	@SideOnly (Side.CLIENT)
	private double velocityY;
	@SideOnly (Side.CLIENT)
	private double velocityZ;

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
			ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
			if (itemstack == null) {
				if (baseStats.IsRideable && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).isIn((EntityPixelmon)this)) {
					player.mountEntity(this);
					return true;
				}
				return false;
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

	@Override
	public void updateRidden() {
		if (ridingHelper != null)
			ridingHelper.updateRidden();
		else
			super.updateRidden();
	}

	public void doMoveEntity(double motionX, double motionY, double motionZ) {
		super.moveEntity(motionX, motionY, motionZ);
	}
}
