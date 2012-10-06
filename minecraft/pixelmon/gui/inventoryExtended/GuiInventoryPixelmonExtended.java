package pixelmon.gui.inventoryExtended;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.GuiPixelmonOverlay;

public class GuiInventoryPixelmonExtended extends GuiInventory {

	public SlotInventoryPixelmon[] pixelmonSlots;

	GuiPixelmonOverlay overlay = new GuiPixelmonOverlay();
	boolean pixelmonMenuOpen;
	GuiButton pMenuButton;
	PixelmonDataPacket selected;

	public GuiInventoryPixelmonExtended(EntityPlayer par1EntityPlayer) {
		super(par1EntityPlayer);
		pixelmonMenuOpen = false;
		pixelmonSlots = new SlotInventoryPixelmon[6];
	}

	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		for (int i = 0; i < pixelmonSlots.length; i++) {
			pixelmonSlots[i] = null;
		}
		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				int i = p.order;
				int x = 3;
				int y = var7 / 6 + i * 30 + 3 + offset;
				pixelmonSlots[i] = new SlotInventoryPixelmon(x, y, p);
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int var4 = this.mc.renderEngine.getTexture("/gui/inventory.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/pixelmon/gui/pixelmonOverlayExtended2.png"));
		this.drawTexturedModalRect(width/2 -130, height/2-83, 0, 0, 160, 182);
        func_74223_a(this.mc, guiLeft + 51, guiTop + 75, 30, (float)(guiLeft + 51) - 102, (float)(guiTop + 75 - 50) - 100);
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		int textureIndex;
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator var2 = Tessellator.instance;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
		RenderHelper.enableGUIStandardItemLighting();

		fontRenderer.setUnicodeFlag(true);
		int i = 0;

		for (SlotInventoryPixelmon slot : pixelmonSlots) {
			if (slot == null) {
				continue;
			}
			PixelmonDataPacket p = slot.pokemonData;
			int offset = 0;
			if (p != null) {
				String displayName = p.name;
				if (!p.nickname.equals(""))
					displayName = p.nickname;

				i = p.order;
				fontRenderer.drawString(displayName, 32, var7 / 6 + i * 30 + 6, 0xFFFFFF);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/pixelmon/gui/pixelmonOverlayExtended2.png"));
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
		super.actionPerformed(par1GuiButton);
		if (par1GuiButton.id == 3) {
			GuiScreenPokeCheckerInv poke = new GuiScreenPokeCheckerInv(selected, this);
			mc.displayGuiScreen(poke);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int par3) {
		super.mouseClicked(x, y, par3);
		if (par3 == 0) {
			if (pixelmonMenuOpen) {
				controlList.remove(pMenuButton);
				pMenuButton = null;
				pixelmonMenuOpen = false;
				selected = null;
			}
		}
		for (SlotInventoryPixelmon s : pixelmonSlots) {
			if (s != null) {
				if (s.getBounds().contains(x, y)) {
					if (par3 == 1) {
						if (pixelmonMenuOpen) {
							controlList.remove(pMenuButton);
							pMenuButton = null;
							pixelmonMenuOpen = false;
							selected = null;
						}
						pMenuButton = new GuiButton(3, x, y, 50, 20, "Summary");
						controlList.add(pMenuButton);
						pixelmonMenuOpen = true;
						selected = s.pokemonData;
					} else if (par3 == 0) {
					}
				}
			}
		}
	}
}
