package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityBulbasaur extends EntityPixelmon
{
	
	public EntityBulbasaur(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Bulbasaur");
	}
}