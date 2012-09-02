package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityWeedle extends EntityGroundPixelmon
{
	
	public EntityWeedle(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Weedle";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		EntityPixelmon entity = new EntityKakuna(worldObj);
		helper.evolve(entity.getHelper());
	}
}
