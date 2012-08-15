package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;


public class EntityOddish extends EntityGroundPixelmon
{

	public EntityOddish(World par1World) 
	{
		super(par1World);
		init();
	}
	
	public void init() 
	{
		name = "Oddish";
		isImmuneToFire = false;
		super.init();
	}

	public void evolve() 
	{
		IHaveHelper entity = new EntityGloom(worldObj);
		helper.evolve(entity.getHelper());
	}
	
}