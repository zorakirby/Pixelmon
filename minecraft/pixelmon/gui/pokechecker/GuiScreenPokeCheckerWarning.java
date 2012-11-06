package pixelmon.gui.pokechecker;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
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

public class GuiScreenPokeCheckerWarning extends GuiContainer {
	protected PixelmonDataPacket targetPacket;
	boolean overY = false;
	boolean overN = false;
	
	public GuiScreenPokeCheckerWarning(PixelmonDataPacket pixelmonDataPacket) {
		super(new ContainerEmpty());
		targetPacket = pixelmonDataPacket;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void initGui() {
		super.initGui();
		controlList.clear();
	}

	protected void mouseClicked(int mouseX, int mouseY, int par3) {
		super.mouseClicked(mouseX, mouseY, par3);
		if(overY){
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.StopStartLevelling, targetPacket.pokemonID));
			mc.thePlayer.closeScreen();
		}
		else if(overN)
			mc.thePlayer.closeScreen();
	}

	
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		String text = "";
		if(!targetPacket.doesLevel)
			text = "enable";
		if(targetPacket.doesLevel)
			text = "disable";
		drawCenteredString(fontRenderer, "Are you sure you'd like", 60, 75, 0xcccccc);
		drawCenteredString(fontRenderer, "to " + text + " leveling?", 60, 85, 0xcccccc);
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