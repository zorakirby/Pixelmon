package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityBlastoise extends EntityPixelmon
{
	
	public EntityBlastoise(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Blastoise");
	}
	
	public void evolve() 
	{
	}

}