package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityJolteon extends EntityPixelmon
{
	
	public EntityJolteon(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Jolteon");
	}
}
