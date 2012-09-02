package pixelmon.entities.pixelmon;

import pixelmon.entities.pixelmon.helpers.RidingHelper;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.ModelBase;
import net.minecraft.src.World;

public abstract class Entity5Rideable extends Entity4Textures {

	private RidingHelper ridingHelper;
	
	public Entity5Rideable(World par1World) {
		super(par1World);
		ridingHelper = new RidingHelper(this, worldObj);
	}
	
	@Override
	protected void init(String name) {
		super.init(name);
		if (baseStats.IsRideable)
			ridingHelper = new RidingHelper(this, worldObj);
	}
	@Override
	public void jump() {
		super.jump();
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
