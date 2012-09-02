package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityRattata extends EntityPixelmon
{
	public EntityRattata(World par1World) 
	{
		super(par1World);
		init();
	}

	public void init() 
	{
		super.init("Rattata");
	}
}