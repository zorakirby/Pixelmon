package pixelmon.client.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.comm.BossDropPacket;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.gui.ContainerEmpty;
import pixelmon.items.ItemHeld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiItemDrops extends GuiContainer {
	static ResourceLocation background = new ResourceLocation("pixelmon:gui/drops/Drops1.png");
	static ResourceLocation itemSlot = new ResourceLocation("pixelmon:gui/drops/Drops2.png");

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
	public void updateScreen() {
		super.updateScreen();
		if (ticks > 0)
			ticks--;
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		int itemSpacingX = 40;
		int itemSpacingY = 30;
		int itemWidth = 24;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		mc.renderEngine.bindTexture(background);
		GuiHelper.drawImageQuad((width - 280) / 2, (height - 182) / 2, 280, 182, 0, 0, 1, 1, zLevel);
		fontRenderer.drawString("You beat a boss " + ClientBattleManager.getOpponent().name + "!", (width - 280) / 2 + 10, (height - 182) / 2 + 25, 0xffffff);
		int x = 0;
		int y = 0;
		for (int d = 0; d < drops.itemIds.length; d++) {
			mc.renderEngine.bindTexture(itemSlot);
			GuiHelper.drawImageQuad((width - 280) / 2 + 15 + x * itemSpacingX, (height - 182) / 2 + 50 + y * itemSpacingY, itemWidth, itemWidth, 0, 0, 1, 1, zLevel);
			itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(drops.itemIds[d], 1, 0), (width - 280) / 2
					+ 19 + x * itemSpacingX, (height - 182) / 2 + 53 + y * itemSpacingY);
			itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(drops.itemIds[d], 1, 0), (width - 280) / 2 + 19
					+ x * itemSpacingX, (height - 182) / 2 + 53 + y * itemSpacingY, null);

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
