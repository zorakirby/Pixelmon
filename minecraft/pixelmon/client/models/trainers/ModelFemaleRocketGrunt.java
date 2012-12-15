package pixelmon.client.models.trainers;

import pixelmon.entities.pixelmon.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class ModelFemaleRocketGrunt extends ModelBase
{
  //fields
    ModelRenderer leftarm;
    ModelRenderer rightarm;
    ModelRenderer RIGHTARMPIECE;
    ModelRenderer HEADPIECE;
    ModelRenderer RIGHTLEGPIECE;
    ModelRenderer LEFTLEGPIECE;
    ModelRenderer BODYPIECE;
    ModelRenderer Pokeball;
    ModelRenderer head;
    ModelRenderer HatBase1;
    ModelRenderer Hatbill;
    ModelRenderer HatBase2;
    ModelRenderer headbak;
    ModelRenderer Hair1;
    ModelRenderer Hair2;
    ModelRenderer Hair3;
    ModelRenderer Hair4;
    ModelRenderer Hair5;
    ModelRenderer Hair6;
    ModelRenderer Foot1;
    ModelRenderer Foot2;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer breast;
    ModelRenderer Waste;
    ModelRenderer body;
  
  public ModelFemaleRocketGrunt()
  {
    textureWidth = 64;
    textureHeight = 32;
    setTextureOffset("RIGHTARMPIECE.Folder1", 0, 0);
    setTextureOffset("HEADPIECE.folder1", 0, 0);
    setTextureOffset("RIGHTLEGPIECE.Folder1", 0, 0);
    setTextureOffset("LEFTLEGPIECE.Folder1", 0, 0);
    setTextureOffset("BODYPIECE.Folder1", 0, 0);
    
      leftarm = new ModelRenderer(this, 34, 17);
      leftarm.addBox(0F, -2F, -1F, 2, 10, 2);
      leftarm.setRotationPoint(3F, 7F, 0F);
      leftarm.setTextureSize(64, 32);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, -0.0743572F);
      rightarm = new ModelRenderer(this, 34, 17);
      rightarm.addBox(-2F, -2F, -1F, 2, 10, 2);
      rightarm.setRotationPoint(-4F, 7F, 0F);
      rightarm.setTextureSize(64, 32);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0.074351F);
    RIGHTARMPIECE = new ModelRenderer(this, "RIGHTARMPIECE");
    RIGHTARMPIECE.setRotationPoint(-4F, 6F, 0F);
    setRotation(RIGHTARMPIECE, 0F, 0F, 0F);
    RIGHTARMPIECE.mirror = true;
      Pokeball = new ModelRenderer(this, 43, 17);
      Pokeball.addBox(-2F, -2F, 0F, 2, 2, 2);
      Pokeball.setRotationPoint(-1F, 10F, -2F);
      Pokeball.setTextureSize(64, 32);
      Pokeball.mirror = true;
      setRotation(Pokeball, 0F, 0F, 0.074351F);
    HEADPIECE = new ModelRenderer(this, "HEADPIECE");
    HEADPIECE.setRotationPoint(-0.5F, 5F, 0F);
    setRotation(HEADPIECE, 0F, 0F, 0F);
    HEADPIECE.mirror = true;
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-2.5F, -5F, -3F, 5, 5, 5);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      HatBase1 = new ModelRenderer(this, 24, 0);
      HatBase1.addBox(0F, 0F, 0F, 6, 2, 6);
      HatBase1.setRotationPoint(-3F, -7F, -3.5F);
      HatBase1.setTextureSize(64, 32);
      HatBase1.mirror = true;
      setRotation(HatBase1, 0F, 0F, 0F);
      Hatbill = new ModelRenderer(this, 24, 0);
      Hatbill.addBox(0F, 0F, 0F, 6, 1, 4);
      Hatbill.setRotationPoint(-3F, -6F, -4.5F);
      Hatbill.setTextureSize(64, 32);
      Hatbill.mirror = true;
      setRotation(Hatbill, 0F, 0F, 0F);
      HatBase2 = new ModelRenderer(this, 24, 0);
      HatBase2.addBox(0F, 0F, 0F, 5, 1, 5);
      HatBase2.setRotationPoint(-2.5F, -8F, -3F);
      HatBase2.setTextureSize(64, 32);
      HatBase2.mirror = true;
      setRotation(HatBase2, 0F, 0F, 0F);
      headbak = new ModelRenderer(this, 54, 0);
      headbak.addBox(-2.5F, -5F, -3F, 5, 7, 0);
      headbak.setRotationPoint(0F, -1F, 5F);
      headbak.setTextureSize(64, 32);
      headbak.mirror = true;
      setRotation(headbak, 0F, 0F, 0F);
      Hair1 = new ModelRenderer(this, 0, 10);
      Hair1.addBox(-2.5F, -5F, -3F, 1, 3, 1);
      Hair1.setRotationPoint(4.5F, 0F, 4F);
      Hair1.setTextureSize(64, 32);
      Hair1.mirror = true;
      setRotation(Hair1, -0.0743572F, 0F, -0.0743572F);
      Hair2 = new ModelRenderer(this, 0, 10);
      Hair2.addBox(-2.5F, -5F, -3F, 1, 4, 1);
      Hair2.setRotationPoint(-0.3F, 1F, 3F);
      Hair2.setTextureSize(64, 32);
      Hair2.mirror = true;
      setRotation(Hair2, -0.4089647F, 0.1858931F, 0.1487144F);
      Hair3 = new ModelRenderer(this, 0, 10);
      Hair3.addBox(-2.5F, -6F, -3F, 1, 5, 1);
      Hair3.setRotationPoint(-0.5F, 1F, 2F);
      Hair3.setTextureSize(64, 32);
      Hair3.mirror = true;
      setRotation(Hair3, -0.2974289F, 0.0743572F, 0.1858931F);
      Hair4 = new ModelRenderer(this, 0, 10);
      Hair4.addBox(-2.5F, -5F, -3F, 1, 3, 1);
      Hair4.setRotationPoint(-0.5F, 0F, 4F);
      Hair4.setTextureSize(64, 32);
      Hair4.mirror = true;
      setRotation(Hair4, -0.0743572F, 0F, 0.1115358F);
      Hair5 = new ModelRenderer(this, 0, 10);
      Hair5.addBox(-2.5F, -5F, -3F, 1, 4, 1);
      Hair5.setRotationPoint(5.3F, 0F, 3F);
      Hair5.setTextureSize(64, 32);
      Hair5.mirror = true;
      setRotation(Hair5, -0.2974289F, 0.2230717F, -0.1858931F);
      Hair6 = new ModelRenderer(this, 0, 10);
      Hair6.addBox(-2.5F, -6F, -3F, 1, 5, 1);
      Hair6.setRotationPoint(5F, 1F, 2F);
      Hair6.setTextureSize(64, 32);
      Hair6.mirror = true;
      setRotation(Hair6, -0.4833219F, 0.1115358F, -0.2230717F);
    RIGHTLEGPIECE = new ModelRenderer(this, "RIGHTLEGPIECE");
    RIGHTLEGPIECE.setRotationPoint(-2F, 15F, 0F);
    setRotation(RIGHTLEGPIECE, 0F, 0F, 0F);
    RIGHTLEGPIECE.mirror = true;
      Foot1 = new ModelRenderer(this, 3, 26);
      Foot1.addBox(-2F, 0F, -2F, 3, 2, 2);
      Foot1.setRotationPoint(0F, 7F, -1F);
      Foot1.setTextureSize(64, 32);
      Foot1.mirror = true;
      setRotation(Foot1, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 17);
      rightleg.addBox(-2F, 0F, -2F, 3, 10, 3);
      rightleg.setRotationPoint(0F, -1F, 0F);
      rightleg.setTextureSize(64, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
    LEFTLEGPIECE = new ModelRenderer(this, "LEFTLEGPIECE");
    LEFTLEGPIECE.setRotationPoint(1F, 15F, 0F);
    setRotation(LEFTLEGPIECE, 0F, 0F, 0F);
    LEFTLEGPIECE.mirror = true;
      leftleg = new ModelRenderer(this, 0, 17);
      leftleg.addBox(-2F, 0F, -2F, 3, 10, 3);
      leftleg.setRotationPoint(1F, -1F, 0F);
      leftleg.setTextureSize(64, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      Foot2 = new ModelRenderer(this, 0, 26);
      Foot2.addBox(-2F, 0F, -2F, 3, 2, 2);
      Foot2.setRotationPoint(1F, 7F, -1F);
      Foot2.setTextureSize(64, 32);
      Foot2.mirror = true;
      setRotation(Foot2, 0F, 0F, 0F);
    BODYPIECE = new ModelRenderer(this, "BODYPIECE");
    BODYPIECE.setRotationPoint(-0.5F, 11F, -1F);
    setRotation(BODYPIECE, 0F, 0F, 0F);
    BODYPIECE.mirror = true;
      breast = new ModelRenderer(this, 44, 8);
      breast.addBox(-4F, 0F, -2F, 7, 3, 3);
      breast.setRotationPoint(0.5F, -5F, 0.6F);
      breast.setTextureSize(64, 32);
      breast.mirror = true;
      setRotation(breast, 0F, 0F, 0F);
      Waste = new ModelRenderer(this, 43, 22);
      Waste.addBox(-4F, 0F, -2F, 6, 4, 3);
      Waste.setRotationPoint(1F, 0F, 1F);
      Waste.setTextureSize(64, 32);
      Waste.mirror = true;
      setRotation(Waste, 0F, 0F, 0F);
      body = new ModelRenderer(this, 13, 17);
      body.addBox(-4F, 0F, -2F, 7, 6, 3);
      body.setRotationPoint(0.5F, -6F, 1F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      
      RIGHTARMPIECE.addChild(Pokeball);
      RIGHTARMPIECE.addChild(rightarm);
      
      RIGHTLEGPIECE.addChild(Foot1);
      RIGHTLEGPIECE.addChild(rightleg);
      
      LEFTLEGPIECE.addChild(Foot2);
      LEFTLEGPIECE.addChild(leftleg);
      
      BODYPIECE.addChild(breast);
      BODYPIECE.addChild(Waste);
      BODYPIECE.addChild(body);
      
      HEADPIECE.addChild(head);
      HEADPIECE.addChild(HatBase1);
      HEADPIECE.addChild(Hatbill);
      HEADPIECE.addChild(HatBase2);
      HEADPIECE.addChild(headbak);
      HEADPIECE.addChild(Hair1);
      HEADPIECE.addChild(Hair2);
      HEADPIECE.addChild(Hair3);
      HEADPIECE.addChild(Hair4);
      HEADPIECE.addChild(Hair5);
      HEADPIECE.addChild(Hair6);
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    leftarm.render(f5);
    rightarm.render(f5);
    RIGHTARMPIECE.render(f5);
    HEADPIECE.render(f5);
    RIGHTLEGPIECE.render(f5);
    LEFTLEGPIECE.render(f5);
    BODYPIECE.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
	  HEADPIECE.rotateAngleY = f3 / (180F / (float) Math.PI);
	  HEADPIECE.rotateAngleX = f4 / (180F / (float) Math.PI);
		 RIGHTLEGPIECE.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	     LEFTLEGPIECE.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	     RIGHTARMPIECE.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * .8F * f1;
	     leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * .8F * f1;

  }

}
