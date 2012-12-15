package pixelmon.structure;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumScatteredStructure;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenScatteredFeature extends MapGenScatteredFeature implements IWorldGenerator {

	private static boolean hasGenerated = false;

	EnumScatteredStructure structure;
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
//		if (hasGenerated) {
//			hasGenerated = true;
		int xPos = random.nextInt(16) + chunkX * 16;
		int yPos = 64;
		int zPos = random.nextInt(16) + chunkZ * 16;
		structure = structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos));
		if(structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos)) != null){// May need to work on this stuff,
//			System.out.println(structure.getStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos)));
//			System.out.println(structure.getSchematicPath());
			if(world.getBiomeGenForCoords(xPos, zPos) == structure.biomeToSpawnIn() && structure.getRarity() == 1){
				SchematicImporter s = new SchematicImporter(structure.getSchematicPath());
				s.readSchematic();
				GeneralScattered g = new GeneralScattered(random, xPos, structure.getY(yPos), zPos, s);
				hasGenerated = g.generate(world, random);
				if(structure.spawnPokemon){
					if (EnumPokemon.hasPokemon(structure.pokemonSpawn)) {
						Entity pokemon = PixelmonEntityList.createEntityByName(structure.pokemonSpawn, world);
						pokemon.setPosition(xPos + structure.pokemonX, structure.getY(yPos) + structure.pokemonY /*+ Can't figure out what to put here to get the pokemon to spawn at the correct spot.*/ , zPos + structure.pokemonZ);
						System.out.println("A Legendary " + structure.pokemonSpawn + " has Spawned at: " + pokemon.posX + ", " + pokemon.posY + ", " + pokemon.posZ);
						((EntityPixelmon)pokemon).setNickname(structure.getSchematicPath());
						if(new Random().nextInt(8059) == 1)
							((EntityPixelmon)pokemon).setIsShiny(true);
						world.spawnEntityInWorld(pokemon);
					}
				}
				System.out.println("A structure has Generated at: " + xPos + ", " + structure.getY(yPos) + ", " + zPos);
			}
		}
	}
}
