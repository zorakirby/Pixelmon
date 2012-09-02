package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityOmanyte extends EntityWaterPixelmon
{
	public EntityOmanyte(World world)
	{
		super(world);
		depthRangeStart = 5;
		depthRangeEnd = 10;
		init();
	}

	public void init() 
	{
		super.init("Omanyte");
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}
}