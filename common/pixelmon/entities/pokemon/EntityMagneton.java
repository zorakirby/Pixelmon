package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityMagneton extends EntityPixelmon
{
	
	public EntityMagneton(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Magneton");
	}
}
