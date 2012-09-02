package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntitySnorlax extends EntityPixelmon
{
	
	public EntitySnorlax(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Snorlax");
	}
}
