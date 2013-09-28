package pixelmon.client.gui.starter;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.network.packet.Packet250CustomPayload;
import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiHelper;
import pixelmon.client.gui.GuiResources;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.gui.ContainerEmpty;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiChooseStarter extends GuiContainer {
	enum StarterScreen {
		Copyright, Choose
	}

	StarterScreen currentScreen = StarterScreen.Copyright;

	public GuiChooseStarter() {
		super(new ContainerEmpty());
		Random r = new Random();
		while (shadowList.size() < 5) {
			EnumShadow shadowType;
			float f = r.nextFloat();
			if (f < 0.2f)
				shadowType = EnumShadow.Large;
			if (f < 0.5f)
				shadowType = EnumShadow.Medium;
			else
				shadowType = EnumShadow.Large;
			shadowList.add(new Shadow(shadowType, this, r.nextFloat()));
		}
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		if (currentScreen == StarterScreen.Choose) {
			int index = 0;
			for (int col = 0; col < 4; col++) {
				for (int row = 0; row < 3; row++) {
					if (index < PixelmonServerStore.starterListPacket.starterList.length)
						buttonList.add(new StarterButton(index, width / 2 + 90 * (col - 2), height / 6 + 45 + row * 41, index));
					else
						buttonList.add(new StarterButton(index, width / 2 + 90 * (col - 2), height / 6 + 45 + row * 41, -1));
					index++;
				}
			}
		}
	}

	public void keyTyped(char i, int i1) {
	}

	public void actionPerformed(GuiButton button) {
		if (((StarterButton) button).starterIndex != -1) {
			Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.ChooseStarter, button.id);
			PacketDispatcher.sendPacketToServer(packet);
			mc.thePlayer.closeScreen();
		}
	}

	ArrayList<Shadow> shadowList = new ArrayList<Shadow>();

	public void drawGuiContainerBackgroundLayer(float par3, int par1, int par2) {
		mc.renderEngine.bindTexture(GuiResources.starterBackground);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(0, 0, width, height, 0, 0, 1, 1, zLevel);

		for (int i = 0; i < shadowList.size(); i++)
			shadowList.get(i).draw(mc, width, height);
		GL11.glEnable(GL11.GL_BLEND);
		mc.renderEngine.bindTexture(GuiResources.starterBorders);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(0, 0, width, height, 0, 0, 1, 1, zLevel);
		if (currentScreen == StarterScreen.Copyright) {
			// mc.renderEngine.bindTexture(GuiResources.cwPanel);
			// drawImageQuad(width/4, height/4, width/2, height/2, 0, 0, 1, 1);
			drawCenteredString(fontRenderer, "This is a fan made mod, all trademarks and copyright for pokemon are owned", width / 2, height / 2 - 30, 0xFFFFFF);
			drawCenteredString(fontRenderer, "by Nintendo and Game Freak Inc.", width / 2, height / 2 - 17, 0xFFFFFF);
			drawCenteredString(fontRenderer, "Support Nintendo/Game Freak by buying Pokemon Black/White", width / 2, height / 2 + 5, 0xFFFFFF);
		} else if (currentScreen == StarterScreen.Choose) {
			drawCenteredString(fontRenderer, "Welcome to the world of Pixelmon!! Thank you for installing this mod!", width / 2, 50, 0xFFFFFF);
			drawCenteredString(fontRenderer, "Now, please pick your desired starter Pokemon!", width / 2, 62, 0xFFFFFF);
		}

	}

	int ticksToChange = 100;

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (currentScreen == StarterScreen.Copyright) {
			ticksToChange--;
			if (ticksToChange <= 0) {
				currentScreen = StarterScreen.Choose;
				initGui();
			}
		}
		for (int i = 0; i < shadowList.size(); i++)
			shadowList.get(i).update();

		if (Minecraft.getMinecraft().thePlayer.getRNG().nextFloat() < 0.008f) {
			EnumShadow shadowType;
			float f = mc.thePlayer.getRNG().nextFloat();
			if (f < 0.2f)
				shadowType = EnumShadow.Large;
			if (f < 0.5f)
				shadowType = EnumShadow.Medium;
			else
				shadowType = EnumShadow.Large;
			shadowList.add(new Shadow(shadowType, this));
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		if (currentScreen == StarterScreen.Copyright) {
			currentScreen = StarterScreen.Choose;
			initGui();
		}
	}

	public void removeShadow(Shadow shadow) {
		shadowList.remove(shadow);
	}
}