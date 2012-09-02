package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityParas extends EntityPixelmon
{
	
	public EntityParas(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Paras");
	}	
}
