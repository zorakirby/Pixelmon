package pixelmon.client.gui.battles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiHelper;
import pixelmon.client.gui.GuiResources;
import pixelmon.comm.BattleQueryPacket;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.enums.EnumPokeballs;
import pixelmon.gui.ContainerEmpty;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiAcceptDeny extends GuiContainer {
	public static BattleQueryPacket opponent;
	static ResourceLocation main = new ResourceLocation("pixelmon:gui/acceptDeny/main.png");
	static ResourceLocation button = new ResourceLocation("pixelmon:gui/acceptDeny/button.png");
	static ResourceLocation buttonOver = new ResourceLocation("pixelmon:gui/acceptDeny/buttonOver.png");
	boolean accepted = false;
	int battleQueryID;

	public GuiAcceptDeny(int battleQueryID) {
		super(new ContainerEmpty());
		this.battleQueryID = battleQueryID;
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		mc.renderEngine.func_110577_a(main);
		GuiHelper.drawImageQuad((width - 280) / 2, (height - 182) / 2, 280, 182, 0, 0, 1, 1, zLevel);
		fontRenderer.drawString(mc.thePlayer.username, (width - 280) / 2 + 97, (height - 182) / 2 + 33, 0xffffff);

		int[] pokeballs1 = new int[6];
		for (int p = 0; p < 6; p++) {
			if (ServerStorageDisplay.pokemon[p] != null) {
				pokeballs1[p] = ServerStorageDisplay.pokemon[p].pokeball.getIndex();
				if (ServerStorageDisplay.pokemon[p].isFainted)
					pokeballs1[p] = pokeballs1[p] * -1 - 1;
			} else
				pokeballs1[p] = -999;
		}
		int pos = 0;
		itemRenderer.renderWithColor = false;
		for (int pid : pokeballs1) {
			if (pid != -999) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				if (pid < 0) {
					pid *= -1;
					pid--;
					GL11.glColor4f(0.4F, 0.4F, 0.4F, 1.0F);
				}
				Item pball = EnumPokeballs.getFromIndex(pid).getItem();

				itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.func_110434_K(), new ItemStack(pball), (width - 280) / 2 + 12 + pos * 12,
						(height - 182) / 2 + 75);
			}
			pos++;
		}

		GL11.glDisable(GL11.GL_LIGHTING);
		fontRenderer.drawString(opponent.opponentName, (width - 280) / 2 + 183 - fontRenderer.getStringWidth(opponent.opponentName), (height - 182) / 2 + 107,
				0xffffff);

		pos = 0;
		for (int pid : opponent.pokeballs) {
			if (pid != -999) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				if (pid < 0) {
					pid *= -1;
					pid--;
					GL11.glColor4f(0.4F, 0.4F, 0.4F, 1.0F);
				}
				Item pball = EnumPokeballs.getFromIndex(pid).getItem();
				itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.func_110434_K(), new ItemStack(pball), (width - 280) / 2 + 188 + pos * 12,
						(height - 182) / 2 + 56);
			}
			pos++;
		}
		itemRenderer.renderWithColor = true;
		GL11.glDisable(GL11.GL_LIGHTING);

		mc.renderEngine.func_110577_a(buttonOver);
		if (accepted)
			GuiHelper.drawImageQuad((width - 280) / 2 + 20, (height - 182) / 2 + 147, 110, 30, 0, 0, 1, 1, zLevel);
		mc.renderEngine.func_110577_a(button);
		GuiHelper.drawImageQuad((width - 280) / 2 + 25, (height - 182) / 2 + 152, 100, 20, 0, 0, 1, 1, zLevel);
		GuiHelper.drawImageQuad((width - 280) / 2 + 145, (height - 182) / 2 + 152, 100, 20, 0, 0, 1, 1, zLevel);

		mc.renderEngine.func_110577_a(buttonOver);
		if (mouseX > (width - 280) / 2 + 25 && mouseX < (width - 280) / 2 + 25 + 100 && !accepted)
			if (mouseY > (height - 182) / 2 + 152 && mouseY < (height - 182) / 2 + 152 + 20) {
				GuiHelper.drawImageQuad((width - 280) / 2 + 25, (height - 182) / 2 + 152, 100, 20, 0, 0, 1, 1, zLevel);
			}
		if (mouseX > (width - 280) / 2 + 145 && mouseX < (width - 280) / 2 + 145 + 100)
			if (mouseY > (height - 182) / 2 + 152 && mouseY < (height - 182) / 2 + 152 + 20) {
				GuiHelper.drawImageQuad((width - 280) / 2 + 145, (height - 182) / 2 + 152, 100, 20, 0, 0, 1, 1, zLevel);
			}
		fontRenderer.drawString("Accept", (width - 280) / 2 + 75 - fontRenderer.getStringWidth("Accept") / 2, (height - 182) / 2 + 158, 0xffffff);
		fontRenderer.drawString("Decline", (width - 280) / 2 + 195 - fontRenderer.getStringWidth("Decline") / 2, (height - 182) / 2 + 158, 0xffffff);

		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected void mouseClicked(int par1, int mouseX, int mouseY) {
		if (mouseX > (width - 280) / 2 + 25 && mouseX < (width - 280) / 2 + 25 + 100 && !accepted)
			if (mouseY > (height - 182) / 2 + 152 && mouseY < (height - 182) / 2 + 152 + 20) {
				accepted = true;
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.AcceptBattle, battleQueryID));
			}
		if (mouseX > (width - 280) / 2 + 145 && mouseX < (width - 280) / 2 + 145 + 100)
			if (mouseY > (height - 182) / 2 + 152 && mouseY < (height - 182) / 2 + 152 + 20) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.DeclineBattle, battleQueryID));
				mc.thePlayer.closeScreen();
			}
	}

}
