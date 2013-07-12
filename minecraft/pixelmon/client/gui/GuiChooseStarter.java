package pixelmon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.network.packet.Packet250CustomPayload;
import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.gui.ContainerEmpty;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiChooseStarter extends GuiContainer {

	String[] starterList;

	public GuiChooseStarter() {
		super(new ContainerEmpty());
		starterList = StarterList.getStarterStringList();
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		buttonList.clear();
		for (int i = 0; i < starterList.length; i++) {
			buttonList.add(new GuiButton(i, width / 3 - 100, height / 6 + i * 20, starterList[i]));
		}
	}

	public void keyTyped(char i, int i1) {
	}

	public void actionPerformed(GuiButton button) {
		String pixelmonName = starterList[button.id];
		Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.ChooseStarter, button.id);
		PacketDispatcher.sendPacketToServer(packet);
		//PlayerStorage.setCurrency(3000);
		mc.thePlayer.closeScreen();
	}

	public void drawGuiContainerBackgroundLayer(float par3, int par1, int par2) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, "Welcome to the world of Pixelmon!! Thank you for installing this mod!", width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Now, please pick your desired starter Pokemon!", width / 2, 20, 0xFFFFFF);
	}
}