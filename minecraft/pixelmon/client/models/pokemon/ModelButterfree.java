// Date: 4/29/2013 4:31:52 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelOBJWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.animations.bird.SkeletonBird;

public class ModelButterfree extends PixelmonModelBase
{
    //fields

    PixelmonModelRenderer Body, Head, LeftWing, RightWing;

    public ModelButterfree()
    {
        textureWidth = 64;
        textureHeight = 32;
        Body = new PixelmonModelRenderer(this, "Body");
        Body.setRotationPoint(0, 35, 0);
        Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/butterfree/Body.obj")));
        Head = new PixelmonModelRenderer(this, 0, 0);
        Head.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/butterfree/Head.obj")));
        Head.setRotationPoint(-0.2F, 3, 0);
        LeftWing = new PixelmonModelRenderer(this, 0, 0);
        LeftWing.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/butterfree/LeftWing.obj")));
        LeftWing.setRotationPoint(-0.2F, 3, -0.4F);
        RightWing = new PixelmonModelRenderer(this, 0, 0);
        RightWing.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/butterfree/RightWing.obj")));
        RightWing.setRotationPoint(-0.2F, 3, -0.4F);
        Body.addChild(Head);
        Body.addChild(LeftWing);
        Body.addChild(RightWing);
        
        int degrees = 180;
        float radians = (float) Math.toRadians(degrees);
        
        setRotation(Body, radians, 0, 0);
        

        float legspeed = 0.5F;
        float legRotationLimit = 1.4F;
        
        ModuleHead headModule = new ModuleHead(Head);
        
        
		
        skeleton = new SkeletonBiped(Body, headModule, null, null, null, null, null);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        Body.render(f5);
    }

    private void setRotation(PixelmonModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
    	LeftWing.rotateAngleY = MathHelper.cos(f2 + 3.14159F) * .5F - 37F;
		RightWing.rotateAngleY = MathHelper.cos(f2) * .5F + 37F;
    }
}
