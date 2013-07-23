package pixelmon.structure.generation;

import java.util.Random;

import pixelmon.structure.StructureData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public abstract class StructureScattered extends StructureComponent {
	/** The size of the bounding box for this feature in the X axis */
	public final int scatteredFeatureSizeX;

	/** The size of the bounding box for this feature in the Y axis */
	public final int scatteredFeatureSizeY;

	/** The size of the bounding box for this feature in the Z axis */
	public final int scatteredFeatureSizeZ;
	protected int field_74936_d = -1;

	protected StructureScattered(Random par1Random, int x, int y, int z, int width, int height, int length) {
		super(0);
		this.scatteredFeatureSizeX = width;
		this.scatteredFeatureSizeY = height;
		this.scatteredFeatureSizeZ = length;
		this.coordBaseMode = 3;//par1Random.nextInt(4);

		switch (this.coordBaseMode) {
		case 0:
		case 2:
			this.boundingBox = new StructureBoundingBox(x, y, z, x + width - 1, y + height - 1, z + length - 1);
			break;
		default:
			this.boundingBox = new StructureBoundingBox(x, y, z, x + length - 1, y + height - 1, z + width - 1);
		}
	}

	protected boolean canStructureFitAtCoords(World world, StructureBoundingBox boundingBox, StructureData structureData) {
		int maxHeight = -1, minHeight = -1;
		for (int i = 0; i < 2; i++) {
			int ix;
			if (i == 0)
				ix = this.boundingBox.minX;
			else
				ix = this.boundingBox.maxX;
			for (int iz = this.boundingBox.minZ; iz <= this.boundingBox.maxZ; ++iz) {
				int blockHeight = getTopSolidBlock(world, ix, iz);
				if (maxHeight == -1 || blockHeight > maxHeight)
					maxHeight = blockHeight;
				else if (minHeight == -1 || blockHeight < minHeight)
					minHeight = blockHeight;
			}
		}
		for (int i = 0; i < 2; i++) {
			int iz;
			if (i == 0)
				iz = this.boundingBox.minZ;
			else
				iz = this.boundingBox.maxZ;
			for (int ix = this.boundingBox.minX; ix <= this.boundingBox.maxX; ++ix) {
				int blockHeight = getTopSolidBlock(world, ix, iz);
				if (maxHeight == -1 || blockHeight > maxHeight)
					maxHeight = blockHeight;
				else if (minHeight == -1 || blockHeight < minHeight)
					minHeight = blockHeight;
			}
		}

		if (maxHeight - minHeight > 4)
			return false;

		boundingBox.offset(0, (minHeight - structureData.depth) - boundingBox.minY + 1, 0);
		return true;
	}

	public int getTopSolidBlock(World world, int par1, int par2) {
		Chunk chunk = world.getChunkFromBlockCoords(par1, par2);
		int k = chunk.getTopFilledSegment() + 15;
		par1 &= 15;

		for (par2 &= 15; k > 0; --k) {
			int l = chunk.getBlockID(par1, k, par2);

			if (l != 0 && Block.blocksList[l].blockMaterial.blocksMovement() && Block.blocksList[l].blockMaterial != Material.leaves
					&& Block.blocksList[l].blockMaterial != Material.wood && !Block.blocksList[l].isBlockFoliage(world, par1, k, par2)) {
				return k;
			}
		}

		return -1;
	}
}
