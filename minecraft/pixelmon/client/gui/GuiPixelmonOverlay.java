package pixelmon.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class GuiPixelmonOverlay extends Gui {
	public static boolean isGuiMinimized = false;
	public FontRenderer fontRenderer;
	public static int selectedPixelmon;
	public static boolean isVisible = true;

	public GuiPixelmonOverlay() {
		fontRenderer = Minecraft.getMinecraft().fontRenderer;
	}

	static int count = 100;

	@ForgeSubscribe
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		if (count++ >= 100) {
			count = 0;
			checkEntitysInWorld(Minecraft.getMinecraft().theWorld);
		}
		if (Minecraft.getMinecraft().currentScreen instanceof GuiInventory && event != null || !isVisible || Minecraft.getMinecraft().gameSettings.hideGUI)
			return;
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (event != null) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
		bindOverlayTexture();
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
				String displayName = p.name;
				if (!p.nickname.equals(""))
					displayName = p.nickname;

				i = p.order;
				if (!isGuiMinimized) {
					fontRenderer.drawString(displayName, 32, var7 / 6 + i * 30 + 6, 0xFFFFFF);
					bindOverlayTexture();
					if (p.isMale)
						this.drawTexturedModalRect(fontRenderer.getStringWidth(displayName) + 35, var7 / 6 + i * 30 + 6 + offset, 33, 208, 5, 9);
					else
						this.drawTexturedModalRect(fontRenderer.getStringWidth(displayName) + 35, var7 / 6 + i * 30 + 6 + offset, 33, 218, 5, 9);

				}
				String numString = "";
				if (p.getNationalPokedexNumber() < 10)
					numString = "00" + p.getNationalPokedexNumber();
				else if (p.getNationalPokedexNumber() < 100)
					numString = "0" + p.getNationalPokedexNumber();
				else
					numString = "" + p.getNationalPokedexNumber();
				if (p.outside) {
					bindOverlayTexture();
					drawTexturedModalRect(2, var7 / 6 + i * 30 + 3 + offset, 75, 209, 28, 28);
				}
				if (p.isShiny)
					mc.renderEngine.func_110577_a(GuiResources.shinySprite(numString));
				else
					mc.renderEngine.func_110577_a(GuiResources.sprite(numString));
				drawImageQuad(3, var7 / 6 + i * 30 + 3 + offset, 24f, 24f, 0f, 0f, 1f, 1f);
				if (p.heldItemId != -1) {
					Minecraft.getMinecraft().renderEngine.func_110577_a(GuiResources.heldItem);
					drawImageQuad(18, var7 / 6 + i * 30 + 19 + offset, 6, 6, 0f, 0f, 1f, 1f);
				}
				if (!isGuiMinimized) {
					fontRenderer.drawString("Lvl " + p.lvl, 32, var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
					if (p.health <= 0) {
						fontRenderer.drawString("Fainted", 33 + fontRenderer.getStringWidth("Lvl " + p.lvl), var7 / 6 + i * 30 + fontRenderer.FONT_HEIGHT + 7
								+ offset, 0xFFFFFF);
					} else {
						fontRenderer.drawString("HP " + p.health + "/" + p.hp, 39 + fontRenderer.getStringWidth("Lvl " + p.lvl), var7 / 6 + i * 30
								+ fontRenderer.FONT_HEIGHT + 7 + offset, 0xFFFFFF);
					}
				}
			}
			i++;
		}

		bindOverlayTexture();
		this.drawTexturedModalRect(2, var7 / 6 + 4 + selectedPixelmon * 30, 45, 209, 28, 28);
		fontRenderer.setUnicodeFlag(false);

		RenderHelper.disableStandardItemLighting();

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	}

	private void bindOverlayTexture() {
		if (isGuiMinimized)
			Minecraft.getMinecraft().renderEngine.func_110577_a(GuiResources.overlaySimple);
		else
			Minecraft.getMinecraft().renderEngine.func_110577_a(GuiResources.overlayExtended);
	}

	public boolean checkEntitysInWorld(World world) {
		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			if (p != null)
				p.outside = false;
		}
		@SuppressWarnings("unchecked")
		List<Entity> EntityList = world.loadedEntityList;
		for (int i = 0; i < EntityList.size(); i++) {
			Entity e = EntityList.get(i);
			if (e instanceof EntityPixelmon) {
				int existingId = ((EntityPixelmon) e).getPokemonId();
				if (existingId != -1) {
					for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
						if (p != null) {
							if (((EntityPixelmon) e).getPokemonId() == p.pokemonID) {
								p.outside = true;
							}
						}
					}
				}
			}
		}
		return false;
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

	private void drawImageQuad(int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) this.zLevel, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) this.zLevel, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) this.zLevel, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}

	public static void checkSelection() {
		if (ServerStorageDisplay.pokemon[selectedPixelmon] == null)
			selectPreviousPixelmon();
	}

}
