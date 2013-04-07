package pixelmon.api.interactions;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.entity.player.EntityPlayer;

public interface IInteraction {
	boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player);
}
