package pixelmon.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import pixelmon.client.gui.GuiHelper;
import pixelmon.config.PixelmonItems;
import pixelmon.items.*;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRendererExperience implements IItemRenderer {
	private static RenderItem renderItem = new RenderItem();
	private Minecraft mc = Minecraft.getMinecraft();
	public static ResourceLocation back = new ResourceLocation("pixelmon:textures/items/back.png");
	public static ResourceLocation front = new ResourceLocation("pixelmon:textures/items/front.png");
	public static ResourceLocation uno = new ResourceLocation("pixelmon:textures/items/unoorb.png");
	public static ResourceLocation dos = new ResourceLocation("pixelmon:textures/items/dosorb.png");
	public static ResourceLocation tres = new ResourceLocation("pixelmon:textures/items/tresorb.png");
	public static float percent = 0;;
	public int p = (int) percent;

	@Override
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
		float height = percent * 16;
		mc.renderEngine.bindTexture(back);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(0, 0, 16, 16, 0, 0, 1, 1, 1);
		if (itemStack.getItem().itemID == PixelmonItems.unoOrb.itemID) {
			mc.renderEngine.bindTexture(uno);
			GuiHelper.drawImageQuad(0, (int) (16 - height), 16, height, 0, 1 - percent, 1, 1, 1);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		} else if (itemStack.getItem().itemID == PixelmonItems.dosOrb.itemID) {
			mc.renderEngine.bindTexture(dos);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GuiHelper.drawImageQuad(0, (int) (16 - height), 16, height, 0, 1 - percent, 1, 1, 1);
		} else if (itemStack.getItem().itemID == PixelmonItems.tresOrb.itemID) {
			mc.renderEngine.bindTexture(tres);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GuiHelper.drawImageQuad(0, (int) (16 - height), 16, height, 0, 1 - percent, 1, 1, 1);
		} else {
			return;
		}
		mc.renderEngine.bindTexture(front);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(0, 0, 16, 16, 0, 0, 1, 1, 1);
	}
}