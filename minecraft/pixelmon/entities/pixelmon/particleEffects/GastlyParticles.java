package pixelmon.entities.pixelmon.particleEffects;

import net.minecraft.util.MathHelper;
import pixelmon.client.ClientProxy;
import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumGrowth;
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

		if (pixelmon.getGrowth() == EnumGrowth.Enormous) {
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var6 + .2F, pixelmon.posY + 2F + var8,
						pixelmon.posZ + (double) var7 + .4F, pixelmon.getIsShiny());
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var62 + .2F, pixelmon.posY + 2F + var82,
						pixelmon.posZ + (double) var72 - .6F, pixelmon.getIsShiny());
		} else if (pixelmon.getGrowth() == EnumGrowth.Huge) {
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var6 + .2F, pixelmon.posY + 1.5F + var8,
						pixelmon.posZ + (double) var7 + .4F, pixelmon.getIsShiny());
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var62 + .2F, pixelmon.posY + 1.5F + var82,
						pixelmon.posZ + (double) var72 - .6F, pixelmon.getIsShiny());
		} else if (pixelmon.getGrowth() == EnumGrowth.Pygmy) {
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var6 + .2F, pixelmon.posY + var8 + .6,
						pixelmon.posZ + (double) var7 + .4F, pixelmon.getIsShiny());
		} else {
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var6 + .2F, pixelmon.posY + 1.2F + var8,
						pixelmon.posZ + (double) var7 + .4F, pixelmon.getIsShiny());
			for (int i = 0; i < 20; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, pixelmon.worldObj, pixelmon.posX + (double) var62 + .2F, pixelmon.posY + 1.2F + var82,
						pixelmon.posZ + (double) var72 - .6F, pixelmon.getIsShiny());
		}
	}

}
