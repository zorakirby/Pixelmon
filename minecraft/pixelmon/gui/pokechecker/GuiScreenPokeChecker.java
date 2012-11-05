package pixelmon.gui.pokechecker;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.StatCollector;
import net.minecraft.src.Tessellator;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.enums.EnumType;
import pixelmon.gui.ContainerEmpty;
import pixelmon.gui.battles.ClientBattleManager;
import pixelmon.gui.inventoryExtended.SlotInventoryPixelmon;

public class GuiScreenPokeChecker extends GuiContainer {
	protected PixelmonDataPacket targetPacket;
	GuiButton nameButton;
	boolean renameButton;
	
	public GuiScreenPokeChecker(PixelmonDataPacket pixelmonDataPacket) {
		super(new ContainerEmpty());
		targetPacket = pixelmonDataPacket;
	}

	public boolean doesGuiPauseGame() {
		return true;
	}

	public void initGui() {
		super.initGui();
		controlList.clear();
		
		controlList.add(new GuiPokeCheckerTabs(3, 0, width / 2 + 107, (int) height / 2 + 80, 17, 15, ""));
		controlList.add(new GuiPokeCheckerTabs(1, 1, width / 2 - 34, (int) height / 2 + 80, 69, 15, "Moves"));
		controlList.add(new GuiPokeCheckerTabs(2, 2, width / 2 + 36, (int) height / 2 + 80, 69, 15, "Stats"));
		controlList.add(new GuiPokeCheckerTabs(4, 4, width / 2 - 44, (int) height / 2 - 107, 9, 9, "", targetPacket));
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			mc.thePlayer.closeScreen();
			break;
		case 1:
			mc.displayGuiScreen(new GuiScreenPokeCheckerMoves(targetPacket));
			break;
		case 2:
			mc.displayGuiScreen(new GuiScreenPokeCheckerStats(targetPacket));
			break;
		case 3:
			mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
			break;
		case 4:
			mc.displayGuiScreen(new GuiScreenPokeCheckerWarning(targetPacket));
			break;
		}

	}
	
	protected void mouseClicked(int x, int y, int par3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		super.mouseClicked(x, y, par3);
		if(x > var6 / 2 - 125 && x < var6 / 2 - 40 && y > var7 / 2 - 15 && y < var7 / 2 + 5){
			if(par3 == 1 && !renameButton){
				nameButton = new GuiButton(3, x, y, 50, 20, "Rename");
				controlList.add(nameButton);
				renameButton = true;
			}	
			else if(par3 != 1 && renameButton){
				controlList.remove(nameButton);
				renameButton = false;
			}
			else if(par3 == 1 && renameButton){
				controlList.remove(nameButton);
				nameButton = new GuiButton(3, x, y, 50, 20, "Rename");
				controlList.add(nameButton);
			}
		}
	}

	
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		drawString(fontRenderer, "PokeChecker", 65, -35, 0xcccccc);
		drawString(fontRenderer, "Lvl: " + targetPacket.lvl, 15, -14, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.nationalPokedexNumber), -30, -14, 0xcccccc);
		drawCenteredString(fontRenderer, targetPacket.health + "/" + targetPacket.hp, 185, 10, 0xdddddd);
		drawString(fontRenderer, "Status", -10, 100, 0xcccccc);
		drawString(fontRenderer, "Total Experience", 95, 40, 0xcccccc);
		drawCenteredString(fontRenderer, String.valueOf(targetPacket.xp), 135, 55, 0xcccccc);
		drawString(fontRenderer, "Level Up", 82, 94, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.nextLvlXP), 152, 98, 0xcccccc);
		drawString(fontRenderer, "TO", 127, 119, 0xcccccc);
		drawString(fontRenderer, "Lvl: " + (targetPacket.lvl + 1), 152, 123, 0xcccccc);
		drawString(fontRenderer, "Summary", -15, 166, -6250336);
		
		int typeImg;
		float x = targetPacket.type1.textureX;
		float y = targetPacket.type1.textureY;
		float x1 = targetPacket.type2.textureX;
		float y1 = targetPacket.type2.textureY;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		typeImg = mc.renderEngine.getTexture("/pixelmon/gui/types.png");
		drawImageQuad(typeImg, 60, 1, 38, 21, x / 256f, y / 128f, (x + 38f) / 256f, (y + 23f) / 128f);
		if((targetPacket.type2 != EnumType.Mystery))
		drawImageQuad(typeImg, 100, 1, 38, 21, x1 / 256f, y1 / 128f, (x1 + 38f) / 256f, (y1 + 23f) / 128f);
		
	}
	
	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		String numString = "";
		if (targetPacket.nationalPokedexNumber < 10)
			numString = "00" + targetPacket.nationalPokedexNumber;
		else if (targetPacket.nationalPokedexNumber < 100)
			numString = "0" + targetPacket.nationalPokedexNumber;
		else
			numString = "" + targetPacket.nationalPokedexNumber;
		
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/summarySummary.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		drawTexturedModalRect((width - xSize) / 2 - 40, (height - ySize) / 2 - 25, 0, 0, 256, 204);
		drawTexturedModalRect((width - xSize) / 2 - 15, (height - ySize) / 2 + 120, 23, 225, 44, 28);
		drawColoredBar((width - xSize) / 2 + 59, (height - ySize) / 2 - 15, 150, 14, 0.0f, 0.0f, 0.0f);
		drawHealthBar((width - xSize) / 2 + 59, (height - ySize) / 2 - 15, 154, 14, targetPacket);
		drawTexturedModalRect((width - xSize) / 2 + 59, (height - ySize) / 2 - 15, 103, 222, 150, 16);
		drawColoredBar((width - xSize) / 2 + 86, (height - ySize) / 2 + 145, 122, 14, 0.0f, 0.0f, 0.4f);
		drawExpBar((width - xSize) / 2 + 86, (height - ySize) / 2 + 145, 122, 14, targetPacket);
		drawTexturedModalRect((width - xSize) / 2 + 59, (height - ySize) / 2 + 145, 104, 239, 150, 16);

		int pimg;
		if(targetPacket.isShiny)
			pimg = mc.renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
		else
			pimg = mc.renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
		drawImageQuad(pimg, width / 2 - 123, height / 2 - 100, 84f, 84f, 0f, 0f, 1f, 1f);
		if(targetPacket.nickname.length() < 1)
		drawCenteredStringWithoutShadow(fontRenderer, String.valueOf(targetPacket.name),(width - xSize) / 2 + 7, (height - ySize) / 2 + 75, targetPacket.type1.getColor());
		else{
			drawCenteredStringWithoutShadow(fontRenderer, "("+String.valueOf(targetPacket.name)+")",(width - xSize) / 2 + 7, (height - ySize) / 2 + 78, targetPacket.type1.getColor());
			drawCenteredStringWithoutShadow(fontRenderer, String.valueOf(targetPacket.nickname),(width - xSize) / 2 + 7, (height - ySize) / 2 + 70, targetPacket.type1.getColor());
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

		int barWidth = (int) (((float) p.health) / ((float) p.hp) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(1.0f - ((float) p.health / (float) p.hp) * 0.8F, 0.2F + ((float) p.health / (float) p.hp) * 0.8F, 0.2F, 1.0F);
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
	
    public void drawCenteredStringWithoutShadow(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5)
    {
        par1FontRenderer.drawString(par2Str, par3 - par1FontRenderer.getStringWidth(par2Str) / 2, par4, par5);
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