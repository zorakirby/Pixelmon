package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;

public class EntityDratini extends EntityWaterPixelmon
{
	
	public EntityDratini(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Dratini");
	}

}