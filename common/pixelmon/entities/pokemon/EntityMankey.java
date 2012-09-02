package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityMankey  extends EntityPixelmon
{
	
	public EntityMankey(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Mankey");
	}
}
