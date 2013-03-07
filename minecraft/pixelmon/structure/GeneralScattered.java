package pixelmon.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class GeneralScattered extends StructureScattered {

	private static SchematicImporter s;

	// TODO implement a system in which we can have special pokemon spawn at
	// special coordinates.

	public GeneralScattered(Random par1Random, int par2, int par3, int par4, SchematicImporter schematic) {
		super(par1Random, par2, par3, par4, schematic.width, schematic.height, schematic.length);
		s = schematic;
	}

	public boolean addComponentParts(World world, Random par2Random, StructureBoundingBox bb) {
		if (!this.func_74935_a(world, bb, 0)) {
			return false;
		} else {
			for (int x = 0; x < s.width; x++)
				for (int z = 0; z < s.length; z++)
					for (int y = 0; y < s.height; y++) {
						try {
							this.placeBlockAtCurrentPosition(world, s.blocks[x][y][z], this.getMetadataWithOffset(s.blocks[x][y][z], s.blockData[x][y][z]), x, y, z, bb);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
			// id, metadata
			return true;
		}
	}

	protected int getMetadataWithOffset(int par1, int par2) {
		return BlockRotation.setBlockRotation (coordBaseMode, par1, par2);
	}

	public boolean generate(World world, Random random) {
		return addComponentParts(world, random, boundingBox);
	}

}
