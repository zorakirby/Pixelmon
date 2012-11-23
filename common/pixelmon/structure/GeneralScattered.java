package pixelmon.structure;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import net.minecraft.src.Chunk;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.World;

public class GeneralScattered extends StructureScattered {

	private static SchematicImporter s;

	// TODO implement a system in which we can have special pokemon spawn at
	// special coordinates.

	public GeneralScattered(Random par1Random, int par2, int par3, SchematicImporter schematic) {
		super(par1Random, par2, 64, par3, schematic.width, schematic.height, schematic.length);
		s = schematic;
	}

	public boolean addComponentParts(World world, Random par2Random, StructureBoundingBox bb) {
		for (int x = 0; x < s.width; x++)
			for (int z = 0; z < s.length; z++)
				for (int y = 0; y < s.height; y++) {
					try {
						this.placeBlockAtCurrentPosition(world, s.blocks[x][y][z], s.blockData[x][y][z], x, y, z, bb);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
		// id, metadata
		return true;
	}

	public void generate(World world, Random random) {
		addComponentParts(world, random, boundingBox);
	}

}
