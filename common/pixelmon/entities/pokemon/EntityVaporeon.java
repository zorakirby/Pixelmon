package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityVaporeon  extends EntityPixelmon
{
	
	public EntityVaporeon(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Vaporeon");
	}
}
