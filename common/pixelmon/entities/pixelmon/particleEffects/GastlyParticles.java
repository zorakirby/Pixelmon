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
		float var42 = rand.nextFloat() * (float) Math.PI * 2.0F;
		float var5 = rand.nextFloat() * 1F + .5F;
		float var52 = rand.nextFloat() * 1F + .5F;
		float var6 = MathHelper.sin(var4) * var2 * .7F * var5;
		float var62 = MathHelper.sin(var42) * var2 * .7F * var52;
		float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
		float var72 = MathHelper.cos(var42) * var2 * .5F * var52;
		float var8 = rand.nextFloat() * var2 * 1.2F;
		float var82 = rand.nextFloat() * var2 * 1.2F;
		for (int i = 0; i < 200; i++)
			ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var6, pixelmon.posY + 1.2F + var8, pixelmon.posZ + (double) var7 , pixelmon.getIsShiny());
		for (int i = 0; i < 200; i++)
			ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var62, pixelmon.posY + 1.2F + var82, pixelmon.posZ + (double) var72 , pixelmon.getIsShiny());
	}

}
