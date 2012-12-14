package pixelmon.items;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public class ItemPixelmonArmor extends ItemArmor{

    public ItemPixelmonArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l)
    {
        super(i, enumArmorMaterial, k, l);
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
}

