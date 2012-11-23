package pixelmon.models.fossils;

import pixelmon.blocks.TileEntityFossilMachine;
import net.minecraft.src.*;

public class ModelHelixFossil extends ModelBase
{
  //fields
    ModelRenderer helix1;
    ModelRenderer helix2;
    ModelRenderer helix3;
    ModelRenderer helix4;
    ModelRenderer helix5;
    ModelRenderer helix6;
    ModelRenderer helix7;
    ModelRenderer helix8;
    ModelRenderer helix_M;
    ModelRenderer base1;
    ModelRenderer base_2;
    ModelRenderer base_3;
    ModelRenderer base_4;
    ModelRenderer base_5;
    ModelRenderer base_6;
  
  public ModelHelixFossil()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      helix1 = new ModelRenderer(this, 0, 9);
      helix1.addBox(-4F, -1F, 0F, 4, 1, 2);
      helix1.setRotationPoint(0F, 0F, 0F);
      helix1.setTextureSize(32, 32);
      helix1.mirror = true;
      setRotation(helix1, 0F, 0.0174533F, 0F);
      helix2 = new ModelRenderer(this, 0, 9);
      helix2.addBox(-4F, -1F, 0F, 4, 1, 2);
      helix2.setRotationPoint(0F, 0.1F, 0F);
      helix2.setTextureSize(32, 32);
      helix2.mirror = true;
      setRotation(helix2, 0F, 0.5235988F, 0F);
      helix3 = new ModelRenderer(this, 0, 9);
      helix3.addBox(-4F, -1F, 0F, 4, 1, 2);
      helix3.setRotationPoint(0F, 0.2F, 0F);
      helix3.setTextureSize(32, 32);
      helix3.mirror = true;
      setRotation(helix3, 0F, 1.047198F, 0F);
      helix4 = new ModelRenderer(this, 0, 9);
      helix4.addBox(-4F, -1F, 0F, 4, 1, 2);
      helix4.setRotationPoint(0F, 0.3F, 0F);
      helix4.setTextureSize(32, 32);
      helix4.mirror = true;
      setRotation(helix4, 0F, 1.396263F, 0F);
      helix5 = new ModelRenderer(this, 0, 9);
      helix5.addBox(-4F, -1F, 0F, 4, 1, 2);
      helix5.setRotationPoint(0F, 0.4F, 0F);
      helix5.setTextureSize(32, 32);
      helix5.mirror = true;
      setRotation(helix5, 0F, 1.745329F, 0F);
      helix6 = new ModelRenderer(this, 0, 9);
      helix6.addBox(-4F, -1F, 0F, 4, 1, 2);
      helix6.setRotationPoint(0F, 0.5F, 0F);
      helix6.setTextureSize(32, 32);
      helix6.mirror = true;
      setRotation(helix6, 0F, 2.094395F, 0F);
      helix7 = new ModelRenderer(this, 0, 9);
      helix7.addBox(-4F, -1F, -0.5F, 4, 1, 2);
      helix7.setRotationPoint(0F, 0.6F, 0F);
      helix7.setTextureSize(32, 32);
      helix7.mirror = true;
      setRotation(helix7, 0F, 2.617994F, 0F);
      helix8 = new ModelRenderer(this, 0, 12);
      helix8.addBox(-3.6F, -1F, -0.5F, 3, 1, 2);
      helix8.setRotationPoint(0F, 0.7F, 0F);
      helix8.setTextureSize(32, 32);
      helix8.mirror = true;
      setRotation(helix8, 0F, 3.141593F, 0F);
      helix_M = new ModelRenderer(this, 0, 15);
      helix_M.addBox(-1.6F, -1F, -0.5F, 2, 1, 2);
      helix_M.setRotationPoint(0F, 0.7F, 0F);
      helix_M.setTextureSize(32, 32);
      helix_M.mirror = true;
      setRotation(helix_M, 0F, 2.495821F, 0F);
      base1 = new ModelRenderer(this, 0, 0);
      base1.addBox(-4F, 0F, -3F, 8, 1, 7);
      base1.setRotationPoint(1F, 0F, 1F);
      base1.setTextureSize(32, 32);
      base1.mirror = true;
      setRotation(base1, 0F, 0.6806784F, 0F);
      base_2 = new ModelRenderer(this, 0, 0);
      base_2.addBox(-6F, 0F, -1F, 6, 1, 2);
      base_2.setRotationPoint(0.3F, 0F, 5.6F);
      base_2.setTextureSize(32, 32);
      base_2.mirror = true;
      setRotation(base_2, 0F, -0.6283185F, 0F);
      base_3 = new ModelRenderer(this, 0, 0);
      base_3.addBox(-4F, 0F, -4.6F, 6, 1, 3);
      base_3.setRotationPoint(1F, 0F, 1F);
      base_3.setTextureSize(32, 32);
      base_3.mirror = true;
      setRotation(base_3, 0F, 0.1570796F, 0F);
      base_4 = new ModelRenderer(this, 0, 0);
      base_4.addBox(-5.5F, 0F, -2.6F, 2, 1, 6);
      base_4.setRotationPoint(1F, 0F, 1F);
      base_4.setTextureSize(32, 32);
      base_4.mirror = true;
      setRotation(base_4, 0F, -0.2617994F, 0F);
      base_5 = new ModelRenderer(this, 0, 0);
      base_5.addBox(-4F, 0F, -2.5F, 7, 1, 5);
      base_5.setRotationPoint(1F, 0.5F, 1F);
      base_5.setTextureSize(32, 32);
      base_5.mirror = true;
      setRotation(base_5, 0F, 0.5410521F, 0F);
      base_6 = new ModelRenderer(this, 0, 0);
      base_6.addBox(-2F, 0F, -1.5F, 4, 1, 3);
      base_6.setRotationPoint(1F, 1F, 1F);
      base_6.setTextureSize(32, 32);
      base_6.mirror = true;
      setRotation(base_6, 0F, 0.296706F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    helix1.render(f5);
    helix2.render(f5);
    helix3.render(f5);
    helix4.render(f5);
    helix5.render(f5);
    helix6.render(f5);
    helix7.render(f5);
    helix8.render(f5);
    helix_M.render(f5);
    base1.render(f5);
    base_2.render(f5);
    base_3.render(f5);
    base_4.render(f5);
    base_5.render(f5);
    base_6.render(f5);
  }
  
  public void renderModel(TileEntityFossilMachine entity, float f5)
  {
	  helix1.render(f5);
	  helix2.render(f5);
	  helix3.render(f5);
	  helix4.render(f5);
	  helix5.render(f5);
	  helix6.render(f5);
	  helix7.render(f5);
	  helix8.render(f5);
	  helix_M.render(f5);
	  base1.render(f5);
	  base_2.render(f5);
	  base_3.render(f5);
	  base_4.render(f5);
	  base_5.render(f5);
	  base_6.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
