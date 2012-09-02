package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class EntityVoltorb extends EntityPixelmon {

	public EntityVoltorb(World world) {
		super(world);
		init();
	}

	public void init() {
		super.init("Voltorb");
	}

	protected int getDropItemId() {
		return Item.gunpowder.shiftedIndex;
	}
}
