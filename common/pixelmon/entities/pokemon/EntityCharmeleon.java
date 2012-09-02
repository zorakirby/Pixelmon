package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityCharmeleon extends EntityPixelmon
{
	
	public EntityCharmeleon(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Charmeleon");
		isImmuneToFire = true;
	}
}
