package pixelmon.Pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;

public class EntityMankey  extends EntityGroundPixelmon
{
	
	public EntityMankey(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Mankey";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		BaseEntityPixelmon entity = new EntityPrimeape(worldObj);
		helper.evolve(entity.getHelper());
	}
}
