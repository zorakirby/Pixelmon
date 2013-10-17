package pixelmon.client.models;

import net.minecraft.client.Minecraft;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.client.render.RenderResources;
import pixelmon.enums.EnumCustomModel;


public class ModelMossyRock extends ModelIcyRock{
	public ModelMossyRock(){
		super();
	}
	@Override
	public void renderInvBlock(BlockContainerPlus block, int meta, float scale) {
		Minecraft.getMinecraft().renderEngine.func_110577_a(RenderResources.mossrocktex);
		rock.render(scale);
	}

	@Override
	public void renderTileEntity(TileEntityDecorativeBase tileEnt, float scale) {
		Minecraft.getMinecraft().renderEngine.func_110577_a(RenderResources.mossrocktex);
		rock.render(0.0625F);
	}

}
