package pixelmon.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import pixelmon.config.PixelmonItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPixelmonArmor extends ItemArmor {

	public ItemPixelmonArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l, String textureName, String itemName) {
		super(i, enumArmorMaterial, k, l);
		this.setMaxDamage(enumArmorMaterial.getDurability(l));
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.textureName = textureName;
		setUnlocalizedName(itemName);
	}

	String textureName;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.getItem() == PixelmonItems.leggingsAluminium)
			return "/pixelmon/armor/aluminum_2.png";

		else {
			return "/pixelmon/armor/aluminum_1.png";
		}
	}
}
