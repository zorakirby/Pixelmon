package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityPikachu extends EntityPixelmon
{
	
	public EntityPikachu(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Pikachu");
	}
}
