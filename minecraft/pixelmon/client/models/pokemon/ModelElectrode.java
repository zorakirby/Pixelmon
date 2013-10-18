package pixelmon.client.models.pokemon;
 
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.EnumArm;
import pixelmon.client.models.animations.EnumLeg;
import pixelmon.client.models.animations.EnumPhase;
import pixelmon.client.models.animations.ModuleArm;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.ModuleLeg;
import pixelmon.client.models.animations.ModuleTailBasic;
import pixelmon.client.models.animations.SkeletonBase;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.entities.pixelmon.EntityPixelmon;
 
public class ModelElectrode extends PixelmonModelBase
{
    PixelmonModelRenderer Body;
 
    public ModelElectrode()
    {
        textureWidth = 64;
        textureHeight = 32;
        Body = new PixelmonModelRenderer(this, "Body");
        Body.setRotationPoint(0, 10, 0);
        Body.addCustomModel(new ModelCustomWrapper(AdvancedModelLoader.loadModel("/pixelmon/client/models/objFiles/electrode/Body.obj")));
       
        int degrees = 180;
        float radians = (float) Math.toRadians(degrees);
        
        setRotation(Body, radians, 0, 0);
        
        skeleton = new SkeletonBase(Body);
    }
 
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        scale=0.9f;
        Body.setRotationPoint(0, 10f, 0);
    }
 
    private void setRotation(PixelmonModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
 
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	((EntityPixelmon) entity).animationNum1 += .5F * f1;
		if (((EntityPixelmon) entity).animationNum1 > 2 * 3.14159F)
			((EntityPixelmon) entity).animationNum1 = 0;
		Body.rotateAngleX = ((EntityPixelmon) entity).animationNum1;
    }
}