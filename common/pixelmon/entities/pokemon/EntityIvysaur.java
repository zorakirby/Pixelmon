package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityIvysaur extends EntityGroundPixelmon
{
	
	public EntityIvysaur(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Ivysaur";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
		EntityPixelmon entity = new EntityVenusaur(worldObj);
		helper.evolve(entity.helper);
	}
}
