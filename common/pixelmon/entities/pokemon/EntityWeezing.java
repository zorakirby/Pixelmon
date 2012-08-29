package pixelmon.entities.pokemon;

import java.util.Random;

import pixelmon.ClientProxy;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.enums.EnumPixelmonParticles;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityWeezing  extends EntityGroundPixelmon
{
	
	public EntityWeezing(World world)
	{
		super(world);
		init();
	}
	public float hoverTimer;

	int count = 0;
	boolean particlesOn = false;

	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			float var2 = helper.stats.BaseStats.Width * helper.giScale * helper.scale;
			float var4 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
			float var5 = this.rand.nextFloat() * 4F + .5F;
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
				ClientProxy.spawnParticle(EnumPixelmonParticles.koffing, worldObj, posX + (double) var6, posY + hoverTimer + 2.5F, posZ + (double) var7);
		}
	}

	public void init()
	{
		name = "Weezing";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight=1f;
		super.init();
	}
	
	public void evolve() 
	{		

	}
}
