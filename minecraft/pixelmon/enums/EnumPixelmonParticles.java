package pixelmon.enums;

import pixelmon.battles.animations.particles.*;

/**
 * Each particle class must have a constructor with the parems {World, double, double, double, double, double, double} to prevent errors!
 * 
 * 
 */
public enum EnumPixelmonParticles {
	gastly(EntityGastlyParticle.class), koffing(EntityKoffingParticle.class),
	flame(EntityFlameParticle.class), smoke(EntitySmokeParticle.class), shiny(EntityShinyParticle.class);
	
	public Class particleClass;
	
	private EnumPixelmonParticles(Class particleClass)
	{
		this.particleClass = particleClass;
	}
}
