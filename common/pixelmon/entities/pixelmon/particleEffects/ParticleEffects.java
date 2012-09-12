package pixelmon.entities.pixelmon.particleEffects;

import java.util.Random;

import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumPokemon;

public abstract class ParticleEffects {

	Random rand = new Random();
	Entity4Textures pixelmon;
	public ParticleEffects(Entity4Textures pixelmon){
		this.pixelmon = pixelmon;
	}
	
	public abstract void onUpdate();

	
	public static ParticleEffects getParticleEffects(Entity4Textures pixelmon){
		if (pixelmon.getName().equalsIgnoreCase("Weezing"))
			return new WeezingParticles(pixelmon);
		else if (pixelmon.getName().equalsIgnoreCase("Koffing"))
			return new KoffingParticles(pixelmon);
		else if (pixelmon.getName().equalsIgnoreCase("Diglett") || pixelmon.getName().equalsIgnoreCase("Dugtrio"))
			return new DiglettParticles(pixelmon);
		else if (pixelmon.getName().equalsIgnoreCase("Gastly"))
			return new GastlyParticles(pixelmon);
		
		return null;
	}
}
