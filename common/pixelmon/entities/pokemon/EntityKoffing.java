package pixelmon.entities.pokemon;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import pixelmon.battles.animations.particles.EntityGastlyParticle;
import pixelmon.battles.animations.particles.EntityKoffingParticle;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityKoffing extends EntityGroundPixelmon {

	public EntityKoffing(World world) {
		super(world);
		init();
	}

	public float hoverTimer;

	int count = 0;
	boolean particlesOn = false;

	public void onUpdate() {
		super.onUpdate();

		float var2 = helper.stats.BaseStats.Width * helper.giScale * helper.scale;
		float var4 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
		float var5 = this.rand.nextFloat() * 4F + .5F;
		float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
		float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
		
		if (count==0){
			particlesOn = !particlesOn;
			if (particlesOn)
				count = (new Random()).nextInt(3);
			else
				count = (new Random()).nextInt(9);
		}
		count--;
		if (particlesOn) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityKoffingParticle(worldObj, posX + (double) var6, posY + hoverTimer + 1.5F, posZ + (double) var7, 0D, 0D, 0D));
	}

	public void init() {
		name = "Koffing";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight = 1f;
		super.init();
	}

	public void evolve() {
		BaseEntityPixelmon entity = new EntityWeezing(worldObj);
		helper.evolve(entity.helper);
	}
}
