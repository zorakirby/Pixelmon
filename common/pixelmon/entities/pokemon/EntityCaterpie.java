package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityCaterpie extends EntityGroundPixelmon
{
	
	public EntityCaterpie(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Caterpie";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		EntityPixelmon entity = new EntityMetapod(worldObj);
		helper.evolve(entity.helper);
	}
}
