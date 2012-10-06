package pixelmon.gui.inventoryExtended;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.pc.GuiScreenPokeCheckerPC;

public class GuiScreenPokeCheckerInv extends GuiScreenPokeCheckerPC{

	public GuiScreenPokeCheckerInv(PixelmonDataPacket packet, GuiScreen parent) {
		super(packet, parent, 0, 0);
	}
	
	public void initGui() {
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8), "Back to Inventory"));
	}
	
	public void actionPerformed(GuiButton b){
		if(b.id == 0){
			mc.displayGuiScreen(parent);
		}
	}

}
