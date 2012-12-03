package pixelmon.structure;

import java.util.Random;

import pixelmon.enums.EnumScatteredStructure;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MapGenScatteredFeature;
import net.minecraft.src.World;

public class WorldGenScatteredFeature extends MapGenScatteredFeature implements IWorldGenerator {

	private static boolean hasGenerated = false;

	EnumScatteredStructure structure;
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
//		if (hasGenerated) {
//			hasGenerated = true;
		int xPos = random.nextInt(16) + chunkX * 16;
		int yPos = 64;
		int zPos = random.nextInt(16) + chunkZ * 16;
		if(structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos)) != null){// May need to work on this stuff,
			structure = structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos));
			if(world.getBiomeGenForCoords(xPos, zPos) == structure.biomeToSpawnIn() && structure.rarity == 1){
				System.out.println(structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos)));
				SchematicImporter s = new SchematicImporter(structure.getSchematicPath());
				s.readSchematic();
				GeneralScattered g = new GeneralScattered(random, xPos, yPos, zPos, s);
				hasGenerated = g.generate(world, random);
				System.out.println("A structure has Generated at " + xPos + ", " + zPos);
			}
		}
	}
}
