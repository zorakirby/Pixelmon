package pixelmon;

import net.minecraft.src.ModLoader;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		
		PixelmonEntityList.addRenderer()	
	}
}
