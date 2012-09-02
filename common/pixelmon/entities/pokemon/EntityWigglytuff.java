package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityWigglytuff extends EntityPixelmon
{
	
	public EntityWigglytuff(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Wigglytuff");
	}
}
