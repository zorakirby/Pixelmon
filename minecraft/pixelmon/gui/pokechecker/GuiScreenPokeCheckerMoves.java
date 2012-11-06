package pixelmon.gui.pokechecker;

import java.sql.Types;

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
import pixelmon.database.DatabaseMoves;
import pixelmon.enums.EnumType;
import pixelmon.gui.ContainerEmpty;

public class GuiScreenPokeCheckerMoves extends GuiScreenPokeChecker {
	protected PixelmonDataPacket targetPacket;
	GuiButton nameButton;
	boolean renameButton;
	static int selectednumber = -1;
	static boolean move1 = true;
	static boolean move2 = false;
	static boolean move3 = false;
	static boolean move4 = false;

	public GuiScreenPokeCheckerMoves(PixelmonDataPacket pixelmonDataPacket) {
		super(pixelmonDataPacket);
		targetPacket = pixelmonDataPacket;
	}

	public boolean doesGuiPauseGame() {
		return true;
	}

	public void initGui() {
		super.initGui();
		controlList.clear();
		
		controlList.add(new GuiPokeCheckerTabs(3, 0, width / 2 + 107, (int) height / 2 + 80, 17, 15, ""));
		controlList.add(new GuiPokeCheckerTabs(0, 1, width / 2 - 127, (int) height / 2 + 80, 90, 15, "Summary"));
		controlList.add(new GuiPokeCheckerTabs(2, 2, width / 2 + 36, (int) height / 2 + 80, 69, 15, "Stats"));
		controlList.add(new GuiPokeCheckerTabs(4, 4, width / 2 - 44, (int) height / 2 - 107, 9, 9, "", targetPacket));
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			mc.thePlayer.closeScreen();
			break;
		case 1:
			mc.displayGuiScreen(new GuiScreenPokeChecker(targetPacket));
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
	
	public void resetAll(){
		move1 = false;
		move2 = false;
		move3 = false;
		move4 = false;
	}
	
	public void drawGuiContainerForegroundLayer(int i, int i1){
		drawString(fontRenderer, "PokeChecker", 65, -35, 0xcccccc);
		for (int i2 = 0; i2 < targetPacket.numMoves; i2++) {
			drawCenteredString(fontRenderer, (targetPacket.moveset[i2]).attackName, 135, -6 + (i2 * 21), 0xcccccc);
			drawCenteredString(fontRenderer, String.valueOf((targetPacket.moveset[i2]).pp)+"/"+String.valueOf((targetPacket.moveset[i2]).ppBase), 193, -4 + (i2 * 21), 0xcccccc);
			int timg = mc.renderEngine.getTexture("/pixelmon/gui/types.png");
			float x = targetPacket.moveset[i2].type.textureX;
			float y = targetPacket.moveset[i2].type.textureY;
			drawImageQuad(timg, 58, 22 * i2 - 15 , 38, 21, x / 256f, y / 128f, (x + 38f) / 256f, (y + 21f) / 128f);
		}
		drawString(fontRenderer, String.valueOf(selectednumber), 15, -24, 0xcccccc);
		drawString(fontRenderer, "Lvl: " + targetPacket.lvl, 15, -14, 0xcccccc);
		drawString(fontRenderer, String.valueOf(targetPacket.nationalPokedexNumber), -30, -14, 0xcccccc);
		drawString(fontRenderer, "Effects", -10, 100, 0xcccccc);
		drawString(fontRenderer, "Description", 107, 100, 0xcccccc);
		drawString(fontRenderer, "Moves", 73, 166, -6250336);
		drawSelection(i, i1);
		drawMoveDescription();
	}
	
	private Attack[] attacks = new Attack[4];
	
	public void drawMoveDescription(){
		if(move1 && targetPacket.numMoves > 0){
			if (attacks[0] == null || !attacks[0].attackName.equals(targetPacket.moveset[0].attackName))
				attacks[0] = DatabaseMoves.getAttack(targetPacket.moveset[0].attackName);
				drawMoveInfo(attacks[0]);
		}
		if(move2 && targetPacket.numMoves > 1){
			if (attacks[1] == null || !attacks[1].attackName.equals(targetPacket.moveset[1].attackName))
				attacks[1] = DatabaseMoves.getAttack(targetPacket.moveset[1].attackName);
			drawMoveInfo(attacks[1]);
		}
		if(move3 && targetPacket.numMoves > 2){
			if (attacks[2] == null || !attacks[2].attackName.equals(targetPacket.moveset[2].attackName))
				attacks[2] = DatabaseMoves.getAttack(targetPacket.moveset[2].attackName);
			drawMoveInfo(attacks[2]);
		}
		if(move4 && targetPacket.numMoves > 3){
			if (attacks[3] == null || !attacks[3].attackName.equals(targetPacket.moveset[3].attackName))
				attacks[3] = DatabaseMoves.getAttack(targetPacket.moveset[3].attackName);
			drawMoveInfo(attacks[3]);
		}
	}
	
	public void switchMoves(int moveToChange, int moveToChange2){
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ReplaceMove, targetPacket.pokemonID, moveToChange2, moveToChange));
		System.out.println(moveToChange + " has switched with " + moveToChange2);
	}
	
	private void drawMoveInfo(Attack attack) {
		drawString(fontRenderer, "Power:", -30, 118, 0xFFFFFF);
		drawString(fontRenderer, "Accuracy:", -30, 148, 0xFFFFFF);
		int bpextra = 0, acextra = 0;
		;
		if (attack.basePower >= 100)
			bpextra = fontRenderer.getCharWidth('0');
		if (attack.accuracy >= 100)
			acextra = fontRenderer.getCharWidth('0');
		if (attack.basePower != -1)
			drawString(fontRenderer, "" + attack.basePower, 30 - bpextra, 118, 0xFFFFFF);
		else
			drawString(fontRenderer, "--", 30 - bpextra, 118, 0xFFFFFF);
		drawString(fontRenderer, "" + attack.accuracy, 30 - acextra, 148, 0xFFFFFF);
		
		fontRenderer.drawSplitString(attack.description, 63, 115, 141, 0xFFFFFF);
	}
	
	public void drawSelection(int i, int i1){
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/summaryMoves.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		if(targetPacket.numMoves > 0 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 100 && i1 < height / 2 - 76 || move1){
			drawTexturedModalRect(58, -17, 1, 231, 153, 24);
			resetAll();
			move1 = true;
		}
		if(targetPacket.numMoves > 1 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 77 && i1 < height / 2 - 53 || move2){
			drawTexturedModalRect(58, 6, 1, 231, 153, 24);
			resetAll();
			move2 = true;
		}
		if(targetPacket.numMoves > 2 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 54 && i1 < height / 2 - 31 || move3){
			drawTexturedModalRect(58, 28, 1, 231, 153, 24);
			resetAll();
			move3 = true;
		}
		if(targetPacket.numMoves > 3 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 32 && i1 < height / 2 - 9 || move4){
			drawTexturedModalRect(58, 50, 1, 231, 153, 24);
			resetAll();
			move4 = true;
		}
		drawSelectedRect();
	}
	
	protected void drawSelectedRect(){
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/summaryMoves.png");
		GL11.glColor3f(0.0F, 1.0F, 0.0F);//Gives the selection a light green color. 
		mc.renderEngine.bindTexture(bg);
		if(selectednumber == 0)
			drawTexturedModalRect(58, -17, 1, 231, 153, 24);
		else if(selectednumber == 1)
			drawTexturedModalRect(58, 6, 1, 231, 153, 24);
		else if(selectednumber == 2)
			drawTexturedModalRect(58, 28, 1, 231, 153, 24);
		else if(selectednumber == 3)
			drawTexturedModalRect(58, 50, 1, 231, 153, 24);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	}
	
	protected int moveClicked(int i, int i1){
		if(targetPacket.numMoves > 0 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 100 && i1 < height / 2 - 76)
			return 0;
		else if(targetPacket.numMoves > 1 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 77 && i1 < height / 2 - 53)
			return 1;
		else if(targetPacket.numMoves > 2 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 54 && i1 < height / 2 - 31)
			return 2;
		else if(targetPacket.numMoves > 3 && i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 32 && i1 < height / 2 - 9)
			return 3;
		else return -1;
	}
	
	protected void selectMove(int i, int i1){
		if(i > width / 2 - 31 && i < width / 2 + 123 && i1 > height / 2 - 100 && i1 < height / 2 - 9 && selectednumber != moveClicked(i, i1)){
			if(selectednumber == -1){
				if(moveClicked(i, i1) == 0)
					selectednumber = 0;
				if(moveClicked(i, i1) == 1)
					selectednumber = 1;
				if(moveClicked(i, i1) == 2)
					selectednumber = 2;
				if(moveClicked(i, i1) == 3)
					selectednumber = 3;
			}
			else if(moveClicked(i, i1) == 0)
				switchMoves(0, moveClicked(i, i1));
			if(moveClicked(i, i1) == 1)
				switchMoves(1, moveClicked(i, i1));
			if(moveClicked(i, i1) == 2)
				switchMoves(2, moveClicked(i, i1));
			if(moveClicked(i, i1) == 3)
				switchMoves(3, moveClicked(i, i1));
		}
		else selectednumber = -1;
	}
	
	protected void mouseClicked(int x, int y, int par3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		super.mouseClicked(x, y, par3);
		selectMove(x, y);
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
		
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/summaryMoves.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		drawTexturedModalRect((width - xSize) / 2 - 40, (height - ySize) / 2 - 25, 0, 0, 256, 204);
		
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