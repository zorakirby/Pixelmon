package pixelmon.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.ScaledResolution;

public class GuiTrading extends GuiContainer {

	private int tradeIndex=-1;
	
	public GuiTrading(int tradeIndex) {
		super(new ContainerEmpty());
		this.tradeIndex = tradeIndex;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();

		int bg = mc.renderEngine.getTexture("/pixelmon/gui/tradeGui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		drawTexturedModalRect((width - xSize) / 2 - 40, (height - ySize) / 2 - 25, 0, 0, 256, 204);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
	}

}
