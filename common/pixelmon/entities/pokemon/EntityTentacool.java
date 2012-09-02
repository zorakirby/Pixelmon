package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityTentacool extends EntityWaterPixelmon
{
	public EntityTentacool(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Tentacool");
		depthRangeStart=3;
		depthRangeEnd=7;
	}

	public float getMoveSpeed() 
	{
		return 0.5F;
	}
}