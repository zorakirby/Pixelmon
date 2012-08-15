package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityGastly extends EntityGroundPixelmon
{

	public EntityGastly(World par1World) 
	{
		super(par1World);
		init();
	}

	protected String getGastlyParticle()
    {
        return "cloud";
    }

    protected EntityGastly createInstance()
    {
        return new EntityGastly(this.worldObj);
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	float var2 = .6F;
    	float var4 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
    	float var5 = this.rand.nextFloat() * 1F + .5F;
    	float var6 = MathHelper.sin(var4) * var2 * .5F * var5;
    	float var7 = MathHelper.cos(var4) * var2 * .5F * var5;
    	
    for(int i=0; i<1; i++)
    	this.worldObj.spawnParticle(this.getGastlyParticle(),this.posX +(double)var6, this.posY + 1.5, this.posZ + (double)var7, 0.0D,0.0D,0.0D);
    }
	public void init() 
	{
		name = "Gastly";
		yOffset = 0;
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight = 1f;
		super.init();
	}

	public void evolve() 
	{
	}
	
}