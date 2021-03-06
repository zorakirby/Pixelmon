package pixelmon.client.models.pokemon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSlowbro extends ModelBase
{
  //fields
    ModelRenderer EarL;
    ModelRenderer LowerJaw;
    ModelRenderer EarR;
    ModelRenderer ArmL;
    ModelRenderer FootBaseL;
    ModelRenderer LeftToe1;
    ModelRenderer LeftToe2;
    ModelRenderer RightToe1;
    ModelRenderer RightToe2;
    ModelRenderer NailL2;
    ModelRenderer HeadU;
    ModelRenderer ToothL;
    ModelRenderer UpperJaw;
    ModelRenderer ToothR;
    ModelRenderer TailBase;
    ModelRenderer Shell6;
    ModelRenderer Spike1;
    ModelRenderer Tail2;
    ModelRenderer Tooth1;
    ModelRenderer Shell4;
    ModelRenderer ArmR;
    ModelRenderer NailL1;
    ModelRenderer NailR1;
    ModelRenderer NailR2;
    ModelRenderer LegBaseL;
    ModelRenderer Foot1L;
    ModelRenderer Foot1R;
    ModelRenderer FootBaseR;
    ModelRenderer BellyPatch;
    ModelRenderer TailBase2;
    ModelRenderer LegBaseR;
    ModelRenderer Shell5;
    ModelRenderer BodyMain;
    ModelRenderer BodyF;
    ModelRenderer HeadMain;
    ModelRenderer Shell1;
    ModelRenderer Shell3;
    ModelRenderer Spike2;
    ModelRenderer Spike3;
    ModelRenderer Spike4;
    ModelRenderer Spike5;
    ModelRenderer Spike6;
    ModelRenderer Spike7;
    ModelRenderer Spike8;
    ModelRenderer Tooth2;
    ModelRenderer Tooth3;
  
  public ModelSlowbro()
  {
    textureWidth = 128;
    textureHeight = 256;
    
      EarL = new ModelRenderer(this, 39, 49);
      EarL.addBox(0F, -2F, 0F, 2, 2, 1);
      EarL.setRotationPoint(2.5F, 6F, -0.5F);
      EarL.setTextureSize(128, 256);
      EarL.mirror = true;
      setRotation(EarL, 0F, 0F, 0F);
      LowerJaw = new ModelRenderer(this, 21, 80);
      LowerJaw.addBox(-3F, -1F, -4F, 4, 1, 3);
      LowerJaw.setRotationPoint(1F, 9.6F, -1.8F);
      LowerJaw.setTextureSize(128, 256);
      LowerJaw.mirror = true;
      setRotation(LowerJaw, 0.8028515F, 0F, 0F);
      EarR = new ModelRenderer(this, 12, 49);
      EarR.addBox(-2F, -2F, 0F, 2, 2, 1);
      EarR.setRotationPoint(-2.5F, 6F, -0.5F);
      EarR.setTextureSize(128, 256);
      EarR.mirror = true;
      setRotation(EarR, 0F, 0F, 0F);
      ArmL = new ModelRenderer(this, 56, 111);
      ArmL.addBox(-1F, 0F, -1F, 2, 6, 2);
      ArmL.setRotationPoint(3F, 12.7F, -0.9F);
      ArmL.setTextureSize(128, 256);
      ArmL.mirror = true;
      setRotation(ArmL, -1.099557F, -0.2617994F, -0.122173F);
      FootBaseL = new ModelRenderer(this, 30, 122);
      FootBaseL.addBox(-2F, 0F, -4F, 3, 2, 5);
      FootBaseL.setRotationPoint(4F, 22F, 1.4F);
      FootBaseL.setTextureSize(128, 256);
      FootBaseL.mirror = true;
      setRotation(FootBaseL, 0F, 0F, 0F);
      LeftToe1 = new ModelRenderer(this, 22, 130);
      LeftToe1.addBox(0F, 0F, -2F, 1, 1, 2);
      LeftToe1.setRotationPoint(2.02F, 23.01F, -1.5F);
      LeftToe1.setTextureSize(128, 256);
      LeftToe1.mirror = true;
      setRotation(LeftToe1, 0F, 0F, 0F);
      LeftToe2 = new ModelRenderer(this, 12, 130);
      LeftToe2.addBox(-1F, 0F, -2F, 1, 1, 2);
      LeftToe2.setRotationPoint(4.99F, 23.01F, -1.5F);
      LeftToe2.setTextureSize(128, 256);
      LeftToe2.mirror = true;
      setRotation(LeftToe2, 0F, 0F, 0F);
      RightToe1 = new ModelRenderer(this, 30, 130);
      RightToe1.addBox(0F, 0F, -2F, 1, 1, 2);
      RightToe1.setRotationPoint(-4.99F, 23.01F, -1.5F);
      RightToe1.setTextureSize(128, 256);
      RightToe1.mirror = true;
      setRotation(RightToe1, 0F, 0F, 0F);
      RightToe2 = new ModelRenderer(this, 40, 130);
      RightToe2.addBox(-1F, 0F, -2F, 1, 1, 2);
      RightToe2.setRotationPoint(-2.01F, 23.01F, -1.5F);
      RightToe2.setTextureSize(128, 256);
      RightToe2.mirror = true;
      setRotation(RightToe2, 0F, 0F, 0F);
      NailL2 = new ModelRenderer(this, 54, 100);
      NailL2.addBox(-0.1F, 0.6F, 0.2F, 1, 1, 0);
      NailL2.setRotationPoint(3.8F, 15.4F, -5.6F);
      NailL2.setTextureSize(128, 256);
      NailL2.mirror = true;
      setRotation(NailL2, -1.099557F, -0.2617994F, -0.122173F);
      HeadU = new ModelRenderer(this, 14, 115);
      HeadU.addBox(-4F, 0F, -4F, 6, 5, 6);
      HeadU.setRotationPoint(1F, 5F, 1F);
      HeadU.setTextureSize(128, 256);
      HeadU.mirror = true;
      setRotation(HeadU, 0F, 0F, 0F);
      ToothL = new ModelRenderer(this, 33, 76);
      ToothL.addBox(-1F, 0F, -1F, 1, 1, 0);
      ToothL.setRotationPoint(1.8F, 8.9F, -4.6F);
      ToothL.setTextureSize(128, 256);
      ToothL.mirror = true;
      setRotation(ToothL, -0.2792527F, -0.2094395F, -0.7330383F);
      UpperJaw = new ModelRenderer(this, 20, 70);
      UpperJaw.addBox(-3F, -1F, -4F, 5, 2, 3);
      UpperJaw.setRotationPoint(0.5F, 9.5F, -1.8F);
      UpperJaw.setTextureSize(128, 256);
      UpperJaw.mirror = true;
      setRotation(UpperJaw, -0.1570796F, 0F, 0F);
      ToothR = new ModelRenderer(this, 21, 76);
      ToothR.addBox(-1F, 0F, -1F, 1, 1, 0);
      ToothR.setRotationPoint(-1.7F, 8.9F, -4.6F);
      ToothR.setTextureSize(128, 256);
      ToothR.mirror = true;
      setRotation(ToothR, -0.2617994F, -0.2094395F, -0.7330383F);
      TailBase = new ModelRenderer(this, 48, 108);
      TailBase.addBox(-2.5F, -0.8F, 0.5F, 5, 5, 2);
      TailBase.setRotationPoint(0F, 17F, 2.9F);
      TailBase.setTextureSize(128, 256);
      TailBase.mirror = true;
      setRotation(TailBase, 0.2617994F, 0F, 0F);
      Shell6 = new ModelRenderer(this, 112, 119);
      Shell6.addBox(-2F, 0F, 3.5F, 2, 2, 3);
      Shell6.setRotationPoint(1F, 13.4F, 11F);
      Shell6.setTextureSize(128, 256);
      Shell6.mirror = true;
      setRotation(Shell6, 0.5061455F, 0F, 0F);
      Spike1 = new ModelRenderer(this, 92, 101);
      Spike1.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike1.setRotationPoint(1.5F, 16.3F, 11F);
      Spike1.setTextureSize(128, 256);
      Spike1.mirror = true;
      setRotation(Spike1, -1.947127F, -0.8787578F, 2.647943F);
      Tail2 = new ModelRenderer(this, 89, 107);
      Tail2.addBox(-2F, 0F, 0F, 6, 6, 3);
      Tail2.setRotationPoint(-1F, 13.5F, 7F);
      Tail2.setTextureSize(128, 256);
      Tail2.mirror = true;
      setRotation(Tail2, 0.5061455F, 0F, 0F);
      Tooth1 = new ModelRenderer(this, 76, 124);
      Tooth1.addBox(-1F, -1F, 0F, 1, 1, 2);
      Tooth1.setRotationPoint(1F, 14.9F, 6F);
      Tooth1.setTextureSize(128, 256);
      Tooth1.mirror = true;
      setRotation(Tooth1, -1.542912F, -0.8179294F, -0.0371786F);
      Shell4 = new ModelRenderer(this, 95, 117);
      Shell4.addBox(-2F, 0F, 2F, 4, 5, 3);
      Shell4.setRotationPoint(0F, 13F, 9F);
      Shell4.setTextureSize(128, 256);
      Shell4.mirror = true;
      setRotation(Shell4, 0.5061455F, 0F, 0F);
      ArmR = new ModelRenderer(this, 23, 114);
      ArmR.addBox(-1F, 0F, -1F, 2, 6, 2);
      ArmR.setRotationPoint(-3F, 12.7F, -0.9F);
      ArmR.setTextureSize(128, 256);
      ArmR.mirror = true;
      setRotation(ArmR, -1.099557F, 0.2617994F, 0.0349066F);
      NailL1 = new ModelRenderer(this, 48, 100);
      NailL1.addBox(-1.1F, 0F, -0.8F, 2, 1, 0);
      NailL1.setRotationPoint(4.8F, 15.4F, -5.9F);
      NailL1.setTextureSize(128, 256);
      NailL1.mirror = true;
      setRotation(NailL1, -1.099557F, -0.2617994F, -0.122173F);
      NailR1 = new ModelRenderer(this, 6, 101);
      NailR1.addBox(-1F, 0.5F, -1F, 2, 1, 0);
      NailR1.setRotationPoint(-4.4F, 15.4F, -5.5F);
      NailR1.setTextureSize(128, 256);
      NailR1.mirror = true;
      setRotation(NailR1, -1.099557F, 0.2617994F, 0.0349066F);
      NailR2 = new ModelRenderer(this, 3, 101);
      NailR2.addBox(0F, -0.2F, 0F, 1, 1, 0);
      NailR2.setRotationPoint(-4.5F, 16F, -5.9F);
      NailR2.setTextureSize(128, 256);
      NailR2.mirror = true;
      setRotation(NailR2, -1.099557F, 0.2617994F, 0.0349066F);
      LegBaseL = new ModelRenderer(this, 30, 110);
      LegBaseL.addBox(0F, 0F, -4.7F, 3, 5, 6);
      LegBaseL.setRotationPoint(2F, 18F, 2.5F);
      LegBaseL.setTextureSize(128, 256);
      LegBaseL.mirror = true;
      setRotation(LegBaseL, -0.2792527F, 0F, 0F);
      Foot1L = new ModelRenderer(this, 30, 110);
      Foot1L.addBox(-0.99F, 0F, -2F, 3, 2, 3);
      Foot1L.setRotationPoint(3F, 21.7F, -0.7F);
      Foot1L.setTextureSize(128, 256);
      Foot1L.mirror = true;
      setRotation(Foot1L, 0F, 0F, 0F);
      Foot1R = new ModelRenderer(this, 30, 110);
      Foot1R.addBox(-1.01F, 0F, -2F, 3, 1, 3);
      Foot1R.setRotationPoint(-4F, 21.7F, -0.6F);
      Foot1R.setTextureSize(128, 256);
      Foot1R.mirror = true;
      setRotation(Foot1R, 0F, 0F, 0F);
      FootBaseR = new ModelRenderer(this, 12, 122);
      FootBaseR.addBox(-3F, 0F, -4F, 3, 2, 5);
      FootBaseR.setRotationPoint(-2F, 22F, 1.4F);
      FootBaseR.setTextureSize(128, 256);
      FootBaseR.mirror = true;
      setRotation(FootBaseR, 0F, 0F, 0F);
      BellyPatch = new ModelRenderer(this, 18, 88);
      BellyPatch.addBox(-4F, 0F, -4F, 6, 4, 6);
      BellyPatch.setRotationPoint(1F, 17.01F, 0.5F);
      BellyPatch.setTextureSize(128, 256);
      BellyPatch.mirror = true;
      setRotation(BellyPatch, 0.1047198F, 0F, 0F);
      TailBase2 = new ModelRenderer(this, 48, 108);
      TailBase2.addBox(-2F, 0F, 0F, 4, 4, 4);
      TailBase2.setRotationPoint(0F, 17.1F, 3F);
      TailBase2.setTextureSize(128, 256);
      TailBase2.mirror = true;
      setRotation(TailBase2, 0.4537856F, 0F, 0F);
      LegBaseR = new ModelRenderer(this, 12, 110);
      LegBaseR.addBox(-3F, 0F, -4.7F, 3, 5, 6);
      LegBaseR.setRotationPoint(-2F, 18F, 2.5F);
      LegBaseR.setTextureSize(128, 256);
      LegBaseR.mirror = true;
      setRotation(LegBaseR, -0.2792527F, 0F, 0F);
      Shell5 = new ModelRenderer(this, 112, 119);
      Shell5.addBox(-2F, 0F, 2F, 3, 3, 3);
      Shell5.setRotationPoint(0.5F, 12.8F, 11F);
      Shell5.setTextureSize(128, 256);
      Shell5.mirror = true;
      setRotation(Shell5, 0.5061455F, 0F, 0F);
      BodyMain = new ModelRenderer(this, 75, 40);
      BodyMain.addBox(-4F, 0F, -4F, 7, 9, 8);
      BodyMain.setRotationPoint(0.5F, 11.9F, 0.2F);
      BodyMain.setTextureSize(128, 256);
      BodyMain.mirror = true;
      setRotation(BodyMain, 0.1047198F, 0F, 0F);
      BodyF = new ModelRenderer(this, 14, 89);
      BodyF.addBox(-4F, 0F, -4F, 6, 8, 8);
      BodyF.setRotationPoint(1F, 13F, -0.1F);
      BodyF.setTextureSize(128, 256);
      BodyF.mirror = true;
      setRotation(BodyF, 0.1047198F, 0F, 0F);
      HeadMain = new ModelRenderer(this, 14, 53);
      HeadMain.addBox(-4F, 0F, -4F, 7, 9, 7);
      HeadMain.setRotationPoint(0.5F, 5.5F, 0.5F);
      HeadMain.setTextureSize(128, 256);
      HeadMain.mirror = true;
      setRotation(HeadMain, 0F, 0F, 0F);
      Shell1 = new ModelRenderer(this, 66, 106);
      Shell1.addBox(-2F, 0F, 1F, 7, 6, 4);
      Shell1.setRotationPoint(-1.5F, 15.1F, 4F);
      Shell1.setTextureSize(128, 256);
      Shell1.mirror = true;
      setRotation(Shell1, 0.5061455F, 0F, 0F);
      Shell3 = new ModelRenderer(this, 108, 107);
      Shell3.addBox(-2F, 0F, 1F, 5, 6, 3);
      Shell3.setRotationPoint(-0.5F, 13.1F, 8F);
      Shell3.setTextureSize(128, 256);
      Shell3.mirror = true;
      setRotation(Shell3, 0.5061455F, 0F, 0F);
      Spike2 = new ModelRenderer(this, 92, 101);
      Spike2.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike2.setRotationPoint(-3.5F, 16.3F, 7F);
      Spike2.setTextureSize(128, 256);
      Spike2.mirror = true;
      setRotation(Spike2, 1.500983F, 1.064651F, 0.5061455F);
      Spike3 = new ModelRenderer(this, 92, 101);
      Spike3.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike3.setRotationPoint(2.5F, 16.3F, 7F);
      Spike3.setTextureSize(128, 256);
      Spike3.mirror = true;
      setRotation(Spike3, -1.649698F, -0.953115F, 2.629767F);
      Spike4 = new ModelRenderer(this, 92, 101);
      Spike4.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike4.setRotationPoint(-1F, 14F, 13.3F);
      Spike4.setTextureSize(128, 256);
      Spike4.mirror = true;
      setRotation(Spike4, 0.7623681F, 1.686566F, 0.0431685F);
      Spike5 = new ModelRenderer(this, 92, 101);
      Spike5.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike5.setRotationPoint(-2.7F, 17.3F, 10F);
      Spike5.setTextureSize(128, 256);
      Spike5.mirror = true;
      setRotation(Spike5, -1.57534F, -2.254366F, 2.29516F);
      Spike6 = new ModelRenderer(this, 92, 101);
      Spike6.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike6.setRotationPoint(-0.5F, 14.3F, 9F);
      Spike6.setTextureSize(128, 256);
      Spike6.mirror = true;
      setRotation(Spike6, -1.57534F, -1.36208F, 2.29516F);
      Spike7 = new ModelRenderer(this, 92, 101);
      Spike7.addBox(-3F, 0F, 0F, 3, 1, 1);
      Spike7.setRotationPoint(-1.2F, 13F, 15.2F);
      Spike7.setTextureSize(128, 256);
      Spike7.mirror = true;
      setRotation(Spike7, -2.244555F, -0.953115F, 1.943099F);
      Spike8 = new ModelRenderer(this, 109, 101);
      Spike8.addBox(-1F, -1F, 0F, 1, 1, 2);
      Spike8.setRotationPoint(0.5F, 14.9F, 8F);
      Spike8.setTextureSize(128, 256);
      Spike8.mirror = true;
      setRotation(Spike8, 1.381702F, 0.0371786F, -0.1487144F);
      Tooth2 = new ModelRenderer(this, 76, 124);
      Tooth2.addBox(-1F, -1F, 0F, 1, 1, 2);
      Tooth2.setRotationPoint(1.5F, 14.9F, 6F);
      Tooth2.setTextureSize(128, 256);
      Tooth2.mirror = true;
      setRotation(Tooth2, -1.542912F, -0.8179294F, -0.0371786F);
      Tooth3 = new ModelRenderer(this, 76, 124);
      Tooth3.addBox(-1F, -1F, 0F, 1, 1, 2);
      Tooth3.setRotationPoint(-0.5F, 14.9F, 6F);
      Tooth3.setTextureSize(128, 256);
      Tooth3.mirror = true;
      setRotation(Tooth3, -1.542912F, -0.8179294F, -0.0371786F);
  }
  
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    EarL.render(f5);
    LowerJaw.render(f5);
    EarR.render(f5);
    ArmL.render(f5);
    FootBaseL.render(f5);
    LeftToe1.render(f5);
    LeftToe2.render(f5);
    RightToe1.render(f5);
    RightToe2.render(f5);
    NailL2.render(f5);
    HeadU.render(f5);
    ToothL.render(f5);
    UpperJaw.render(f5);
    ToothR.render(f5);
    TailBase.render(f5);
    Shell6.render(f5);
    Spike1.render(f5);
    Tail2.render(f5);
    Tooth1.render(f5);
    Shell4.render(f5);
    ArmR.render(f5);
    NailL1.render(f5);
    NailR1.render(f5);
    NailR2.render(f5);
    LegBaseL.render(f5);
    Foot1L.render(f5);
    Foot1R.render(f5);
    FootBaseR.render(f5);
    BellyPatch.render(f5);
    TailBase2.render(f5);
    LegBaseR.render(f5);
    Shell5.render(f5);
    BodyMain.render(f5);
    BodyF.render(f5);
    HeadMain.render(f5);
    Shell1.render(f5);
    Shell3.render(f5);
    Spike2.render(f5);
    Spike3.render(f5);
    Spike4.render(f5);
    Spike5.render(f5);
    Spike6.render(f5);
    Spike7.render(f5);
    Spike8.render(f5);
    Tooth2.render(f5);
    Tooth3.render(f5);
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
  }

}
