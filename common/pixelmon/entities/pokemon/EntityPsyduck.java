package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityPsyduck  extends EntityPixelmon
{
	
	public EntityPsyduck(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Psyduck");
	}
}
