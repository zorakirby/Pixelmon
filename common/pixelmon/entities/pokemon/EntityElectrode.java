package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityElectrode extends EntityPixelmon {

	public EntityElectrode(World world) {
		super(world);
		init();
	}

	public void init() {
		super.init("Electrode");
	}

	protected int getDropItemId() {
		return Item.gunpowder.shiftedIndex;
	}
}
