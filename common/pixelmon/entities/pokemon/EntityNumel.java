package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityNumel extends EntityGroundPixelmon
{
	
	public EntityNumel(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Numel";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{	
	}
}