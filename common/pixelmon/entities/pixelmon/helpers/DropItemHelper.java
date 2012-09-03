package pixelmon.entities.pixelmon.helpers;

import pixelmon.entities.pixelmon.Entity8HoldsItems;
import net.minecraft.src.Item;

public class DropItemHelper {

	Entity8HoldsItems pixelmon;
	
	public DropItemHelper(Entity8HoldsItems entity8HoldsItems) {
		pixelmon = entity8HoldsItems;
	}

	public int getDropItemID() {
		return Item.bone.shiftedIndex;
	}

	public boolean dropsItem() {
		return false;
	}

}
