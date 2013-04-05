package pixelmon.api;

import pixelmon.api.interactions.IInteraction;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonApi {
	public static void addInteraction(IInteraction interaction){
		EntityPixelmon.interactionList.add(interaction);
	}
}
