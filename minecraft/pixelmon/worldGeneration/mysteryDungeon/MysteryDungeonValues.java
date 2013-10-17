package pixelmon.worldGeneration.mysteryDungeon;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import pixelmon.util.BlockHelper;
import pixelmon.util.PixelmonDebug;

public class MysteryDungeonValues {
	public final int wallID, floorID, ceilingID, shaftID;
	public final int wallMeta, floorMeta, ceilingMeta, shaftMeta;
	
	public static final MysteryDungeonValues simpleBrick = new MysteryDungeonValues(new int[]{Block.field_111039_cA.blockID, 12}, Block.brick, Block.stoneBrick, Block.stoneDoubleSlab);
	
	/**
	 * @param blocks - 4 objects representing the Ids and Metadata of blocks to use 
	 * in this Mystery Dungeon.<br> 
	 * Each parameter must be one of: {@link Block}, {@link Integer}, {@link ItemStack}, {@code int[]},
	 * or {@code Integer[]}.<br>
	 * If you wish to set metadata values, Itemstack, int[], or Integer[] must be used.<br>
	 * The Block identified by the shaft ID <b>must</b>
	 * return true for {@link BlockHelper#isBlockNormalCube(Block block)}, or else an 
	 * {@link IllegalArgumentException} will be thrown.
	 */
	public MysteryDungeonValues(Object floorParam, Object wallParam, Object ceilingParam, Object ladderParam){
		int[][] blockValues = parseBlocks(new Object[]{floorParam, wallParam, ceilingParam, ladderParam});
		this.floorID = blockValues[0][0];
		this.wallID = blockValues[0][1];
		this.ceilingID = blockValues[0][2];
		this.shaftID = blockValues[0][3];
		this.floorMeta = blockValues[1][0];
		this.wallMeta = blockValues[1][1];
		this.ceilingMeta = blockValues[1][2];
		this.shaftMeta = blockValues[1][3];
	}
	
	
	private int[][] parseBlocks(Object... blocks){
		int[] ids = new int[4];
		int[] metas = new int[]{0, 0, 0, 0};
		for(int i = 0; i < 4; i++){
			if(blocks[i] == null)
				ids[i] = 0;
			else if(blocks[i] instanceof int[]){
				ids[i] = ((int[])blocks[i])[0];
				metas[i] = ((int[])blocks[i])[1];
			}
			else if(blocks[i] instanceof Integer[]){
				ids[i] = ((Integer[])blocks[i])[0];
				metas[i] = ((Integer[])blocks[i])[1];
			}
			else if(blocks[i] instanceof Object[]){
				try{
					ids[i] = ((Block)((Object[])blocks[i])[0]).blockID;
				}catch(ClassCastException e){
					throw wasNotABlock(((Object[])blocks[i])[0]);
				}
			}
			else if(blocks[i] instanceof Integer)
				ids[i] = (Integer)blocks[i];
			else if(blocks[i] instanceof Block)
				ids[i] = ((Block)blocks[i]).blockID;
			else if(blocks[i] instanceof ItemStack){//Wow, it's probably been months since I've used the word "ItemStack" what with all the worldGen and model programming only
				ids[i] = ((ItemStack)blocks[i]).itemID;
				metas[i] = ((ItemStack)blocks[i]).getItemDamage();
			}
			else{
				throw notEvenClose(blocks[i]);
			}
		}
		if(!BlockHelper.isBlockNormalCube(Block.blocksList[ids[3]]))
			throw new IllegalArgumentException("The block specified for the ladder shaft Id MUST be a solid block! The block specified was " + Block.blocksList[ids[3]].getLocalizedName());
		return new int[][]{ids, metas};
	}
	
	/**
	 * Yes, I care that much about the correct usage of 'an'/'a'
	 * @return An {@code IllegalArgumentException} explaining to the failure programmer
	 * of their stupendous, epic fail, being that a certain Object passed-in to the
	 * {@link MysteryDungeonValues#MysteryDungeonValues(Object, Object, Object, Object) MysteryDungeonValues}
	 * constructor was <i>supposed</i> to be a {@link Block} instance, but was instead 
	 * whatever it was they passed in, <i><b>complete</b></i> with usage of 'a' or 'an'
	 * <u>based on the first letter of the class of {@code o}</u>.<br>
	 */
	private static IllegalArgumentException wasNotABlock(Object o){
		String message = "An Object[] was passed-in to the MysteryDungeonValues constructor; element 0 was expected to be a Block, but was instead ";
		String className = o == null ? "null" : o.getClass().getSimpleName();
		if(className.equals("null"))
			message += "null";
		else{
			boolean vowel = PixelmonDebug.startsWithVowel(className);
			message += (vowel ? "an " : "a ") + className;
		}
		return new IllegalArgumentException(message);
	}
	
	private static IllegalArgumentException notEvenClose(Object o){
		String message = "Alright, what do we have here? You were given instructions on how to do this. It's not rocket-science! Look, the valid parameter types for the MysteryDungeonValues constructor are as follows : int, Integer, Block, ItemStack, int[], Integer[], and Object[]. You know what you shoved in there? ";
		String className = o == null ? "null" : o.getClass().getSimpleName();
		className = PixelmonDebug.upperCaseFirstChar(className);
		String indef = o == null ? "" : PixelmonDebug.startsWithVowel(className) ? "an" : "a";
		message += "/n" + className + ". YOU GAVE IT " + indef.toUpperCase() + className.toUpperCase() + "!";
		
		if(o == null){
			//lets chastise them for an even worse fail
			message += "\nNULL, as in YOU GAVE IT A BLANK VALUE! Go back and think about what you've done.";
		}
		return new IllegalArgumentException(message);
		
	}
}
