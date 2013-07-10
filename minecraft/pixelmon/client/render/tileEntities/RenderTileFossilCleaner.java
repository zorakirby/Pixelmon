package pixelmon.client.render.tileEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import pixelmon.blocks.TileEntityFossilCleaner;
import pixelmon.blocks.TileEntityFossilMachine;
import pixelmon.client.models.ModelFossilCleaner;
import pixelmon.client.render.RenderResources;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonItemsFossils;

public class RenderTileFossilCleaner extends TileEntitySpecialRenderer {
	private ModelFossilCleaner model;

	public RenderTileFossilCleaner() {
		model = new ModelFossilCleaner();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
		TileEntityFossilCleaner tileEntity = (TileEntityFossilCleaner) tile;
		int i = tile.getBlockMetadata(); // this is for rotation
		int j = 0;

		if (i == 0) {
			j = 0;
		}

		if (i == 1) {
			j = 90;
		}

		if (i == 2) {
			j = 180;
		}

		if (i == 3) {
			j = 270;
		}

		if (i < 0)
			return;
		if (Minecraft.getMinecraft().theWorld.getBlockId(tile.xCoord, tile.yCoord - 1, tile.zCoord) == PixelmonBlocks.pc.blockID)
			return;

		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F); // size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		func_110628_a(RenderResources.fossilCleaningMachine);
		if (tileEntity.isOn())
			model.rotateModel(tileEntity);
		else
			model.clearRotation();
		model.renderModel(tileEntity, 0.0625F);
		GL11.glTranslatef(0, -1.3F, 0);
		renderModel(tileEntity, 0.0625f);
		func_110628_a(RenderResources.fossilCleaningMachine);
		GL11.glPopMatrix(); // end
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		model.renderGlass(tileEntity, 0.0625F);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	EntityItem uncoveredEntity;

	public String fossilTexture(TileEntityFossilCleaner tile) {
		return PixelmonItemsFossils.getFossilFromIndex(tile.itemInCleaner).getModelName().toLowerCase();
	}

	public void renderModel(TileEntityFossilCleaner tile, float f) {
		if (tile.itemInCleaner != -1)
			if (PixelmonItemsFossils.getFossilFromIndex(tile.itemInCleaner) != null) {
				func_110628_a(new ResourceLocation("/pixelmon/texture/fossils/" + fossilTexture(tile) + ".png"));
				GL11.glTranslatef(0, 2.23F, 0);
				if (PixelmonItemsFossils.getFossilFromIndex(tile.itemInCleaner).getModel() != null)
					PixelmonItemsFossils.getFossilFromIndex(tile.itemInCleaner).getModel().renderModel(f);
			} else if (PixelmonItemsFossils.getCoveredFossilFromIndex(tile.itemInCleaner) != null) {
				if (uncoveredEntity != null)
					if (uncoveredEntity.getEntityItem().itemID != tile.itemInCleaner)
						uncoveredEntity = null;
				if (uncoveredEntity == null) {
					uncoveredEntity = new EntityItem(tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord, new ItemStack(
							PixelmonItemsFossils.getCoveredFossilFromIndex(tile.itemInCleaner)));
				}
				GL11.glTranslatef(0, 0.15F, 0);
				GL11.glRotatef(-1*tile.timer/1.5f * 57.296f, 0.0F, 1.0F, 0.0F);
				((RenderItem) RenderManager.instance.getEntityClassRenderObject(EntityItem.class)).doRenderItem(uncoveredEntity, 0, 1.8, 0, tile.timer / 1.5f, 0);
				GL11.glRotatef(tile.timer/1.5f * 57.296f, 0.0F, 1.0F, 0.0F);
			}
	}
}
