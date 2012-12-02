package pixelmon.entities.pixelmon.interactions;

import net.minecraft.src.EntityPlayer;
import pixelmon.entities.pixelmon.Entity8HoldsItems;

public abstract class PixelmonInteraction {

	Entity8HoldsItems pixelmon;
	public PixelmonInteraction(Entity8HoldsItems pixelmon) {
		this.pixelmon = pixelmon;
	}
	
	public abstract boolean interact(EntityPlayer par1EntityPlayer);

	public static PixelmonInteraction getInteraction(Entity8HoldsItems pixelmon) {
		if (pixelmon.getName().equalsIgnoreCase("Miltank"))
			return new MiltankInteraction(pixelmon);
		else if (pixelmon.getName().equalsIgnoreCase("Camerupt"))
			return new CameruptInteraction(pixelmon);
		
		return null;
	}
}
