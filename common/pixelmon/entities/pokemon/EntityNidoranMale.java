package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityNidoranMale extends EntityPixelmon
{
	public EntityNidoranMale(World par1World) 
	{
		super(par1World);
		init();
	}
	
	public void init() 
	{
		super.init("NidoranMale");
	}	
}