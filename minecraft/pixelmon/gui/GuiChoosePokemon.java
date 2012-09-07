package pixelmon.gui;

import java.util.ArrayList;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import net.minecraft.src.NBTTagCompound;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.network.PacketDispatcher;

import pixelmon.ServerStorageDisplay;
import pixelmon.battles.BattleController;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.storage.PokeballManager;

public class GuiChoosePokemon extends GuiScreen {

	private BattleController bc;
	private GuiScreen parentGui;
	private PixelmonDataPacket userPacket;
	int bcIndex;

	public GuiChoosePokemon(PixelmonDataPacket userPacket, int bcIndex, GuiAttacking parentGui) {
		this.parentGui = parentGui;
		this.userPacket = userPacket;
		this.bcIndex = bcIndex;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		int i = 0;
		PixelmonDataPacket[] pokemon = ServerStorageDisplay.pokemon;
		for (PixelmonDataPacket p : pokemon) {
			if (p != null) {
				if (!p.isFainted && p.pokemonID != userPacket.pokemonID) {
					controlList.add(new GuiButton(p.order, width / 2 - 100, height / 8 + i * 24 + 20 + 12, p.nickname.equals("") ? p.name : p.nickname));
					i++;
				}
			}
		}
		controlList.add(new GuiButton(10, width / 2 - 100, height / 8 + i * 24 + 20 + 12, "Back"));
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.id < 6) {
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SwitchPokemon, par1GuiButton.id, bcIndex, 0));
			mc.displayGuiScreen(parentGui);
			mc.setIngameFocus();
		} else
			mc.displayGuiScreen(parentGui);
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}

	public void drawScreen(int i, int i1, float f) {

		drawDefaultBackground();
		super.drawScreen(i, i1, f);
		drawCenteredString(fontRenderer, "Who do you want to send out?", width / 2, 10, 0xFFFFFF);
	}
}
