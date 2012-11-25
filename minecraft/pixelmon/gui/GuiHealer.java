package pixelmon.gui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.StatCollector;
import pixelmon.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiHealer extends GuiContainer {

	public GuiHealer() {
		super(new ContainerEmpty());
	}

	public GuiButton cancelButton;

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
	}

	@Override
	public void updateScreen() {
	}

	public void actionPerformed(GuiButton b) {

	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {

		drawCenteredString(fontRenderer, "Heal your pokemon?", width / 2, 10, 0xFFFFFF);

		// int j = 0;
		// for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
		// int offset = 0;
		// if (p != null) {
		// drawPokemonStats(p, width / 2 - 100 -
		// fontRenderer.getStringWidth(p.nickname.equals("") ? p.name :
		// p.nickname) - 1, height * (j + 2) / 10);
		// progressBars[j].draw(width / 2, height * (j + 2) / 10, 20, 200,
		// width, height);
		// if (progressBars[j].value != 100)
		// drawString(fontRenderer, progressBars[j].value + " %", width / 2 +
		// 110, height * (j + 2) / 10 + 6, 0xDDDDDD);
		// else
		// drawString(fontRenderer, "Healed", width / 2 + 110, height * (j + 2)
		// / 10 + 6, 0xDDDDDD);
		// j++;
		// }
		// }

	}

	public void drawPokemonStats(PixelmonDataPacket pixelmon, int x, int y) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.nickname.equals("") ? pixelmon.name : pixelmon.nickname, x, y, 0xDDDDDD);
		drawCenteredString(fontRenderer, pixelmon.health + "/" + pixelmon.hp,
				x + fontRenderer.getStringWidth(pixelmon.nickname.equals("") ? pixelmon.name : pixelmon.nickname) / 2, y + 15, 0xDDDDDD);
	}
}
