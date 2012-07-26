package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityGastly extends EntityGroundPixelmon
{

	public EntityGastly(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		name = "Gastly";
		yOffset = 0;
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight = 1f;
		super.init();
	}

	public void evolve() 
	{
	}
	
}