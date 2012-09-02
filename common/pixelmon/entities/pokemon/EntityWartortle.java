package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;


public class EntityWartortle extends EntityGroundPixelmon
{

	public EntityWartortle(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		name = "Wartortle";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
		EntityPixelmon entity = new EntityBlastoise(worldObj);
		helper.evolve(entity.helper);
	}
}
