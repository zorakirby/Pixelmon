package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityPrimeape  extends EntityPixelmon
{
	
	public EntityPrimeape(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Primeape");
	}
}
