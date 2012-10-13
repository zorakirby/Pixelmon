package pixelmon.entities;

import pixelmon.Pixelmon;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;

public class EntityCamera extends EntityLiving {

	public EntityCamera(World world) {
		super(world);
		Pixelmon.proxy.registerCameraEntity(this);
	}
	
	public EntityCamera(World par1World, double x, double y, double z, float angleYaw, float anglePitch) {
		super(par1World);
		this.posX = x; this.posY = y; this.posZ = z;
		this.lastTickPosX = x; this.lastTickPosY = y; this.lastTickPosZ = z;
		this.rotationYawHead = angleYaw;
		this.rotationPitch = anglePitch;
		this.rotationYaw = angleYaw;
	}
	
	@Override
	public int getMaxHealth() {
		return 1;
	}
	
	@Override
	public void onUpdate() {
		this.posY +=0.01;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void fall(float par1) {
	}
	
	@Override
	public void onEntityUpdate() {
	}
}
