package pixelmon.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class PixelmonItemHoe extends ItemHoe {

	public PixelmonItemHoe(int par1, EnumToolMaterial par2EnumToolMaterial, String textureName) {
		super(par1, par2EnumToolMaterial);
		this.textureName = textureName;
	}

	String textureName;

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.func_94245_a(textureName);
	}
}
