package pixelmon.client.gui.pc;

import java.awt.Rectangle;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiPixelmonOverlay;
import pixelmon.client.gui.GuiResources;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.ContainerEmpty;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PlayerComputerStorage;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiPC extends GuiContainer {

	private int boxNumber, trashX, trashY, checkX, checkY;
	private SlotPCPC[][] pcSlots = new SlotPCPC[PlayerComputerStorage.boxCount][ComputerBox.boxLimit];
	private SlotPCParty[] partySlots = new SlotPCParty[6];

	public GuiPC() {
		super(new ContainerEmpty());
		boxNumber = 0;
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.RequestPCData));
	}

	public GuiPC(PixelmonDataPacket targetPacket) {
		this();
		mouseHeldPokemon = targetPacket;
	}

	public void initGui() {
		super.initGui();
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 131, height / 6 + 60, 20, 20, "<-"));
		buttonList.add(new GuiButton(1, width / 2 - 91 + 205, height / 6 + 60, 20, 20, "->"));
		trashX = width / 2 - 91 + 202;
		trashY = height / 6 + 150;
		checkY = trashY;
		checkX = width / 2 - 140;

		for (int i = 0; i < PlayerComputerStorage.boxCount; i++) {
			for (int j = 0; j < ComputerBox.boxLimit; j++) {
				int x = j % 6;
				int y = j / 6;
				x *= 30;
				y *= 28;
				x += width / 2 - 90;
				y += height / 6 - 5;
				pcSlots[i][j] = new SlotPCPC(x, y, i, j);
				PixelmonDataPacket p = null;

				p = PixelmonServerStore.getFromBox(i, j);

				if (p != null) {
					pcSlots[i][j].setPokemon(p);
				}

			}
		}
		for (int i = 0; i < 6; i++) {
			int x = i;
			x *= 30;
			x += width / 2 - 90;
			int y = height / 6 + 147;
			partySlots[i] = new SlotPCParty(x, y, i);
			PixelmonDataPacket p = ServerStorageDisplay.pokemon[i];
			if (p != null) {
				partySlots[i].setPokemon(p);
			}
		}
	}

	public SlotPC getSlotAt(int x, int y) {
		for (SlotPCPC slot : pcSlots[boxNumber]) {
			if (slot.getBounds().contains(x, y)) {
				return slot;
			}
		}
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

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		if (par3 == 0) {
			SlotPC slot = getSlotAt(par1, par2);
			if (slot != null) {

				boolean changed = false;
				PixelmonDataPacket temp = slot.pokemonData;
				if ((slot instanceof SlotPCParty && PixelmonServerStore.getMousePokemon() == null && checkIfLast())) {
					return;
				}
				if (slot.pokemonData != null) {
					changed = true;
				}
				if (PixelmonServerStore.getMousePokemon() != null) {
					changed = true;
				}
				if (changed) {
					if (slot instanceof SlotPCParty) {
						int pos = ((SlotPCParty) slot).partyPosition;
						PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.PCClickOnParty, pos));
					}
					if (slot instanceof SlotPCPC) {
						int boxNumber = ((SlotPCPC) slot).boxNumber;
						int pos = ((SlotPCPC) slot).boxPosition;
						PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.PCClickOnBox, this.boxNumber, pos));
					}
				}
				return;

			} else if (new Rectangle(trashX, trashY, 32, 32).contains(par1, par2)) {
				if (PixelmonServerStore.getMousePokemon() != null) {
					PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.PCTrashPokemon));
				}

				return;
			} else if (new Rectangle(checkX, checkY, 32, 32).contains(par1, par2)) {
				if (PixelmonServerStore.getMousePokemon() != null) {
					goingToPokeChecker = true;
					mc.displayGuiScreen(new GuiScreenPokeCheckerPC(PixelmonServerStore.getMousePokemon(), 0, 0));
				}
				return;

			} else {
				return;
			}
		}
	}

	public PixelmonDataPacket mouseHeldPokemon = null;

	private boolean goingToPokeChecker = false;

	public void onGuiClosed() {
		super.onGuiClosed();
		if (goingToPokeChecker)
			return;
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.PCClosed));
		PixelmonServerStore.clearList();
		PixelmonServerStore.clearMousePokemon();

		GuiPixelmonOverlay.checkSelection();
	}

	public void actionPerformed(GuiButton button) {
		int b = button.id;
		if (b == 0) {
			if (boxNumber == 0) {
				boxNumber = 7;
			} else {
				boxNumber--;
			}
		}
		if (b == 1) {
			if (boxNumber == 7) {
				boxNumber = 0;
			} else {
				boxNumber++;
			}
		}

	}

	public boolean doesGuiPauseGame() {
		return false;
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

	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		refreshSlots();
		int w = width;
		int h = height;

		SlotPC mouseSlot = new SlotPC(var2 - 15, var3 - 15, PixelmonServerStore.getMousePokemon());
		RenderHelper.disableStandardItemLighting();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		
		mc.renderEngine.bindTexture(GuiResources.pcPartyBox);
		drawTexturedModalRect(width / 2 - 91, height / 6 + 151, 0, 0, 182, 29);
		int i = 0;
		mc.renderEngine.bindTexture(GuiResources.pcBox);
		drawTexturedModalRect(width / 2 - 91, height / 6, 0, 0, 182, 141);
		drawTexturedModalRect(trashX, trashY, 0, 256 - 32, 32, 32);
		mc.renderEngine.bindTexture(GuiResources.pokechecker);
		drawImageQuad(checkX, checkY, 32f, 32f, 0f, 0f, 1f, 1f);
		for (int a = 0; a < pcSlots[boxNumber].length; a++) {
			SlotPCPC slot = pcSlots[boxNumber][a];
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
				mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
			else
				mc.renderEngine.bindTexture(GuiResources.sprite(numString));
			drawImageQuad(slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			if (slot.pokemonData.heldItemId != -1) {
				mc.renderEngine.bindTexture(GuiResources.heldItem);
				drawImageQuad(slot.x + 22, slot.y + 22, 8, 8, 0, 0, 1f, 1f);
			}
		}
		for (int a = 0; a < partySlots.length; a++) {
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
				mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
			else
				mc.renderEngine.bindTexture(GuiResources.sprite(numString));
			drawImageQuad(slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			if (slot.pokemonData.heldItemId != -1) {
				mc.renderEngine.bindTexture(GuiResources.heldItem);
				drawImageQuad(slot.x + 18, slot.y + 22, 8, 8, 0, 0, 1f, 1f);
			}
		}
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
				mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
			else
				mc.renderEngine.bindTexture(GuiResources.sprite(numString));
			drawImageQuad(mouseSlot.x, mouseSlot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			if (mouseSlot.pokemonData.heldItemId != -1) {
				mc.renderEngine.bindTexture(GuiResources.heldItem);
				drawImageQuad(mouseSlot.x + 22, mouseSlot.y + 22, 8, 8, 0, 0, 1f, 1f);
			}
			if (p.nickname == null || p.nickname.equalsIgnoreCase("")) {
				p.nickname = p.name;
			}
			fontRenderer.drawString(p.nickname, mouseSlot.x + 30, mouseSlot.y + 10, 0xFFFFFF);
			mc.renderEngine.bindTexture(GuiResources.pixelmonOverlay);
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

		}
		fontRenderer.drawString("Box: " + (boxNumber + 1), width / 2 - 18, height / 6 - 20, 0xffffff);

	}

	private void refreshSlots() {
		for (int i = 0; i < PlayerComputerStorage.boxCount; i++) {
			for (int j = 0; j < ComputerBox.boxLimit; j++) {
				PixelmonDataPacket p = PixelmonServerStore.getFromBox(i, j);
				pcSlots[i][j].setPokemon(p);

			}
		}
		for (int i = 0; i < 6; i++) {
			PixelmonDataPacket p = ServerStorageDisplay.pokemon[i];
			if (p != null) {
				partySlots[i].setPokemon(p);
			}
		}
	}

}
