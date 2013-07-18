package pixelmon.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonConfig;
import pixelmon.enums.EnumBiomes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureRegistry {
	public static ArrayList<StructureData> scatteredStructures = new ArrayList<StructureData>();


	public static void loadStructures(Side side) {
		AbstractResourcePack resourcePack = (AbstractResourcePack) FMLClientHandler.instance().getResourcePackFor("pixelmon");
		System.out.println("Attempting to load structures.....");
		int i = 1;
		while (resourcePack.func_110589_b(new ResourceLocation("pixelmon:structures/standAlone/pokecenter" + i + ".data"))) {
			System.out.println("Struct while loop");
			StructureData data = loadStructureData("pokecenter" + i, "pixelmon:structures/standAlone");
			i++;
			if (data != null) {
				scatteredStructures.add(data);
				System.out.println("Structure added!");
			} else
				System.out.println("No Structures added ):");
		}
	}

	private static StructureData loadStructureData(String filename, String path) {
		StructureData data = new StructureData();
		AbstractResourcePack resourcePack = (AbstractResourcePack) FMLClientHandler.instance().getResourcePackFor("pixelmon");
		
		if (!resourcePack.func_110589_b(new ResourceLocation("pixelmon:structures/standAlone/" + filename +".schematic")))
			return null;
		data.path = path + "/" + filename +".schematic";
		System.out.println(data.path);
		InputStream is;
		BufferedReader br;
		try {
			is = resourcePack.func_110590_a(new ResourceLocation(path + "/" + filename +".data"));
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));

			String line = br.readLine();
			while (line != null) {
				if (line.contains("biomes:")) {
					String[] biomeList = line.split(":")[1].split(";");
					data.biomes = new BiomeGenBase[biomeList.length];
					int i = 0;
					for (String biomeName : biomeList) {
						EnumBiomes e = EnumBiomes.parseBiome(biomeName);
						data.biomes[i] = e.getBiome();
						i++;
					}
				} else if (line.startsWith("hasPokemon")) {
					if (line.split(":")[1].equalsIgnoreCase("true"))
						data.hasPokemon = true;
				} else if (line.startsWith("depth")) {
					data.depth = Integer.parseInt(line.split(":")[1]);
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SchematicImporter s = new SchematicImporter(data.path);
		s.readHeader();
		data.xSize = s.width;
		data.ySize = s.height;
		data.zSize = s.length;
		return data;
	}

	public static StructureData getScatteredStructureFromBiome(BiomeGenBase biomeGenForCoords) {
		ArrayList<StructureData> possibleStructures = new ArrayList<StructureData>();
		for (StructureData s : scatteredStructures) {
			for (BiomeGenBase b : s.biomes)
				if (b == biomeGenForCoords)
					possibleStructures.add(s);
		}
		if (possibleStructures.size() == 0)
			return null;
		else
			return possibleStructures.get(RandomHelper.getRandomNumberBetween(0, possibleStructures.size() - 1));
	}
}
