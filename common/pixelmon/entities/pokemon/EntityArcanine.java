package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.World;

public class EntityArcanine extends EntityGroundPixelmon
{
	
	public EntityArcanine(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Arcanine";
		isImmuneToFire = true;
		super.init();
	}
	
	public void evolve() 
	{

	}

}
