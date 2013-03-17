package pixelmon.client.gui.pokechecker;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.ContainerEmpty;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiScreenPokeCheckerWarning extends GuiContainer {
	protected PixelmonDataPacket targetPacket;
	boolean overY = false;
	boolean overN = false;
	int warningIndex;
	int moveToDelete;
	
	public GuiScreenPokeCheckerWarning(PixelmonDataPacket pixelmonDataPacket, int i) {
		super(new ContainerEmpty());
		targetPacket = pixelmonDataPacket;
		warningIndex = i;
	}
	
	public GuiScreenPokeCheckerWarning(PixelmonDataPacket pixelmonDataPacket, int i, int j) {
		super(new ContainerEmpty());
		targetPacket = pixelmonDataPacket;
		warningIndex = i;
		moveToDelete = j;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void initGui() {
		super.initGui();
		buttonList.clear();
	}

	protected void mouseClicked(int mouseX, int mouseY, int par3) {
		super.mouseClicked(mouseX, mouseY, par3);
		if(overY && warningIndex == 0){
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.StopStartLevelling, targetPacket.pokemonID));
			mc.thePlayer.closeScreen();
		}
		if(overY && warningIndex == 1){
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.DeleteMove, targetPacket.pokemonID, moveToDelete));
			mc.thePlayer.closeScreen();
		}
		else if(overN)
			mc.thePlayer.closeScreen();
	}

	
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		String text = "";
		if(warningIndex == 0){
			if(!targetPacket.doesLevel)
				text = "enable";
			if(targetPacket.doesLevel)
				text = "disable";
			drawCenteredString(fontRenderer, "Are you sure you'd like", 60, 75, 0xcccccc);
			drawCenteredString(fontRenderer, "to " + text + " leveling?", 60, 85, 0xcccccc);
		}
		if(warningIndex == 1){
			drawCenteredString(fontRenderer, "Are you sure you'd like", 60, 75, 0xcccccc);
			drawCenteredString(fontRenderer, targetPacket.name + " to forget ", 60, 85, 0xcccccc);
			drawCenteredString(fontRenderer, targetPacket.moveset[moveToDelete].attackName + "?", 60, 95, 0xcccccc);
		}
	}
	
	public void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		int guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/yesNo.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - 256 / 2, height / 2 - 50, 256, 100, 0, 0, 1, 100f / 128f);
		
		if (!(mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 - 33 && mouseY < height / 2 - 7)){
			drawString(fontRenderer, "Yes", width / 2 + 76, height / 2 - 23, 0xFFFFFF);
		}
		if (!(mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 + 5 && mouseY < height / 2 + 31)){
			drawString(fontRenderer, "No", width / 2 + 80, height / 2 + 15, 0xFFFFFF);
		}
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 - 33 && mouseY < height / 2 - 7){
			drawImageQuad(guiIndex, width / 2 + 63, height / 2 - 33, 45, 26, 154f / 256f, 101f / 128f, 199f / 256f, 127f / 128f);
			drawString(fontRenderer, "Yes", width / 2 + 76, height / 2 - 23, 16777120);
			overY = true;
			overN = false;
		}
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 + 5 && mouseY < height / 2 + 31){
			drawImageQuad(guiIndex, width / 2 + 63, height / 2 + 5, 45, 26, 154f / 256f, 101f / 128f, 199f / 256f, 127f / 128f);		
			drawString(fontRenderer, "No", width / 2 + 80, height / 2 + 15, 16777120);
			overY = false;
			overN = true;
		}
	}
	
	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
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