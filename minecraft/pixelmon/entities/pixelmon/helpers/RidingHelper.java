package pixelmon.entities.pixelmon.helpers;

import net.minecraft.world.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

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
		//parent.rotationYaw = parent.riddenByEntity.rotationYaw;
	}

	public void onLivingUpdate() {
		
	}

	public void updateRidden() {
		this.field_70768_au = this.field_70766_av;
		this.field_70766_av = 0.0F;
		parent.fallDistance = 0.0F;
	}

}