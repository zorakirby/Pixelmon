package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;

import pixelmon.Pixelmon;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsFossils;
import pixelmon.enums.EnumEvolutionStone;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockFossil extends Block {

	public BlockFossil(int id) {
		super(id, Material.rock);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(PixelmonCreativeTabs.natural);
		setTextureFile("/pixelmon/block/blocks.png");
		blockIndexInTexture = 5;
	}

	@Override
	public int getRenderType() {
		return 0;
	}

	public int idDropped(int i, Random rand, int j) {
		return PixelmonItemsFossils.getRandomFossilId();
	}

}
