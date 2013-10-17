package pixelmon.client.models;

import net.minecraft.client.Minecraft;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.client.render.RenderResources;
import pixelmon.enums.EnumCustomModel;


public class ModelIcyRock extends ModelEntityBlock{
	PixelmonModelRenderer rock;
	public ModelIcyRock(){
		rock = new PixelmonModelRenderer(this);
		rock.addCustomModel(new ModelCustomWrapper(EnumCustomModel.EvoRock.theModel));
	}
	@Override
	public void renderInvBlock(BlockContainerPlus block, int meta, float scale) {
		Minecraft.getMinecraft().renderEngine.func_110577_a(RenderResources.icyrocktex);
		rock.render(scale);
	}

	@Override
	public void renderTileEntity(TileEntityDecorativeBase tileEnt, float scale) {
		Minecraft.getMinecraft().renderEngine.func_110577_a(RenderResources.icyrocktex);
		rock.render(0.0625F);
	}

}
