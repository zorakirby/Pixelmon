package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityBellsprout extends EntityPixelmon
{
	
	public EntityBellsprout(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Bellsprout");
	}
}
