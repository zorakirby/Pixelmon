package pixelmon.client.gui.pc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiPixelmonOverlay;
import pixelmon.client.render.GraphicsHelper;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.grethen.TradeHandler;
import pixelmon.gui.ContainerEmpty;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiTrading extends GuiContainer {

	private SlotPC mouseSlot;
	private SlotPCParty[] partySlots = new SlotPCParty[6];
	private TradeHandler tradeHandler;

	public GuiTrading(PlayerStorage p1, PlayerStorage p2) 
	{
		super(new ContainerEmpty());
		tradeHandler = new TradeHandler(p1, p2);
		//boxNumber = 0;
	}

	public void initGui() {
		super.initGui();
		controlList.clear();
		mouseSlot = new SlotPC(0, 0, (PixelmonDataPacket) null);
		for (int i = 0; i < 6; i++) {
			int x = i;
			x *= 30;
			x += width / 2 - 90;
			int y = height / 6 + 145;
			partySlots[i] = new SlotPCParty(x, y, i);
			PixelmonDataPacket p = ServerStorageDisplay.pokemon[i];
			if (p != null) {
				partySlots[i].setPokemon(p);
			}
		}
		sort();

	}

	public void sort() {
		for (int i = 0; i < partySlots.length; i++) {
			PixelmonDataPacket p = partySlots[i].pokemonData;
			if (p != null) {
				int pos = p.order;
				partySlots[i].setPokemon((PixelmonDataPacket) null);
				partySlots[pos].setPokemon(p);
			}
		}
	}

	public SlotPCParty getSlotAt(int x, int y) {
		for (SlotPCParty slot : partySlots) {
			if (slot.getBounds().contains(x, y)) {
				return slot;
			}
		}
		return null;
	}

	public boolean checkIfLast() {
		int i = 0;
		for (SlotPC slot : partySlots) {
			if (slot.pokemonData != null)
				i++;
		}
		if (i == 1) {
			return true;
		}
		return false;
	}

	protected void mouseClicked(int par1, int par2, int par3) 
	{
		super.mouseClicked(par1, par2, par3);
		SlotPCParty slot = getSlotAt(par1, par2);
		if (slot != null) 
		{
			PixelmonDataPacket temp = slot.pokemonData;
			mouseSlot.pokemonData = temp;
			if(par3 == 0)
			{
				EntityPixelmon ep = (EntityPixelmon) PixelmonEntityList.createEntityFromNBT(tradeHandler.players[0].getNBT(getSlotID(slot)), mc.theWorld);
				tradeHandler.setCurrentOffer(0, ep);
			} else if (mouseSlot.pokemonData != null) 
			{
				goingToPokeChecker = true;
				mc.displayGuiScreen(new GuiScreenPokeCheckerPC(mouseSlot.pokemonData, this, 0, 0));
			}
		}
	}
	
	private int getSlotID(SlotPCParty p)
	{
		for(int i = 0; i < partySlots.length; i++)
			if(p == partySlots[i])
				return i;
		return -1;
	}
	
	private boolean goingToPokeChecker = false;

	public void onGuiClosed() {
		super.onGuiClosed();
		if (mouseSlot.pokemonData != null) {
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.PCClick, -4));
		}
		mouseSlot.pokemonData = null;
		if (!goingToPokeChecker) {
			PixelmonServerStore.store.clear();

			GuiPixelmonOverlay.checkSelection();
		}
		goingToPokeChecker = false;

	}

	
	public void actionPerformed(GuiButton button) 
	{	
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		//float var7 = 0.00390625F;
		//float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) this.zLevel, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) this.zLevel, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) this.zLevel, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		//int w = width;
		//int h = height;
		
		RenderHelper.disableStandardItemLighting();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int partyTexture = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/gui/pcPartyBox.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(partyTexture);
		drawTexturedModalRect(width / 2 - 91, height / 6 + 151, 0, 0, 182, 29);
		//int i = 0;
		partyTexture = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/gui/pcBox.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(partyTexture);
		drawTexturedModalRect(width / 2 - 91, height / 6, 0, 0, 182, 141);
		int image = 0;
		for (int a = 0; a < partySlots.length; a++) {
			image = 0;
			SlotPCParty slot = partySlots[a];
			slot.setPokemon(ServerStorageDisplay.pokemon[a]);
			if (slot.pokemonData == null) {
				continue;
			}
			String numString = "";
			if (slot.pokemonData.getNationalPokedexNumber() < 10)
				numString = "00" + slot.pokemonData.getNationalPokedexNumber();
			else if (slot.pokemonData.getNationalPokedexNumber() < 100)
				numString = "0" + slot.pokemonData.getNationalPokedexNumber();
			else
				numString = "" + slot.pokemonData.getNationalPokedexNumber();
			if (slot.pokemonData.isShiny)
				image = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
			else
				image = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
			drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			if (slot.pokemonData.heldItemId != -1) {
				drawImageQuad(mc.renderEngine.getTexture("/pixelmon/image/pitems.png"), slot.x + 22, slot.y + 22, 8, 8, 0, 0, 16f / 256f, 16f / 256f);
			}
		}
		image = 0;
		if (mouseSlot.pokemonData != null) {
			PixelmonDataPacket p = mouseSlot.pokemonData;
			String numString = "";
			if (p.getNationalPokedexNumber() < 10)
				numString = "00" + p.getNationalPokedexNumber();
			else if (p.getNationalPokedexNumber() < 100)
				numString = "0" + p.getNationalPokedexNumber();
			else
				numString = "" + p.getNationalPokedexNumber();
			if (p.isShiny)
				image = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
			else
				image = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
			drawImageQuad(image, mouseSlot.x, mouseSlot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			if (mouseSlot.pokemonData.heldItemId != -1) {
				drawImageQuad(mc.renderEngine.getTexture("/pixelmon/image/pitems.png"), mouseSlot.x + 22, mouseSlot.y + 22, 8, 8, 0, 0, 16f / 256f, 16f / 256f);
			}
			if (p.nickname == null || p.nickname.equalsIgnoreCase("")) {
				p.nickname = p.name;
			}
			fontRenderer.drawString(p.nickname, mouseSlot.x + 30, mouseSlot.y + 10, 0xFFFFFF);
			partyTexture = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlay.png");
			Minecraft.getMinecraft().renderEngine.bindTexture(partyTexture);
			if (p.isMale)
				drawTexturedModalRect(fontRenderer.getStringWidth(p.nickname) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 208, 5, 9);
			else
				drawTexturedModalRect(fontRenderer.getStringWidth(p.nickname) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 218, 5, 9);
			fontRenderer.drawString("Lvl " + p.lvl, mouseSlot.x + 30, mouseSlot.y + 20, 0xFFFFFF);
			if (p.isFainted) {
				fontRenderer.drawString("Fainted", mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + p.lvl), mouseSlot.y + 20, 0xFFFFFF);
			} else {
				fontRenderer.drawString("HP " + p.health + "/" + p.hp, mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + p.lvl), mouseSlot.y + 20, 0xFFFFFF);
			}
			if(tradeHandler.getCurrentOffer(0) != null)
				GraphicsHelper.drawModelToScreen(1, 10, 10, width / 2, height / 2, tradeHandler.getCurrentOffer(0), this, true);
			if(tradeHandler.getCurrentOffer(0) != null)
				GraphicsHelper.drawModelToScreen(1, 10, 10, width / 2, height / 2, tradeHandler.getCurrentOffer(1), this, true);
		}

	}

}
