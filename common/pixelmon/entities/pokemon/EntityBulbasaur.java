package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityBulbasaur extends EntityGroundPixelmon
{
	
	public EntityBulbasaur(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Bulbasaur";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		EntityPixelmon entity = new EntityIvysaur(worldObj);
		helper.evolve(entity.helper);
	}
}