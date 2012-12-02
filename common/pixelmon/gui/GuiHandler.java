package pixelmon.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.gui.ContainerEmpty;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.EntityPlayer;

import net.minecraft.src.World;

public class GuiHandler {
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerEmpty();
	}
}
