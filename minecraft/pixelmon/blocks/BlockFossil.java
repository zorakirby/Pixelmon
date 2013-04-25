package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItemsFossils;

public class BlockFossil extends Block {

	public BlockFossil(int id) {
		super(id, Material.rock);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(PixelmonCreativeTabs.natural);
		setUnlocalizedName("Fossil Block");
	}

	Icon icon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon = par1IconRegister.registerIcon("pixelmon:fossiloreblock");
	}

	@Override
	public Icon getBlockTexture(net.minecraft.world.IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return icon;
	};

	@Override
	public int getRenderType() {
		return 0;
	}

	public int idDropped(int i, Random rand, int j) {
		return PixelmonItemsFossils.getRandomFossilId();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return icon;
	}
}
