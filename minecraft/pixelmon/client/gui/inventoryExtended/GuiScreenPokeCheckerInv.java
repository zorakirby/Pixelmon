package pixelmon.client.gui.inventoryExtended;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import pixelmon.client.gui.pc.GuiScreenPokeCheckerPC;
import pixelmon.comm.PixelmonDataPacket;

public class GuiScreenPokeCheckerInv extends GuiScreenPokeCheckerPC{

	public GuiScreenPokeCheckerInv(PixelmonDataPacket packet, GuiScreen parent) {
		super(packet, parent, 0, 0);
	}
	
	public void initGui() {
		super.initGui();
	}
	
	public void actionPerformed(GuiButton b){
		if(b.id == 0){
			mc.displayGuiScreen(parent);
		}
	}

}
