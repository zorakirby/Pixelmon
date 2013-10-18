package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelOBJWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.quadruped.SkeletonQuadruped;


public class ModelBeldum extends PixelmonModelBase {
	
	PixelmonModelRenderer Body, Head;
	
	
	public ModelBeldum() {
		textureWidth = 64;
		textureHeight = 32;
		Body = new PixelmonModelRenderer(this, "Body");
		Body.setRotationPoint(0, 3.744F, 0);
		Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/beldum/Body.obj")));
		Head = new PixelmonModelRenderer(this, 0 ,0);
		Head.setRotationPoint(-0.049F, -0.948F, 3.616F);
		Head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/beldum/Head.obj")));
		Body.addChild(Head);
		
		
		int degrees = 180;
		float radians = (float) Math.toRadians(degrees);
		
		setRotation(Body, radians, 0, 0);
		ModuleHead headModule = new ModuleHead(Head);
		
		skeleton = new SkeletonQuadruped(Body, headModule, null, null, null, null, null);

	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Body.render(f5);
	}

	private void setRotation(PixelmonModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
	}
		
}
