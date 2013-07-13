package pixelmon.client.models;

import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class ModelOBJWrapper{
	
	IModelCustom model;
	float offsetX = 0, offsetY = 0, offsetZ =0;
	
	public ModelOBJWrapper(IModelCustom m){
		this.model = m;
	}
	
	public ModelOBJWrapper(IModelCustom m, float x, float y, float z){
		this.model = m;
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
	}
	
	public ModelOBJWrapper setOffsets(float x, float y, float z){
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
		return this;
	}
	
	public void render(float scale){
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glTranslatef(offsetX, offsetZ, offsetY); //Z comes before Y because we are rotating by 90 first
		
		model.renderAll();
		GL11.glPopMatrix();
	}
}
