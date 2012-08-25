package pixelmon.enums;

import pixelmon.battles.animations.particles.EntityGastlyParticle;
import pixelmon.battles.animations.particles.EntityKoffingParticle;

/**
 * Each particle class must have a constructor with the parems {World, double, double, double, double, double, double} to prevent errors!
 * 
 *
 */
public enum EnumPixelmonParticles {
	gastly(EntityGastlyParticle.class), koffing(EntityKoffingParticle.class);
	
	public Class particleClass;
	
	private EnumPixelmonParticles(Class particleClass)
	{
		this.particleClass = particleClass;
	}
}
