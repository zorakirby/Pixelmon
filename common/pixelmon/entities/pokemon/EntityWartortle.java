package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.World;

public class EntityWartortle extends EntityPixelmon {

	public EntityWartortle(World par1World) {
		super(par1World);
		init();
	}

	public void init() {
		super.init("Wartortle");
	}
}
