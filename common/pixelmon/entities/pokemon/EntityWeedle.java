package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityWeedle extends EntityPixelmon
{
	
	public EntityWeedle(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Weedle");
	}
}
