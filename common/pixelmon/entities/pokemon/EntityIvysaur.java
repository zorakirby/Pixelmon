package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityIvysaur extends EntityPixelmon
{
	
	public EntityIvysaur(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Ivysaur");
	}
}
