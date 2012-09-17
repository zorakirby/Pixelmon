package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelKabuto extends ModelBase
{
  //fields
    ModelRenderer mainbody;
    ModelRenderer backlowlegR;
    ModelRenderer lowerbody;
    ModelRenderer backlowlegL;
    ModelRenderer shellfront;
    ModelRenderer backuplegR;
    ModelRenderer shellback;
    ModelRenderer backuplegL;
    ModelRenderer shellright;
    ModelRenderer frontlowlegR;
    ModelRenderer shelltop;
    ModelRenderer frontlowlegL;
    ModelRenderer shellleft;
    ModelRenderer frontuplegL;
    ModelRenderer frontuplegR;
    ModelRenderer LegFL;
    ModelRenderer LegFR;
    ModelRenderer LegBL;
    ModelRenderer LegBR;
  
  public ModelKabuto()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      mainbody = new ModelRenderer(this, 84, 0);
      mainbody.addBox(0F, 0F, 0F, 8, 4, 13);
      mainbody.setRotationPoint(-3.8F, 17.5F, -6F);
      mainbody.setTextureSize(128, 32);
      mainbody.mirror = true;
      setRotation(mainbody, 0F, 0F, 0F);
      lowerbody = new ModelRenderer(this, 86, 2);
      lowerbody.addBox(0F, 0F, 0F, 6, 1, 11);
      lowerbody.setRotationPoint(-2.8F, 21.5F, -5F);
      lowerbody.setTextureSize(128, 32);
      lowerbody.mirror = true;
      setRotation(lowerbody, 0F, 0F, 0F);
      shellback = new ModelRenderer(this, 0, 0);
      shellback.addBox(0F, 0F, 0F, 8, 6, 2);
      shellback.setRotationPoint(-3.8F, 16.5F, 6F);
      shellback.setTextureSize(128, 32);
      shellback.mirror = true;
      setRotation(shellback, 0.0523599F, 0F, 0F);
      shellleft = new ModelRenderer(this, 2, 0);
      shellleft.addBox(0F, 0F, 0F, 2, 5, 14);
      shellleft.setRotationPoint(4F, 16.5F, -7F);
      shellleft.setTextureSize(128, 32);
      shellleft.mirror = true;
      setRotation(shellleft, 0F, 0F, -0.0872665F);
      shelltop = new ModelRenderer(this, 0, 0);
      shelltop.addBox(0F, 0F, 0F, 10, 4, 14);
      shelltop.setRotationPoint(-4.8F, 15.5F, -7F);
      shelltop.setTextureSize(128, 32);
      shelltop.mirror = true;
      setRotation(shelltop, 0F, 0F, 0F);
      shellright = new ModelRenderer(this, 1, 0);
      shellright.addBox(0F, 0F, 0F, 2, 5, 14);
      shellright.setRotationPoint(-5.6F, 16.5F, -7F);
      shellright.setTextureSize(128, 32);
      shellright.mirror = true;
      setRotation(shellright, 0F, 0F, 0.0872665F);
    LegFL = new ModelRenderer(this, "LegFL");
    LegFL.setRotationPoint(3.5F, 21F, -2F);
    setRotation(LegFL, 0F, 0F, 0F);
    LegFL.mirror = true;
      frontlowlegL = new ModelRenderer(this, 0, 29);
      frontlowlegL.addBox(0F, 0F, 0F, 2, 1, 2);
      frontlowlegL.setRotationPoint(-0.8F, 2.5F, -1F);
      frontlowlegL.setTextureSize(128, 32);
      frontlowlegL.mirror = true;
      setRotation(frontlowlegL, 0F, 0F, -0.8726646F);
      frontuplegL = new ModelRenderer(this, 0, 27);
      frontuplegL.addBox(0F, 0F, 0F, 2, 3, 2);
      frontuplegL.setRotationPoint(-1.3F, -0.5F, -1F);
      frontuplegL.setTextureSize(128, 32);
      frontuplegL.mirror = true;
      setRotation(frontuplegL, 0F, 0F, -0.418879F);
      
      LegFL.addChild(frontlowlegL);
      LegFL.addChild(frontuplegL);
      
    LegFR = new ModelRenderer(this, "LegFR");
    LegFR.setRotationPoint(-2.5F, 21F, -2F);
    setRotation(LegFR, 0F, 0F, 0F);
    LegFR.mirror = true;
      frontlowlegR = new ModelRenderer(this, 0, 29);
      frontlowlegR.addBox(0F, 0F, 0F, 2, 1, 2);
      frontlowlegR.setRotationPoint(-1.3F, 1F, -1F);
      frontlowlegR.setTextureSize(128, 32);
      frontlowlegR.mirror = true;
      setRotation(frontlowlegR, 0F, 0F, 0.8726646F);
      frontuplegR = new ModelRenderer(this, 0, 27);
      frontuplegR.addBox(0F, 0F, 0F, 2, 3, 2);
      frontuplegR.setRotationPoint(-1.3F, -1.5F, -1F);
      frontuplegR.setTextureSize(128, 32);
      frontuplegR.mirror = true;
      setRotation(frontuplegR, 0F, 0F, 0.418879F);
      
      LegFR.addChild(frontlowlegR);
      LegFR.addChild(frontuplegR);
      
    LegBL = new ModelRenderer(this, "LegBL");
    LegBL.setRotationPoint(3.5F, 21F, 4F);
    setRotation(LegBL, 0F, 0F, 0F);
    LegBL.mirror = true;
      backuplegL = new ModelRenderer(this, 0, 27);
      backuplegL.addBox(0F, 0F, 0F, 2, 3, 2);
      backuplegL.setRotationPoint(-1.3F, -0.5F, -1F);
      backuplegL.setTextureSize(128, 32);
      backuplegL.mirror = true;
      setRotation(backuplegL, 0F, 0F, -0.418879F);
      backlowlegL = new ModelRenderer(this, 0, 29);
      backlowlegL.addBox(0F, 0F, 0F, 2, 1, 2);
      backlowlegL.setRotationPoint(-0.8F, 2.5F, -1F);
      backlowlegL.setTextureSize(128, 32);
      backlowlegL.mirror = true;
      setRotation(backlowlegL, 0F, 0F, -0.8726646F);
      
      LegBL.addChild(backlowlegL);
      LegBL.addChild(backuplegL);
      
    LegBR = new ModelRenderer(this, "LegBR");
    LegBR.setRotationPoint(-2.5F, 21F, 4F);
    setRotation(LegBR, 0F, 0F, 0F);
    LegBR.mirror = true;
      backuplegR = new ModelRenderer(this, 0, 27);
      backuplegR.addBox(0F, -1F, 0F, 2, 3, 2);
      backuplegR.setRotationPoint(-1.3F, -0.5F, -1F);
      backuplegR.setTextureSize(128, 32);
      backuplegR.mirror = true;
      setRotation(backuplegR, 0F, 0F, 0.418879F);
      backlowlegR = new ModelRenderer(this, 0, 29);
      backlowlegR.addBox(0F, 0F, 0F, 2, 1, 2);
      backlowlegR.setRotationPoint(-1.3F, 1F, -1F);
      backlowlegR.setTextureSize(128, 32);
      backlowlegR.mirror = true;
      setRotation(backlowlegR, 0F, 0F, 0.8726646F);
      
      LegBR.addChild(backlowlegR);
      LegBR.addChild(backuplegR);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    mainbody.render(f5);
    lowerbody.render(f5);
    shellback.render(f5);
    shellleft.render(f5);
    shelltop.render(f5);
    shellright.render(f5);
    LegFL.render(f5);
    LegFR.render(f5);
    LegBL.render(f5);
    LegBR.render(f5);
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
