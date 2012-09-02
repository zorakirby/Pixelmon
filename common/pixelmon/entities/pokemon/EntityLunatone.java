package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityLunatone extends EntityPixelmon
{
	
	public EntityLunatone(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Lunatone");
		doesHover = true;
		hoverHeight=1f;
	}
	
	public boolean getCanSpawnHere()
	{
		if(!worldObj.isDaytime() ) {
			return true;
		}
		
		return false;
	}
	
}
	
	