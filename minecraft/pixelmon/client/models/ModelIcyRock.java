package pixelmon.client.models;

import net.minecraft.client.Minecraft;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.enums.EnumCustomModel;


public class ModelIcyRock extends ModelEntityBlock{
	PixelmonModelRenderer rock;
	public ModelIcyRock(){
		rock = new PixelmonModelRenderer(this);
		rock.addCustomModel(new ModelCustomWrapper(EnumCustomModel.EvoRock.theModel));
	}
	@Override
	public void renderInvBlock(BlockContainerPlus block, int meta, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture("/pixelmon/models/icyrock/icyrocktex.png");
		rock.render(scale);
	}

	@Override
	public void renderTileEntity(TileEntityDecorativeBase tileEnt, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture("/pixelmon/models/icyrock/icyrocktex.png");
		rock.render(0.0625F);
	}

}
