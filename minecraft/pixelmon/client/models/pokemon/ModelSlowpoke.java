package pixelmon.client.models.pokemon;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSlowpoke extends ModelBase
{
  //fields
    ModelRenderer BodyMain;
    ModelRenderer BodyL;
    ModelRenderer BodyU;
    ModelRenderer BodyR;
    ModelRenderer ToothL;
    ModelRenderer EarR;
    ModelRenderer EarL;
    ModelRenderer FacePatch;
    ModelRenderer LowerJaw;
    ModelRenderer ToothR;
    ModelRenderer HeadL;
    ModelRenderer UpperJaw;
    ModelRenderer HeadMain;
    ModelRenderer HeadU;
    ModelRenderer HeadR;
    ModelRenderer Tail3;
    ModelRenderer Tail7;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer FrontToeL;
    ModelRenderer FrontLegL;
    ModelRenderer FrontToeR;
    ModelRenderer FrontLegR;
    ModelRenderer BackToeL;
    ModelRenderer BackLegL;
    ModelRenderer BackLegR;
    ModelRenderer BackToeR;
    ModelRenderer HeadBase;
    ModelRenderer FRONTLEGL;
    ModelRenderer FRONTLEGR;
    ModelRenderer BACKLEGL;
    ModelRenderer BACKLEGR;
    ModelRenderer TAIL;
    ModelRenderer TAIL2;
    ModelRenderer TAIL3;
  
  public ModelSlowpoke()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      BodyMain = new ModelRenderer(this, 30, 17);
      BodyMain.addBox(-4F, 0F, 0F, 9, 7, 12);
      BodyMain.setRotationPoint(0F, 14F, -6F);
      BodyMain.setTextureSize(128, 64);
      BodyMain.mirror = true;
      setRotation(BodyMain, 0F, 0F, 0F);
      BodyL = new ModelRenderer(this, 91, 43);
      BodyL.addBox(1F, 0F, -4F, 2, 6, 11);
      BodyL.setRotationPoint(2.3F, 14.3F, -1.5F);
      BodyL.setTextureSize(128, 64);
      BodyL.mirror = true;
      setRotation(BodyL, 0F, 0F, 0F);
      BodyU = new ModelRenderer(this, 33, 0);
      BodyU.addBox(-3F, 0F, -4F, 8, 2, 11);
      BodyU.setRotationPoint(-0.5F, 13.3F, -1.4F);
      BodyU.setTextureSize(128, 64);
      BodyU.mirror = true;
      setRotation(BodyU, 0F, 0F, 0F);
      BodyR = new ModelRenderer(this, 91, 43);
      BodyR.addBox(-3F, 0F, -4F, 2, 7, 11);
      BodyR.setRotationPoint(-1.3F, 14.4F, -1.5F);
      BodyR.setTextureSize(128, 64);
      BodyR.mirror = true;
      setRotation(BodyR, 0F, 0F, 0F);
    HeadBase = new ModelRenderer(this, "HeadBase");
    HeadBase.setRotationPoint(0F, 16F, -6F);
    setRotation(HeadBase, 0F, 0F, 0F);
    HeadBase.mirror = true;
      ToothL = new ModelRenderer(this, 6, 41);
      ToothL.addBox(-1F, 0F, -0.2666667F, 1, 1, 0);
      ToothL.setRotationPoint(3.11F, 1.2F, -5.6F);
      ToothL.setTextureSize(128, 64);
      ToothL.mirror = true;
      setRotation(ToothL, -0.2094395F, -0.1396263F, -0.7853982F);
      EarR = new ModelRenderer(this, 20, 0);
      EarR.addBox(-2F, -2F, -1F, 2, 2, 1);
      EarR.setRotationPoint(-2F, -1.5F, -1F);
      EarR.setTextureSize(128, 64);
      EarR.mirror = true;
      setRotation(EarR, 0F, 0F, 0F);
      EarL = new ModelRenderer(this, 7, 0);
      EarL.addBox(0F, -2F, -1F, 2, 2, 1);
      EarL.setRotationPoint(3F, -1.5F, -1F);
      EarL.setTextureSize(128, 64);
      EarL.mirror = true;
      setRotation(EarL, 0F, 0F, 0F);
      LowerJaw = new ModelRenderer(this, 3, 35);
      LowerJaw.addBox(-3F, -1F, -3F, 7, 1, 3);
      LowerJaw.setRotationPoint(0.01F, 3.1F, -2.5F);
      LowerJaw.setTextureSize(128, 64);
      LowerJaw.mirror = true;
      setRotation(LowerJaw, 0.3839724F, 0F, 0F);
      ToothR = new ModelRenderer(this, 13, 41);
      ToothR.addBox(-1F, 0F, -0.2666667F, 1, 1, 0);
      ToothR.setRotationPoint(-2.19F, 1.2F, -5.6F);
      ToothR.setTextureSize(128, 64);
      ToothR.mirror = true;
      setRotation(ToothR, -0.2094395F, -0.1396263F, -0.7853982F);
      HeadL = new ModelRenderer(this, 92, 46);
      HeadL.addBox(-3F, -3F, -5F, 6, 5, 4);
      HeadL.setRotationPoint(1.4F, 1F, 1.3F);
      HeadL.setTextureSize(128, 64);
      HeadL.mirror = true;
      setRotation(HeadL, 0F, 0F, 0F);
      UpperJaw = new ModelRenderer(this, 3, 27);
      UpperJaw.addBox(-3F, -2F, -3F, 7, 2, 3);
      UpperJaw.setRotationPoint(0.01F, 2.8F, -3.2F);
      UpperJaw.setTextureSize(128, 64);
      UpperJaw.mirror = true;
      setRotation(UpperJaw, -0.2094395F, 0F, 0F);
      HeadMain = new ModelRenderer(this, 3, 11);
      HeadMain.addBox(-3F, -3F, -5F, 7, 6, 6);
      HeadMain.setRotationPoint(0F, 0.5F, 1F);
      HeadMain.setTextureSize(128, 64);
      HeadMain.mirror = true;
      setRotation(HeadMain, 0F, 0F, 0F);
      HeadU = new ModelRenderer(this, 92, 46);
      HeadU.addBox(-3F, -3F, -5F, 6, 6, 4);
      HeadU.setRotationPoint(0.6F, 0.3F, 1.3F);
      HeadU.setTextureSize(128, 64);
      HeadU.mirror = true;
      setRotation(HeadU, 0F, 0F, 0F);
      HeadR = new ModelRenderer(this, 92, 46);
      HeadR.addBox(-3F, -3F, -5F, 6, 5, 4);
      HeadR.setRotationPoint(-0.3F, 1F, 1.3F);
      HeadR.setTextureSize(128, 64);
      HeadR.mirror = true;
      setRotation(HeadR, 0F, 0F, 0F);
      
      HeadBase.addChild(ToothL);
      HeadBase.addChild(ToothR);
      HeadBase.addChild(EarR);
      HeadBase.addChild(EarL);
      HeadBase.addChild(LowerJaw);
      HeadBase.addChild(UpperJaw);
      HeadBase.addChild(HeadL);
      HeadBase.addChild(HeadR);
      HeadBase.addChild(HeadU);
      HeadBase.addChild(HeadMain);
      
    FRONTLEGL = new ModelRenderer(this, "FRONTLEGL");
    FRONTLEGL.setRotationPoint(4F, 20F, -5F);
    setRotation(FRONTLEGL, 0F, 0F, 0F);
    FRONTLEGL.mirror = true;
      FrontToeL = new ModelRenderer(this, 47, 39);
      FrontToeL.addBox(-1F, 0F, -2F, 2, 1, 2);
      FrontToeL.setRotationPoint(0F, 2.8F, 0.8F);
      FrontToeL.setTextureSize(128, 64);
      FrontToeL.mirror = true;
      setRotation(FrontToeL, 0F, 0F, -0.0872665F);
      FrontLegL = new ModelRenderer(this, 30, 52);
      FrontLegL.addBox(-2F, 0F, -1F, 3, 5, 3);
      FrontLegL.setRotationPoint(0F, -1.1F, 0F);
      FrontLegL.setTextureSize(128, 64);
      FrontLegL.mirror = true;
      setRotation(FrontLegL, 0F, 0F, -0.0872665F);
      
      FRONTLEGL.addChild(FrontToeL);
      FRONTLEGL.addChild(FrontLegL);
      
    FRONTLEGR = new ModelRenderer(this, "FRONTLEGR");
    FRONTLEGR.setRotationPoint(-3F, 20F, -5F);
    setRotation(FRONTLEGR, 0F, 0F, 0F);
    FRONTLEGR.mirror = true;
      FrontToeR = new ModelRenderer(this, 47, 39);
      FrontToeR.addBox(-0.9F, 0F, -2F, 2, 1, 2);
      FrontToeR.setRotationPoint(0F, 2.8F, 0.8F);
      FrontToeR.setTextureSize(128, 64);
      FrontToeR.mirror = true;
      setRotation(FrontToeR, 0F, 0F, 0.0872665F);
      FrontLegR = new ModelRenderer(this, 30, 40);
      FrontLegR.addBox(-2F, -1F, -5F, 3, 5, 3);
      FrontLegR.setRotationPoint(1F, -0.1F, 4F);
      FrontLegR.setTextureSize(128, 64);
      FrontLegR.mirror = true;
      setRotation(FrontLegR, 0F, 0F, 0.0872665F);

      FRONTLEGR.addChild(FrontToeR);
      FRONTLEGR.addChild(FrontLegR);
      
    BACKLEGL = new ModelRenderer(this, "BACKLEGL");
    BACKLEGL.setRotationPoint(3F, 19F, 5F);
    setRotation(BACKLEGL, 0F, 0F, 0F);
    BACKLEGL.mirror = true;
      BackToeL = new ModelRenderer(this, 47, 39);
      BackToeL.addBox(-0.5F, 0F, -2F, 2, 1, 2);
      BackToeL.setRotationPoint(0F, 3.8F, -0.2F);
      BackToeL.setTextureSize(128, 64);
      BackToeL.mirror = true;
      setRotation(BackToeL, 0F, 0F, 0F);
      BackLegL = new ModelRenderer(this, 61, 41);
      BackLegL.addBox(-2F, 0F, -1F, 3, 5, 3);
      BackLegL.setRotationPoint(1F, -0.1F, -1F);
      BackLegL.setTextureSize(128, 64);
      BackLegL.mirror = true;
      setRotation(BackLegL, 0F, 0F, 0F);
      
      BACKLEGL.addChild(BackToeL);
      BACKLEGL.addChild(BackLegL);
      
    BACKLEGR = new ModelRenderer(this, "BACKLEGR");
    BACKLEGR.setRotationPoint(-3F, 19F, 5F);
    setRotation(BACKLEGR, 0F, 0F, 0F);
    BACKLEGR.mirror = true;
      BackLegR = new ModelRenderer(this, 60, 52);
      BackLegR.addBox(-2F, 0F, -1F, 3, 5, 3);
      BackLegR.setRotationPoint(1F, -0.1F, -1F);
      BackLegR.setTextureSize(128, 64);
      BackLegR.mirror = true;
      setRotation(BackLegR, 0F, 0F, 0F);
      BackToeR = new ModelRenderer(this, 47, 39);
      BackToeR.addBox(-0.5F, 0F, -2F, 2, 1, 2);
      BackToeR.setRotationPoint(0F, 3.8F, -0.2F);
      BackToeR.setTextureSize(128, 64);
      BackToeR.mirror = true;
      setRotation(BackToeR, 0F, 0F, 0F);
      
      BACKLEGR.addChild(BackToeR);
      BACKLEGR.addChild(BackLegR);
      
    TAIL = new ModelRenderer(this, "TAIL");
    TAIL.setRotationPoint(0.5F, 16.5F, 6F);
    setRotation(TAIL, 0F, 0F, 0F);
    TAIL.mirror = true;
      Tail1 = new ModelRenderer(this, 93, 46);
      Tail1.addBox(-2F, 0F, 0F, 4, 4, 5);
      Tail1.setRotationPoint(0F, -2F, -1F);
      Tail1.setTextureSize(128, 64);
      Tail1.mirror = true;
      setRotation(Tail1, 0F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 75, 15);
      Tail2.addBox(-2F, 0F, 0F, 4, 4, 4);
      Tail2.setRotationPoint(0F, -2F, 4F);
      Tail2.setTextureSize(128, 64);
      Tail2.mirror = true;
      setRotation(Tail2, 0F, 0F, 0F);
      
      TAIL.addChild(Tail1);
      TAIL.addChild(Tail2);
      
    TAIL2 = new ModelRenderer(this, "TAIL2");
    TAIL2.setRotationPoint(0.5F, 0F, 8F);
    setRotation(TAIL2, 0F, 0F, 0F);
    TAIL2.mirror = true;
      Tail3 = new ModelRenderer(this, 92, 17);
      Tail3.addBox(-2F, 0F, 0F, 3, 3, 4);
      Tail3.setRotationPoint(0F, -1.5F, 0F);
      Tail3.setTextureSize(128, 64);
      Tail3.mirror = true;
      setRotation(Tail3, 0F, 0F, 0F);
      Tail4 = new ModelRenderer(this, 92, 17);
      Tail4.addBox(-2F, 0F, 0F, 3, 3, 4);
      Tail4.setRotationPoint(0F, -1.5F, 4F);
      Tail4.setTextureSize(128, 64);
      Tail4.mirror = true;
      setRotation(Tail4, 0F, 0F, 0F);
      
      TAIL2.addChild(Tail3);
      TAIL2.addChild(Tail4);
      TAIL.addChild(TAIL2);
      
    TAIL3 = new ModelRenderer(this, "TAIL3");
    TAIL3.setRotationPoint(0F, 0F, 8F);
    setRotation(TAIL3, 0F, 0F, 0F);
    TAIL3.mirror = true;
      Tail5 = new ModelRenderer(this, 108, 46);
      Tail5.addBox(-1F, 0F, 0F, 2, 2, 3);
      Tail5.setRotationPoint(-0.5F, -1F, 0F);
      Tail5.setTextureSize(128, 64);
      Tail5.mirror = true;
      setRotation(Tail5, 0F, 0F, 0F);
      Tail6 = new ModelRenderer(this, 107, 19);
      Tail6.addBox(-1F, -1F, 0F, 2, 2, 3);
      Tail6.setRotationPoint(-0.5F, 0F, 3F);
      Tail6.setTextureSize(128, 64);
      Tail6.mirror = true;
      setRotation(Tail6, 0F, 0F, 0F);
      Tail7 = new ModelRenderer(this, 118, 20);
      Tail7.addBox(-1F, -1F, 0F, 1, 1, 3);
      Tail7.setRotationPoint(0F, 0.5F, 6F);
      Tail7.setTextureSize(128, 64);
      Tail7.mirror = true;
      setRotation(Tail7, 0F, 0F, 0F);
      
      TAIL3.addChild(Tail5);
      TAIL3.addChild(Tail6);
      TAIL3.addChild(Tail7);
      TAIL2.addChild(TAIL3);
  }
  
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BodyMain.render(f5);
    BodyL.render(f5);
    BodyU.render(f5);
    BodyR.render(f5);
    HeadBase.render(f5);
    FRONTLEGL.render(f5);
    FRONTLEGR.render(f5);
    BACKLEGL.render(f5);
    BACKLEGR.render(f5);
    TAIL.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		HeadBase.rotateAngleX = f4 / 57.29578F;
        HeadBase.rotateAngleY = f3 / 57.29578F;
        BACKLEGL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        BACKLEGR.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        FRONTLEGR.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        FRONTLEGL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        TAIL.rotateAngleX = 0.885398163F;
        TAIL2.rotateAngleX = 1.485398163F;
        TAIL3.rotateAngleX = 0.885398163F;
        TAIL.rotateAngleZ = MathHelper.cos(f * 0.6662F) * .4F * f1;
  }

}
