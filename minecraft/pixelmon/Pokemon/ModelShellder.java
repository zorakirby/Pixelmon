package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelShellder extends ModelBase
{
  //fields
    ModelRenderer BottomShell1;
    ModelRenderer BottomShell2;
    ModelRenderer BottomShell3;
    ModelRenderer BottomShell4;
    ModelRenderer BottomShell5;
    ModelRenderer Tongue;
    ModelRenderer TopShell1;
    ModelRenderer TopShell2;
    ModelRenderer TopShell3;
    ModelRenderer TopShell4;
    ModelRenderer TopShell5;
    ModelRenderer Head;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
  
  public ModelShellder()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      BottomShell1 = new ModelRenderer(this, 0, 25);
      BottomShell1.addBox(0F, 0F, -6F, 5, 1, 6);
      BottomShell1.setRotationPoint(-3F, 22F, 2F);
      BottomShell1.setTextureSize(64, 32);
      BottomShell1.mirror = true;
      setRotation(BottomShell1, 0.0698132F, 0F, 0F);
      BottomShell2 = new ModelRenderer(this, 23, 25);
      BottomShell2.addBox(0F, 0F, -6F, 1, 1, 6);
      BottomShell2.setRotationPoint(-4F, 21F, 2F);
      BottomShell2.setTextureSize(64, 32);
      BottomShell2.mirror = true;
      setRotation(BottomShell2, 0.0698132F, 0F, 0F);
      BottomShell3 = new ModelRenderer(this, 23, 25);
      BottomShell3.addBox(0F, 0F, -6F, 1, 1, 6);
      BottomShell3.setRotationPoint(2F, 21F, 2F);
      BottomShell3.setTextureSize(64, 32);
      BottomShell3.mirror = true;
      setRotation(BottomShell3, 0.0698132F, 0F, 0F);
      BottomShell4 = new ModelRenderer(this, 23, 20);
      BottomShell4.addBox(0F, 0F, 0F, 1, 2, 1);
      BottomShell4.setRotationPoint(1.3F, 22.4F, -2.4F);
      BottomShell4.setTextureSize(64, 32);
      BottomShell4.mirror = true;
      setRotation(BottomShell4, 0F, 0F, -0.2443461F);
      BottomShell5 = new ModelRenderer(this, 23, 20);
      BottomShell5.addBox(-1F, 0F, 0F, 1, 2, 1);
      BottomShell5.setRotationPoint(-2.3F, 22.4F, -2.5F);
      BottomShell5.setTextureSize(64, 32);
      BottomShell5.mirror = true;
      setRotation(BottomShell5, 0F, 0F, 0.2443461F);
      Tongue = new ModelRenderer(this, 30, 0);
      Tongue.addBox(0F, 0F, 0F, 2, 0, 4);
      Tongue.setRotationPoint(-1.5F, 22.6F, -7F);
      Tongue.setTextureSize(64, 32);
      Tongue.mirror = true;
      setRotation(Tongue, 0.122173F, 0F, 0F);
      TopShell1 = new ModelRenderer(this, 0, 18);
      TopShell1.addBox(0F, 0F, -6F, 5, 1, 6);
      TopShell1.setRotationPoint(-3F, 20F, 2F);
      TopShell1.setTextureSize(64, 32);
      TopShell1.mirror = true;
      setRotation(TopShell1, -0.2792527F, 0F, 0F);
      TopShell2 = new ModelRenderer(this, 23, 25);
      TopShell2.addBox(0F, 0F, -6F, 1, 1, 6);
      TopShell2.setRotationPoint(-4F, 21F, 2F);
      TopShell2.setTextureSize(64, 32);
      TopShell2.mirror = true;
      setRotation(TopShell2, -0.2792527F, 0F, 0F);
      TopShell3 = new ModelRenderer(this, 23, 25);
      TopShell3.addBox(0F, 0F, -6F, 1, 1, 6);
      TopShell3.setRotationPoint(2F, 21F, 2F);
      TopShell3.setTextureSize(64, 32);
      TopShell3.mirror = true;
      setRotation(TopShell3, -0.2792527F, 0F, 0F);
      TopShell4 = new ModelRenderer(this, 23, 20);
      TopShell4.addBox(0F, 0F, 0F, 1, 2, 1);
      TopShell4.setRotationPoint(2F, 18F, -2F);
      TopShell4.setTextureSize(64, 32);
      TopShell4.mirror = true;
      setRotation(TopShell4, 0F, -0.1919862F, 0.2443461F);
      TopShell5 = new ModelRenderer(this, 23, 20);
      TopShell5.addBox(-1F, 0F, 0F, 1, 2, 1);
      TopShell5.setRotationPoint(-3F, 18F, -2F);
      TopShell5.setTextureSize(64, 32);
      TopShell5.mirror = true;
      setRotation(TopShell5, 0F, 0.1919862F, -0.2443461F);
      Head = new ModelRenderer(this, 50, 0);
      Head.addBox(0F, 0F, 0F, 4, 3, 3);
      Head.setRotationPoint(-2.5F, 19.6F, -3F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, -0.1047198F, 0F, 0F);
      Tail1 = new ModelRenderer(this, 0, 0);
      Tail1.addBox(0F, 0F, 0F, 5, 1, 4);
      Tail1.setRotationPoint(-3F, 22.3F, 1.3F);
      Tail1.setTextureSize(64, 32);
      Tail1.mirror = true;
      setRotation(Tail1, 0.8203047F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 0, 5);
      Tail2.addBox(0F, 0F, 0F, 5, 1, 4);
      Tail2.setRotationPoint(-3F, 20F, 2F);
      Tail2.setTextureSize(64, 32);
      Tail2.mirror = true;
      setRotation(Tail2, -0.837758F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    BottomShell1.render(f5);
    BottomShell2.render(f5);
    BottomShell3.render(f5);
    BottomShell4.render(f5);
    BottomShell5.render(f5);
    Tongue.render(f5);
    TopShell1.render(f5);
    TopShell2.render(f5);
    TopShell3.render(f5);
    TopShell4.render(f5);
    TopShell5.render(f5);
    Head.render(f5);
    Tail1.render(f5);
    Tail2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
