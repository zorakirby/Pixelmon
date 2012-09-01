package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityJigglypuff extends EntityGroundPixelmon
{
	
	public EntityJigglypuff(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Jigglypuff";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		EntityPixelmon entity = new EntityWigglytuff(worldObj);
		helper.evolve(entity.helper);
	}
}
