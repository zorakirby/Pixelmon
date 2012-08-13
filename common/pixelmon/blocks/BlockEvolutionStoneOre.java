package pixelmon.blocks;

import java.util.Random;

import pixelmon.items.PixelmonItems;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class BlockEvolutionStoneOre extends Block{
	
	private int type;
	
	public BlockEvolutionStoneOre(int id, int type, float hardness){
		super(id, Material.rock);
		this.type = type;
		setHardness(hardness);
		setStepSound(Block.soundStoneFootstep);
		if (id == PixelmonBlocks.waterStoneOreId) setLightValue(0.5f);
	}
	
    public boolean isOpaqueCube()
    {
        return !(type == 1);
    }

	public int idDropped(int i, Random rand, int j){
		int result = 0;
		switch(type){
		case 0:
			result = PixelmonItems.thunderStoneShard.shiftedIndex;
			break;
		case 1:
			result = PixelmonItems.leafStoneShard.shiftedIndex;
			break;
		case 2:
			result = PixelmonItems.waterStone.shiftedIndex;
		}
		
		return result;
	}
	
	public int quantityDropped(Random rand){
		if(type == 2){
			return 1;
		}
		else{
			return rand.nextInt(3) + 2;
		}
	}

}
