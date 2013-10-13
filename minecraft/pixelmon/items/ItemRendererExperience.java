package pixelmon.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
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
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		mc.renderEngine.bindTexture(front);
		mc.renderEngine.bindTexture(back);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (itemStack.getItem().itemID == PixelmonItems.unoOrb.itemID) {
			mc.renderEngine.bindTexture(uno);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		} else if (itemStack.getItem().itemID == PixelmonItems.dosOrb.itemID) {
			mc.renderEngine.bindTexture(dos);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		} else if (itemStack.getItem().itemID == PixelmonItems.tresOrb.itemID) {
			mc.renderEngine.bindTexture(tres);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		} else {
			return;
		}
		renderItem.renderWithColor = true;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}