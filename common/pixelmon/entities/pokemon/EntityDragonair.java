package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityDragonair extends EntityWaterPixelmon
{
	
	public EntityDragonair(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Dragonair");
		depthRangeStart = 3;
		depthRangeEnd = 8;
	}
	
}
