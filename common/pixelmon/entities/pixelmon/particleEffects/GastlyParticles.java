package pixelmon.entities.pixelmon.particleEffects;

import net.minecraft.src.MathHelper;
import pixelmon.ClientProxy;
import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumPixelmonParticles;

public class GastlyParticles extends ParticleEffects {

	public GastlyParticles(Entity4Textures pixelmon) {
		super(pixelmon);
	}

	@Override
	public void onUpdate() {
		float var2 = .6F;
		float var4 = rand.nextFloat() * (float) Math.PI * 2.0F;
		float var5 = rand.nextFloat() * 1F + .5F;
		float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
		float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
		float var8 = rand.nextFloat() * var2;
		for (int i = 0; i < 2; i++)
			ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var6, pixelmon.posY + 1.3F + var8, pixelmon.posZ + (double) var7, pixelmon.getIsShiny());
	}

}
