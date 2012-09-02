package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityNumel extends EntityPixelmon
{
	
	public EntityNumel(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Numel");
	}
}