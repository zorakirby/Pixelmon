package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;

import pixelmon.Pixelmon;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumEvolutionStone;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockEvolutionStoneOre extends Block {

	private EnumEvolutionStone type;

	public BlockEvolutionStoneOre(int id, EnumEvolutionStone thunderstone, float hardness) {
		super(id, Material.rock);
		this.type = thunderstone;
		setHardness(hardness);
		setStepSound(Block.soundStoneFootstep);
		if (id == PixelmonBlocks.waterStoneOreId)
			setLightValue(0.5f);
		setCreativeTab(PixelmonCreativeTabs.natural);
		blockIndexInTexture = Pixelmon.proxy.getTexture("/terrain.png", "/pixelmon/block/" + type.toString().toLowerCase() + ".png");
	}

	public boolean isOpaqueCube() {
		return !(type == EnumEvolutionStone.Leafstone);
	}

	@SuppressWarnings("incomplete-switch")
	public int idDropped(int i, Random rand, int j) {
		int result = 0;
		switch (type) {
		case Thunderstone:
			result = PixelmonItems.thunderStoneShard.shiftedIndex;
			break;
		case Leafstone:
			result = PixelmonItems.leafStoneShard.shiftedIndex;
			break;
		case Waterstone:
			result = PixelmonItems.waterStoneShard.shiftedIndex;
			break;
		case Firestone:
			result = PixelmonItems.fireStoneShard.shiftedIndex;
			break;
		}

		return result;
	}

}
