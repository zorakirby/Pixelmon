package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityKakuna extends EntityPixelmon
{
	
	public EntityKakuna(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Kakuna");
		hoverHeight=1f;
	}
}
