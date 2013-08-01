// Date: 4/29/2013 4:31:52 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
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

public class ModelPoliwrath extends PixelmonModelBase
{
    //fields

    PixelmonModelRenderer Body, footL, footR, ArmL, ArmL2, ArmR, ArmR2;

    public ModelPoliwrath()
    {
        textureWidth = 64;
        textureHeight = 32;
        Body = new PixelmonModelRenderer(this, "Body");
        Body.setRotationPoint(0, 20, 0);
        Body.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathBodyCon.obj")));
        footL = new PixelmonModelRenderer(this, 0, 0);
        footL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathLegLeftCon.obj"), 0, 0, 0 ));
        footR = new PixelmonModelRenderer(this, 0, 0);
        footR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathLegRightCon.obj"), 0, 0, 0));
        ArmL = new PixelmonModelRenderer(this, 0, 0);
        ArmL.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathUpperArmLeftCon.obj"), 0, 0, 0));
        ArmL2 = new PixelmonModelRenderer(this, 0, 0);
        ArmL2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathLowerArmLeftCon.obj"), 0, 0, 0));
        ArmR = new PixelmonModelRenderer(this, 0, 0);
        ArmR.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathUpperArmRightCon.obj"), 0, 0, 0));
        ArmR2 = new PixelmonModelRenderer(this, 0, 0);
        ArmR2.addOBJModel(new ModelOBJWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/poliwrath/PoliwrathLowerArmRightCon.obj"), 0, 0, 0));
        ArmL.addChild(ArmL2);
        ArmR.addChild(ArmR2);
        Body.addChild(ArmL);
        Body.addChild(ArmR);
        Body.addChild(footL);
        Body.addChild(footR);
        
        int degrees = 90;
        float radians = (float) Math.toRadians(degrees);
        
        setRotation(Body, radians, 0, 0);

        float legspeed = 0.5F;
        float legRotationLimit = 1.4F;
        
        ModuleLeg leftLegModule = new ModuleLeg(footL, EnumLeg.FrontLeft,
                EnumPhase.InPhase, legRotationLimit, legspeed);
        ModuleLeg rightLegModule = new ModuleLeg(footR, EnumLeg.FrontRight,
                EnumPhase.InPhase, legRotationLimit, legspeed);
        
        ModuleArm leftArmModule = new ModuleArm(ArmL, EnumArm.Left, 0, 1F, legspeed);
		ModuleArm rightArmModule = new ModuleArm(ArmR, EnumArm.Right, 0, 1F, legspeed);
		
		
        skeleton = new SkeletonBiped(Body, null, leftArmModule, rightArmModule,
				leftLegModule, rightLegModule, null);
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
        Body.rotateAngleX = (float) Math.toRadians(90);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
    }
}