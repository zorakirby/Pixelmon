package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumEvolutionStone;

public class BlockEvolutionStoneOre extends Block {

	private EnumEvolutionStone type;
	private Icon icon;

	public BlockEvolutionStoneOre(int id, EnumEvolutionStone type, float hardness) {
		super(id, Material.rock);
		this.type = type;
		setHardness(hardness);
		setStepSound(Block.soundStoneFootstep);
		if (id == PixelmonBlocks.waterStoneOreId)
			setLightValue(0.5f);
		setCreativeTab(PixelmonCreativeTabs.natural);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister) {
		icon = par1IconRegister.func_94245_a("pixelmon:" + type.toString().toLowerCase());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
		return icon;
	}

	public boolean isOpaqueCube() {
		return !(type == EnumEvolutionStone.Leafstone);
	}

	@Override
	public int getRenderType() {
		return 0;
	}

	@SuppressWarnings("incomplete-switch")
	public int idDropped(int i, Random rand, int j) {
		int result = 0;
		switch (type) {
		case Thunderstone:
			result = PixelmonItems.thunderStoneShard.itemID;
			break;
		case Leafstone:
			result = PixelmonItems.leafStoneShard.itemID;
			break;
		case Waterstone:
			result = PixelmonItems.waterStoneShard.itemID;
			break;
		case Firestone:
			result = PixelmonItems.fireStoneShard.itemID;
			break;
		}

		return result;
	}

}
