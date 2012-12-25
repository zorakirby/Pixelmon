package pixelmon.client.models.pokemon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelParas extends ModelBase
{
  //fields
    ModelRenderer Eye3;
    ModelRenderer Eye1;
    ModelRenderer Eye2;
    ModelRenderer Eye4;
    ModelRenderer Leg_part9;
    ModelRenderer Body;
    ModelRenderer Right_tooth2;
    ModelRenderer ButtLayer1;
    ModelRenderer ButtLayer2;
    ModelRenderer Stim1;
    ModelRenderer Left_tooth1;
    ModelRenderer Left_tooth2;
    ModelRenderer BaseMushroom1;
    ModelRenderer Right_tooth1;
    ModelRenderer BaseMushroom2;
    ModelRenderer Head;
    ModelRenderer Stim2;
    ModelRenderer Nose;
    ModelRenderer Leg_part3;
    ModelRenderer Leg_part10;
    ModelRenderer Leg_part5;
    ModelRenderer Leg_part6;
    ModelRenderer Leg_part7;
    ModelRenderer Leg_part8;
    ModelRenderer Leg_part4;
    ModelRenderer Leg_part1;
    ModelRenderer claw1;
    ModelRenderer Leg_part2;
    ModelRenderer claw2;
    ModelRenderer LEFTCLAW;
    ModelRenderer RIGHTCLAW;
  
  public ModelParas()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Eye3 = new ModelRenderer(this, 32, 20);
      Eye3.addBox(-2F, 0F, 0F, 1, 1, 0);
      Eye3.setRotationPoint(3.5F, 19F, -0.2F);
      Eye3.setTextureSize(64, 32);
      Eye3.mirror = true;
      setRotation(Eye3, 0F, 0F, 0F);
      Eye1 = new ModelRenderer(this, 22, 22);
      Eye1.addBox(-2F, 0F, 0F, 2, 2, 0);
      Eye1.setRotationPoint(3F, 18.5F, -0.1F);
      Eye1.setTextureSize(64, 32);
      Eye1.mirror = true;
      setRotation(Eye1, 0F, 0F, 0F);
      Eye2 = new ModelRenderer(this, 18, 22);
      Eye2.addBox(-2F, 0F, 0F, 2, 2, 0);
      Eye2.setRotationPoint(0F, 18.5F, -0.1F);
      Eye2.setTextureSize(64, 32);
      Eye2.mirror = true;
      setRotation(Eye2, 0F, 0F, 0F);
      Eye3 = new ModelRenderer(this, 34, 20);
      Eye3.addBox(-2F, 0F, 0F, 1, 1, 0);
      Eye3.setRotationPoint(0.5F, 19F, -0.2F);
      Eye3.setTextureSize(64, 32);
      Eye3.mirror = true;
      setRotation(Eye3, 0F, 0F, 0F);
      Leg_part9 = new ModelRenderer(this, 12, 22);
      Leg_part9.addBox(-2F, 0F, 3F, 2, 1, 1);
      Leg_part9.setRotationPoint(4F, 19.5F, 2F);
      Leg_part9.setTextureSize(64, 32);
      Leg_part9.mirror = true;
      setRotation(Leg_part9, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 8, 8);
      Body.addBox(-2F, 0F, 0F, 5, 4, 6);
      Body.setRotationPoint(0F, 19F, 4F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Right_tooth2 = new ModelRenderer(this, 0, 10);
      Right_tooth2.addBox(-1.5F, 0F, -0.15F, 1, 1, 1);
      Right_tooth2.setRotationPoint(0.6F, 22F, 0F);
      Right_tooth2.setTextureSize(64, 32);
      Right_tooth2.mirror = true;
      setRotation(Right_tooth2, 0F, -0.5205006F, 0F);
      ButtLayer1 = new ModelRenderer(this, 18, 0);
      ButtLayer1.addBox(-1.5F, 0.5F, 0F, 4, 3, 5);
      ButtLayer1.setRotationPoint(0F, 19F, 6.5F);
      ButtLayer1.setTextureSize(64, 32);
      ButtLayer1.mirror = true;
      setRotation(ButtLayer1, 0F, 0F, 0F);
      ButtLayer2 = new ModelRenderer(this, 36, 0);
      ButtLayer2.addBox(0F, 0F, 0F, 3, 2, 4);
      ButtLayer2.setRotationPoint(-1F, 20F, 9F);
      ButtLayer2.setTextureSize(64, 32);
      ButtLayer2.mirror = true;
      setRotation(ButtLayer2, 0F, 0F, 0F);
      Stim1 = new ModelRenderer(this, 0, 16);
      Stim1.addBox(0F, 0F, 0F, 1, 2, 1);
      Stim1.setRotationPoint(-2F, 18F, 5F);
      Stim1.setTextureSize(64, 32);
      Stim1.mirror = true;
      setRotation(Stim1, 0F, 0F, -0.3346075F);
      Left_tooth1 = new ModelRenderer(this, 0, 13);
      Left_tooth1.addBox(0.2F, -1F, 1F, 1, 1, 1);
      Left_tooth1.setRotationPoint(0.6F, 22F, -1F);
      Left_tooth1.setTextureSize(64, 32);
      Left_tooth1.mirror = true;
      setRotation(Left_tooth1, 0F, 0.5751325F, 0F);
      Left_tooth2 = new ModelRenderer(this, 0, 13);
      Left_tooth2.addBox(0.3F, 0F, 0F, 1, 1, 1);
      Left_tooth2.setRotationPoint(0.6F, 22F, 0F);
      Left_tooth2.setTextureSize(64, 32);
      Left_tooth2.mirror = true;
      setRotation(Left_tooth2, 0F, 0.5576792F, 0F);
      BaseMushroom1 = new ModelRenderer(this, 0, 27);
      BaseMushroom1.addBox(-1F, 0F, 0F, 3, 2, 3);
      BaseMushroom1.setRotationPoint(-2.5F, 16.5F, 4F);
      BaseMushroom1.setTextureSize(64, 32);
      BaseMushroom1.mirror = true;
      setRotation(BaseMushroom1, 0F, 0F, -0.3346075F);
      Right_tooth1 = new ModelRenderer(this, 0, 10);
      Right_tooth1.addBox(-1.9F, -1F, 0F, 1, 1, 1);
      Right_tooth1.setRotationPoint(0.6F, 22F, 0F);
      Right_tooth1.setTextureSize(64, 32);
      Right_tooth1.mirror = true;
      setRotation(Right_tooth1, 0F, -0.5205006F, 0F);
      BaseMushroom2 = new ModelRenderer(this, 0, 22);
      BaseMushroom2.addBox(0F, 0F, 0F, 3, 2, 3);
      BaseMushroom2.setRotationPoint(1.7F, 15.8F, 4F);
      BaseMushroom2.setTextureSize(64, 32);
      BaseMushroom2.mirror = true;
      setRotation(BaseMushroom2, 0F, 0F, 0.3346145F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-2F, 0F, 0F, 5, 4, 4);
      Head.setRotationPoint(0F, 19F, 0F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Stim2 = new ModelRenderer(this, 0, 19);
      Stim2.addBox(0F, 0F, 0F, 1, 2, 1);
      Stim2.setRotationPoint(2F, 17.7F, 5F);
      Stim2.setTextureSize(64, 32);
      Stim2.mirror = true;
      setRotation(Stim2, 0F, 0F, 0.3346145F);
      Nose = new ModelRenderer(this, 0, 8);
      Nose.addBox(-1F, 0F, 0F, 2, 1, 1);
      Nose.setRotationPoint(0.5F, 20.5F, -0.7F);
      Nose.setTextureSize(64, 32);
      Nose.mirror = true;
      setRotation(Nose, 0F, 0F, 0F);
      Leg_part3 = new ModelRenderer(this, 12, 22);
      Leg_part3.addBox(0F, 0F, -1F, 2, 1, 1);
      Leg_part3.setRotationPoint(-3F, 19.5F, 6F);
      Leg_part3.setTextureSize(64, 32);
      Leg_part3.mirror = true;
      setRotation(Leg_part3, 0F, 0F, 0F);
      Leg_part10 = new ModelRenderer(this, 36, 6);
      Leg_part10.addBox(-0.5F, -0.5F, -0.5F, 6, 1, 1);
      Leg_part10.setRotationPoint(4F, 20.2F, 5.5F);
      Leg_part10.setTextureSize(64, 32);
      Leg_part10.mirror = true;
      setRotation(Leg_part10, 0F, 0F, 0.7063871F);
      Leg_part5 = new ModelRenderer(this, 12, 22);
      Leg_part5.addBox(0F, 0F, 3F, 2, 1, 1);
      Leg_part5.setRotationPoint(-3F, 19.5F, 6F);
      Leg_part5.setTextureSize(64, 32);
      Leg_part5.mirror = true;
      setRotation(Leg_part5, 0F, 0F, 0F);
      Leg_part6 = new ModelRenderer(this, 36, 6);
      Leg_part6.addBox(-5.5F, -0.5F, -0.5F, 6, 1, 1);
      Leg_part6.setRotationPoint(-3F, 20.2F, 9.5F);
      Leg_part6.setTextureSize(64, 32);
      Leg_part6.mirror = true;
      setRotation(Leg_part6, 0F, 0F, -0.7063871F);
      Leg_part7 = new ModelRenderer(this, 12, 22);
      Leg_part7.addBox(-2F, 0F, 3F, 2, 1, 1);
      Leg_part7.setRotationPoint(4F, 19.5F, 6F);
      Leg_part7.setTextureSize(64, 32);
      Leg_part7.mirror = true;
      setRotation(Leg_part7, 0F, 0F, 0F);
      Leg_part8 = new ModelRenderer(this, 36, 6);
      Leg_part8.addBox(0F, 0F, -0.5F, 6, 1, 1);
      Leg_part8.setRotationPoint(4F, 19.5F, 9.5F);
      Leg_part8.setTextureSize(64, 32);
      Leg_part8.mirror = true;
      setRotation(Leg_part8, 0F, 0F, 0.7063871F);
      Leg_part4 = new ModelRenderer(this, 36, 6);
      Leg_part4.addBox(-5.5F, -0.5F, -0.5F, 6, 1, 1);
      Leg_part4.setRotationPoint(-3F, 20.2F, 5.5F);
      Leg_part4.setTextureSize(64, 32);
      Leg_part4.mirror = true;
      setRotation(Leg_part4, 0F, 0F, -0.7063936F);
      Leg_part1 = new ModelRenderer(this, 11, 24);
      Leg_part1.addBox(0F, -1F, -1F, 6, 2, 2);
      Leg_part1.setRotationPoint(0F,0F,0F);
      Leg_part1.setTextureSize(64, 32);
      Leg_part1.mirror = true;
      setRotation(Leg_part1, 0F, 2.327274F, 0.7200181F);
      claw1 = new ModelRenderer(this, 0, 0);
      claw1.addBox(-2F, 0F, 0F, 2, 1, 1);
      claw1.setRotationPoint(0F,0F,0F);
      claw1.setTextureSize(64, 32);
      claw1.mirror = true;
      setRotation(claw1, 0F, -0.8088379F, -0.6676583F);
      Leg_part2 = new ModelRenderer(this, 12, 24);
      Leg_part2.addBox(0F, -1F, -1F, 6, 2, 2);
      Leg_part2.setRotationPoint(0F,0F,0F);
      Leg_part2.setTextureSize(64, 32);
      Leg_part2.mirror = true;
      setRotation(Leg_part2, 0F, 0.8437445F, 0.7200181F);
      claw2 = new ModelRenderer(this, 0, 0);
      claw2.addBox(-6.5F, 0F, -0.3F, 2, 1, 1);
      claw2.setRotationPoint(0F,0F,0F);
      claw2.setTextureSize(64, 32);
      claw2.mirror = true;
      setRotation(claw2, 0F, -2.379634F, -0.6676583F);
    LEFTCLAW = new ModelRenderer(this, "LEFTCLAW");
    LEFTCLAW.setRotationPoint(3F, 19F, 3F);
    setRotation(LEFTCLAW, 0F, 0F, 0F);
    LEFTCLAW.mirror = true;
    
    RIGHTCLAW = new ModelRenderer(this, "RIGHTCLAW");
    RIGHTCLAW.setRotationPoint(-2F, 19F, 3F);
    setRotation(RIGHTCLAW, 0F, 0F, 0F);
    RIGHTCLAW.mirror = true;
    
    
    LEFTCLAW.addChild(Leg_part2);
    LEFTCLAW.addChild(claw2);
    
    RIGHTCLAW.addChild(Leg_part1);
    RIGHTCLAW.addChild(claw1);
    
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Eye3.render(f5);
    Eye1.render(f5);
    Eye2.render(f5);
    Eye3.render(f5);
    Leg_part9.render(f5);
    Body.render(f5);
    Right_tooth2.render(f5);
    ButtLayer1.render(f5);
    ButtLayer2.render(f5);
    Stim1.render(f5);
    Left_tooth1.render(f5);
    Left_tooth2.render(f5);
    BaseMushroom1.render(f5);
    Right_tooth1.render(f5);
    BaseMushroom2.render(f5);
    Head.render(f5);
    Stim2.render(f5);
    Nose.render(f5);
    Leg_part3.render(f5);
    Leg_part10.render(f5);
    Leg_part5.render(f5);
    Leg_part6.render(f5);
    Leg_part7.render(f5);
    Leg_part8.render(f5);
    Leg_part4.render(f5);
    Leg_part1.render(f5);
    claw1.render(f5);
    Leg_part2.render(f5);
    claw2.render(f5);
    LEFTCLAW.render(f5);
    RIGHTCLAW.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {}

}
