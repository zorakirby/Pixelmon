package pixelmon.client.gui.pokechecker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiResources;
import pixelmon.client.gui.pc.GuiPC;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonConfig;
import pixelmon.enums.EnumType;
import pixelmon.gui.ContainerEmpty;

public class GuiScreenPokeChecker extends GuiContainer {
	protected PixelmonDataPacket targetPacket;
	GuiButton nameButton;
	boolean renameButton;
	boolean isPC;

	public GuiScreenPokeChecker(PixelmonDataPacket pixelmonDataPacket, boolean b) {
		super(new ContainerEmpty());
		targetPacket = pixelmonDataPacket;
		isPC = b;
	}

	public boolean doesGuiPauseGame() {
		return true;
	}

	public void initGui() {
		super.initGui();
		buttonList.clear();

		buttonList.add(new GuiPokeCheckerTabs(3, 0, width / 2 + 107, (int) height / 2 + 80, 17, 15, ""));
		buttonList.add(new GuiPokeCheckerTabs(1, 1, width / 2 - 34, (int) height / 2 + 80, 69, 15, "Moves"));
		buttonList.add(new GuiPokeCheckerTabs(2, 2, width / 2 + 36, (int) height / 2 + 80, 69, 15, "Stats"));
		buttonList.add(new GuiPokeCheckerTabs(4, 4, width / 2 - 44, (int) height / 2 - 107, 9, 9, "", targetPacket));
		buttonList.add(new GuiPokeCheckerTabs(7, 5, width / 2 - 44, (int) height / 2 - 14, 9, 8, "", targetPacket));
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (!isPC)
				mc.thePlayer.closeScreen();
			else {
				GuiPC gui = new GuiPC(targetPacket);
				mc.displayGuiScreen(gui);
			}
			break;
		case 1:
			mc.displayGuiScreen(new GuiScreenPokeCheckerMoves(targetPacket, isPC));
			break;
		case 2:
			mc.displayGuiScreen(new GuiScreenPokeCheckerStats(targetPacket, isPC));
			break;
		case 3:
			if (PixelmonConfig.allowNicknames){
				mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			}
			break;
		case 4:
			mc.displayGuiScreen(new GuiScreenPokeCheckerWarning(targetPacket, 0));
			break;
		case 5:
			if (PixelmonConfig.allowNicknames){
				mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			}
			break;
		}

	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (par2 == 1) {
			if (isPC) {
				GuiPC gui = new GuiPC(targetPacket);
				mc.displayGuiScreen(gui);
			}
		}
	}

	protected void mouseClicked(int x, int y, int par3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		super.mouseClicked(x, y, par3);
		if (x > var6 / 2 - 125 && x < var6 / 2 - 40 && y > var7 / 2 - 15 && y < var7 / 2 + 5) {
			if (par3 == 1 && !renameButton) {
				nameButton = new GuiButton(3, x, y, 50, 20, "Rename");
				buttonList.add(nameButton);
				renameButton = true;
			} else if (par3 != 1 && renameButton) {
				buttonList.remove(nameButton);
				renameButton = false;
			} else if (par3 == 1 && renameButton) {
				buttonList.remove(nameButton);
				nameButton = new GuiButton(3, x, y, 50, 20, "Rename");
				buttonList.add(nameButton);
			}
		}
		arrowsMouseClicked(x, y);
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		drawString(fontRenderer, "Lvl: " + targetPacket.lvl, 15, -14, 0xffffff);
		drawString(fontRenderer, String.valueOf(targetPacket.getNationalPokedexNumber()), -30, -14, 0xffffff);
		drawCenteredString(fontRenderer, targetPacket.health + "/" + targetPacket.hp, 185, 10, 0xdddddd);
		drawString(fontRenderer, "Status", -10, 100, 0xffffff);
		if (targetPacket.isFainted)
			drawString(fontRenderer, "*FAINTED*", 117, -11, 0xffffff);
		drawString(fontRenderer, "Total Experience", 95, 40, 0xffffff);
		drawCenteredString(fontRenderer, String.valueOf(targetPacket.xp), 135, 55, 0xffffff);
		drawString(fontRenderer, "Level Up", 82, 94, 0xffffff);
		drawString(fontRenderer, String.valueOf(targetPacket.nextLvlXP), 152, 98, 0xffffff);
		drawString(fontRenderer, "TO", 127, 119, 0xffffff);
		drawString(fontRenderer, "Lvl: " + (targetPacket.lvl + 1), 152, 123, 0xffffff);
		drawString(fontRenderer, "Summary", -15, 166, 0xffffff);

		int typeImg;
		float x = targetPacket.getType1().textureX;
		float y = targetPacket.getType1().textureY;
		float x1 = 0;
		float y1 = 0;
		if(targetPacket.getType2() != null) {
			x1 = targetPacket.getType2().textureX;
			y1 = targetPacket.getType2().textureY;
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiResources.types);
		drawImageQuad(60, 1, 38, 21, x / 256f, y / 128f, (x + 38f) / 256f, (y + 23f) / 128f);
		if ((targetPacket.getType2() != EnumType.Mystery) && targetPacket.getType2() != null)
			drawImageQuad(100, 1, 38, 21, x1 / 256f, y1 / 128f, (x1 + 38f) / 256f, (y1 + 23f) / 128f);

	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		String numString = "";
		if (targetPacket.getNationalPokedexNumber() < 10)
			numString = "00" + targetPacket.getNationalPokedexNumber();
		else if (targetPacket.getNationalPokedexNumber() < 100)
			numString = "0" + targetPacket.getNationalPokedexNumber();
		else
			numString = "" + targetPacket.getNationalPokedexNumber();

		mc.renderEngine.bindTexture(GuiResources.summarySummary);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect((width - xSize) / 2 - 40, (height - ySize) / 2 - 25, 0, 0, 256, 204);
		drawTexturedModalRect((width - xSize) / 2 - 15, (height - ySize) / 2 + 120, 23, 225, 44, 28);
		drawHealthBar((width - xSize) / 2 + 59, (height - ySize) / 2 - 15, 154, 14, targetPacket);
		drawTexturedModalRect((width - xSize) / 2 + 59, (height - ySize) / 2 - 15, 103, 222, 150, 16);
		drawExpBar((width - xSize) / 2 + 86, (height - ySize) / 2 + 145, 122, 14, targetPacket);
		drawTexturedModalRect((width - xSize) / 2 + 59, (height - ySize) / 2 + 145, 104, 239, 150, 16);

		if (targetPacket.isShiny)
			mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
		else
			mc.renderEngine.bindTexture(GuiResources.sprite(numString));
		drawImageQuad(width / 2 - 123, height / 2 - 100, 84f, 84f, 0f, 0f, 1f, 1f);
		if (targetPacket.nickname.length() < 1)
			drawCenteredStringWithoutShadow(fontRenderer, String.valueOf(targetPacket.name), (width - xSize) / 2 + 7, (height - ySize) / 2 + 75, targetPacket
					.getType1().getColor());
		else {
			drawCenteredStringWithoutShadow(fontRenderer, "(" + String.valueOf(targetPacket.name) + ")", (width - xSize) / 2 + 7, (height - ySize) / 2 + 78,
					targetPacket.getType1().getColor());
			drawCenteredStringWithoutShadow(fontRenderer, String.valueOf(targetPacket.nickname), (width - xSize) / 2 + 7, (height - ySize) / 2 + 70,
					targetPacket.getType1().getColor());
		}

		drawArrows(i, i1);
	}

	public void arrowsMouseClicked(int x, int y) {
		if (isPC) return;
		int l1 = (width - xSize) / 2 + 220;
		int l2 = (width - xSize) / 2 - 62;
		int w = 16;
		int t = (height - ySize) / 2;
		int h = 21;
		if (y > t && y < t + h) {
			if (x > l1 && x < l1 + w)
				getNextPokemon();
			if (x > l2 && x < l2 + w)
				getPrevPokemon();
		}
	}

	private void getPrevPokemon() {
		if (!isPC){
			int pos = targetPacket.order;
			targetPacket = ServerStorageDisplay.getPrevFromPos(pos);
		}
	}

	private void getNextPokemon() {
		if (!isPC){
			int pos = targetPacket.order;
			targetPacket = ServerStorageDisplay.getNextFromPos(pos);
		}
	}

	public void drawArrows(int mouseX, int mouseY) {
		if (isPC) return;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiResources.summaryMoves);
		int l1 = (width - xSize) / 2 + 220;
		int l2 = (width - xSize) / 2 - 62;
		int w = 16;
		int t = (height - ySize) / 2;
		int h = 21;
		drawTexturedModalRect(l1, t, 24, 207, w, h);
		drawTexturedModalRect(l2, t, 42, 207, w, h);
		if (mouseY > t && mouseY < t + h) {
			if (mouseX > l1 && mouseX < l1 + w)
				drawTexturedModalRect(l1, t, 60, 207, w, h);
			if (mouseX > l2 && mouseX < l2 + w)
				drawTexturedModalRect(l2, t, 78, 207, w, h);
		}
	}

	private void drawExpBar(int x, int y, int width, int height, PixelmonDataPacket p) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();

		int barWidth = (int) (((float) p.xp) / ((float) p.nextLvlXP) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(0.0f, 0.0f, 0.4f, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + width, y + height, 0.0);
		tessellator.addVertex(x + width, y, 0.0);
		tessellator.setColorRGBA_F(0.3f, 1.0f, 1.0f, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	public void drawHealthBar(int x, int y, int width, int height, PixelmonDataPacket p) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();

		int barWidth = width - 6;
		tessellator.setColorRGBA_F(1.0F, 0.2F, 0.2F, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y, 0.0);
		barWidth = (int) (((float) p.health) / ((float) p.hp) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(0.2F, 1F, 0.2F, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	public void drawCenteredStringWithoutShadow(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5) {
		par1FontRenderer.drawString(par2Str, par3 - par1FontRenderer.getStringWidth(par2Str) / 2, par4, par5);
	}

	private void drawImageQuad(int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture

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
