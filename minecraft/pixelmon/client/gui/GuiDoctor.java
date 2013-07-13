package pixelmon.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import pixelmon.gui.ContainerEmpty;

public class GuiDoctor extends GuiContainer {
	static ResourceLocation battleGui3 = new ResourceLocation("/pixelmon/gui/battleGui3.png");
	enum EnumGuiDoctorMode {
		Before, Healing, After;
	}

	private EnumGuiDoctorMode mode = EnumGuiDoctorMode.Before;
	private int guiWidth = 300;
	private int guiHeight = 60;

	public GuiDoctor() {
		super(new ContainerEmpty());
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		drawMessageScreen();
	}

	int flashCount = 0;

	private void drawMessageScreen() {
		mc.renderEngine.func_110577_a(battleGui3);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f, zLevel);
		if (mode == EnumGuiDoctorMode.Before) {
			drawCenteredString(fontRenderer, "I'm a Pokemon Doctor.  Shall I heal your pokemon?", width / 2, height - 35, 0xFFFFFF);
			flashCount++;
			if (flashCount > 30) {
				mc.renderEngine.func_110577_a(battleGui3);
				GuiHelper.drawImageQuad(width / 2 + 130, height - 15, 10, 6, 611f / 640f, 149f / 480f, 628f / 640f, 159f / 480f, zLevel);
				if (flashCount > 60)
					flashCount = 0;
			}
		} else {
			flashCount++;
			if (flashCount >= 160)
				flashCount = 0;
			if (flashCount < 40)
				drawCenteredString(fontRenderer, "Waiting", width / 2, height - 35, 0xFFFFFF);
			else if (flashCount < 80)
				drawCenteredString(fontRenderer, "Waiting.", width / 2, height - 35, 0xFFFFFF);
			else if (flashCount < 120)
				drawCenteredString(fontRenderer, "Waiting..", width / 2, height - 35, 0xFFFFFF);
			else if (flashCount < 160)
				drawCenteredString(fontRenderer, "Waiting...", width / 2, height - 35, 0xFFFFFF);

		}
	}
}
