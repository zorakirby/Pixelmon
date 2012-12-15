package pixelmon.client.models.plates;

import net.minecraft.src.*;

public class ModelPlate extends ModelBase
{
  //fields
    ModelRenderer Plate;
  
  public ModelPlate()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      Plate = new ModelRenderer(this, 3, 20);
      Plate.addBox(-13.9F, 0F, -2.5F, 6, 1, 6);
      Plate.setRotationPoint(6.5F, 13F, 0F);
      Plate.setTextureSize(32, 32);
      Plate.mirror = true;
      setRotation(Plate, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Plate.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void renderModel(float f5) {
	 Plate.render(f5);
  }
  
}
