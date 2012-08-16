package pixelmon.gui.pc;

import java.awt.Rectangle;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import pixelmon.PixelmonServerStore;
import pixelmon.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.gui.ContainerEmpty;
import pixelmon.gui.GuiScreenPokeCheckerPC;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.ComputerManager;
import pixelmon.storage.PlayerComputerStorage;

public class GuiPC extends GuiContainer {

	private int boxNumber, trashX, trashY, checkX, checkY;
	private SlotPC mouseSlot;
	private SlotPCPC[][] pcSlots = new SlotPCPC[PlayerComputerStorage.boxCount][ComputerBox.boxLimit];
	private SlotPCParty[] partySlots = new SlotPCParty[6];

	public GuiPC() {
		super(new ContainerEmpty());
		boxNumber = 0;
	}

	public void initGui() {
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 131, height / 6 + 60, 20, 20, "<-"));
		controlList.add(new GuiButton(1, width / 2 - 91 + 205, height / 6 + 60, 20, 20, "->"));
		trashX = width / 2 - 91 + 202;
		trashY = height / 6 + 150;
		checkY = trashY;
		checkX = width / 2 - 140;

		mouseSlot = new SlotPC(0, 0, (PixelmonDataPacket) null);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < ComputerBox.boxLimit; j++) {
				int x = j % 6;
				int y = j / 6;
				x *= 30;
				y *= 28;
				x += width / 2 - 90;
				y += height / 6 - 5;
				pcSlots[i][j] = new SlotPCPC(x, y, i, j, null);
				PixelmonDataPacket p = null;
				if (j < PixelmonServerStore.store.size()) {
					p = PixelmonServerStore.store.get(j);
				}
				if (p != null) {
					pcSlots[i][j].setPokemon(p);
				}

			}
		}
		for (int i = 0; i < 6; i++) {
			int x = i;
			x *= 30;
			x += width / 2 - 90;
			int y = height / 6 + 145;
			partySlots[i] = new SlotPCParty(x, y, i, null);
			PixelmonDataPacket p = ServerStorageDisplay.pokemon[i];
			if (p != null) {
				partySlots[i].setPokemon(p);
			}
		}
		sort();

	}

	public void sort() {
		SlotPCPC[][] temp = new SlotPCPC[PlayerComputerStorage.boxCount][ComputerBox.boxLimit];
		for (int i = 0; i < pcSlots.length; i++) {
			for (int j = 0; j < pcSlots[i].length; j++) {
				PixelmonDataPacket p = pcSlots[i][j].pokemonData;
				if (p != null) {
					int box = p.boxNumber;
					int pos = p.order;
					temp[box][pos] = new SlotPCPC(pcSlots[box][pos].x, pcSlots[box][pos].y, box, pos, null);
					temp[box][pos].setPokemon(p);
				}
			}
		}
		int z = 0;
		for (SlotPCPC[] i : temp) {
			int y = 0;
			for (SlotPCPC j : temp[z]) {
				if (j != null) {
					pcSlots[z][y] = j;
				} else {
					pcSlots[z][y].setPokemon((PixelmonDataPacket) null);
				}
				y++;
			}
			z++;
		}
		for (int i = 0; i < partySlots.length; i++) {
			PixelmonDataPacket p = partySlots[i].pokemonData;
			if (p != null) {
				int pos = p.order;
				partySlots[i].setPokemon((PixelmonDataPacket) null);
				partySlots[pos].setPokemon(p);
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
			if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
				if (slot.pokemonData != null)
					i++;
			} else {
				if (slot.pokemon != null)
					i++;
			}
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
				if ((slot instanceof SlotPCParty && mouseSlot.pokemonData == null && checkIfLast())) {
					return;
				}
				if (slot.pokemonData != null) {
					slot.setPokemon((PixelmonDataPacket) null);
					changed = true;
				}
				if (mouseSlot.pokemonData != null) {
					slot.setPokemon(mouseSlot.pokemonData);
					mouseSlot.pokemonData = null;
					changed = true;
				}
				mouseSlot.pokemonData = temp;
				if (changed) {
					if (slot instanceof SlotPCParty) {
						int pos = ((SlotPCParty) slot).partyPosition;
						ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.PCClick, -1, boxNumber, pos));
					}
					if (slot instanceof SlotPCPC) {
						int boxNumber = ((SlotPCPC) slot).boxNumber;
						int pos = ((SlotPCPC) slot).boxPosition;
						ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.PCClick, this.boxNumber, boxNumber, pos));
					}
				}
				return;

			} else if (new Rectangle(trashX, trashY, 32, 32).contains(par1, par2)) {
				if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
					if (mouseSlot.pokemonData != null) {
						ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.PCClick, -2));
					}
				}
				mouseSlot.clearPokemon();
				return;
			} else if (new Rectangle(checkX, checkY, 32, 32).contains(par1, par2)) {
				if (mouseSlot.pokemonData != null) {
					mc.displayGuiScreen(new GuiScreenPokeCheckerPC(mouseSlot.pokemonData, this, 0, 0));
				}
				return;

			} else {
				return;
			}
		}
	}

	protected void mouseMovedOrUp(int par1, int par2, int par3) {
		super.mouseMovedOrUp(par1, par2, par3);
		mouseSlot.setXandY(par1 - 15, par2 - 15);
	}

	public void drawScreen(int var1, int var2, float var3) {
		super.drawScreen(var1, var2, var3);
	}

	public void onGuiClosed() {
		super.onGuiClosed();

		PixelmonServerStore.store.clear();
		if (mouseSlot.pokemonData != null) {
			for (int i = 0; i < pcSlots.length; i++) {
				for (int j = 0; j < pcSlots[i].length; j++) {
					if (pcSlots[i][j].pokemonData == null) {
						ModLoader.sendPacket(PacketCreator.createPacket(EnumPackets.PCClick, -4, i, j));
						break;
					}
				}
			}
		}
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

	public void switchBackFromGui(int box, int i) {
		mouseSlot.pokemon = pcSlots[box][i].pokemon;
		pcSlots[box][i].clearPokemon();
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

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
		int w = width;
		int h = height;
		int partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pcPartyBox.png");
		ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
		drawTexturedModalRect(width / 2 - 91, height / 6 + 151, 0, 0, 182, 29);
		int i = 0;
		partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pcBox.png");
		ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
		drawTexturedModalRect(width / 2 - 91, height / 6, 0, 0, 182, 141);
		drawTexturedModalRect(trashX, trashY, 0, 256 - 32, 32, 32);
		drawImageQuad(ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/image/pokechecker.png"), checkX, checkY, 32f, 32f, 0f, 0f, 1f, 1f);
		if (ModLoader.getMinecraftInstance().theWorld.isRemote) {
			int image = 0;
			for (int a = 0; a < pcSlots[boxNumber].length; a++) {
				image = 0;
				SlotPCPC slot = pcSlots[boxNumber][a];
				if (slot.pokemonData == null) {
					continue;
				}
				String numString = "";
				if (slot.pokemonData.nationalPokedexNumber < 10)
					numString = "00" + slot.pokemonData.nationalPokedexNumber;
				else if (slot.pokemonData.nationalPokedexNumber < 100)
					numString = "0" + slot.pokemonData.nationalPokedexNumber;
				else
					numString = "" + slot.pokemonData.nationalPokedexNumber;
				if (slot.pokemonData.isShiny)
					image = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					image = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			for (int a = 0; a < partySlots.length; a++) {
				image = 0;
				SlotPCParty slot = partySlots[a];
				if (slot.pokemonData == null) {
					continue;
				}
				String numString = "";
				if (slot.pokemonData.nationalPokedexNumber < 10)
					numString = "00" + slot.pokemonData.nationalPokedexNumber;
				else if (slot.pokemonData.nationalPokedexNumber < 100)
					numString = "0" + slot.pokemonData.nationalPokedexNumber;
				else
					numString = "" + slot.pokemonData.nationalPokedexNumber;
				if (slot.pokemonData.isShiny)
					image = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					image = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			image = 0;
			if (mouseSlot.pokemonData != null) {
				PixelmonDataPacket p = mouseSlot.pokemonData;
				String numString = "";
				if (p.nationalPokedexNumber < 10)
					numString = "00" + p.nationalPokedexNumber;
				else if (p.nationalPokedexNumber < 100)
					numString = "0" + p.nationalPokedexNumber;
				else
					numString = "" + p.nationalPokedexNumber;
				if (p.isShiny)
					image = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					image = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(image, mouseSlot.x, mouseSlot.y, 30f, 30f, 0f, 0f, 1f, 1f);
				if (p.nickname == null || p.nickname.equalsIgnoreCase("")) {
					p.nickname = p.name;
				}
				fontRenderer.drawString(p.nickname, mouseSlot.x + 30, mouseSlot.y + 10, 0xFFFFFF);
				partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlay.png");
				ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
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
		} else {
			for (int a = 0; a < pcSlots[boxNumber].length; a++) {
				SlotPCPC slot = pcSlots[boxNumber][a];
				if (slot.pokemon == null) {
					continue;
				}
				int image = slot.getRenderInt();
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			for (int a = 0; a < partySlots.length; a++) {
				SlotPCParty slot = partySlots[a];
				if (slot.pokemon == null) {
					continue;
				}
				int image = slot.getRenderInt();
				drawImageQuad(image, slot.x, slot.y, 30f, 30f, 0f, 0f, 1f, 1f);
			}
			if (mouseSlot.pokemon != null) {
				int image = mouseSlot.getRenderInt();
				drawImageQuad(image, mouseSlot.x, mouseSlot.y, 30f, 30f, 0f, 0f, 1f, 1f);
				NBTTagCompound n = mouseSlot.pokemon;
				if (n.getString("Nickname") == null || n.getString("Nickname").equalsIgnoreCase("")) {
					n.setString("Nickname", n.getName());
				}
				fontRenderer.drawString(n.getString("Nickname"), mouseSlot.x + 30, mouseSlot.y + 10, 0xFFFFFF);
				partyTexture = ModLoader.getMinecraftInstance().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlay.png");
				ModLoader.getMinecraftInstance().renderEngine.bindTexture(partyTexture);
				if (n.getBoolean("IsMale"))
					drawTexturedModalRect(fontRenderer.getStringWidth(n.getString("Nickname")) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 208, 5, 9);
				else
					drawTexturedModalRect(fontRenderer.getStringWidth(n.getString("Nickname")) + mouseSlot.x + 32, mouseSlot.y + 10, 33, 218, 5, 9);
				fontRenderer.drawString("Lvl " + n.getInteger("Level"), mouseSlot.x + 30, mouseSlot.y + 20, 0xFFFFFF);
				if (n.getBoolean("IsFainted")) {
					fontRenderer.drawString("Fainted", mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + n.getInteger("Level")), mouseSlot.y + 20, 0xFFFFFF);
				} else {
					fontRenderer.drawString("HP " + n.getShort("Health") + "/" + n.getInteger("StatsHP"), mouseSlot.x + 35 + fontRenderer.getStringWidth("Lvl " + n.getInteger("Level")),
							mouseSlot.y + 20, 0xFFFFFF);
				}
			}
		}
		fontRenderer.drawString("Box: " + (boxNumber + 1), width / 2 - 18, height / 6 - 20, 0xffffff);

	}

}
