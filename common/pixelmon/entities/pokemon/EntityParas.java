package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityParas extends EntityGroundPixelmon
{
	
	public EntityParas(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Paras";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{

	}
	
}
