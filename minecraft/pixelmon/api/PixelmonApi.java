package pixelmon.api;

import pixelmon.Pixelmon;
import pixelmon.api.interactions.IInteraction;
import pixelmon.client.ClientProxy;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonApi {
	public static void addInteraction(IInteraction interaction){
		EntityPixelmon.interactionList.add(interaction);
	}
	
	public static void addModelLibrary(String location){
		ClientProxy.modelPaths.add(0, location);
	}
}
