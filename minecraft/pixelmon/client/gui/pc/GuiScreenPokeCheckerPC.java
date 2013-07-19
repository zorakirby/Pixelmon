package pixelmon.client.gui.pc;

import net.minecraft.client.gui.GuiButton;
import pixelmon.client.gui.pokechecker.GuiRenamePokemon;
import pixelmon.client.gui.pokechecker.GuiScreenPokeChecker;
import pixelmon.client.gui.pokechecker.GuiScreenPokeCheckerMoves;
import pixelmon.client.gui.pokechecker.GuiScreenPokeCheckerStats;
import pixelmon.client.gui.pokechecker.GuiScreenPokeCheckerWarning;
import pixelmon.comm.PixelmonDataPacket;

public class GuiScreenPokeCheckerPC extends GuiScreenPokeChecker {

	private int index, box;

	public GuiScreenPokeCheckerPC(PixelmonDataPacket packet, int box, int index) {
		super(packet, true);
		this.index = index;
		this.box = box;
	}

	public void initGui() {
		super.initGui();
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			GuiPC gui = new GuiPC(targetPacket);
			mc.displayGuiScreen(gui);
			break;
		case 1:
			mc.displayGuiScreen(new GuiScreenPokeCheckerMoves(targetPacket, true));
			break;
		case 2:
			mc.displayGuiScreen(new GuiScreenPokeCheckerStats(targetPacket, true));
			break;
		case 3:
			mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			break;
		case 4:
			mc.displayGuiScreen(new GuiScreenPokeCheckerWarning(targetPacket, 0));
			break;
		case 5:
			mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			break;
		}
	}

	public void keyTyped(char c, int i) {
		if (i == 1) {
			GuiPC gui = new GuiPC(targetPacket);
			mc.displayGuiScreen(gui);
		}
	}

}
