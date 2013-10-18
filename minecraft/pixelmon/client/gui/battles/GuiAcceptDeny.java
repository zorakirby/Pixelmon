package pixelmon.client.gui.battles;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;

import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiHelper;
import pixelmon.client.gui.GuiResources;
import pixelmon.comm.BattleQueryPacket;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.enums.EnumPokeballs;
import pixelmon.gui.ContainerEmpty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiAcceptDeny extends GuiContainer {
	public static BattleQueryPacket opponent;
	static ResourceLocation topLeft = new ResourceLocation("pixelmon:gui/acceptDeny/Opponent1.png");
	static ResourceLocation middle = new ResourceLocation("pixelmon:gui/acceptDeny/Opponent2.png");
	static ResourceLocation bottomRight = new ResourceLocation("pixelmon:gui/acceptDeny/Opponent3.png");
	static ResourceLocation vs = new ResourceLocation("pixelmon:gui/acceptDeny/Opponent8.png");
	static ResourceLocation player1Frame = new ResourceLocation("pixelmon:gui/acceptDeny/player1Frame.png");
	static ResourceLocation player2Frame = new ResourceLocation("pixelmon:gui/acceptDeny/player2Frame.png");
	static ResourceLocation player1Name = new ResourceLocation("pixelmon:gui/acceptDeny/player1Name.png");
	static ResourceLocation player2Name = new ResourceLocation("pixelmon:gui/acceptDeny/player2Name.png");
	static ResourceLocation pokeballHolder = new ResourceLocation("pixelmon:gui/acceptDeny/pokeballHolder.png");
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

	int ticks = 5;

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (ticks > 0)
			ticks--;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		mc.renderEngine.bindTexture(topLeft);
		GuiHelper.drawImageQuad((width - 280) / 2 - ticks * 50, (height - 182) / 2, 280, 182, 0, 0, 1, 1, zLevel);
		mc.renderEngine.bindTexture(middle);
		GuiHelper.drawImageQuad((width - 280) / 2 + ticks * 50, (height - 182) / 2, 280, 182, 0, 0, 1, 1, zLevel);
		mc.renderEngine.bindTexture(bottomRight);
		GuiHelper.drawImageQuad((width - 280) / 2, (height - 182) / 2, 280, 182, 0, 0, 1, 1, zLevel);
		if (ticks == 0) {
			mc.renderEngine.bindTexture(vs);
			GuiHelper.drawImageQuad((width - 280) / 2, (height - 182) / 2, 280, 226, 0, 0, 1, 1, zLevel);
		}

		// Player 1
		mc.renderEngine.bindTexture(player1Name);
		GuiHelper.drawImageQuad((width - 280) / 2 + 76 - ticks * 50, (height - 182) / 2 + 9, 145, 17, 0, 0, 1, 1, zLevel);
		drawEntity(Minecraft.getMinecraft().thePlayer, (width - 280) / 2 + 19 - ticks * 50, (height - 182) / 2 + 11, 20, 0, 0);
		mc.renderEngine.bindTexture(player1Frame);
		GuiHelper.drawImageQuad((width - 280) / 2 + 16 - ticks * 50, (height - 182) / 2 + 9, 65, 65, 0, 0, 1, 1, zLevel);
		mc.renderEngine.bindTexture(pokeballHolder);
		GuiHelper.drawImageQuad((width - 280) / 2 + 82 - ticks * 50, (height - 182) / 2 + 27, 80, 17, 0, 0, 1, 1, zLevel);
		fontRenderer.drawString(mc.thePlayer.username, (width - 280) / 2 + 92 - ticks * 50, (height - 182) / 2 + 15, 0xffffff);

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

				itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(pball), (width - 280) / 2 + 84 + pos * 12
						- ticks * 50, (height - 182) / 2 + 27);
			}
			pos++;
		}

		GL11.glDisable(GL11.GL_LIGHTING);

		// Player2
		mc.renderEngine.bindTexture(player2Name);
		GuiHelper.drawImageQuad((width - 280) / 2 + 56 + ticks * 50, (height - 182) / 2 + 103, 145, 17, 0, 0, 1, 1, zLevel);
		drawEntity(mc.theWorld.getPlayerEntityByName(opponent.opponentName), (width - 280) / 2 + 202 + ticks * 50, (height - 182) / 2 + 76, 20, 0, 0);
		mc.renderEngine.bindTexture(player2Frame);
		GuiHelper.drawImageQuad((width - 280) / 2 + 199 + ticks * 50, (height - 182) / 2 + 74, 65, 65, 0, 0, 1, 1, zLevel);
		mc.renderEngine.bindTexture(pokeballHolder);
		GuiHelper.drawImageQuad((width - 280) / 2 + 118 + ticks * 50, (height - 182) / 2 + 121, 80, 17, 0, 0, 1, 1, zLevel);
		fontRenderer.drawString(opponent.opponentName, (width - 280) / 2 + 188 - fontRenderer.getStringWidth(opponent.opponentName) + ticks * 50,
				(height - 182) / 2 + 108, 0xffffff);
		// fontRenderer.drawString("Francis", (width - 280) / 2 + 188 -
		// fontRenderer.getStringWidth("Francis") + ticks * 50, (height - 182) /
		// 2 + 108, 0xffffff);

		pos = 0;
		for (int pid : opponent.pokeballs) {
			// for (int pid : pokeballs1) {
			if (pid != -999) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				if (pid < 0) {
					pid *= -1;
					pid--;
					GL11.glColor4f(0.4F, 0.4F, 0.4F, 1.0F);
				}
				Item pball = EnumPokeballs.getFromIndex(pid).getItem();
				itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(pball), (width - 280) / 2 + 120 + pos
						* 12 + ticks * 50, (height - 182) / 2 + 121);
			}
			pos++;
		}
		itemRenderer.renderWithColor = true;
		GL11.glDisable(GL11.GL_LIGHTING);

		if (ticks == 0) {
			mc.renderEngine.bindTexture(buttonOver);
			if (accepted)
				GuiHelper.drawImageQuad((width - 280) / 2 + 20, (height - 182) / 2 + 147, 110, 30, 0, 0, 1, 1, zLevel);
			mc.renderEngine.bindTexture(button);
			GuiHelper.drawImageQuad((width - 280) / 2 + 25, (height - 182) / 2 + 152, 100, 20, 0, 0, 1, 1, zLevel);
			GuiHelper.drawImageQuad((width - 280) / 2 + 145, (height - 182) / 2 + 152, 100, 20, 0, 0, 1, 1, zLevel);

			mc.renderEngine.bindTexture(buttonOver);
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
		}
		GL11.glDisable(GL11.GL_BLEND);
	}

	protected void drawEntity(EntityLivingBase entity, int par1, int par2, int par3, float par4, float par5) {
		RenderManager.instance.renderEngine.bindTexture(((AbstractClientPlayer) entity).getLocationSkin());
		GuiHelper.drawImageQuad(par1, par2, 60, 60, 0.11f, 0.26f, 0.26f, 0.49f, this.zLevel);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int par3) {
		if (ticks > 0)
			return;
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
