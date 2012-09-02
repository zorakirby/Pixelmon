package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityDrowzee extends EntityPixelmon
{
	
	public EntityDrowzee(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Drowzee");
	}

	
}
