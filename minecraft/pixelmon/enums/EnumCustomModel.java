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
	Haunter ("/assets/pixelmon/models/haunter/haunter.obj"),
	Froslass("/pixelmon/client/models/smdFiles/froslass/froslass.pqc"),
	GlalieMatte("/pixelmon/client/models/objFiles/glalie/glalie_matte.obj"),
	GlalieReflective("/pixelmon/client/models/objFiles/glalie/glalie_reflective.obj"),
	
	//blocks
	EvoRock("/pixelmon/client/models/objFiles/blocks/evoRock/evoRock.obj"),
	PillarPlatform("/pixelmon/client/models/objFiles/blocks/pillar/pillar_platform.obj"),
	PillarColumn("/pixelmon/client/models/objFiles/blocks/pillar/pillar_column.obj"),
	PillarColumnFracturedBottom("/pixelmon/client/models/objFiles/blocks/pillar/pillar_column_fractured_bottom.obj"),
	PillarColumnFracturedTop("/pixelmon/client/models/objFiles/blocks/pillar/pillar_column_fractured_top.obj"),
	;
	
	
	String fileName;
	public IModelCustom theModel;
	
	public static HashMap<EnumCustomModel, IModelCustom> modelMap = new HashMap();
	EnumCustomModel(String fileName){
		this.fileName = fileName;
	}
	
	public static void preloadModels(){
		AdvancedModelLoader.registerModelHandler(ValveStudioModelLoader.instance);
		
		for(EnumCustomModel cm : EnumCustomModel.values()){
			try{
			cm.theModel = AdvancedModelLoader.loadModel(cm.fileName);
			}catch(Exception e){
				System.out.println("Could not load the model: " + cm.fileName);
				e.printStackTrace();
			}
		}
	}

}
