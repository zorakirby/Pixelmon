package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityButterfree extends EntityGroundPixelmon
{
	
	public EntityButterfree(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Butterfree";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight = 1f;
		super.init();
	}
	
	public void evolve() 
	{
		
	}

}
