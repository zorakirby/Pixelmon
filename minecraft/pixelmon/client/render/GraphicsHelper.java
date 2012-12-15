package pixelmon.client.render;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GraphicsHelper {
	static float spinCount = 0;
	public static void drawModelToScreen(float size, int xSize, int ySize, int xPos, int yPos, Entity entity, GuiScreen gui, boolean spin) {
		//GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		//GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (xPos), (float) (yPos), 50.0F);
		//if (entity.width > entity.height) {
			//size = xSize / entity.width;
		//} else
			//size = xSize / entity.height;
		//GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
		GL11.glScalef(size, size, size);
		//GL11.glTranslatef(0, 0, size);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		//float var12 = (float) (yPos + 75 - 50) - ySize;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		//RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(100, 0F, 1F, 0F);
		entity.rotationYaw = 90;
		if (spin)
			GL11.glRotatef(spinCount += 0.55F, 0.0F, 1F, 0.0F);
		RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 90.0F, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		//RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
		//GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		//GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}
	
	public static void drawEntityToScreen(int x, int y, int w, int l, Entity e, float pt, boolean spin)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		RenderHelper.enableStandardItemLighting();
		GL11.glTranslatef(x, y, 100);
		float eheight = l / e.height / 4;
		float ewidth = l / e.width / 4;
		float scalar = eheight > ewidth?eheight:ewidth;
		GL11.glScalef(scalar, scalar, scalar);
		GL11.glRotatef(180, 0, 0, 1);
		if(spin)
			GL11.glRotatef(spinCount += 0.66F, 0, 1, 0);
		RenderHelper.enableStandardItemLighting();
		try
		{
			RenderManager.instance.renderEntityWithPosYaw(e, 0, e.yOffset, 0, 0, pt);
			RenderManager.instance.playerViewY = 180.0F;
		} catch (Exception ex)
		{
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
	}
}
