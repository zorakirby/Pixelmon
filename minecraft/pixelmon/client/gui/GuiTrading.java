package pixelmon.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.Pixelmon;
import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.client.gui.battles.GuiBattle;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.enums.EnumGui;
import pixelmon.gui.ContainerEmpty;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiTrading extends GuiContainer {


	private int tradeIndex = -1;
	public boolean ready = false;
	private int selected = -1;

	public GuiTrading(int tradeIndex) {
		super(new ContainerEmpty());
		this.tradeIndex = tradeIndex;
		ClientTradingManager.reset();
	}

	protected void drawEntity(EntityLivingBase entity, int par1, int par2, int par3, float par4, float par5) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par1, (float) par2, 50.0F);
		GL11.glScalef((float) (-par3), (float) par3, (float) par3);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float var6 = entity.renderYawOffset;
		float var7 = entity.rotationYaw;
		float var8 = entity.rotationPitch;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan((double) (par4 / 40.0F)) * 20.0F;
		entity.rotationYaw = (float) Math.atan((double) (par4 / 40.0F)) * 40.0F;
		entity.rotationPitch = -((float) Math.atan((double) (par5 / 40.0F))) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		entity.renderYawOffset = var6;
		entity.rotationYaw = var7;
		entity.rotationPitch = var8;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	public void onGuiClosed() {
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.DeRegisterTrader, 0));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();

		mc.renderEngine.bindTexture(GuiResources.tradeGui);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect((width - 256) / 2, (height - 206) / 2, 0, 0, 256, 204);

		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				String displayName = p.name;
				if (!p.nickname.equals(""))
					displayName = p.nickname;

				int i = p.order;
				String numString = "";
				if (p.getNationalPokedexNumber() < 10)
					numString = "00" + p.getNationalPokedexNumber();
				else if (p.getNationalPokedexNumber() < 100)
					numString = "0" + p.getNationalPokedexNumber();
				else
					numString = "" + p.getNationalPokedexNumber();

				if (p.isShiny)
					Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.shinySprite(numString));
				else
					Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.sprite(numString));
				drawImageQuad(width / 2 - 93 + 25 * i, height / 2 + 68, 24f, 24f, 0f, 0f, 1f, 1f);
				if (p.heldItemId != -1) {
					Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.heldItem);
					drawImageQuad(width / 2 - 97 + 25 * i + 18, height / 2 + 68 + 18, 6, 6, 0f, 0f, 1f, 1f);
				}
			}
		}

		/** Characters **/

		if (Minecraft.getMinecraft().thePlayer != null)
			drawEntity(Minecraft.getMinecraft().thePlayer, (width - 210) / 2, (height - 82) / 2, 20, 0, 0);
		else {
			GL11.glPushMatrix();
			mc.renderEngine.bindTexture(GuiResources.tradeGui);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			drawTexturedModalRect((width - 75), (height - 265), 227, 242, 10, 14);
			GL11.glPopMatrix();
		}
		if (ClientTradingManager.tradePartner != null)
			drawEntity(ClientTradingManager.tradePartner, (width + 60) / 2, (height - 82) / 2, 20, 0, 0);
		else {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			mc.renderEngine.bindTexture(GuiResources.tradeGui);
			drawTexturedModalRect(width / 3 + 15, height / 3 - 46, 227, 242, 10, 14);
			GL11.glPopMatrix();
		}

		/** Stats **/

		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0f);

		drawString(fontRenderer, "HP:", (width + 87), (height - 156), 0xFFFFFF);
		drawString(fontRenderer, "Attack:", (width + 87), (height - 140), 0xFFFFFF);
		drawString(fontRenderer, "Defence:", (width + 87), (height - 124), 0xFFFFFF);
		drawString(fontRenderer, "SP.Attack:", (width + 87), (height - 109), 0xFFFFFF);
		drawString(fontRenderer, "SP.Defense:", (width + 87), (height - 93), 0xFFFFFF);
		drawString(fontRenderer, "Speed:", (width + 87), (height - 77), 0xFFFFFF);
		if (ClientTradingManager.tradeTargetStats != null) {
			drawString(fontRenderer, String.valueOf(ClientTradingManager.tradeTargetStats.HP), (width + 223), (height - 156), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.tradeTargetStats.Attack), (width + 223), (height - 140), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.tradeTargetStats.Defence), (width + 223), (height - 124), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.tradeTargetStats.SpecialAttack), (width + 223), (height - 109), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.tradeTargetStats.SpecialDefence), (width + 223), (height - 93), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.tradeTargetStats.Speed), (width + 223), (height - 77), 0xFFFFFF);
		} else
			for (int i = 0; i < 6; i++) {
				drawString(fontRenderer, "?", (width + 223), (height - 156) + i * 16, 0xFFFFFF);
			}
		drawString(fontRenderer, "HP:", (width - 183), (height - 156), 0xFFFFFF);
		drawString(fontRenderer, "Attack:", (width - 183), (height - 140), 0xFFFFFF);
		drawString(fontRenderer, "Defence:", (width - 183), (height - 124), 0xFFFFFF);
		drawString(fontRenderer, "SP.Attack:", (width - 183), (height - 109), 0xFFFFFF);
		drawString(fontRenderer, "SP.Defense:", (width - 183), (height - 93), 0xFFFFFF);
		drawString(fontRenderer, "Speed:", (width - 183), (height - 77), 0xFFFFFF);
		if (ClientTradingManager.selectedStats != null) {
			drawString(fontRenderer, String.valueOf(ClientTradingManager.selectedStats.HP), (width - 47), (height - 156), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.selectedStats.Attack), (width - 47), (height - 140), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.selectedStats.Defence), (width - 47), (height - 124), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.selectedStats.SpecialAttack), (width - 47), (height - 109), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.selectedStats.SpecialDefence), (width - 47), (height - 93), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(ClientTradingManager.selectedStats.Speed), (width - 47), (height - 77), 0xFFFFFF);
		} else
			for (int i = 0; i < 6; i++) {
				drawString(fontRenderer, "?", (width - 47), (height - 156) + i * 16, 0xFFFFFF);
			}
		GL11.glPopMatrix();

		if (selected != -1 && ServerStorageDisplay.pokemon[selected] != null) {
			PixelmonDataPacket p = ServerStorageDisplay.pokemon[selected];
			String displayName = p.name;
			if (!p.nickname.equals(""))
				displayName = p.nickname;

			String numString = "";
			if (p.getNationalPokedexNumber() < 10)
				numString = "00" + p.getNationalPokedexNumber();
			else if (p.getNationalPokedexNumber() < 100)
				numString = "0" + p.getNationalPokedexNumber();
			else
				numString = "" + p.getNationalPokedexNumber();
			if (p.isShiny)
				Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.shinySprite(numString));
			else
				Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.sprite(numString));
			drawImageQuad(width / 2 - 117, height / 2 - 33, 24f, 24f, 0f, 0f, 1f, 1f);
			if (p.heldItemId != -1) {
				Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.heldItem);
				drawImageQuad(width / 2 - 117 + 18, height / 2 - 33 + 18, 6, 6, 0f, 0f, 1f, 1f);
			}
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0f);
			drawCenteredString(fontRenderer, p.nickname.equals("") ? p.name : p.nickname, (width - 212), (height - 10), 0xFFFFFF);
			fontRenderer.drawSplitString(p.getDescription(), (width - 180), (height - 55), 150, 0xFFFFFF);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			mc.renderEngine.bindTexture(GuiResources.tradeGui);
			drawTexturedModalRect(width / 3 - 75, height / 3 - 20, 227, 242, 10, 14);
			GL11.glPopMatrix();
		}

		if (ClientTradingManager.tradeTarget != null) {
			PixelmonDataPacket p = ClientTradingManager.tradeTarget;
			String displayName = p.name;
			if (!p.nickname.equals(""))
				displayName = p.nickname;

			String numString = "";
			if (p.getNationalPokedexNumber() < 10)
				numString = "00" + p.getNationalPokedexNumber();
			else if (p.getNationalPokedexNumber() < 100)
				numString = "0" + p.getNationalPokedexNumber();
			else
				numString = "" + p.getNationalPokedexNumber();
			if (p.isShiny)
				Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.shinySprite(numString));
			else
				Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.sprite(numString));
			drawImageQuad(width / 2 + 18, height / 2 - 33, 24f, 24f, 0f, 0f, 1f, 1f);
			if (p.heldItemId != -1) {
				Minecraft.getMinecraft().renderEngine.bindTexture(GuiResources.heldItem);
				drawImageQuad(width / 2 + 18 + 18, height / 2 - 33 + 18, 6, 6, 0f, 0f, 1f, 1f);
			}
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0f);
			drawCenteredString(fontRenderer, p.nickname.equals("") ? p.name : p.nickname, (width + 58), (height - 10), 0xFFFFFF);
			fontRenderer.drawSplitString(p.getDescription(), (width + 90), (height - 55), 150, 0xFFFFFF);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			mc.renderEngine.bindTexture(GuiResources.tradeGui);
			drawTexturedModalRect(width / 3 + 15, height / 3 - 20, 227, 242, 10, 14);
			GL11.glPopMatrix();
		}

		/** Buttons **/

		drawString(fontRenderer, "Ready", (width + 130) / 2, (height + 157) / 2, 0xFFFFFF);
		drawButtonReady(var2, var3);

		if (ClientTradingManager.player1Ready && ClientTradingManager.player2Ready) {
			drawString(fontRenderer, "Trade", (width - 30) / 2, (height + 38) / 2, 0xFFFFFF);
			drawButtonTrade(var2, var3);
		} else
			drawString(fontRenderer, "Not Ready", (width - 45) / 2, (height + 38) / 2, 0xFFFFFF);

		drawPokemonSelection(var2, var3);

		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0f);
		drawString(fontRenderer, Minecraft.getMinecraft().thePlayer.username + " wants to trade", (width - 235), (height - 178), 0xFFFFFF);
		if (ClientTradingManager.tradePartner != null)
			drawString(fontRenderer, ClientTradingManager.tradePartner.username + " wants to trade", (width + 35), (height - 178), 0xFFFFFF);
		else
			drawString(fontRenderer, "No user found!", (width + 35), (height - 178), 0xFFFFFF);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(GuiResources.tradeGui);
		if (ClientTradingManager.tradePartner != null && !ClientTradingManager.player2Ready) {
			GL11.glColor3f(1f, 0f, 0f);
			drawTexturedModalRect((width + 45) / 2, (height + 85) / 2, 61, 242, 90, 14);
		} else if (!ClientTradingManager.player2Ready) {
			GL11.glColor3f(1f, 0f, 0f);
			drawTexturedModalRect((width + 65) / 2, (height + 85) / 2, 153, 242, 72, 14);
		} else if (ClientTradingManager.player2Ready) {
			GL11.glColor3f(0f, 1f, 0f);
			drawTexturedModalRect((width + 75) / 2, (height + 85) / 2, 1, 242, 58, 14);
		}

		if (!ClientTradingManager.player1Ready) {
			GL11.glColor3f(1f, 0f, 0f);
			drawTexturedModalRect((width - 225) / 2, (height + 85) / 2, 61, 242, 90, 14);
		} else if (ClientTradingManager.player1Ready) {
			GL11.glColor3f(0f, 1f, 0f);
			drawTexturedModalRect((width - 195) / 2, (height + 85) / 2, 1, 242, 58, 14);
		}
		GL11.glPopMatrix();

		if (selected != -1) {
			GL11.glPushMatrix();
			GL11.glColor3f(0f, 1.0f, 0f);
			mc.renderEngine.bindTexture(GuiResources.tradeGui);
			drawTexturedModalRect((width - 190 + selected * 50) / 2, (height + 140) / 2, 1, 206, 26, 24);
			GL11.glPopMatrix();
		}

		drawButtonClose(var2, var3);

		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0f);
		fontRenderer.drawSplitString("Select a Pokemon", (width / 2 - 118) * 2, (height / 2 + 77) * 2, 50, 0xFFFFFF);
		GL11.glPopMatrix();
	}

	PixelmonDataPacket[] p = ServerStorageDisplay.pokemon;

	public int drawPokemonSelection(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiResources.tradeGui);
		if (par1 >= (width - 190) / 2 && par1 <= (width - 140) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[0] != null) {
			drawTexturedModalRect((width - 190) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 0;
		}
		if (par1 >= (width - 140) / 2 && par1 <= (width - 90) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[1] != null) {
			drawTexturedModalRect((width - 140) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 1;
		}
		if (par1 >= (width - 90) / 2 && par1 <= (width - 40) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[2] != null) {
			drawTexturedModalRect((width - 90) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 2;
		}
		if (par1 >= (width - 40) / 2 && par1 <= (width + 10) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[3] != null) {
			drawTexturedModalRect((width - 40) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 3;
		}
		if (par1 >= (width + 10) / 2 && par1 <= (width + 60) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[4] != null) {
			drawTexturedModalRect((width + 10) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 4;
		}
		if (par1 >= (width + 60) / 2 && par1 <= (width + 110) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[5] != null) {
			drawTexturedModalRect((width + 60) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 5;
		}

		return -1;
	}

	public boolean drawButtonTrade(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiResources.tradeGui);

		if (par1 >= (width - 72) / 2 && par1 <= (width + 70) / 2 && par2 >= (height + 26) / 2 && par2 <= (height + 62) / 2) {
			drawTexturedModalRect((width - 72) / 2, (height + 26) / 2, 28, 205, 72, 19);
			drawString(fontRenderer, "Trade", (width - 30) / 2, (height + 38) / 2, 16777120);
			return true;
		}

		return false;
	}

	public boolean drawButtonClose(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiResources.tradeGui);

		if (par1 >= (width + 213) / 2 && par1 <= (width + 248) / 2 && par2 <= (height + 199) / 2 && par2 >= (height + 170) / 2) {// Highlight
			drawTexturedModalRect((width + 214) / 2, (height + 170) / 2, 67, 225, 17, 15);
			return true;
		}

		return false;
	}

	public boolean drawButtonReady(int par1, int par2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiResources.tradeGui);
		if (par1 >= (width + 123) / 2 && par1 <= (width + 197) / 2 && par2 <= (height + 179) / 2 && par2 >= (height + 148) / 2) {// Highlight
			drawTexturedModalRect((width + 122) / 2, (height + 148) / 2, 28, 225, 38, 16);
			drawString(fontRenderer, "Ready", (width + 130) / 2, (height + 157) / 2, 16777120);
			return true;
		}

		return false;
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		if (drawButtonReady(par1, par2) && selected >= 0) {
			if (ClientTradingManager.tradeTarget != null) {
				this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
				ClientTradingManager.player1Ready = !ClientTradingManager.player1Ready;
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SetTradingReady, ClientTradingManager.player1Ready ? 1 : 0));
			}
		}
		if (drawButtonTrade(par1, par2)) {
			if (ClientTradingManager.player1Ready && ClientTradingManager.player2Ready) {
				this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Trade, 0));
			}
		}
		if (drawPokemonSelection(par1, par2) != -1) {
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			this.selected = drawPokemonSelection(par1, par2);
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SelectPokemonForTrade, selected));
		}
		if (drawButtonClose(par1, par2)) {
			this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
			mc.thePlayer.closeScreen();
			if (GuiBattle.evolveList.size() > 0) {
				int pokemonID = GuiBattle.evolveList.get(0);
				GuiBattle.evolveList.remove(0);
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
			}
		}
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
	}

	private void drawImageQuad(int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
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

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);

	}
}