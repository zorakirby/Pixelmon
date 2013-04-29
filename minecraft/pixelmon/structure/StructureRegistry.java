package 	pixelmon.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;

import pixelmon.RandomHelper;
import pixelmon.enums.EnumBiomes;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureRegistry {
	public static ArrayList<StructureData> scatteredStructures = new ArrayList<StructureData>();

	public static void loadStructures() {
		File f = new File(MinecraftServer.getServer().getFolderName() + "/resources/pixelmon/structures/standAlone");
		if (!f.isDirectory()) {
			System.out.println("Standalone structures directory is corrupted");
			return;
		}

		for (String filename : f.list(filter)) {
			StructureData data = loadStructureData(filename, f.getAbsolutePath());
			if (data != null)
				scatteredStructures.add(data);
		}
	}

	private static StructureData loadStructureData(String filename, String path) {
		StructureData data = new StructureData();
		int schind = filename.indexOf(".data");
		data.path = path + "/" + filename.substring(0, schind) + ".schematic";
		if (!new File(data.path).exists())
			return null;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path + "/" + filename));
			String line = br.readLine();
			while (line != null) {
				if (line.startsWith("biomes:")) {
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

	static FilenameFilter filter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			if (name.endsWith(".data"))
				return true;
			return false;
		}
	};

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
