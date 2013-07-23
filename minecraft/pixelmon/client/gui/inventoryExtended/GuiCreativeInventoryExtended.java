package pixelmon.client.gui.inventoryExtended;

import java.awt.Rectangle;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.Pixelmon;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.GuiResources;
import pixelmon.client.gui.pokechecker.GuiPokeCheckerTabs;
import pixelmon.client.gui.pokechecker.GuiScreenPokeChecker;
import pixelmon.client.gui.pokechecker.GuiScreenPokeCheckerMoves;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.enums.EnumGui;
import pixelmon.items.ItemHeld;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiCreativeInventoryExtended extends GuiContainerCreative {
	public SlotInventoryPixelmon[] pixelmonSlots;
	private RenderItem itemRenderer = new RenderItem();

	boolean pixelmonMenuOpen;
	int menuX;
	int menuY;
	GuiButton pMenuButtonSumm;
	GuiButton pMenuButtonMove;
	GuiButton pMenuButtonStat;
	PixelmonDataPacket selected;

	float xSize_lo, ySize_lo;

	public GuiCreativeInventoryExtended(EntityPlayer par1EntityPlayer) {
		super(par1EntityPlayer);
		pixelmonMenuOpen = false;
		pixelmonSlots = new SlotInventoryPixelmon[6];
	}

	public void drawButtonContainer() {
		if (pixelmonMenuOpen) {
			this.mc.renderEngine.func_110577_a(GuiResources.pokecheckerPopup);
			this.drawTexturedModalRect(menuX - 73, menuY - 10, 0, 0, 67, 76);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		ScaledResolution var5 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int width = var5.getScaledWidth();
		int height = var5.getScaledHeight();
		for (int i = 0; i < pixelmonSlots.length; i++) {
			pixelmonSlots[i] = null;
		}
		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				int i = p.order;
				int x = width / 2 - 141;
				int y = height / 2 + i * 18 - 65;
				pixelmonSlots[i] = new SlotInventoryPixelmon(x, y, p);
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
		if (pixelmonMenuOpen) {
			this.drawCenteredString(fontRenderer, selected.name, menuX - 40, menuY - 8, 0xffffff);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		super.drawGuiContainerBackgroundLayer(par1, par2, par3);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		this.mc.renderEngine.func_110577_a(GuiResources.pixelmonCreativeInventory);
		this.drawTexturedModalRect(width / 2 - 150, height / 2 - 83, 0, 0, 54, 167);

		ScaledResolution var5 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();
		int textureIndex;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator var2 = Tessellator.instance;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.entityRenderer.setupOverlayRendering();

		fontRenderer.setUnicodeFlag(true);

		for (SlotInventoryPixelmon slot : pixelmonSlots) {
			if (slot == null) {
				continue;
			}
			PixelmonDataPacket p = slot.pokemonData;
			int offset = 0;
			if (p != null) {
				String numString = "";
				if (p.getNationalPokedexNumber() < 10)
					numString = "00" + p.getNationalPokedexNumber();
				else if (p.getNationalPokedexNumber() < 100)
					numString = "0" + p.getNationalPokedexNumber();
				else
					numString = "" + p.getNationalPokedexNumber();

				if (p.isShiny)
					mc.renderEngine.func_110577_a(GuiResources.shinySprite(numString));
				else
					mc.renderEngine.func_110577_a(GuiResources.sprite(numString));
				drawImageQuad(slot.x, slot.y, 16f, 16f, 0f, 0f, 1f, 1f);

				if (p.heldItemId != -1) {
					ItemHeld heldItem = (ItemHeld) PixelmonItemsHeld.getHeldItem(p.heldItemId);
					if (heldItem != null) {
			            itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.func_110434_K(), new ItemStack(heldItem), slot.heldItemX, slot.heldItemY);
			            itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.func_110434_K(), new ItemStack(heldItem), slot.heldItemX, slot.heldItemY, null);
					}
				} else {
					mc.renderEngine.func_110577_a(GuiResources.heldItem);
					drawImageQuad(slot.heldItemX + 3, slot.heldItemY + 3, 10f, 10f, 0f, 0f, 1f, 1f);
				}
			}
			drawButtonContainer();
		}
		int mouseX = Mouse.getX() * this.width / this.mc.displayWidth;
		int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
		if (!pixelmonMenuOpen) {
			for (SlotInventoryPixelmon s : pixelmonSlots) {
				if (s != null) {
					if (s.getBounds().contains(mouseX, mouseY)) {
						drawPokemonInfo(mouseX, mouseY, s);
					}
					if (s.getHeldItemBounds().contains(mouseX, mouseY) && heldItemQualifies(s)) {
						mc.renderEngine.func_110577_a(GuiResources.pixelmonCreativeInventory);
						drawImageQuad(s.heldItemX - 2, s.heldItemY - 2, 20, 20, 58f / 256f, 185f / 256f, 78f / 256f, 205f / 256f);
					}
				}
			}
		}
		//fontRenderer.drawString(StatCollector.translateToLocal(PlayerStorage.getCurrency() + ""), -29, 154, 0xFFFFFF);

		fontRenderer.setUnicodeFlag(false);
		RenderHelper.disableStandardItemLighting();

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	}

	public void drawIcon(int x, int y, Icon par3Icon, int width, int height) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + height), (double) this.zLevel, (double) par3Icon.getMinU(),
				(double) par3Icon.getMaxV());
		tessellator.addVertexWithUV((double) (x + width), (double) (y + height), (double) this.zLevel, (double) par3Icon.getMaxU(),
				(double) par3Icon.getMaxV());
		tessellator.addVertexWithUV((double) (x + width), (double) (y + 0), (double) this.zLevel, (double) par3Icon.getMaxU(),
				(double) par3Icon.getMinV());
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) par3Icon.getMinU(),
				(double) par3Icon.getMinV());
		tessellator.draw();
	}

	private boolean heldItemQualifies(SlotInventoryPixelmon s) {
		InventoryPlayer inv = mc.thePlayer.inventory;
		if (inv.getItemStack() == null && s.pokemonData.heldItemId != -1)
			return true;
		else if (inv.getItemStack() == null)
			return false;
		if (PixelmonItemsHeld.getHeldItem(inv.getItemStack().itemID) != null)
			return true;
		return false;
	}

	private void drawPokemonInfo(int x, int y, SlotInventoryPixelmon s) {
		if (s == null)
			return;
		this.drawGradientRect(s.x - 84, s.y - 2, s.x - 4, s.y + 20, -13158600, -13158600);
		PixelmonDataPacket p = s.pokemonData;
		String displayName = p.name;
		if (!p.nickname.equals(""))
			displayName = p.nickname;
		fontRenderer.drawString(displayName, s.x - 82, s.y, 0xFFFFFF);
		mc.renderEngine.func_110577_a(GuiResources.pixelmonOverlay);
		if (p.isMale)
			this.drawTexturedModalRect(fontRenderer.getStringWidth(displayName) + s.x - 81, s.y, 33, 208, 5, 9);
		else
			this.drawTexturedModalRect(fontRenderer.getStringWidth(displayName) + s.x - 81, s.y, 33, 218, 5, 9);
		fontRenderer.drawString("Lvl " + p.lvl, s.x + -81, s.y + fontRenderer.FONT_HEIGHT + 1, 0xFFFFFF);
		if (p.health <= 0) {
			fontRenderer.drawString("Fainted", s.x - 77 + fontRenderer.getStringWidth("Lvl " + p.lvl), s.y + fontRenderer.FONT_HEIGHT + 1, 0xFFFFFF);
		} else {
			fontRenderer.drawString("HP " + p.health + "/" + p.hp, s.x - 77 + fontRenderer.getStringWidth("Lvl " + p.lvl), s.y + fontRenderer.FONT_HEIGHT + 1,
					0xFFFFFF);
		}

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

	Rectangle buttonBounds;
	Rectangle buttonBoundsMoves;
	Rectangle buttonBoundsStat;

	@Override
	protected void mouseClicked(int x, int y, int par3) {
		if (par3 == 0) {
			if (pixelmonMenuOpen && buttonBounds.contains(x, y)) {
				GuiScreenPokeChecker poke = new GuiScreenPokeChecker(selected, false);
				mc.thePlayer.openGui(Pixelmon.instance, EnumGui.PokeChecker.getIndex(), mc.theWorld, selected.pokemonID, 0, 0);
			}
			if (pixelmonMenuOpen && buttonBoundsMoves.contains(x, y)) {
				GuiScreenPokeChecker poke = new GuiScreenPokeCheckerMoves(selected, false);
				mc.thePlayer.openGui(Pixelmon.instance, EnumGui.PokeCheckerMoves.getIndex(), mc.theWorld, selected.pokemonID, 0, 0);
			}
			if (pixelmonMenuOpen && buttonBoundsStat.contains(x, y)) {
				GuiScreenPokeChecker poke = new GuiScreenPokeChecker(selected, false);
				mc.thePlayer.openGui(Pixelmon.instance, EnumGui.PokeCheckerStats.getIndex(), mc.theWorld, selected.pokemonID, 0, 0);
			}
			if (pixelmonMenuOpen) {
				buttonList.remove(pMenuButtonSumm);
				buttonList.remove(pMenuButtonMove);
				buttonList.remove(pMenuButtonStat);
				pMenuButtonSumm = null;
				pMenuButtonMove = null;
				pMenuButtonStat = null;
				pixelmonMenuOpen = false;
				selected = null;
			}
		}
		for (SlotInventoryPixelmon s : pixelmonSlots) {
			if (s != null) {
				if (s.getBounds().contains(x, y)) { // click on a pokemon sprite
					if (par3 == 1) {
						if (pixelmonMenuOpen) {
							buttonList.remove(pMenuButtonSumm);
							buttonList.remove(pMenuButtonMove);
							buttonList.remove(pMenuButtonStat);
							pMenuButtonSumm = null;
							pMenuButtonMove = null;
							pMenuButtonStat = null;
							pixelmonMenuOpen = false;
							selected = null;
						}
						pMenuButtonSumm = new GuiPokeCheckerTabs(6, 3, x - 63, y + 5, 47, 13, "Summary");
						pMenuButtonMove = new GuiPokeCheckerTabs(6, 4, x - 63, y + 24, 47, 13, "Moves");
						pMenuButtonStat = new GuiPokeCheckerTabs(6, 5, x - 63, y + 43, 47, 13, "Stats");
						menuX = x;
						menuY = y;
						buttonBounds = new Rectangle(x - 63, y + 5, 47, 13);
						buttonBoundsMoves = new Rectangle(x - 63, y + 24, 47, 13);
						buttonBoundsStat = new Rectangle(x - 63, y + 43, 47, 13);
						buttonList.add(pMenuButtonSumm);
						buttonList.add(pMenuButtonMove);
						buttonList.add(pMenuButtonStat);
						pixelmonMenuOpen = true;
						selected = s.pokemonData;
						return;
					} else if (par3 == 0) {

					}
				}
				if (s.getHeldItemBounds().contains(x, y) && heldItemQualifies(s)) {
					InventoryPlayer inventory = mc.thePlayer.inventory;
					ItemStack currentItem = inventory.getItemStack();
					int oldItemId = s.pokemonData.heldItemId;
					int itemId = currentItem == null ? -1 : currentItem.itemID;

					s.pokemonData.heldItemId = itemId;

					if (currentItem != null)
						currentItem.stackSize--;
					if (oldItemId == -1 || PixelmonItemsHeld.getHeldItem(oldItemId) == null) {
						if (currentItem == null || currentItem.stackSize <= 0)
							inventory.setItemStack(null);
						else
							inventory.setItemStack(currentItem);
					} else {
						if (itemId == -1) {
							inventory.setItemStack(new ItemStack(PixelmonItemsHeld.getHeldItem(oldItemId)));
						} else if (itemId != oldItemId) {
							if (currentItem.stackSize <= 0)
								inventory.setItemStack(new ItemStack(PixelmonItemsHeld.getHeldItem(oldItemId)));
							else
								inventory.setItemStack(currentItem);
						} else {
							currentItem.stackSize++;
							inventory.setItemStack(currentItem);
						}
					}
					if (currentItem != null)
						PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SetHeldItem, s.pokemonData.pokemonID, currentItem.itemID));
					else
						PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SetHeldItem, s.pokemonData.pokemonID, -1));
					return;
				}
			}
		}
		if (x > width / 2 - 130 && x < width / 2 - 84 && y > height / 2 - 83 && y < height / 2 + 83)
			return;
		super.mouseClicked(x, y, par3);
	}
}
