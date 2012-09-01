package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityMagnemite  extends EntityGroundPixelmon
{
	
	public EntityMagnemite(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Magnemite";
		isImmuneToFire = false;
		helper.doesHover = true;
		helper.hoverHeight=1f;
		super.init();
	}
	
	public void evolve() 
	{		
		EntityPixelmon entity = new EntityMagneton(worldObj);
		helper.evolve(entity.helper);
	}
}
