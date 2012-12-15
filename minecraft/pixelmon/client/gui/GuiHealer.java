package pixelmon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.ContainerEmpty;

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

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	@Override
	public void handleKeyboardInput() {
		return;
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {

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
