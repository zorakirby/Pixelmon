package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityMagikarp extends EntityWaterPixelmon
{
	
	public EntityMagikarp(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Magikarp");
		depthRangeStart =0;
		depthRangeEnd=5;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}
}