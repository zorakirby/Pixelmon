package pixelmon.entities.pokemon;

import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityKoffing  extends EntityGroundPixelmon
{
	
	public EntityKoffing(World world)
	{
		super(world);
		init();
	}
	
	public float hoverTimer;
	
	protected String getKoffingParticle()
    {
        return "smoke";
    }

    protected EntityKoffing createInstance()
    {
        return new EntityKoffing(this.worldObj);
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	float var2 = helper.stats.BaseStats.Width * helper.giScale * helper.scale;
    	float var4 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
    	float var5 = this.rand.nextFloat() * 4F + .5F;
    	float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
    	float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
    	
    
    	this.worldObj.spawnParticle(this.getKoffingParticle(),this.posX +(double)var6, this.posY + hoverTimer + 1.5, this.posZ + (double)var7, 0.0D,0.0D,0.0D);
    }


	public void init()
	{
		name = "Koffing";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight=1f;
		super.init();
	}
	
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityWeezing(worldObj);
		helper.evolve(entity.helper);
	}
}
