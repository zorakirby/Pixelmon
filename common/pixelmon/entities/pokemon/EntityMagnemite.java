package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityMagnemite  extends EntityPixelmon
{
	
	public EntityMagnemite(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Magnemite");
		doesHover = true;
		hoverHeight=1f;
	}
}
