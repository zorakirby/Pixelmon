package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityFlyingPixelmon;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class EntityPidgeot extends EntityFlyingPixelmon {

	public EntityPidgeot(World world) {
		super(world);
		init();
	}

	protected int getDropItemId() {
		return Item.feather.shiftedIndex;
	}

	public void init() {
		super.init("Pidgeot");
	}
}
