package pixelmon.gui.inventoryExtended;

import org.lwjgl.opengl.GL11;

import pixelmon.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.GuiPixelmonOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;

public class GuiInventoryPixelmonExtended extends GuiInventory {

	GuiPixelmonOverlay overlay = new GuiPixelmonOverlay();

	public GuiInventoryPixelmonExtended(EntityPlayer par1EntityPlayer) {
		super(par1EntityPlayer);
	}

	@Override
	public void initGui() {
		super.initGui();

		controlList.add(new GuiButton(2, this.width * 4 / 5, this.height / 2, 50, 20, "Pixelmon"));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		super.drawGuiContainerBackgroundLayer(par1, par2, par3);
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int textureIndex;
		RenderHelper.enableGUIStandardItemLighting();
		textureIndex = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlayExtended.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(textureIndex);
		Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
		this.drawTexturedModalRect(0, var7 / 6, 0, 0, 160, 182);

		fontRenderer.setUnicodeFlag(true);
		int i = 0;

		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				String displayName = p.name;
				if (!p.nickname.equals(""))
					displayName = p.nickname;

				i = p.order;
				fontRenderer.drawString(displayName, 32, var7 / 6 + i * 30 + 6, 0xFFFFFF);
				Minecraft.getMinecraft().renderEngine.bindTexture(textureIndex);
				if (p.isMale)
					this.drawTexturedModalRect(fontRenderer.getStringWidth(displayName) + 35, var7 / 6 + i * 30 + 6 + offset, 33, 208, 5, 9);
				else
					this.drawTexturedModalRect(fontRenderer.getStringWidth(displayName) + 35, var7 / 6 + i * 30 + 6 + offset, 33, 218, 5, 9);

				String numString = "";
				if (p.nationalPokedexNumber < 10)
					numString = "00" + p.nationalPokedexNumber;
				else if (p.nationalPokedexNumber < 100)
					numString = "0" + p.nationalPokedexNumber;
				else
					numString = "" + p.nationalPokedexNumber;
				int spriteIndex;
				if (p.isShiny)
					spriteIndex = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					spriteIndex = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(spriteIndex, 3, var7 / 6 + i * 30 + 3 + offset, 24f, 24f, 0f, 0f, 1f, 1f);
				if (p.heldItem != null) {
					spriteIndex = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/image/pitems.png");
					drawImageQuad(spriteIndex, 17, var7 / 6 + i * 30 + 17 + offset, 8, 8, 0f, 0f, 16f / 256f, 16f / 256f);
				}

				fontRenderer.drawString("Lvl " + p.lvl, 32, var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
				if (p.health <= 0) {
					fontRenderer.drawString("Fainted", 33 + fontRenderer.getStringWidth("Lvl " + p.lvl), var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7
							+ offset, 0xFFFFFF);
				} else {
					fontRenderer.drawString("HP " + p.health + "/" + p.hp, 33 + fontRenderer.getStringWidth("Lvl " + p.lvl), var7 / 6 + i * 30
							+ fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
				}

			}
			i++;
		}

		fontRenderer.setUnicodeFlag(false);

		RenderHelper.disableStandardItemLighting();

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
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

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		System.out.println("new gui action performed");
		super.actionPerformed(par1GuiButton);
	}
}
