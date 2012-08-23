package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityPrimeape  extends EntityGroundPixelmon
{
	
	public EntityPrimeape(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Primeape";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{}
}
