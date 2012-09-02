package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityMetapod extends EntityGroundPixelmon
{
	
	public EntityMetapod(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Metapod";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{
		EntityPixelmon entity = new EntityButterfree(worldObj);
		helper.evolve(entity.helper);
	}

}
