package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntitySeaking extends EntityWaterPixelmon
{
	
	public EntitySeaking(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Seaking");
		depthRangeStart=6;
		depthRangeEnd=20;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}
}