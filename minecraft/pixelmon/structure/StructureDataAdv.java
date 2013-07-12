package pixelmon.structure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

import pixelmon.blocks.apricornTrees.BlockApricornTree;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonBlocksApricornTrees;
import pixelmon.enums.EnumBiomes;

import net.minecraft.block.Block;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureDataAdv extends StructureData{
	
	public ArrayList<SchematicImporter> components = new ArrayList();
	public EnumSet<SpecialOption> specials = EnumSet.noneOf(SpecialOption.class);
	protected int arrayIndex = 0;
	
	public StructureDataAdv(String filename) {
		if(PixelmonBlocks.anvil == null){
			System.out.println("StructureDataAdv should not be used until after PixelmonBlocks has been initialized!");
			return;
		}
		BufferedReader br;
		try {
			this.path = StructureDataAdv.class.getResource(filename).getFile();
			br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			while (line != null) {
				if (line.startsWith("biomes:")) {
					String[] biomeList = line.split(":")[1].split(";");
					biomes = new BiomeGenBase[biomeList.length];
					int i = 0;
					for (String biomeName : biomeList) {
						EnumBiomes e = EnumBiomes.parseBiome(biomeName);
						biomes[i] = e.getBiome();
						i++;
					}
				} else if (line.startsWith("hasPokemon")) {
					if (line.split(":")[1].equalsIgnoreCase("true"))
						hasPokemon = true;
				} else if (line.startsWith("depth")) {
					depth = Integer.parseInt(line.split(":")[1]);
				} else if(line.startsWith("swap")){
					swapBlocks(line.split(":", 2)[1]);
				} else if(line.startsWith("include")){
					include(line.split(":", 2)[1]);
				} else if(line.startsWith("special")){
					useSpecial(line.split(":",2)[1]);
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(SchematicImporter s : components){
			s.readHeader();
			s.readSchematic();
		}
	}
	
	private void swapBlocks(String line){
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
			if(filter == null)
				filter = new HashMap();
			filter.put(key, value);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void include(String line){
		String[] params = line.split(":");
		System.out.println(line + " is being included!");
		int lastSlash = path.lastIndexOf("/");
		String schematicPath = path.substring(0, lastSlash+1) + params[0];
		SchematicImporter schem = new SchematicImporter(schematicPath, this);
		if(params.length > 1){
			String[] xyz = params[1].replaceAll("<", "").replaceAll(">", "").split(",");
			int[] pos = new int[3];
			for(int i = 0; i < 3; i++)
				pos[i] = Integer.parseInt(xyz[i]);
			schem.setOffset(pos[0], pos[1], pos[2]);
		}
		components.add(arrayIndex, schem);
		arrayIndex++;
	}
	
	private void useSpecial(String line){
		if(line.equalsIgnoreCase("wool=apricorntrees"))
			specials.add(SpecialOption.WoolApricorns);
	}
	
	@Override
	public int[] doFilter(int[] idAndMeta){
		idAndMeta = super.doFilter(idAndMeta);
		for(SpecialOption s : specials){
			idAndMeta = s.doFilter(idAndMeta);
		}
		return idAndMeta;
	}
	
	
	public enum SpecialOption{
		WoolApricorns {
			@Override
			public int[] doFilter(int[] idAndMeta) {
				if(idAndMeta[0] == Block.cloth.blockID){
					int id = 0;
					switch(idAndMeta[1]){
					case 0 : id = PixelmonBlocksApricornTrees.apTreeWhiteId;
					break;
					case 6 : id = PixelmonBlocksApricornTrees.apTreeOrangeId; //acculy iz Pink, but cal'd 'OrangeId'
					break;
					case 14 : id = PixelmonBlocksApricornTrees.apTreeRedId;
					break;
					case 4 : id = PixelmonBlocksApricornTrees.apTreeYellowId;
					break;
					case 13 : id = PixelmonBlocksApricornTrees.apTreeGreenId;
					break;
					case 11 : id = PixelmonBlocksApricornTrees.apTreeBlueId;
					break;
					case 15 : id = PixelmonBlocksApricornTrees.apTreeBlackId;
					break;
					default : id = PixelmonBlocksApricornTrees.apTreeWhiteId;
					break;
					}
					idAndMeta[0] = id;
					idAndMeta[1] = BlockApricornTree.numStages - 1;
				}
				return idAndMeta;
			}
		},
		;
		
		public abstract int[] doFilter(int[] idAndMeta);
		
	}

	
}
