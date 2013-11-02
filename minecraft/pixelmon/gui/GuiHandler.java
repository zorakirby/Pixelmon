package pixelmon.gui;

import pixelmon.enums.EnumGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler {
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGui.ItemDrops.getIndex())
			return player.inventoryContainer;
		return new ContainerEmpty();

	}
}
