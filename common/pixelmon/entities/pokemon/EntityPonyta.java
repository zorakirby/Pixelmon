package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityPonyta extends EntityPixelmon
{

	public EntityPonyta(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		super.init("Ponyta");
	}
}