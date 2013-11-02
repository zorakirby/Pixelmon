package pixelmon.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.client.model.IModelCustom;

public class ModelCustomWrapper{
	
	IModelCustom model;
	float offsetX = 0, offsetY = 0, offsetZ =0;
	
	public ModelCustomWrapper(IModelCustom m){
		this.model = m;
	}
	
	public ModelCustomWrapper(IModelCustom m, float x, float y, float z){
		this.model = m;
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
	}
	
	public ModelCustomWrapper setOffsets(float x, float y, float z){
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
		return this;
	}
	
	public void render(float scale){
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		//GL11.glRotatef(90, 1, 0, 0);
		GL11.glTranslatef(offsetX, offsetZ, offsetY); //Z comes before Y because we are rotating by 90 first
		
		model.renderAll();
		GL11.glPopMatrix();
	}
}
