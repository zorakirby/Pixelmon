package pixelmon.entities.pixelmon.particleEffects;

import java.util.Random;

import pixelmon.entities.pixelmon.Entity4Textures;

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
		else if (pixelmon.getName().equalsIgnoreCase("Charmander"))
			return new FlameParticles(pixelmon, 0.55F, 0.52F, 2);
		else if (pixelmon.getName().equalsIgnoreCase("Charmeleon"))
			return new FlameParticles(pixelmon, 0.95F, 1.2F, 4);
		else if (pixelmon.getName().equalsIgnoreCase("Charizard"))
			return new FlameParticles(pixelmon, 4.35F, 1.55F, 7);
		return null;
	}
}
