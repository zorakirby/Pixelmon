package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

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
