package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntitySquirtle extends EntityPixelmon
{
	public EntitySquirtle(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Squirtle");
	}
}