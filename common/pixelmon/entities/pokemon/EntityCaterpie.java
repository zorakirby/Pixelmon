package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityCaterpie extends EntityPixelmon
{
	
	public EntityCaterpie(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Caterpie");
	}
}
