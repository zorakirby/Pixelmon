package pixelmon.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import pixelmon.ServerStorageDisplay;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class GuiLearnMove extends GuiScreen {

	private int pokemonId;
	private EntityPlayer player;
	private Attack moveToLearn;
	private PixelmonDataPacket dataPacket;

	public GuiLearnMove(int x, EntityPlayer player, Attack attack) {
		pokemonId = x;
		this.player = player;
		moveToLearn = attack;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		controlList.clear();

		dataPacket = ServerStorageDisplay.get(pokemonId);
		if (dataPacket != null) {
			for (int i = 0; i < dataPacket.numMoves; i++) {
				controlList.add(new GuiButton(i, width / 2 - 100, height / 4 + i * 24 + 20 + 12, dataPacket.moveset[i].attackName));
			}
		}

		controlList.add(new GuiButton(10, width / 2 - 100, height / 4 + 96 + 20 + 12, "Cancel"));
	}

	private void teachMove(int index, Attack a) {
		if (index == 10) {
			ChatHandler.sendChat(player, "Decided not to teach " + dataPacket.name + " " + a.attackName + ".");
			return;
		}

		PixelmonDataPacket p = ServerStorageDisplay.get(pokemonId);
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ReplaceMove, pokemonId, moveToLearn.attackIndex, index));
	}

	public void actionPerformed(GuiButton b) {
		if (b.id != 10)
			teachMove(b.id, moveToLearn);
		mc.thePlayer.closeScreen();
	}

	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawCenteredString(fontRenderer, "Your " + dataPacket.name + " wants to learn the move " + moveToLearn.attackName + ",", width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "but " + dataPacket.name + " already knows four moves. Which move should be forgotten?", width / 2, 20, 0xFFFFFF);
	}

}