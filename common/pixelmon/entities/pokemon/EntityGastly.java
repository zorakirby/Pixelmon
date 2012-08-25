package pixelmon.entities.pokemon;

import pixelmon.battles.animations.particles.EntityGastlyParticle;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.client.Minecraft;
import net.minecraft.src.MathHelper;

import net.minecraft.src.World;

public class EntityGastly extends EntityGroundPixelmon {

	public EntityGastly(World par1World) {
		super(par1World);
		init();
	}

	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			float var2 = .6F;
			float var4 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
			float var5 = this.rand.nextFloat() * 1F + .5F;
			float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
			float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
			float var8 = this.rand.nextFloat() * var2;

			for (int i = 0; i < 2; i++)
				Minecraft.getMinecraft().effectRenderer.addEffect(new EntityGastlyParticle(worldObj, posX + (double) var6, posY + 1.3F + var8, posZ + (double) var7, 0D, 0D, 0D));
		}
	}

	public void init() {
		name = "Gastly";
		yOffset = 0;
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight = 1f;
		super.init();
	}

	public void evolve() {
	}

}