package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityBeedrill extends EntityPixelmon
{
	
	public EntityBeedrill(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Beedrill");
	}
}
