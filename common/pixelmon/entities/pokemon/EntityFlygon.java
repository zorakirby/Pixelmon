package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityFlygon extends EntityPixelmon
{
	
	public EntityFlygon(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Flygon");
	}
}