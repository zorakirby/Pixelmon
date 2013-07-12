package pixelmon.structure.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import pixelmon.structure.SchematicImporter;
import pixelmon.structure.StructureData;
import pixelmon.structure.StructureDataAdv;

public class ComplexScattered extends StructureScattered{
	
	protected HashMap<StructureBoundingBox, SchematicImporter> components = new HashMap();
	protected ArrayList<StructureBoundingBox> list = new ArrayList();
	
	public static HashMap<String, StructureDataAdv> registry = new HashMap();
	
	public ComplexScattered(Random rand, int x, int y, int z, String registeredName) {
		super(rand, x, y, z, -1, -1, -1);
		for(int i = 0; i < registry.get(registeredName).components.size(); i++){
			SchematicImporter schem = registry.get(registeredName).components.get(i);
			StructureBoundingBox bb = initBB(schem, x, y, z);
			list.add(i, bb);
			components.put(bb, schem);
		}
	}
	
	
	public static final void registerStructures(){
		registerStructure("temple", "/pixelmon/schematics/Temple.data");
	}
	
	public static void registerStructure(String name, String filename){
		StructureDataAdv dataAdv = new StructureDataAdv(filename);
		registry.put(name, dataAdv);
	}

	protected StructureBoundingBox initBB(SchematicImporter schematic, int x, int y, int z) {
		int trueX = x + schematic.offsetX;
		int trueY = y + schematic.offsetY;
		int trueZ = z + schematic.offsetZ;
		StructureBoundingBox bb = null;
		switch (this.coordBaseMode) {
		case 0:
		case 2:
			bb = new StructureBoundingBox(trueX, trueY, trueZ, trueX + schematic.width - 1,  trueY + schematic.height - 1, trueZ + schematic.length - 1);
			break;
		default:
			bb = new StructureBoundingBox(trueX, trueY, trueZ, trueX + schematic.length - 1,  trueY + schematic.height - 1, trueZ + schematic.width - 1);
		}
		return bb;
	}
	
	protected int getMetadataWithOffset(int par1, int par2) {
		return BlockRotation.setBlockRotation (coordBaseMode, par1, par2);
	}

	public boolean generate(World world, Random random) {
		//System.out.println("Size of component list = " + components.size());
		for(int i = 0; i < list.size(); i++)
			addComponentParts(world, random, list.get(i));
		fixAllLighting(world);
		return true;
	}

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox bb){
		SchematicImporter schem = components.get(bb);
		System.out.println("now placing complex structure at (" + bb.minX + ", " + bb.minY + ", " + bb.minZ + ")");
		if (!this.canStructureFitAtCoords(world, bb, schem.creator)) {
			return false;
		} else {
			for (int x = 0; x < schem.width; x++)
				for (int z = 0; z < schem.length; z++)
					for (int y = 0; y < schem.height; y++) {
						try {
							this.placeBlockAtCurrentPosition(world, Math.abs(schem.blocks[x][y][z]), this.getMetadataWithOffset(schem.blocks[x][y][z], schem.blockData[x][y][z]), x + schem.offsetX, y + schem.offsetY, z + schem.offsetZ, bb);
						} catch (Exception e) {
						//	System.out.println(e.getMessage());
						}
					}
			return true;
		}
	}
	
	protected boolean canStructureFitAtCoords(World world, StructureBoundingBox boundingBox, StructureData structureData) {
		return true;
	}

	protected void fixAllLighting(World world){
		for(StructureBoundingBox bb : components.keySet())
			fixLighting(world, bb);
	}
}
