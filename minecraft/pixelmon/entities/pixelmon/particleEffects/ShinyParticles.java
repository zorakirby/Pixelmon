package pixelmon.entities.pixelmon.particleEffects;

import java.util.Random;

import net.minecraft.util.MathHelper;
import pixelmon.client.ClientProxy;
import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumPixelmonParticles;

public class ShinyParticles extends ParticleEffects {

	public ShinyParticles(Entity4Textures pixelmon) {
		super(pixelmon);
	}

	int count = 0;
	boolean particlesOn = false;

	@Override
	public void onUpdate() {

		if (count <= 2) {
			particlesOn = !particlesOn;
			if (particlesOn)
				count = (new Random()).nextInt(3);
			else {
				count = (new Random()).nextInt(30);
			}
		}
		count--;
		if (particlesOn)
			ClientProxy.spawnParticle(EnumPixelmonParticles.shiny, pixelmon.worldObj, pixelmon.posX, pixelmon.posY + 0.6F, pixelmon.posZ, pixelmon.getIsShiny());
	}
}
