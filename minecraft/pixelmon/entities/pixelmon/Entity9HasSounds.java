package pixelmon.entities.pixelmon;

import net.minecraft.world.World;

public abstract class Entity9HasSounds extends Entity8HoldsItems {

	public Entity9HasSounds(World par1World) {
		super(par1World);
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return ("pixelmon:pixelmon." + getName().toLowerCase());
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	// @Override
	// protected String getHurtSound()
	// {
	// return "mob.cowhurt";
	// }
	//
	// /**
	// * Returns the sound this mob makes on death.
	// */
	// @Override
	// protected String getDeathSound()
	// {
	// return "mob.cowhurt";
	// }

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}


}
