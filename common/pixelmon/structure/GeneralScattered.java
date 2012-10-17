package pixelmon.structure;

import java.util.Random;

import net.minecraft.src.StructureBoundingBox;
import net.minecraft.src.World;

public class GeneralScattered extends StructureScattered{

	private static SchematicImporter s;
	
	//TODO implement a system in which we can have special pokemon spawn at special coordinates.
	
	protected GeneralScattered(Random par1Random, int par2, int par3, SchematicImporter schematic) {
		super(par1Random, par2, 64, par3, s.width, s.height, s.length);
	}

    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
		return false;
    }
	
}
