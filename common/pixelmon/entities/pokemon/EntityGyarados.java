package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityGyarados extends EntityWaterPixelmon
{
	
	public EntityGyarados(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Gyarados");
		depthRangeStart = 10;
		depthRangeEnd = 100;
		isImmuneToFire = false;
		this.swimSpeed = 3;
	}

	public float getMoveSpeed() 
	{
		return 1.5F;
	}
}