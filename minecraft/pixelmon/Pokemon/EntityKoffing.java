package pixelmon.Pokemon;

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
	
	protected String getKoffingParticle()
    {
        return "smoke";
    }

    protected EntityKoffing createInstance()
    {
        return new EntityKoffing(this.worldObj);
    }


	public void init()
	{
		name = "Koffing";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight=1f;
		super.init();
		helper.giScale = 0.33F;
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		worldObj.spawnParticle("smoke", posX, posY +  1.5F, posZ, 0.0D, 0.0D, 0.0D);
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityWeezing(worldObj);
		helper.evolve(entity.helper);
	}
}
