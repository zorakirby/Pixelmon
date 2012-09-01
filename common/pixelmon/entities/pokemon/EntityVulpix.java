package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityVulpix  extends EntityGroundPixelmon
{
	
	public EntityVulpix(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Vulpix";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{		
		EntityPixelmon entity = new EntityNinetales(worldObj);
		helper.evolve(entity.helper);
	}
}
