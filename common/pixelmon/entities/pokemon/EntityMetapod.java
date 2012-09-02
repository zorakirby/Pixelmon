package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityMetapod extends EntityPixelmon
{
	
	public EntityMetapod(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Metapod");
	}
}
