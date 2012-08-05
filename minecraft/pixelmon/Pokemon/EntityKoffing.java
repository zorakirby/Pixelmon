package pixelmon.Pokemon;

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

	public void init()
	{
		name = "Koffing";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight=1f;
		super.init();
		helper.giScale = 0.33F;
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityWeezing(worldObj);
		helper.evolve(entity.helper);
	}
}
