package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.enums.EnumCustomModel;

public class ModelHaunter extends PixelmonModelBase{
	PixelmonModelRenderer body;
	
	public ModelHaunter(){
	 body = new PixelmonModelRenderer(this, "Body");
	 body.setRotationPoint(0, 18, 0);
     body.addCustomModel(new ModelCustomWrapper(EnumCustomModel.Haunter.theModel));
     
     
     skeleton = new SkeletonBiped(body, null, null, null,
                                  null, null, null);
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
