package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityLapras extends EntityWaterPixelmon
{
	
	public EntityLapras(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Lapras");
		depthRangeStart = 0;
		depthRangeEnd = 1;
	}
	
}
