package pixelmon.client.models.trainers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFemaleScientist extends ModelBase
{
  //fields
    ModelRenderer Glass1;
    ModelRenderer ear1;
    ModelRenderer Glass2;
    ModelRenderer Ear_2;
    ModelRenderer Glass_3;
    ModelRenderer Glass_4;
    ModelRenderer Glass__5;
    ModelRenderer Item;
    ModelRenderer Shoe_1;
    ModelRenderer Shoes_2;
    ModelRenderer Shoes_3;
    ModelRenderer Shoe_4;
    ModelRenderer Coat_1;
    ModelRenderer Coat_2;
    ModelRenderer Coat_3;
    ModelRenderer Skirt;
    ModelRenderer Hair;
    ModelRenderer Rack;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    
  
  public ModelFemaleScientist()
  {
	  System.out.println("Finds class");
    textureWidth = 72;
    textureHeight = 72;
    
      Glass1 = new ModelRenderer(this, 36, 0);
      Glass1.addBox(0F, 0F, 0F, 1, 1, 4);
      Glass1.setRotationPoint(-4F, -7F, -3.5F);
      Glass1.setTextureSize(72, 72);
      Glass1.mirror = true;
      setRotation(Glass1, 0F, 0F, 0F);
      ear1 = new ModelRenderer(this, 60, 0);
      ear1.addBox(0F, 0F, 0F, 1, 1, 1);
      ear1.setRotationPoint(-4F, -6F, -1F);
      ear1.setTextureSize(72, 72);
      ear1.mirror = true;
      setRotation(ear1, 0F, 0F, 0F);
      Glass2 = new ModelRenderer(this, 36, 0);
      Glass2.addBox(0F, 0F, 0F, 1, 1, 4);
      Glass2.setRotationPoint(4F, -7F, -3.5F);
      Glass2.setTextureSize(72, 72);
      Glass2.mirror = true;
      setRotation(Glass2, 0F, 0F, 0F);
      Ear_2 = new ModelRenderer(this, 60, 0);
      Ear_2.addBox(0F, 0F, 0F, 1, 1, 1);
      Ear_2.setRotationPoint(4F, -6F, -1F);
      Ear_2.setTextureSize(72, 72);
      Ear_2.mirror = true;
      setRotation(Ear_2, 0F, 0F, 0F);
      Glass_3 = new ModelRenderer(this, 30, 7);
      Glass_3.addBox(0F, 0F, 0F, 3, 2, 1);
      Glass_3.setRotationPoint(1F, -7F, -4F);
      Glass_3.setTextureSize(72, 72);
      Glass_3.mirror = true;
      setRotation(Glass_3, 0F, 0F, 0F);
      Glass_4 = new ModelRenderer(this, 30, 10);
      Glass_4.addBox(0F, 0F, 0F, 3, 2, 1);
      Glass_4.setRotationPoint(-3F, -7F, -4F);
      Glass_4.setTextureSize(72, 72);
      Glass_4.mirror = true;
      setRotation(Glass_4, 0F, 0F, 0F);
      Glass__5 = new ModelRenderer(this, 36, 0);
      Glass__5.addBox(0F, 0F, 0F, 1, 1, 1);
      Glass__5.setRotationPoint(0F, -7F, -4F);
      Glass__5.setTextureSize(72, 72);
      Glass__5.mirror = true;
      setRotation(Glass__5, 0F, 0F, 0F);
      Item = new ModelRenderer(this, 0, 36);
      Item.addBox(0F, 0F, 1.3F, 1, 3, 1);
      Item.setRotationPoint(-6.466667F, 6F, -5.4F);
      Item.setTextureSize(72, 72);
      Item.mirror = true;
      setRotation(Item, 0.1115358F, 0F, 0F);
      Shoe_1 = new ModelRenderer(this, 31, 45);
      Shoe_1.addBox(0F, 0F, 0F, 4, 1, 5);
      Shoe_1.setRotationPoint(1F, 23F, -4F);
      Shoe_1.setTextureSize(72, 72);
      Shoe_1.mirror = true;
      setRotation(Shoe_1, 0.2268928F, 0F, 0F);
      Shoes_2 = new ModelRenderer(this, 31, 45);
      Shoes_2.addBox(0F, 0F, 0F, 4, 1, 5);
      Shoes_2.setRotationPoint(-4F, 23F, -4F);
      Shoes_2.setTextureSize(72, 72);
      Shoes_2.mirror = true;
      setRotation(Shoes_2, 0.2268928F, 0F, 0F);
      Shoes_3 = new ModelRenderer(this, 31, 40);
      Shoes_3.addBox(0F, 0F, 0F, 3, 1, 1);
      Shoes_3.setRotationPoint(-3.5F, 23F, 0F);
      Shoes_3.setTextureSize(72, 72);
      Shoes_3.mirror = true;
      setRotation(Shoes_3, 0F, 0F, 0F);
      Shoe_4 = new ModelRenderer(this, 31, 40);
      Shoe_4.addBox(0F, 0F, 0F, 3, 1, 1);
      Shoe_4.setRotationPoint(1.5F, 23F, 0F);
      Shoe_4.setTextureSize(72, 72);
      Shoe_4.mirror = true;
      setRotation(Shoe_4, 0F, 0F, 0F);
      Coat_1 = new ModelRenderer(this, 5, 44);
      Coat_1.addBox(0F, 0F, 0F, 1, 11, 3);
      Coat_1.setRotationPoint(-4F, 10.13333F, -2F);
      Coat_1.setTextureSize(72, 72);
      Coat_1.mirror = true;
      setRotation(Coat_1, 0F, 0F, 0.1570796F);
      Coat_2 = new ModelRenderer(this, 5, 44);
      Coat_2.addBox(0F, 0F, 0F, 1, 11, 3);
      Coat_2.setRotationPoint(4F, 10.33333F, -2F);
      Coat_2.setTextureSize(72, 72);
      Coat_2.mirror = true;
      setRotation(Coat_2, 0F, 0F, -0.1570796F);
      Coat_3 = new ModelRenderer(this, 5, 46);
      Coat_3.addBox(0F, 0F, 0F, 9, 11, 1);
      Coat_3.setRotationPoint(-4F, 10F, 0.2666667F);
      Coat_3.setTextureSize(72, 72);
      Coat_3.mirror = true;
      setRotation(Coat_3, 0.122173F, 0F, 0F);
      Skirt = new ModelRenderer(this, 42, 36);
      Skirt.addBox(0F, 0F, 0F, 9, 6, 1);
      Skirt.setRotationPoint(-4F, 10F, -2.2F);
      Skirt.setTextureSize(72, 72);
      Skirt.mirror = true;
      setRotation(Skirt, -0.122173F, 0F, 0F);
      Hair = new ModelRenderer(this, 40, 6);
      Hair.addBox(0F, 0F, 0F, 7, 8, 1);
      Hair.setRotationPoint(-3F, -2F, 2F);
      Hair.setTextureSize(72, 72);
      Hair.mirror = true;
      setRotation(Hair, 0.0994838F, 0F, 0F);
      Rack = new ModelRenderer(this, 18, 20);
      Rack.addBox(0F, 0F, 0.5F, 9, 3, 2);
      Rack.setRotationPoint(-4F, -1F, -3F);
      Rack.setTextureSize(72, 72);
      Rack.mirror = true;
      setRotation(Rack, 0F, 0F, 0F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 7, 7, 6);
      head.setRotationPoint(1F, -1F, 1F);
      head.setTextureSize(72, 72);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 17);
      body.addBox(-4F, 0F, -2F, 9, 12, 4);
      body.setRotationPoint(0F, -2F, 0F);
      body.setTextureSize(72, 72);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 48, 16);
      rightarm.addBox(-3F, -2F, -2F, 3, 12, 4);
      rightarm.setRotationPoint(-4F, 0F, 0F);
      rightarm.setTextureSize(72, 72);
      rightarm.mirror = true;
      setRotation(rightarm, -0.1115358F, 0F, 0F);
      leftarm = new ModelRenderer(this, 48, 16);
      leftarm.addBox(-1F, -2F, -2F, 3, 12, 4);
      leftarm.setRotationPoint(6F, 0F, 0F);
      leftarm.setTextureSize(72, 72);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 4, 12, 3);
      rightleg.setRotationPoint(-2F, 10.5F, 0F);
      rightleg.setTextureSize(72, 72);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, -0.0349066F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, -1F, -2F, 4, 12, 3);
      leftleg.setRotationPoint(3F, 11.53333F, 0F);
      leftleg.setTextureSize(72, 72);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
   // super.render(entity, f, f1, f2, f3, f4, f5);
    //setRotationAngles(f, f1, f2, f3, f4, f5);
    Glass1.render(f5);
    ear1.render(f5);
    Glass2.render(f5);
    Ear_2.render(f5);
    Glass_3.render(f5);
    Glass_4.render(f5);
    Glass__5.render(f5);
    Item.render(f5);
    Shoe_1.render(f5);
    Shoes_2.render(f5);
    Shoes_3.render(f5);
    Shoe_4.render(f5);
    Coat_1.render(f5);
    Coat_2.render(f5);
    Coat_3.render(f5);
    Skirt.render(f5);
    Hair.render(f5);
    Rack.render(f5);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
    
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {

  }

}