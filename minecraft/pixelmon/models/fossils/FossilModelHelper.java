package pixelmon.models.fossils;

import java.util.ArrayList;

import pixelmon.blocks.TileEntityFossilMachine;
import pixelmon.enums.EnumPokeballs;

public class FossilModelHelper {
	
	private static ArrayList<ModelFossil> fossilModels = new ArrayList<ModelFossil>();
	
	static{
		fossilModels.add(new ModelHelixFossil());
		fossilModels.add(new ModelDomeFossil());
		fossilModels.add(new ModelOldAmber());
		fossilModels.add(new ModelRootFossil());
		fossilModels.add(new ModelClawFossil());
		fossilModels.add(new ModelSkullFossil());
//		fossilModels.add(new ModelArmorFossil());
		fossilModels.add(new ModelCoverFossil());
//		fossilModels.add(new ModelPlumeFossil());
	}

	public static void renderModel(String fossilName, TileEntityFossilMachine tile, float f){
		for (ModelFossil fossil: fossilModels)
			if (fossil.getFossilName() == fossilName) fossil.renderModel(tile, f);
	}
	
	public static ModelFossil getModel(String fossilName){
		ModelFossil fossilToRender = null;
		for (ModelFossil fossil: fossilModels)
			if (fossil.getFossilName() == fossilName){
				fossilToRender = fossil;
			}
		return fossilToRender;
	}
	
}
