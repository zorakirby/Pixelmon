package pixelmon.structure.generation;

import java.util.Random;

import pixelmon.config.PixelmonBlocks;
import pixelmon.structure.SchematicImporter;
import pixelmon.structure.StructureData;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class GeneralScattered extends StructureScattered {

	private SchematicImporter s;
	private StructureData sd;

	// TODO implement a system in which we can have special pokemon spawn at
	// special coordinates.

	public GeneralScattered(Random par1Random, int par2, int par3, int par4, SchematicImporter schematic, StructureData structureData) {
		super(par1Random, par2, par3, par4, schematic.width, schematic.height, schematic.length);
		s = schematic;
		sd = structureData;
	}

	public boolean addComponentParts(World world, Random par2Random, StructureBoundingBox bb) {
		if (!this.canStructureFitAtCoords(world, bb, sd)) {
			return false;
		} else {
			for (int x = 0; x < s.width; x++)
				for (int z = 0; z < s.length; z++)
					for (int y = 0; y < s.height; y++) {
						this.placeBlockAtCurrentPosition(world, 0, 0, x, y, z, bb);
						int blockId = s.blocks[x][y][z];
						try {
							if (blockId < 0)
								blockId += 256;
							if (Block.blocksList[blockId] != null)
								this.placeBlockAtCurrentPosition(world, blockId, this.getMetadataWithOffset(blockId, s.blockData[x][y][z]), x, y, z, bb);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
			// id, metadata
			for (int i = 0; i < s.tileEntities.tagCount(); i++) {
				NBTTagCompound nbt = (NBTTagCompound) s.tileEntities.tagAt(i);
				int x = nbt.getInteger("x");
				int y = nbt.getInteger("y");
				int z = nbt.getInteger("z");
				if (nbt.getString("id").equalsIgnoreCase("healer")) {
					this.placeBlockAtCurrentPosition(world, PixelmonBlocks.healer.blockID,
							this.getPixelmonMetadata(PixelmonBlocks.healer.blockID, s.blockData[x][y][z]), x, y, z, bb);
				} else if (nbt.getString("id").equalsIgnoreCase("trade machine")) {
					this.placeBlockAtCurrentPosition(world, PixelmonBlocks.tradeMachine.blockID,
							this.getPixelmonMetadata(PixelmonBlocks.tradeMachine.blockID, s.blockData[x][y][z]), x, y, z, bb);
				} else if (nbt.getString("id").equalsIgnoreCase("pokemon pc")) {
					this.placeBlockAtCurrentPosition(world, PixelmonBlocks.pc.blockID,
							this.getPixelmonMetadata(PixelmonBlocks.pc.blockID, s.blockData[x][y][z]), x, y, z, bb);
				}
			}
			return true;
		}
	}

	protected int getMetadataWithOffset(int par1, int par2) {
		return BlockRotation.setBlockRotation(coordBaseMode, par1, par2);
	}

	protected int getPixelmonMetadata(int par1, int par2) {
		return BlockRotation.setPixelmonBlockRotation(coordBaseMode, par1, par2);
	}

	public boolean generate(World world, Random random) {
		return addComponentParts(world, random, boundingBox);
	}

	@Override
	protected void func_143012_a(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void func_143011_b(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

}
