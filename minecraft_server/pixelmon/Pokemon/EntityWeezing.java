package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityWeezing  extends EntityGroundPixelmon
{
	
	public EntityWeezing(World world)
	{
		super(world);
		init();
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
