package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityVibrava extends EntityPixelmon
{
	
	public EntityVibrava(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Vibrava");
	}
}