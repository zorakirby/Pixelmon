package pixelmon.enums;

import java.util.HashMap;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.smd.ValveStudioModelLoader;

/**
 *
 *This is an Enum for storing custom models. If a Model class tries to initialize a custom model itself, the model will load upon joining the game, which is totally uncool
 *
 */
public enum EnumCustomModel{
	//pokemon
	Froslass("/pixelmon/models/froslass/froslass.pqc"),
	GlalieMatte("/pixelmon/models/glalie/glalie_matte.obj"),
	GlalieReflective("/pixelmon/models/glalie/glalie_reflective.obj"),
	
	//blocks
	EvoRock("/pixelmon/models/icyrock/icyrock.obj"),
	PillarPlatform("/pixelmon/models/pillar/pillar_platform.obj"),
	PillarColumn("/pixelmon/models/pillar/pillar_column.obj"),
	PillarColumnFracturedBottom("/pixelmon/models/pillar/pillar_column_fractured_bottom.obj"),
	PillarColumnFracturedTop("/pixelmon/models/pillar/pillar_column_fractured_top.obj");
	
	
	
	//fields
	String fileName;
	public IModelCustom theModel;
	
	public static HashMap<EnumCustomModel, IModelCustom> modelMap = new HashMap();
	EnumCustomModel(String fileName){
		this.fileName = fileName;
	}
	
	public static void preloadModels(){
		AdvancedModelLoader.registerModelHandler(ValveStudioModelLoader.instance);
		
		for(EnumCustomModel cm : EnumCustomModel.values()){
			cm.theModel = AdvancedModelLoader.loadModel(cm.fileName);
		}
	}

}
