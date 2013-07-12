package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.biped.SkeletonBiped;

public class ModelSnorunt extends PixelmonModelBase{
	PixelmonModelRenderer body, footL, footR;
	
	public ModelSnorunt(){
	 body = new PixelmonModelRenderer(this, "Body");
	 body.setRotationPoint(0, 18, 0);
     body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/models/snorunt/snorunt_body.obj")));
     footL = new PixelmonModelRenderer(this, 0, 0);
     footL.setRotationPoint(-1.341F, 4.5F, 0);
     footL.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/models/snorunt/snorunt_foot.obj"), 0, 0, 0));
     footR = new PixelmonModelRenderer(this, 0, 0);
     footR.setRotationPoint(1.341F, 4.5F, 0);
     footR.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/models/snorunt/snorunt_foot.obj"), 0, 0, 0));
     body.addChild(footL);
     body.addChild(footR);
     float legspeed = 0.5F;
     float legRotationLimit = 1.4F;
     ModuleLeg footModuleL = new ModuleLeg(footL, EnumLeg.FrontLeft,
             EnumPhase.InPhase, legRotationLimit, legspeed);
     ModuleLeg footModuleR = new ModuleLeg(footR, EnumLeg.FrontRight,
             EnumPhase.InPhase, legRotationLimit, legspeed);
     skeleton = new SkeletonBiped(body, null, null, null,
                                  footModuleL, footModuleR, null);
	}
	
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        body.render(f5);
    }
    
    private void setRotation(PixelmonModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
