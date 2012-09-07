package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityRaichu extends EntityPixelmon
{
	
	public EntityRaichu(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Raichu");
	}
}
