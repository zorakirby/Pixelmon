package pixelmon.gui;

import pixelmon.ServerStorageDisplay;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.*;

public class GuiLearnMove extends GuiScreen {

	private PixelmonEntityHelper user;
	private Attack moveToLearn;

	public GuiLearnMove(PixelmonEntityHelper helper, Attack a) {
		user = helper;
		moveToLearn = a;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		controlList.clear();

		PixelmonDataPacket p = ServerStorageDisplay.get(user.getPokemonId());
		for (int i = 0; i < p.numMoves; i++) {
			controlList.add(new GuiButton(i, width / 2 - 100, height / 4 + i * 24 + 20 + 12, p.moveset[i].attackName));
		}

		controlList.add(new GuiButton(10, width / 2 - 100, height / 4 + 96 + 20 + 12, "Cancel"));
	}

	private void teachMove(int index, Attack a) {
		if (index == 10) {
			ChatHandler.sendChat(user.getOwner(), "Decided not to teach " + user.getName() + " " + a.attackName + ".");
			return;
		}
		
		PixelmonDataPacket p = ServerStorageDisplay.get(user.getPokemonId());
		ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.ReplaceMove, user.getPokemonId(), moveToLearn.attackIndex, index));
	}

	public void actionPerformed(GuiButton b) {
		teachMove(b.id, moveToLearn);
		mc.displayGuiScreen(null);
	}

	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		drawCenteredString(fontRenderer, "Your " + user.getName() + " wants to learn the move " + moveToLearn.attackName + ",", width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRenderer, "but " + user.getName() + " already knows four moves. Which move should be forgotten?", width / 2, 20, 0xFFFFFF);
	}

}