package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;


public class EntityEevee extends EntityPixelmon
{

	public EntityEevee(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		super.init("Eevee");
	}	
}