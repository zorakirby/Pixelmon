package pixelmon.battles.animations.particles;

import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityShinyParticle extends EntityExplodeFX {
	float smokeParticleScale;

	public EntityShinyParticle(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
	}

	public EntityShinyParticle(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14) {
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.particleRed = 1F;
		this.particleGreen = 0.8F;
		this.particleBlue = 0.3F;
		this.motionX = par8 + (double) ((float) (Math.random()));
		this.motionY = par10 + (double) ((float) (Math.random()));
		this.motionZ = par12;
		this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() + 0.7F;
		this.particleMaxAge = 3;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
		this.motionY += 0.004D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.8999999761581421D;
		this.motionY *= 0.8999999761581421D;
		this.motionZ *= 0.8999999761581421D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}
