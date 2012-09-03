package pixelmon.entities.pixelmon.helpers;

import net.minecraft.src.Item;

public class DropItemHelper {

	public int getDropItemID() {
		return Item.bone.shiftedIndex;
	}

	public boolean dropsItem() {
		return false;
	}

}
