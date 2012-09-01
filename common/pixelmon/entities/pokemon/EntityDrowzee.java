package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityDrowzee extends EntityGroundPixelmon
{
	
	public EntityDrowzee(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Drowzee";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
	}
}
