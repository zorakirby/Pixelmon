package pixelmon.structure;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MapGenScatteredFeature;
import net.minecraft.src.World;

public class WorldGenScatteredFeature extends MapGenScatteredFeature implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int xPos = random.nextInt(16) + chunkX * 16;
		int zPos = random.nextInt(16) + chunkZ * 16;
		SchematicImporter s = new SchematicImporter("resources/pixelmon/structures/standAlone/Mansion.schematic");
		s.readSchematic();
		GeneralScattered g = new GeneralScattered(random, xPos, zPos, s);
		g.generate(world, random);
		System.out.println("A structure has Generated at " + xPos + ", " + zPos);
	}

}
