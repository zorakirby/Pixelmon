package pixelmon.gui;

import net.minecraft.src.Gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.eventbus.Subscribe;

import pixelmon.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.storage.PokeballManager;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.Tessellator;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiPixelmonOverlay extends Gui {
	public static boolean isGuiMinimized = false;
	public FontRenderer fontRenderer;
	public static int selectedPixelmon;

	public GuiPixelmonOverlay() {
		fontRenderer = ModLoader.getMinecraftInstance().fontRenderer;
	}

	@ForgeSubscribe
	public void onRenderWorldLast(RenderWorldLastEvent event){
		ScaledResolution var5 = new ScaledResolution(ModLoader.getMinecraftInstance().gameSettings, ModLoader.getMinecraftInstance().displayWidth, ModLoader.getMinecraftInstance().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		int var4;
		if (isGuiMinimized)
			var4 = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlaySimple.png");
		else
			var4 = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlay.png");
		ModLoader.getMinecraftInstance().renderEngine.bindTexture(var4);
		ModLoader.getMinecraftInstance().entityRenderer.setupOverlayRendering();
		if (isGuiMinimized)
			this.drawTexturedModalRect(0, var7 / 6, 0, 0, 60, 182);
		else
			this.drawTexturedModalRect(0, var7 / 6, 0, 0, 147, 182);
		RenderHelper.enableGUIStandardItemLighting();

		fontRenderer.setUnicodeFlag(true);
		int i = 0;

		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				i = p.order;
				if (!isGuiMinimized) {
					fontRenderer.drawString(p.nickname, 32, var7 / 6 + i * 30 + 6, 0xFFFFFF);
					ModLoader.getMinecraftInstance().renderEngine.bindTexture(var4);
					if (p.isMale)
						this.drawTexturedModalRect(fontRenderer.getStringWidth(p.nickname) + 35, var7 / 6 + i * 30 + 6 + offset, 33, 208, 5, 9);
					else
						this.drawTexturedModalRect(fontRenderer.getStringWidth(p.nickname) + 35, var7 / 6 + i * 30 + 6 + offset, 33, 218, 5, 9);

				}
				String numString = "";
				if (p.nationalPokedexNumber < 10)
					numString = "00" + p.nationalPokedexNumber;
				else if (p.nationalPokedexNumber < 100)
					numString = "0" + p.nationalPokedexNumber;
				else
					numString = "" + p.nationalPokedexNumber;
				int var9;
				if (p.isShiny)
					var9 = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					var9 = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(var9, 3, var7 / 6 + i * 30 + 3 + offset, 24f, 24f, 0f, 0f, 1f, 1f);
				if (!isGuiMinimized) {
					fontRenderer.drawString("Lvl " + p.lvl, 32, var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
					if (p.health <= 0) {
						fontRenderer.drawString("Fainted", 33 + fontRenderer.getStringWidth("Lvl " + p.lvl), var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
					} else {
						fontRenderer.drawString("HP " + p.health + "/" + p.hp, 33 + fontRenderer.getStringWidth("Lvl " + p.lvl), var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
					}
				}
			}
			i++;
		}

		ModLoader.getMinecraftInstance().renderEngine.bindTexture(var4);
		this.drawTexturedModalRect(2, var7 / 6 + 4 + selectedPixelmon * 30, 45, 209, 40, 40);
		fontRenderer.setUnicodeFlag(false);

		RenderHelper.disableStandardItemLighting();

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	}

	public static void selectNextPixelmon() {
		selectedPixelmon++;
		if (selectedPixelmon >= 6)
			selectedPixelmon = 0;
		while (ServerStorageDisplay.pokemon[selectedPixelmon] == null) {
			selectedPixelmon++;
			if (selectedPixelmon >= 6)
				selectedPixelmon = 0;
		}

	}

	public static void selectPreviousPixelmon() {
		selectedPixelmon--;
		if (selectedPixelmon < 0)
			selectedPixelmon = 5;
		while (ServerStorageDisplay.pokemon[selectedPixelmon] == null) {
			selectedPixelmon--;
			if (selectedPixelmon < 0)
				selectedPixelmon = 5;
		}
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
