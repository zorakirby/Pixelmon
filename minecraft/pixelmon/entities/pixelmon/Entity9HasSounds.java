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
		String name = getName().toLowerCase();
		if (baseStats.hasMaleFemaleSound)
			name += isMale ? "M" : "F";
		if (baseStats.numSounds == 1)
			return ("pixelmon:pixelmon." + name);
		if (baseStats.numSounds > 1) {
			int ind = getRNG().nextInt(baseStats.numSounds);
			if (ind == 0) {
				return ("pixelmon:pixelmon." + name);
			} else {
				ind++;
				return ("pixelmon:pixelmon." + name + ind);
			}
		}
		return "";
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
