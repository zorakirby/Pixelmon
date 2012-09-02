package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityGrowlithe extends EntityPixelmon
{
	
	public EntityGrowlithe(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Growlithe");
	}

}
