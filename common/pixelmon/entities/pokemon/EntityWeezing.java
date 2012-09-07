package pixelmon.entities.pokemon;

import java.util.Random;

import pixelmon.ClientProxy;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPixelmonParticles;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityWeezing  extends EntityPixelmon
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
			float var2 = baseStats.Width * baseStats.giScale * getScale();
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
		super.init("Weezing");
		doesHover = true;
		hoverHeight=1f;
	}
}
