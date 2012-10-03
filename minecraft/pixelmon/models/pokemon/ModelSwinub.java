package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelSwinub extends ModelBase
{
  //fields
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer foot3;
    ModelRenderer foot4;
    ModelRenderer toe1;
    ModelRenderer toe2;
    ModelRenderer toe3;
    ModelRenderer toe4;
    ModelRenderer furlayer1;
    ModelRenderer furlayer2;
    ModelRenderer furlayer3;
    ModelRenderer furlayer4;
    ModelRenderer snout;
    ModelRenderer eyeLpart1;
    ModelRenderer eyeLpart2;
    ModelRenderer eyeLpart3;
    ModelRenderer eyeRpart1;
    ModelRenderer eyeRpart2;
    ModelRenderer eyeRpart3;
    ModelRenderer fur1;
    ModelRenderer fur2;
    ModelRenderer fur3;
    ModelRenderer tail;
    ModelRenderer LegLF;
    ModelRenderer LegRF;
    ModelRenderer LegLB;
    ModelRenderer LegRB;
  
  public ModelSwinub()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      furlayer1 = new ModelRenderer(this, 0, 8);
      furlayer1.addBox(0F, 0F, 0F, 10, 7, 10);
      furlayer1.setRotationPoint(-5F, 16.7F, -5F);
      furlayer1.setTextureSize(128, 64);
      furlayer1.mirror = true;
      setRotation(furlayer1, 0F, 0F, 0F);
      furlayer2 = new ModelRenderer(this, 42, 27);
      furlayer2.addBox(0F, 0F, 0F, 8, 6, 11);
      furlayer2.setRotationPoint(-4F, 17.6F, -5.5F);
      furlayer2.setTextureSize(128, 64);
      furlayer2.mirror = true;
      setRotation(furlayer2, 0F, 0F, 0F);
      furlayer3 = new ModelRenderer(this, 0, 28);
      furlayer3.addBox(0F, 0F, 0F, 11, 6, 9);
      furlayer3.setRotationPoint(-5.5F, 17.6F, -4.5F);
      furlayer3.setTextureSize(128, 64);
      furlayer3.mirror = true;
      setRotation(furlayer3, 0F, 0F, 0F);
      furlayer4 = new ModelRenderer(this, 42, 8);
      furlayer4.addBox(0F, 0F, 0F, 8, 7, 9);
      furlayer4.setRotationPoint(-4F, 16F, -4.5F);
      furlayer4.setTextureSize(128, 64);
      furlayer4.mirror = true;
      setRotation(furlayer4, 0F, 0F, 0F);
      snout = new ModelRenderer(this, 11, 2);
      snout.addBox(0F, 0F, 0F, 3, 2, 3);
      snout.setRotationPoint(-1.5F, 21F, -6.5F);
      snout.setTextureSize(128, 64);
      snout.mirror = true;
      setRotation(snout, 0F, 0F, 0F);
      eyeLpart1 = new ModelRenderer(this, 27, 2);
      eyeLpart1.addBox(0F, 0F, 0F, 1, 1, 1);
      eyeLpart1.setRotationPoint(2.7F, 19.5F, -5.6F);
      eyeLpart1.setTextureSize(128, 64);
      eyeLpart1.mirror = true;
      setRotation(eyeLpart1, 0F, 0F, 0F);
      eyeRpart2 = new ModelRenderer(this, 27, 2);
      eyeRpart2.addBox(0F, 0F, 0F, 1, 1, 1);
      eyeRpart2.setRotationPoint(-3.2F, 19.6F, -5.6F);
      eyeRpart2.setTextureSize(128, 64);
      eyeRpart2.mirror = true;
      setRotation(eyeRpart2, 0F, 0F, -0.3839724F);
      eyeRpart3 = new ModelRenderer(this, 27, 2);
      eyeRpart3.addBox(1F, -0.2F, 0F, 1, 1, 1);
      eyeRpart3.setRotationPoint(-3.2F, 20F, -5.6F);
      eyeRpart3.setTextureSize(128, 64);
      eyeRpart3.mirror = true;
      setRotation(eyeRpart3, 0F, 0F, -0.5585054F);
      eyeRpart1 = new ModelRenderer(this, 27, 2);
      eyeRpart1.addBox(0F, 0F, 0F, 1, 1, 1);
      eyeRpart1.setRotationPoint(-3.8F, 19.5F, -5.6F);
      eyeRpart1.setTextureSize(128, 64);
      eyeRpart1.mirror = true;
      setRotation(eyeRpart1, 0F, 0F, 0F);
      eyeLpart2 = new ModelRenderer(this, 27, 2);
      eyeLpart2.addBox(0F, 0F, 0F, 1, 1, 1);
      eyeLpart2.setRotationPoint(2.1F, 19.2F, -5.6F);
      eyeLpart2.setTextureSize(128, 64);
      eyeLpart2.mirror = true;
      setRotation(eyeLpart2, 0F, 0F, 0.3839724F);
      eyeLpart3 = new ModelRenderer(this, 27, 2);
      eyeLpart3.addBox(0.2F, 0.8F, 0F, 1, 1, 1);
      eyeLpart3.setRotationPoint(1.7F, 18F, -5.6F);
      eyeLpart3.setTextureSize(128, 64);
      eyeLpart3.mirror = true;
      setRotation(eyeLpart3, 0F, 0F, 0.5585054F);
      fur1 = new ModelRenderer(this, 33, 2);
      fur1.addBox(0F, 0F, 0F, 1, 1, 1);
      fur1.setRotationPoint(2F, 22.7F, 5F);
      fur1.setTextureSize(128, 64);
      fur1.mirror = true;
      setRotation(fur1, 0F, 0F, 0F);
      fur2 = new ModelRenderer(this, 33, 2);
      fur2.addBox(0F, 0F, 0F, 1, 1, 1);
      fur2.setRotationPoint(-3F, 22.7F, 5F);
      fur2.setTextureSize(128, 64);
      fur2.mirror = true;
      setRotation(fur2, 0F, 0F, 0F);
      fur3 = new ModelRenderer(this, 39, 2);
      fur3.addBox(0F, 0F, 0F, 2, 1, 1);
      fur3.setRotationPoint(-1F, 22.7F, 5F);
      fur3.setTextureSize(128, 64);
      fur3.mirror = true;
      setRotation(fur3, 0F, 0F, 0F);
      tail = new ModelRenderer(this, 39, 2);
      tail.addBox(0F, 0F, 0F, 1, 1, 1);
      tail.setRotationPoint(-0.5F, 21F, 5.3F);
      tail.setTextureSize(128, 64);
      tail.mirror = true;
      setRotation(tail, 0F, 0F, 0F);
    LegLF = new ModelRenderer(this, "LegLF");
    LegLF.setRotationPoint(2.5F, 23F, -2F);
    setRotation(LegLF, 0F, 0F, 0F);
    LegLF.mirror = true;
      foot1 = new ModelRenderer(this, 0, 5);
      foot1.addBox(0F, 0F, 0F, 3, 1, 2);
      foot1.setRotationPoint(-1.5F, 0F, -1F);
      foot1.setTextureSize(128, 64);
      foot1.mirror = true;
      setRotation(foot1, 0F, 0F, 0F);
      toe1 = new ModelRenderer(this, 0, 0);
      toe1.addBox(0F, 0F, 0F, 1, 1, 3);
      toe1.setRotationPoint(-0.5F, 0F, -2F);
      toe1.setTextureSize(128, 64);
      toe1.mirror = true;
      setRotation(toe1, 0F, 0F, 0F);
      
      LegLF.addChild(foot1);
      LegLF.addChild(toe1);
      
    LegRF = new ModelRenderer(this, "LegRF");
    LegRF.setRotationPoint(-2.5F, 23F, -2F);
    setRotation(LegRF, 0F, 0F, 0F);
    LegRF.mirror = true;
      foot2 = new ModelRenderer(this, 0, 5);
      foot2.addBox(0F, 0F, 0F, 3, 1, 2);
      foot2.setRotationPoint(-1.5F, 0F, -1F);
      foot2.setTextureSize(128, 64);
      foot2.mirror = true;
      setRotation(foot2, 0F, 0F, 0F);
      toe2 = new ModelRenderer(this, 0, 0);
      toe2.addBox(0F, 0F, 0F, 1, 1, 3);
      toe2.setRotationPoint(-0.5F, 0F, -2F);
      toe2.setTextureSize(128, 64);
      toe2.mirror = true;
      setRotation(toe2, 0F, 0F, 0F);
      
      LegRF.addChild(foot2);
      LegRF.addChild(toe2);
      
    LegLB = new ModelRenderer(this, "LegLB");
    LegLB.setRotationPoint(2.5F, 23F, 3F);
    setRotation(LegLB, 0F, 0F, 0F);
    LegLB.mirror = true;
      foot3 = new ModelRenderer(this, 0, 5);
      foot3.addBox(0F, 0F, 0F, 3, 1, 2);
      foot3.setRotationPoint(-1.5F, 0F, -1F);
      foot3.setTextureSize(128, 64);
      foot3.mirror = true;
      setRotation(foot3, 0F, 0F, 0F);
      toe3 = new ModelRenderer(this, 0, 0);
      toe3.addBox(0F, 0F, 0F, 1, 1, 3);
      toe3.setRotationPoint(-0.5F, 0F, -2F);
      toe3.setTextureSize(128, 64);
      toe3.mirror = true;
      setRotation(toe3, 0F, 0F, 0F);

      LegLB.addChild(foot3);
      LegLB.addChild(toe3);
      
    LegRB = new ModelRenderer(this, "LegRB");
    LegRB.setRotationPoint(-2.5F, 23F, 3F);
    setRotation(LegRB, 0F, 0F, 0F);
    LegRB.mirror = true;
      foot4 = new ModelRenderer(this, 0, 5);
      foot4.addBox(0F, 0F, 0F, 3, 1, 2);
      foot4.setRotationPoint(-1.5F, 0F, -1F);
      foot4.setTextureSize(128, 64);
      foot4.mirror = true;
      setRotation(foot4, 0F, 0F, 0F);
      toe4 = new ModelRenderer(this, 0, 0);
      toe4.addBox(0F, 0F, 0F, 1, 1, 3);
      toe4.setRotationPoint(-0.5F, 0F, -2F);
      toe4.setTextureSize(128, 64);
      toe4.mirror = true;
      setRotation(toe4, 0F, 0F, 0F);

      LegRB.addChild(foot4);
      LegRB.addChild(toe4);
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    furlayer1.render(f5);
    furlayer2.render(f5);
    furlayer3.render(f5);
    furlayer4.render(f5);
    snout.render(f5);
    eyeLpart1.render(f5);
    eyeRpart2.render(f5);
    eyeRpart3.render(f5);
    eyeRpart1.render(f5);
    eyeLpart2.render(f5);
    eyeLpart3.render(f5);
    fur1.render(f5);
    fur2.render(f5);
    fur3.render(f5);
    tail.render(f5);
    LegLF.render(f5);
    LegRF.render(f5);
    LegLB.render(f5);
    LegRB.render(f5);
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
