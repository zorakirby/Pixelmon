package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.biped.SkeletonBiped;


public class ModelBlastoise extends PixelmonModelBase
{
    //fields

    PixelmonModelRenderer Body, Head, footL, footR, ArmL, ArmR;

    public ModelBlastoise()
    {
        textureWidth = 64;
        textureHeight = 32;
        Body = new PixelmonModelRenderer(this, "Body");
        Body.setRotationPoint(0, 24, 0);
        Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/blastoise/Body.obj")));
        Head = new PixelmonModelRenderer(this, 0, 0);
        Head.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/blastoise/Head.obj"), 0,0,0F));
        Head.setRotationPoint(0.114F, 3.502F, 1.296F);
        footL = new PixelmonModelRenderer(this, 0, 0);
        footL.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/blastoise/LeftLeg.obj"), 0, 0, 0 ));
        footL.setRotationPoint(1.031F, 1.390F, 0.152F);
        footR = new PixelmonModelRenderer(this, 0, 0);
        footR.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/blastoise/RightLeg.obj"), 0, 0, 0));
        footR.setRotationPoint(-1.031F, 1.390F, 0.152F);
        ArmL = new PixelmonModelRenderer(this, 0, 0);
        ArmL.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/blastoise/LeftArm.obj"), 0, 0, 0));
        ArmL.setRotationPoint(1.57F, 3.7F, 0.9F);
        ArmR = new PixelmonModelRenderer(this, 0, 0);
        ArmR.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/blastoise/RightArm.obj"), 0, 0, 0));
        ArmR.setRotationPoint(-1.57F, 3.7F, 0.9F);
        Body.addChild(Head);
        Body.addChild(ArmL);
        Body.addChild(ArmR);
        Body.addChild(footL);
        Body.addChild(footR);
        
        int degrees = 180;
        float radians = (float) Math.toRadians(degrees);
        
        setRotation(Body, radians, 0, 0);
        

        float legspeed = 0.5F;
        float legRotationLimit = 1.4F;
        
        ModuleHead headModule = new ModuleHead(Head);
        
        ModuleLeg leftLegModule = new ModuleLeg(footL, EnumLeg.FrontLeft,
                EnumPhase.InPhase, legRotationLimit, legspeed);
        ModuleLeg rightLegModule = new ModuleLeg(footR, EnumLeg.FrontRight,
                EnumPhase.InPhase, legRotationLimit, legspeed);
        
        ModuleArm leftArmModule = new ModuleArm(ArmL, EnumArm.Left, 0, 1F, legspeed);
		ModuleArm rightArmModule = new ModuleArm(ArmR, EnumArm.Right, 0, 1F, legspeed);
		
		
        skeleton = new SkeletonBiped(Body, headModule, leftArmModule, rightArmModule,
				leftLegModule, rightLegModule, null);
        
        scale = 5;
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
    }
}