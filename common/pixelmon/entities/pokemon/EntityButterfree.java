package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityButterfree extends EntityPixelmon
{
	
	public EntityButterfree(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Butterfree");
		doesHover = true;
		hoverHeight = 1f;
	}
	
	public void evolve() 
	{
		
	}

}
