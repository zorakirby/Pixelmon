package pixelmon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import pixelmon.battles.attacks.Attack;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.ContainerEmpty;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiLearnMove extends GuiContainer {

	private int pokemonId;
	private EntityPlayer player;
	private Attack moveToLearn;
	private PixelmonDataPacket dataPacket;

	public GuiLearnMove(int x, EntityPlayer player, Attack attack) {
		super(new ContainerEmpty());
		pokemonId = x;
		this.player = player;
		moveToLearn = attack;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		buttonList.clear();

		dataPacket = ServerStorageDisplay.get(pokemonId);
		if (dataPacket != null) {
			for (int i = 0; i < dataPacket.numMoves; i++) {
				buttonList.add(new GuiButton(i, width / 2 - 100, height / 4 + i * 24 + 20 + 12, dataPacket.moveset[i].attackName));
			}
		}

		buttonList.add(new GuiButton(10, width / 2 - 100, height / 4 + 96 + 20 + 12, "Cancel"));
	}

	private void teachMove(int index, Attack a) {
		if (index == 10) {
			ChatHandler.sendChat(player, "Decided not to teach " + dataPacket.name + " " + a.baseAttack.attackName + ".");
			return;
		}

		PixelmonDataPacket p = ServerStorageDisplay.get(pokemonId);
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ReplaceMove, pokemonId, moveToLearn.baseAttack.attackIndex, index));
	}

	public void actionPerformed(GuiButton b) {
		if (b.id != 10)
			teachMove(b.id, moveToLearn);
		mc.thePlayer.closeScreen();
	}

	public void drawGuiContainerBackgroundLayer(float par3, int par1, int par2) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, "Your " + dataPacket.name + " wants to learn the move " + moveToLearn.baseAttack.attackName + ",", width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "but " + dataPacket.name + " already knows four moves. Which move should be forgotten?", width / 2, 20, 0xFFFFFF);
	}

}