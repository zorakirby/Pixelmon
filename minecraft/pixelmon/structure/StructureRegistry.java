package pixelmon.structure;

import galileo.collada.nodes.newparam;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class StructureRegistry {
	public static ArrayList<StructureData> scatteredStructures;
	
	public static void loadStructures(){
		File f = new File("resources/pixelmon/structures/standAlone");
		if (!f.isDirectory()){
			System.out.println("Standalone structures directory is corrupted");
			return;
		}
		
		for (String filename: f.list(filter)){
			
		}
	}
	
	static FilenameFilter filter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			if (name.endsWith(".schematic"))
				return true;
			return false;
		}
	};
}
