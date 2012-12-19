package pixelmon.items;

import pixelmon.config.PixelmonItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemPixelmonArmor extends ItemArmor implements IArmorTextureProvider{

    public ItemPixelmonArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l)
    {
        super(i, enumArmorMaterial, k, l);
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

	public String getArmorTextureFile(ItemStack itemstack) {
		if(itemstack.getItem() == PixelmonItems.leggingsAluminum)
		return "/pixelmon/armor/aluminum_2.png";
		else return "/pixelmon/armor/aluminum_1.png";
	}
}

