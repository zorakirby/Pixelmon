package pixelmon.structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.relauncher.Side;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonConfig;
import pixelmon.enums.EnumBiomes;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureRegistry {
	public static ArrayList<StructureData> scatteredStructures = new ArrayList<StructureData>();

	public static void loadStructures(Side side) {
		File f = null;
		if (side == Side.CLIENT)
			f = new File(Minecraft.getMinecraft().mcDataDir + "/resources/pixelmon/structures/standAlone");
		else if (side == Side.SERVER)
			f = new File(MinecraftServer.getServer().getFolderName() + "/resources/pixelmon/structures/standAlone");
		if (f != null && !f.isDirectory()) {
			System.out.println("Standalone structures directory is corrupted");
			return;
		}

		for (String filename : f.list(filter)) {
			StructureData data = loadStructureData(filename, f.getAbsolutePath());
			if (data != null)
				scatteredStructures.add(data);
		}
	}

	public static StructureData loadStructureData(String filename, String path) {
		StructureData data = new StructureData();
		int schind = filename.indexOf(".data");
		data.path = path + "/" + filename.substring(0, schind) + ".schematic";
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
