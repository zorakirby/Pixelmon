package pixelmon.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.ContainerEmpty;

public class GuiTrading extends GuiContainer {

	private int tradeIndex = -1;

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
		drawTexturedModalRect((width - 256) / 2, (height - 206) / 2, 0, 0, 256, 206);

		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				String displayName = p.name;
				if (!p.nickname.equals(""))
					displayName = p.nickname;

				int i = p.order;
				String numString = "";
				if (p.getNationalPokedexNumber() < 10)
					numString = "00" + p.getNationalPokedexNumber();
				else if (p.getNationalPokedexNumber() < 100)
					numString = "0" + p.getNationalPokedexNumber();
				else
					numString = "" + p.getNationalPokedexNumber();
				int var9;
				if (p.isShiny)
					var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(var9, width / 2 - 85 + 24 * i, height / 2 + 68, 24f, 24f, 0f, 0f, 1f, 1f);
				if (p.heldItemId != -1) {
					var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/image/helditem.png");
					drawImageQuad(var9, width / 2 - 85 + 22 * i+18, height / 2 + 68+18, 6, 6, 0f, 0f, 1f, 1f);
				}

			}
		}
		
		fontRenderer.drawSplitString("Select a Pokemon", width/2-116, height/2+70, 50, 0xFFFFFF);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) this.zLevel, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) this.zLevel, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) this.zLevel, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}
}
