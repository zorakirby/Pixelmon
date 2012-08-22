package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityKakuna extends EntityGroundPixelmon
{
	
	public EntityKakuna(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Kakuna";
		isImmuneToFire = false;
		helper.hoverHeight=1f;
		super.init();
		helper.giScale = 0.3F;
	}
	
	public void evolve() 
	{		
		
	}
}
