package pixelmon.client.models.smd;

import java.net.URL;

import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.IModelCustomLoader;
import net.minecraftforge.client.model.ModelFormatException;

public class ValveStudioModelLoader implements IModelCustomLoader{

	private static final String[] types = {"pqc"}; //Pseudo Quake C
	
	public static final ValveStudioModelLoader instance = new ValveStudioModelLoader();
	
	@Override
	public String getType() {
		return "Valve Studio Model Collection";
	}

	@Override
	public String[] getSuffixes() {
		return types;
	}

	@Override
	public IModelCustom loadInstance(String resourceName, URL resource)
			throws ModelFormatException {
		return new ValveStudioModel(resourceName, resource);
	}

}
