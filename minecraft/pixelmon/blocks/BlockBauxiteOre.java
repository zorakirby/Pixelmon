package pixelmon.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockBauxiteOre extends Block {

	public BlockBauxiteOre(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	Icon icon;

	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister) {
		icon = par1IconRegister.func_94245_a("pixelmon:" + "bauxiteore");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
		return icon;
	}
}
