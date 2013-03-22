package pixelmon.client.gui.pokechecker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;

import pixelmon.client.gui.pc.GuiPC;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.enums.EnumNature;

public class GuiScreenPokeCheckerStats extends GuiScreenPokeChecker {
	protected PixelmonDataPacket targetPacket;
	GuiButton nameButton;
	boolean renameButton;
	boolean isPC;

	public GuiScreenPokeCheckerStats(PixelmonDataPacket pixelmonDataPacket, boolean b) {
		super(pixelmonDataPacket, b);
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
		buttonList.add(new GuiPokeCheckerTabs(0, 1, width / 2 - 127, (int) height / 2 + 80, 90, 15, "Summary"));
		buttonList.add(new GuiPokeCheckerTabs(1, 2, width / 2 - 34, (int) height / 2 + 80, 69, 15, "Moves"));
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
			mc.displayGuiScreen(new GuiScreenPokeChecker(targetPacket, isPC));
			break;
		case 2:
			mc.displayGuiScreen(new GuiScreenPokeCheckerMoves(targetPacket, isPC));
			break;
		case 3:
			mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			break;
		case 4:
			mc.displayGuiScreen(new GuiScreenPokeCheckerWarning(targetPacket, 0));
			break;
		case 5:
			mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			break;
		}

	}

	protected void mouseClicked(int x, int y, int par3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
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
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		drawString(fontRenderer, "Lvl: " + targetPacket.lvl, 15, -14, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.getNationalPokedexNumber()), -30, -14, 0xcccccc);
		drawString(fontRenderer, "OT. Trainer", -20, 100, 0xcccccc);
		drawCenteredString(fontRenderer, String.valueOf(mc.thePlayer.username), 8, 115, 0xcccccc);

		drawString(fontRenderer, "HP:", 60, -7, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.HP), 200 - (String.valueOf(targetPacket.HP).length() * 3), -7, 0xcccccc);
		drawString(fontRenderer, "Attack:", 60, 13, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.Attack), 200 - (String.valueOf(targetPacket.Attack).length() * 3), 13, 0xcccccc);
		drawString(fontRenderer, "Defence:", 60, 32, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.Defence), 200 - (String.valueOf(targetPacket.Defence).length() * 3), 32, 0xcccccc);
		drawString(fontRenderer, "SP.Attack:", 60, 53, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.SpecialAttack), 200 - (String.valueOf(targetPacket.SpecialAttack).length() * 3), 53, 0xcccccc);
		drawString(fontRenderer, "SP.Defense:", 60, 73, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.SpecialDefence), 200 - (String.valueOf(targetPacket.SpecialDefence).length() * 3), 73, 0xcccccc);
		drawString(fontRenderer, "Speed:", 60, 93, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.Speed), 200 - (String.valueOf(targetPacket.Speed).length() * 3), 93, 0xcccccc);

		drawString(fontRenderer, "Happiness", 72, 115, 0xcccccc);
		drawCenteredString(fontRenderer, String.valueOf(targetPacket.friendship), 95, 130, 0xcccccc);
		drawString(fontRenderer, "Nature", 158, 115, 0xcccccc);
		drawCenteredString(fontRenderer, targetPacket.nature.toString(), 174, 130, -1);
		drawCenteredString(fontRenderer, "Growth", 8, 135, 0xcccccc);
		drawCenteredString(fontRenderer, targetPacket.growth.toString(), 8, 148, -1);
		drawString(fontRenderer, "Stats", 145, 166, -6250336);
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		String numString = "";
		if (targetPacket.getNationalPokedexNumber() < 10)
			numString = "00" + targetPacket.getNationalPokedexNumber();
		else if (targetPacket.getNationalPokedexNumber() < 100)
			numString = "0" + targetPacket.getNationalPokedexNumber();
		else
			numString = "" + targetPacket.getNationalPokedexNumber();

		mc.renderEngine.bindTexture("/pixelmon/gui/summaryStats.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect((width - xSize) / 2 - 40, (height - ySize) / 2 - 25, 0, 0, 256, 204);

		if (targetPacket.isShiny)
			mc.renderEngine.bindTexture("/mods/pixelmon/sprites/shinypokemon/" + numString + ".png");
		else
			mc.renderEngine.bindTexture("/mods/pixelmon/sprites/pokemon/" + numString + ".png");
		drawImageQuad(width / 2 - 123, height / 2 - 100, 84f, 84f, 0f, 0f, 1f, 1f);
		if (targetPacket.nickname.length() < 1)
			drawCenteredStringWithoutShadow(fontRenderer, String.valueOf(targetPacket.name), (width - xSize) / 2 + 7, (height - ySize) / 2 + 75, targetPacket.getType1().getColor());
		else {
			drawCenteredStringWithoutShadow(fontRenderer, "(" + String.valueOf(targetPacket.name) + ")", (width - xSize) / 2 + 7, (height - ySize) / 2 + 78, targetPacket
					.getType1().getColor());
			drawCenteredStringWithoutShadow(fontRenderer, String.valueOf(targetPacket.nickname), (width - xSize) / 2 + 7, (height - ySize) / 2 + 70, targetPacket.getType1()
					.getColor());
		}
	}

	private void drawColoredBar(int x, int y, int width, int height, float r, float g, float b) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();

		tessellator.setColorRGBA_F(r, g, b, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + width, y + height, 0.0);
		tessellator.addVertex(x + width, y, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	private void drawExpBar(int x, int y, int width, int height, PixelmonDataPacket p) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();

		int barWidth = (int) (((float) p.xp) / ((float) p.nextLvlXP) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(0.3f, 0.6f, 1.0f, 1.0F);
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