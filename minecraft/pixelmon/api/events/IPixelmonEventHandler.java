package pixelmon.api.events;

import net.minecraft.entity.player.EntityPlayer;

public interface IPixelmonEventHandler {
	public void eventFired(EventType eventType, EntityPlayer player);
}
