package pixelmon.entities.pokemon;

import pixelmon.ClientProxy;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPixelmonParticles;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityGastly extends EntityPixelmon {

	public EntityGastly(World par1World) {
		super(par1World);
		init();
	}

	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			float var2 = .6F;
			float var4 = rand.nextFloat() * (float) Math.PI * 2.0F;
			float var5 = rand.nextFloat() * 1F + .5F;
			float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
			float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
			float var8 = rand.nextFloat() * var2;
			for (int i = 0; i < 2; i++)
				ClientProxy.spawnParticle(EnumPixelmonParticles.gastly, worldObj, posX + (double) var6, posY + 1.3F + var8, posZ + (double) var7);
		}
	}

	public void init() {
		super.init("Gastly");
		doesHover = true;
		hoverHeight = 1f;
	}
}