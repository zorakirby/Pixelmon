package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityJigglypuff extends EntityPixelmon
{
	
	public EntityJigglypuff(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Jigglypuff");
	}
}
