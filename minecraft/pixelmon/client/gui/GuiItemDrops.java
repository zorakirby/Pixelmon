package pixelmon.client.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.Pixelmon;
import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.client.gui.battles.GuiBattle;
import pixelmon.comm.BossDropPacket;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.enums.EnumGui;
import pixelmon.gui.ContainerEmpty;
import pixelmon.items.ItemHeld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiItemDrops extends GuiContainer {


	BossDropPacket drops;

	public GuiItemDrops() {
		super(Minecraft.getMinecraft().thePlayer.inventoryContainer);
		drops = PixelmonServerStore.bossDrops;
		ticks = drops.itemIds.length * 5 + 5;
		PixelmonServerStore.bossDrops = null;
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	int ticks = 0;

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (ticks > 0)
			ticks--;
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		if (par2 == 1) {
			if (GuiBattle.evolveList.size() > 0) {
				int pokemonID = GuiBattle.evolveList.get(0);
				GuiBattle.evolveList.remove(0);
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
			}
		}
	}

	@Override
	protected void mouseClicked(int i, int j, int par3) {
		int xPos = (width - 280) / 2 + 122;
		int yPos = (height - 182) / 2 + 150;
		int buttonWidth = 50, buttonHeight = 20;
		if (i >= xPos && i <= xPos + buttonWidth && j >= yPos && j <= yPos + buttonWidth) {
			mc.thePlayer.closeScreen();
			if (GuiBattle.evolveList.size() > 0) {
				int pokemonID = GuiBattle.evolveList.get(0);
				GuiBattle.evolveList.remove(0);
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
			}
		}
	}

	boolean isFirst = true;

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		int itemSpacingX = 40;
		int itemSpacingY = 30;
		int itemWidth = 24;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		mc.renderEngine.bindTexture(GuiResources.background);
		GuiHelper.drawImageQuad((width - 280) / 2, (height - 182) / 2, 280, 182, 0, 0, 1, 1, zLevel);
		fontRenderer.drawString("You beat a boss " + ClientBattleManager.getOpponent().name + "!", (width - 280) / 2 + 10, (height - 182) / 2 + 25, 0xffffff);

		int xPos = (width - 280) / 2 + 122;
		int yPos = (height - 182) / 2 + 150;
		int buttonWidth = 50, buttonHeight = 20;
		if (i >= xPos && i <= xPos + buttonWidth && j >= yPos && j <= yPos + buttonWidth)
			mc.renderEngine.bindTexture(GuiResources.itemSlotOver);
		else
			mc.renderEngine.bindTexture(GuiResources.itemSlot);
		GuiHelper.drawImageQuad(xPos, yPos, buttonWidth, buttonHeight, 0, 0, 1, 1, zLevel);
		fontRenderer.drawString("OK", xPos + 20, yPos + 7, 0xffffff);
		int x = 0;
		int y = 0;
		int mouseOverIndex = -1;
		for (int d = 0; d < drops.itemIds.length; d++) {
			xPos = (width - 280) / 2 + 15 + x * itemSpacingX;
			yPos = (height - 182) / 2 + 50 + y * itemSpacingY;
			if (i >= xPos && i <= xPos + itemWidth && j >= yPos && j <= yPos + itemWidth) {
				mc.renderEngine.bindTexture(GuiResources.itemSlotOver);
				mouseOverIndex = d;
			} else
				mc.renderEngine.bindTexture(GuiResources.itemSlot);
			GuiHelper.drawImageQuad(xPos, (height - 182) / 2 + 50 + y * itemSpacingY, itemWidth, itemWidth, 0, 0, 1, 1, zLevel);
			x++;
			if (x > 3) {
				x = 0;
				y++;
			}
		}
		if (mouseOverIndex != -1) {
			String itemName = (new ItemStack(drops.itemIds[mouseOverIndex], 1, 0)).getDisplayName();
			int itemXPos = (width - 280) / 2 + 228 - fontRenderer.getStringWidth(itemName) / 2;
			int itemYPos = (height - 182) / 2 + 160;
			fontRenderer.drawString(itemName, itemXPos, itemYPos, 0xffffff);
			itemXPos = (width / 3 - 280 / 3) / 2 + 208 / 3;
			itemYPos = (height / 3 - 182 / 3) / 2 + 93 / 3;
			GL11.glScalef(3f, 3, 3);
			itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(drops.itemIds[mouseOverIndex], 1, 0),
					itemXPos, itemYPos);
			itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(drops.itemIds[mouseOverIndex], 1, 0), itemXPos,
					itemYPos, null);
			GL11.glScalef(1f / 3f, 1f / 3f, 1f / 3f);
			isFirst = false;
		} else if (isFirst) {
			fontRenderer.drawString("Mouse over item", (width - 280) / 2 + 228 - fontRenderer.getStringWidth("Mouse over item") / 2, (height - 182) / 2 + 100,
					0x888888);
			fontRenderer
					.drawString("to see more", (width - 280) / 2 + 228 - fontRenderer.getStringWidth("to see more") / 2, (height - 182) / 2 + 114, 0x888888);
			fontRenderer.drawString("details", (width - 280) / 2 + 228 - fontRenderer.getStringWidth("details") / 2, (height - 182) / 2 + 128, 0x888888);
		}
		x = y = 0;
		for (int d = 0; d < drops.itemIds.length; d++) {
			xPos = (width - 280) / 2 + 15 + x * itemSpacingX;
			yPos = (height - 182) / 2 + 50 + y * itemSpacingY;
			itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(drops.itemIds[d], 1, 0), xPos + 4, yPos + 3);
			itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(drops.itemIds[d], 1, 0), xPos + 4, yPos + 3,
					null);
			x++;
			if (x > 3) {
				x = 0;
				y++;
			}
		}

	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		int k = this.guiLeft;
		int l = this.guiTop;
		this.drawGuiContainerBackgroundLayer(par3, par1, par2);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		// Forge: Force lighting to be disabled as there are some issue where
		// lighting would
		// incorrectly be applied based on items that are in the inventory.
		GL11.glDisable(GL11.GL_LIGHTING);
		this.drawGuiContainerForegroundLayer(par1, par2);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
}
