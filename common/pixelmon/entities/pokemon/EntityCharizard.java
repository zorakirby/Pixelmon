package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityCharizard extends EntityPixelmon
{
	
	public EntityCharizard(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Charizard");
	}
}
