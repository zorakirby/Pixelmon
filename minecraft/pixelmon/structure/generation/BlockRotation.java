package pixelmon.structure.generation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockTrapDoor;
import pixelmon.blocks.decorative.BlockContainerPlus;

public class BlockRotation {

	public static int setBlockRotation(int coordBaseMode, int par1, int par2) {

		Block theBlock = Block.blocksList[par1];
		if (theBlock instanceof BlockStairs)
			return rotateStairs(coordBaseMode, par1, par2);
		if (par1 == Block.lever.blockID) {
			return rotateLever(coordBaseMode, par1, par2);
		}
		if (par1 == Block.pistonBase.blockID || par1 == Block.pistonStickyBase.blockID) {
			return rotatePiston(coordBaseMode, par1, par2);
		}
		if (par1 == Block.trapdoor.blockID)
			return rotateTrapDoor(coordBaseMode, par1, par2);
		if (par1 == Block.torchWood.blockID || par1 == Block.torchRedstoneActive.blockID || par1 == Block.torchRedstoneIdle.blockID)
			return rotateTorch(coordBaseMode, par1, par2);
		if (par1 == Block.signPost.blockID || par1 == Block.signWall.blockID)
			return rotateSign(coordBaseMode, par1, par2);
		if(Block.blocksList[par1] instanceof BlockContainerPlus)
			return BlockContainerPlus.rotate(coordBaseMode, theBlock, par2);
		return par2;
	}

	private static int rotateSign(int coordBaseMode, int par1, int par2) {
		if (coordBaseMode == 1) {
			if (par2 == 4) {
				return 2;
			}
			if (par2 == 2) {
				return 5;
			}
			if (par2 == 5) {
				return 3;
			}
			if (par2 == 3) {
				return 4;
			}
		}
		if (coordBaseMode == 2) {
			if (par2 == 4) {
				return 5;
			}
			if (par2 == 2) {
				return 3;
			}
			if (par2 == 5) {
				return 4;
			}
			if (par2 == 3) {
				return 2;
			}
		}
		if (coordBaseMode == 3) {
			if (par2 == 4) {
				return 3;
			}
			if (par2 == 2) {
				return 4;
			}
			if (par2 == 5) {
				return 2;
			}
			if (par2 == 3) {
				return 5;
			}
		}

		return par2;
	}

	/*
	 * coordBaseMode: 0 - No rotation 1 - rotated 90 anti clockwise 2 - rotated
	 * 180 3 - rotated 90 clockwise
	 */
	private static int rotateTrapDoor(int coordBaseMode, int par1, int par2) {
		boolean opened = BlockTrapDoor.isTrapdoorOpen(par2);
		boolean placedTop = false;
		int side = par2;
		int returnval = -1;
		if (opened)
			side = par2 ^ 4;
		if (side > 7) {
			placedTop = true;
			side &= ~8;
		}
		if (coordBaseMode == 1) {
			if (side == 2)
				returnval = 0;
			else if (side == 0)
				returnval = 3;
			else if (side == 3)
				returnval = 1;
			else if (side == 1)
				returnval = 2;
		}
		if (coordBaseMode == 2) {
			if (side == 2)
				returnval = 2;
			else if (side == 0)
				returnval = 1;
			else if (side == 3)
				returnval = 3;
			else if (side == 1)
				returnval = 0;
		}
		if (coordBaseMode == 3) {
			if (side == 2)
				returnval = 0;
			else if (side == 0)
				returnval = 2;
			else if (side == 3)
				returnval = 1;
			else if (side == 1)
				returnval = 3;
		}
		if (returnval != -1) {
			if (placedTop)
				returnval |= 8;
			if (opened)
				returnval = returnval ^ 4;
			return returnval;
		}
		return par2;
	}

	private static int rotateLever(int coordBaseMode, int par1, int par2) {
		int othermeta = par2 & 8;
		int side = par2 & 7;
		if (coordBaseMode == 1) {
			if (side == 4) {
				return 1 + othermeta;
			}

			if (side == 1) {
				return 3 + othermeta;
			}

			if (side == 3) {
				return 2 + othermeta;
			}

			if (side == 2) {
				return 4 + othermeta;
			}
		} else if (coordBaseMode == 3) {
			if (side == 4) {
				return 2 + othermeta;
			}

			if (side == 1) {
				return 4 + othermeta;
			}

			if (side == 3) {
				return 1 + othermeta;
			}

			if (side == 2) {
				return 3 + othermeta;
			}
		} else if (coordBaseMode == 2) {
			if (side == 1) {
				return 2 + othermeta;
			}

			if (side == 2) {
				return 1 + othermeta;
			}

			if (side == 4) {
				return 3 + othermeta;
			}

			if (side == 3) {
				return 4 + othermeta;
			}
		}
		return par2;
	}

	private static int rotatePiston(int coordBaseMode, int par1, int par2) {
		int extended = BlockPistonBase.isExtended(par2) ? 1 : 0;
		int side = BlockPistonBase.getOrientation(par2);
		if (coordBaseMode == 1) {
			if (side == 5) {
				return 2 + extended;
			}

			if (side == 4) {
				return 4 + extended;
			}

			if (side == 3) {
				return 2 + extended;
			}

			if (side == 2) {
				return 5 + extended;
			}
		} else if (coordBaseMode == 3) {
			if (side == 5) {
				return 3 + extended;
			}

			if (side == 2) {
				return 5 + extended;
			}

			if (side == 4) {
				return 2 + extended;
			}

			if (side == 3) {
				return 4 + extended;
			}
		} else if (coordBaseMode == 2) {
			if (side == 2) {
				return 3 + extended;
			}

			if (side == 3) {
				return 2 + extended;
			}

			if (side == 5) {
				return 4 + extended;
			}

			if (side == 4) {
				return 3 + extended;
			}
		}
		return par2;
	}

	private static int rotateStairs(int coordBaseMode, int par1, int par2) {
		if (coordBaseMode == 2) {
			if (par2 == 2) {
				return 3;
			}

			if (par2 == 3) {
				return 2;
			}
		} else if (coordBaseMode == 1) {
			if (par2 == 0) {
				return 2;
			}

			if (par2 == 1) {
				return 3;
			}

			if (par2 == 2) {
				return 1;
			}

			if (par2 == 3) {
				return 0;
			}
		} else if (coordBaseMode == 3) // ---FIXED
		{
			if (par2 == 0) {
				return 2;
			}

			if (par2 == 1) {
				return 3;
			}

			if (par2 == 2) {
				return 0;
			}

			if (par2 == 3) {
				return 1;
			}
		}
		return par2;
	}

	private static int rotateTorch(int coordBaseMode, int par1, int par2) {
		if (coordBaseMode == 2) {
			if (par2 == 4) {
				return 3;
			}

			if (par2 == 1) {
				return 2;
			}

			if (par2 == 3) {
				return 4;
			}

			if (par2 == 2) {
				return 1;
			}
		} else if (coordBaseMode == 1) {
			if (par2 == 4) {
				return 1;
			}

			if (par2 == 1) {
				return 3;
			}

			if (par2 == 3) {
				return 2;
			}

			if (par2 == 2) {
				return 4;
			}
		} else if (coordBaseMode == 3) {
			if (par2 == 4) {
				return 2;
			}

			if (par2 == 1) {
				return 3;
			}

			if (par2 == 3) {
				return 1;
			}

			if (par2 == 2) {
				return 4;
			}
		}
		return par2;
	}

	private static int rotatePixelmonBlock(int coordBaseMode, int par1, int par2) {
		if (coordBaseMode == 2) {
			if (par2 == 2) {
				return 0;
			}

			if (par2 == 0) {
				return 2;
			}
		} else if (coordBaseMode == 1) {
			if (par2 == 0) {
				return 3;
			}

			if (par2 == 1) {
				return 0;
			}

			if (par2 == 2) {
				return 1;
			}

			if (par2 == 3) {
				return 2;
			}
		} else if (coordBaseMode == 3) // ---FIXED
		{
			if (par2 == 2) {
				return 3;
			}

			if (par2 == 1) {
				return 0;
			}

			if (par2 == 0) {
				return 1;
			}

			if (par2 == 3) {
				return 2;
			}
		}
		return par2;
	}

	public static int setPixelmonBlockRotation(int coordBaseMode, int par1, int par2) {
		return rotatePixelmonBlock(coordBaseMode, par1, par2);
	}

}
