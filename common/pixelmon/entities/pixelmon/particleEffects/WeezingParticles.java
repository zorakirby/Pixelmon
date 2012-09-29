package pixelmon.entities.pixelmon.particleEffects;

import java.util.Random;

import net.minecraft.src.MathHelper;
import pixelmon.ClientProxy;
import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumPixelmonParticles;

public class WeezingParticles extends ParticleEffects {

	public WeezingParticles(Entity4Textures pixelmon) {
		super(pixelmon);
	}

	int count = 0;
	boolean particlesOn = false;

	@Override
	public void onUpdate() {
		float var2 = pixelmon.baseStats.Width * pixelmon.baseStats.giScale * pixelmon.getScale();
		float var4 = rand.nextFloat() * (float) Math.PI * 2.0F;
		float var5 = rand.nextFloat() * 4F + .5F;
		float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
		float var7 = MathHelper.cos(var4) * var2 * .5F * var5;

		if (count <= 0) {
			particlesOn = !particlesOn;
			if (particlesOn)
				count = (new Random()).nextInt(3);
			else
				count = (new Random()).nextInt(21);
		}
		count--;
		if (particlesOn)
			ClientProxy.spawnParticle(EnumPixelmonParticles.koffing, pixelmon.worldObj, pixelmon.posX + (double) var6, pixelmon.posY + pixelmon.hoverTimer + 2.5F, pixelmon.posZ + (double) var7, pixelmon.getIsShiny());
	}

}
