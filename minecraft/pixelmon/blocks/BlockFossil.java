package pixelmon.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItemsFossils;

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
