package pixelmon.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.pc.GuiPC;
import net.minecraft.src.GuiButton;

import net.minecraft.src.StatCollector;

public class GuiScreenPokeCheckerPC extends GuiScreenPokeChecker {

	private GuiPC parent;
	private int index, box;

	public GuiScreenPokeCheckerPC(PixelmonDataPacket packet, GuiPC parent, int box, int index) {
		super(packet);
		this.parent = parent;
		this.index = index;
		this.box = box;
	}

	public void initGui() {
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8), "Back to PC"));
	}

	public void onGuiClosed() {
		super.onGuiClosed();
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:

			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.PCClick, -5));
			mc.displayGuiScreen(parent);
			break;
		}

	}

}
