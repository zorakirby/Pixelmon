package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityNinetales  extends EntityPixelmon
{
	public EntityNinetales(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Ninetales");
	}
}
