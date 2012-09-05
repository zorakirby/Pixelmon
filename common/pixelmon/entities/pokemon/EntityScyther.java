package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityScyther extends EntityPixelmon
{
	
	public EntityScyther(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Scyther");
	}
}