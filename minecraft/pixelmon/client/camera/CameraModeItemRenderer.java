package pixelmon.client.camera;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;

public class CameraModeItemRenderer extends ItemRenderer {

	public CameraModeItemRenderer(Minecraft par1Minecraft) {
		super(par1Minecraft);
	}
	
	@Override
	public void renderItemInFirstPerson(float par1){}

}
