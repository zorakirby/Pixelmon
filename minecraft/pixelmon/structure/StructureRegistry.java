package pixelmon.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.server.FMLServerHandler;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonConfig;
import pixelmon.enums.EnumBiomes;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureRegistry {
	public static ArrayList<StructureData> scatteredStructures = new ArrayList<StructureData>();

	public static void loadStructures(Side side) {
		int i = 1;
		while (StructureRegistry.class.getResourceAsStream("/pixelmon/structure/standAlone/" + "pokecenter" + i + ".data") != null) {
			StructureData data = loadStructureData("pokecenter" + i, "/pixelmon/structure/standAlone");
			i++;
			if (data != null)
				scatteredStructures.add(data);
		}
	}

	public static StructureData loadStructureData(String filename, String path) {
		StructureData data = new StructureData();
		if (StructureRegistry.class.getResourceAsStream("/pixelmon/structure/standAlone/" + filename + ".schematic") == null)
			return null;
		data.path = path + "/" + filename + ".schematic";
		InputStream is;
		BufferedReader br;
		try {
			is = StructureRegistry.class.getResourceAsStream("/pixelmon/structure/standAlone/" + filename + ".data");
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));

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
				} else if(line.startsWith("swap")){
					swapBlocks(data, line.split(":", 2)[1]);
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
	
	private static void swapBlocks(StructureData data, String line){
		String[] params = line.split(":");
		int key = Integer.parseInt(params[0]);
		try{
			int value = -1;
			try{
				value = Integer.parseInt(params[1]);
			}catch(Exception e){
				Block theBlock = (Block) PixelmonBlocks.class.getField(params[1]).get(null);
				value = theBlock.blockID;
			}
			if(data.filter == null)
				data.filter = new HashMap();
			data.filter.put(key, value);
		}catch(Exception e){
			e.printStackTrace();
		}
		
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
