package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityVulpix  extends EntityPixelmon
{
	
	public EntityVulpix(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Vulpix");
	}
}
