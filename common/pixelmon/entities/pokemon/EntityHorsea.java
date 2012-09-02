package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityHorsea extends EntityWaterPixelmon
{
	
	public EntityHorsea(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Horsea");
		depthRangeStart = 2;
		depthRangeEnd = 20;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}
}