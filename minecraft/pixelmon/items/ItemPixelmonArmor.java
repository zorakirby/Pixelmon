package pixelmon.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;

public class ItemPixelmonArmor extends ItemArmor{

    public ItemPixelmonArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l)
    {
        super(i, enumArmorMaterial, k, l);
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
}

