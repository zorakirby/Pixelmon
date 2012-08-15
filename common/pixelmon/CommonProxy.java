package pixelmon;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;
import pixelmon.config.PixelmonBlocks;
import pixelmon.gui.GuiHandler;

public class CommonProxy implements IGuiHandler {

	public void registerRenderers() {}
	
	public World GetClientWorld(){
		return null;
	}
	
	
	
	public GuiHandler guiHandler = new GuiHandler();
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return guiHandler.getServerGuiElement(ID, player, world, x, y, z);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	
	
}
