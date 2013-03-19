package pixelmon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixelmon.config.PixelmonCreativeTabs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class PixelmonItemBlock extends ItemBlock {

	public PixelmonItemBlock(int par1) {
		super(par1);
		setCreativeTab(PixelmonCreativeTabs.natural);
	}
	

}
