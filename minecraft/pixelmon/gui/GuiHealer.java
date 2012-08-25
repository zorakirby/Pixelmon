package pixelmon.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import pixelmon.Pixelmon;
import pixelmon.ServerStorageDisplay;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.StatCollector;

public class GuiHealer extends GuiScreen {

	private boolean isHealing = false;
	public ProgressBar[] progressBars;

	public GuiHealer() {
		progressBars = new ProgressBar[6];
		for (int i = 0; i < progressBars.length; i++)
			progressBars[i] = new ProgressBar();
	}

	public GuiButton cancelButton;

	@SuppressWarnings("unchecked")
	public void initGui() {
		int j = 0;
		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			if (p != null) {
				progressBars[j].value = (int) (((float) p.health) / ((float) p.hp) * 100f);
				j++;
			}
		}

		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8), 100, 20, StatCollector.translateToLocal("Heal")));
		cancelButton = new GuiButton(1, width / 2, (int) (height * 0.8), 100, 20, StatCollector.translateToLocal("Close"));
		controlList.add(cancelButton);
	}

	@Override
	public void updateScreen() {
		if (!isHealing)
			return;
		int num = 0;
		num = ServerStorageDisplay.count();
		for (int i = 0; i < num; i++) {
			if (progressBars[i].value < 100) {
				progressBars[i].value++;
				if (progressBars[i].value == 100) {
					int j = 0;
					for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
						if (p != null) {
							if (j == i) {
								PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.HealPokemon, p.pokemonID));
								p.health = p.hp;
							}
							j++;
						}
					}

					if (i == num - 1)
						isHealing = false;
				}
				break;
			}
		}
	}

	public void actionPerformed(GuiButton b) {
		if (b.id == 0)
			isHealing = true;
		else
			mc.displayGuiScreen(null);

	}

	public void drawScreen(int i, int i1, float f) {
		drawDefaultBackground();
		super.drawScreen(i, i1, f);
		drawCenteredString(fontRenderer, "Heal your pokemon?", width / 2, 10, 0xFFFFFF);

		int j = 0;
		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				drawPokemonStats(p, width / 2 - 100 - fontRenderer.getStringWidth(p.nickname) - 1, height * (j + 2) / 10);
				progressBars[j].draw(width / 2, height * (j + 2) / 10, 20, 200, width, height);
				if (progressBars[j].value != 100)
					drawString(fontRenderer, progressBars[j].value + " %", width / 2 + 110, height * (j + 2) / 10 + 6, 0xDDDDDD);
				else
					drawString(fontRenderer, "Healed", width / 2 + 110, height * (j + 2) / 10 + 6, 0xDDDDDD);
				j++;
			}
		}

	}

	public void drawPokemonStats(NBTTagCompound pixelmon, int x, int y) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.getString("Nickname"), x, y, 0xDDDDDD);
		drawCenteredString(fontRenderer, pixelmon.getShort("Health") + "/" + pixelmon.getInteger("StatsHP"), x + fontRenderer.getStringWidth(pixelmon.getString("Nickname")) / 2, y + 15, 0xDDDDDD);
	}

	public void drawPokemonStats(PixelmonDataPacket pixelmon, int x, int y) {
		fontRenderer.FONT_HEIGHT = 10;
		drawString(fontRenderer, pixelmon.nickname, x, y, 0xDDDDDD);
		drawCenteredString(fontRenderer, pixelmon.health + "/" + pixelmon.hp, x + fontRenderer.getStringWidth(pixelmon.nickname) / 2, y + 15, 0xDDDDDD);
	}
}
