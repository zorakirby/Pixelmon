package pixelmon.client.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.client.render.RenderResources;
import pixelmon.enums.EnumCustomModel;

public class ModelLolVase extends ModelEntityBlock{
	PixelmonModelRenderer vase;
	public ModelLolVase(){
		vase = new PixelmonModelRenderer(this);
		vase.addCustomModel(new ModelCustomWrapper(EnumCustomModel.LolVase.theModel));
	}
	@Override
	public void renderInvBlock(BlockContainerPlus block, int meta, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture(RenderResources.simpleGradient);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		vase.render(scale);
		GL11.glShadeModel(GL11.GL_FLAT);
	}

	@Override
	public void renderTileEntity(TileEntityDecorativeBase tileEnt, float scale) {
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glPushMatrix();
		//GL11.glRotatef(-90, 1, 0, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(RenderResources.simpleGradient);
		vase.render(0.0625F);
		GL11.glPopMatrix();
		GL11.glShadeModel(GL11.GL_FLAT);
	}

}
