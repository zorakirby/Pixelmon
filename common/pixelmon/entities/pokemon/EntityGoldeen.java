package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityGoldeen extends EntityWaterPixelmon
{
	
	public EntityGoldeen(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Goldeen");
		depthRangeStart=2;
		depthRangeEnd=10;
		isImmuneToFire = false;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}
}