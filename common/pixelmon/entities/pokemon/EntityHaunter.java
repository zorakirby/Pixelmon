package pixelmon.entities.pokemon;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;


public class EntityHaunter extends EntityPixelmon
{

	public EntityHaunter(World par1World) 
	{
		super(par1World);
		init();
	}
	
	public void init() 
	{
		super.init("Haunter");
	}	
}