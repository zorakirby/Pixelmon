package pixelmon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemShrineOrb extends Item {
	private String texturePath;
	public String itemNames;

	public ItemShrineOrb(int par1, String textureName, String itemName) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxStackSize(5);
		this.itemNames = itemName;
		this.texturePath = textureName;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("pixelmon:" + this.texturePath.toLowerCase());
	}

}
