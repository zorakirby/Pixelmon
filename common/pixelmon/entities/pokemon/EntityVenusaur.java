package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityVenusaur extends EntityPixelmon
{
	
	public EntityVenusaur(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Venusaur");
	}
}