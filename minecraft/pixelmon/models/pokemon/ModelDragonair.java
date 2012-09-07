package pixelmon.models.pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDragonair extends ModelBase
{
  //fields
    ModelRenderer tail7slope;
    ModelRenderer tail6slope;
    ModelRenderer tail6_2;
    ModelRenderer tail6slope_2;
    ModelRenderer tail3;
    ModelRenderer tail2;
    ModelRenderer tail1;
    ModelRenderer neckrattle;
    ModelRenderer ear1;
    ModelRenderer noseslope;
    ModelRenderer ear2;
    ModelRenderer horn;
    ModelRenderer HEADPIECE;
    ModelRenderer TAIL1PIECE;
    ModelRenderer TAIL2PIECE;
    ModelRenderer TAIL3PIECE;
    ModelRenderer TAIL4PIECE;
    ModelRenderer TAIL5PIECE;
    ModelRenderer TAIL6PIECE;
    ModelRenderer TAIL7PIECE;
    ModelRenderer TAIL8PIECE;
    ModelRenderer tail8;
    ModelRenderer tailrattle1;
    ModelRenderer tailrattle2;
    ModelRenderer tailrattle3;
    ModelRenderer tailrattle4;
    ModelRenderer tailrattle5;
    ModelRenderer tailrattle6;
    ModelRenderer tailrattle7;
    ModelRenderer tailrattle8;
    ModelRenderer tailrattle9;
    ModelRenderer tailrattle10;
    ModelRenderer tailrattle11;
    ModelRenderer tail7;
    ModelRenderer tail6;
    ModelRenderer tail5;
    ModelRenderer tail4;
    ModelRenderer nose;
    ModelRenderer head1;
    ModelRenderer head2;
    
    
    
  
  public ModelDragonair()
  {
    textureWidth = 64;
    textureHeight = 64;
    setTextureOffset("HEADPIECE.DELETE_THIS", 0, 0);
    
      tail7slope = new ModelRenderer(this, 0, 0);
      tail7slope.addBox(0F, -0.9F, -0.1F, 1, 1, 5);
      tail7slope.setRotationPoint(0F,0F,0F);
      tail7slope.setTextureSize(64, 64);
      tail7slope.mirror = true;
      setRotation(tail7slope, -0.1858931F, 0F, 0F);
      tail6slope = new ModelRenderer(this, 0, 0);
      tail6slope.addBox(-1.5F, -1.9F, -0.4F, 3, 1, 6);
      tail6slope.setRotationPoint(0F,0F,0F);
      tail6slope.setTextureSize(64, 64);
      tail6slope.mirror = true;
      setRotation(tail6slope, -0.1487144F, 0F, 0F);
      tail6_2 = new ModelRenderer(this, 26, 25);
      tail6_2.addBox(-1F, 0F, 5F, 2, 2, 4);
      tail6_2.setRotationPoint(0F,0F,0F);
      tail6_2.setTextureSize(64, 64);
      tail6_2.mirror = true;
      setRotation(tail6_2, 0F, 0F, 0F);
      tail6slope_2 = new ModelRenderer(this, 0, 0);
      tail6slope_2.addBox(-1F, -2.8F, 5.6F, 2, 1, 4);
      tail6slope_2.setRotationPoint(0F,0F,0F);
      tail6slope_2.setTextureSize(64, 64);
      tail6slope_2.mirror = true;
      setRotation(tail6slope_2, -0.2974289F, 0F, 0F);
      tail3 = new ModelRenderer(this, 29, 50);
      tail3.addBox(-2F, -2.2F, -0.3F, 4, 4, 8);
      tail3.setRotationPoint(0F,0F,0F);
      tail3.setTextureSize(64, 64);
      tail3.mirror = true;
      setRotation(tail3, -0.2974289F, 0F, 0F);
      tail2 = new ModelRenderer(this, 26, 47);
      tail2.addBox(-2F, -1.7F, -0.3F, 4, 4, 11);
      tail2.setRotationPoint(0F,0F,0F);
      tail2.setTextureSize(64, 64);
      tail2.mirror = true;
      setRotation(tail2, -0.4461433F, 0F, 0F);
      tail1 = new ModelRenderer(this, 27, 48);
      tail1.addBox(-2F, -3.5F, -1F, 4, 4, 10);
      tail1.setRotationPoint(0F,0F,0F);
      tail1.setTextureSize(64, 64);
      tail1.mirror = true;
      setRotation(tail1, -0.1963495F, 0F, 0F);
      neckrattle = new ModelRenderer(this, 0, 50);
      neckrattle.addBox(-1F, -0.5F, 1.5F, 2, 2, 2);
      neckrattle.setRotationPoint(0F,0F,0F);
      neckrattle.setTextureSize(64, 64);
      neckrattle.mirror = true;
      setRotation(neckrattle, -0.2602503F, 0F, 0F);
      ear1 = new ModelRenderer(this, 42, 0);
      ear1.addBox(2F, -9F, -1F, 1, 5, 3);
      ear1.setRotationPoint(0F,0F,0F);
      ear1.setTextureSize(64, 64);
      ear1.mirror = true;
      setRotation(ear1, -0.1963495F, 0.5890486F, 0F);
      noseslope = new ModelRenderer(this, 0, 0);
      noseslope.addBox(0F, -3F, -7.48F, 4, 1, 3);
      noseslope.setRotationPoint(0F,0F,0F);
      noseslope.setTextureSize(64, 64);
      noseslope.mirror = true;
      setRotation(noseslope, 0.2530727F, 0F, 0F);
      ear2 = new ModelRenderer(this, 32, 0);
      ear2.addBox(-3F, -9F, -1F, 1, 5, 3);
      ear2.setRotationPoint(0F,0F,0F);
      ear2.setTextureSize(64, 64);
      ear2.mirror = true;
      setRotation(ear2, -0.1963495F, -0.5890486F, 0F);
      horn = new ModelRenderer(this, 0, 35);
      horn.addBox(-0.5F, -3F, -9F, 1, 1, 5);
      horn.setRotationPoint(0F,0F,0F);
      horn.setTextureSize(64, 64);
      horn.mirror = true;
      setRotation(horn, -0.5414135F, 0F, 0F);
    HEADPIECE = new ModelRenderer(this, "HEADPIECE");
    HEADPIECE.setRotationPoint(0F, 15F, -34F);
    setRotation(HEADPIECE, 0F, 0F, 0F);
    HEADPIECE.mirror = true;
      head2 = new ModelRenderer(this, 0, 0);
      head2.addBox(0F, 0F, 0F, 4, 1, 4);
      head2.setRotationPoint(-2F, -5.5F, -4F);
      head2.setTextureSize(64, 64);
      head2.mirror = true;
      setRotation(head2, 0F, 0F, 0F);
      head1 = new ModelRenderer(this, 0, 18);
      head1.addBox(0F, 0F, 0F, 6, 6, 6);
      head1.setRotationPoint(-3F, -5F, -5F);
      head1.setTextureSize(64, 64);
      head1.mirror = true;
      setRotation(head1, 0F, 0F, 0F);
      nose = new ModelRenderer(this, 0, 0);
      nose.addBox(0F, 0F, 0F, 4, 2, 4);
      nose.setRotationPoint(-2F, -1F, -7F);
      nose.setTextureSize(64, 64);
      nose.mirror = true;
      setRotation(nose, 0F, 0F, 0F);
    TAIL1PIECE = new ModelRenderer(this, "TAIL1PIECE");
    TAIL1PIECE.setRotationPoint(0F, 0F, 1F);
    setRotation(TAIL1PIECE, 0F, 0F, 0F);
    TAIL1PIECE.mirror = true;
    TAIL2PIECE = new ModelRenderer(this, "TAIL2PIECE");
    TAIL2PIECE.setRotationPoint(0F, 0F, 9F);
    setRotation(TAIL2PIECE, 0F, 0F, 0F);
    TAIL2PIECE.mirror = true;
    TAIL3PIECE = new ModelRenderer(this, "TAIl3PIECE");
    TAIL3PIECE.setRotationPoint(0F, 5F, 9.5F);
    setRotation(TAIL3PIECE, 0F, 0F, 0F);
    TAIL3PIECE.mirror = true;
    TAIL4PIECE = new ModelRenderer(this, "TAIL4PIECE");
    TAIL4PIECE.setRotationPoint(0F, 2F, 6.7F);
    setRotation(TAIL4PIECE, 0F, 0F, 0F);
    TAIL4PIECE.mirror = true;
      TAIL4PIECE.addBox("tail4", -2F, -2F, 0F, 4, 4, 8);
    TAIL5PIECE = new ModelRenderer(this, "TAIL5PIECE");
    TAIL5PIECE.setRotationPoint(0F, 0F, 8F);
    setRotation(TAIL5PIECE, 0F, 0F, 0F);
    TAIL5PIECE.mirror = true;
      TAIL5PIECE.addBox("tail5", -2F, -2F, 0F, 4, 4, 6);
    TAIL6PIECE = new ModelRenderer(this, "TAIL6PIECE");
    TAIL6PIECE.setRotationPoint(0F, 0F, 6F);
    setRotation(TAIL6PIECE, 0F, 0F, 0F);
    TAIL6PIECE.mirror = true;
      TAIL6PIECE.addBox("tail6", -1.5F, -1F, 0F, 3, 3, 6);
    TAIL7PIECE = new ModelRenderer(this, "TAIL7PIECE");
    TAIL7PIECE.setRotationPoint(-0.5F, 1F, 9F);
    setRotation(TAIL7PIECE, 0F, 0F, 0F);
    TAIL7PIECE.mirror = true;
      TAIL7PIECE.addBox("tail7", 0F, 0F, 0F, 1, 1, 5);
    TAIL8PIECE = new ModelRenderer(this, "TAIL8PIECE");
    TAIL8PIECE.setRotationPoint(0.5F, 0.5F, 5F);
    setRotation(TAIL8PIECE, 0F, 0F, 0F);
    TAIL8PIECE.mirror = true;
      tail8 = new ModelRenderer(this, 0, 0);
      tail8.addBox(0F, 0F, 0F, 1, 1, 1);
      tail8.setRotationPoint(-0.5F, -1F, 2F);
      tail8.setTextureSize(64, 64);
      tail8.mirror = true;
      setRotation(tail8, 0F, 0F, 0F);
      tailrattle1 = new ModelRenderer(this, 0, 50);
      tailrattle1.addBox(0F, 0F, 0F, 2, 2, 2);
      tailrattle1.setRotationPoint(-1F, -1.5F, 0F);
      tailrattle1.setTextureSize(64, 64);
      tailrattle1.mirror = true;
      setRotation(tailrattle1, 0F, 0F, 0F);
      tailrattle2 = new ModelRenderer(this, 4, 50);
      tailrattle2.addBox(-1.3F, -0.7F, 1.5F, 1, 1, 1);
      tailrattle2.setRotationPoint(0.5F, 0F, 0.7F);
      tailrattle2.setTextureSize(64, 64);
      tailrattle2.mirror = true;
      setRotation(tailrattle2, 0F, 0F, 0F);
      tailrattle3 = new ModelRenderer(this, 4, 50);
      tailrattle3.addBox(0F, -0.7F, 0F, 1, 1, 1);
      tailrattle3.setRotationPoint(-0.2F, 0F, 2.2F);
      tailrattle3.setTextureSize(64, 64);
      tailrattle3.mirror = true;
      setRotation(tailrattle3, 0F, 0F, 0F);
      tailrattle4 = new ModelRenderer(this, 6, 50);
      tailrattle4.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle4.setRotationPoint(-0.2F, -1.2F, 2.2F);
      tailrattle4.setTextureSize(64, 64);
      tailrattle4.mirror = true;
      setRotation(tailrattle4, 0F, 0F, 0F);
      tailrattle5 = new ModelRenderer(this, 2, 50);
      tailrattle5.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle5.setRotationPoint(-0.8F, -1.2F, 2.2F);
      tailrattle5.setTextureSize(64, 64);
      tailrattle5.mirror = true;
      setRotation(tailrattle5, 0F, 0F, 0F);
      tailrattle6 = new ModelRenderer(this, 4, 53);
      tailrattle6.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle6.setRotationPoint(-0.2F, -1.2F, 2.7F);
      tailrattle6.setTextureSize(64, 64);
      tailrattle6.mirror = true;
      setRotation(tailrattle6, 0F, 0F, 0F);
      tailrattle7 = new ModelRenderer(this, 4, 50);
      tailrattle7.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle7.setRotationPoint(-0.2F, -1.2F, 2.7F);
      tailrattle7.setTextureSize(64, 64);
      tailrattle7.mirror = true;
      setRotation(tailrattle7, 0F, 0F, 0F);
      tailrattle8 = new ModelRenderer(this, 4, 50);
      tailrattle8.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle8.setRotationPoint(-0.2F, -0.7F, 2.7F);
      tailrattle8.setTextureSize(64, 64);
      tailrattle8.mirror = true;
      setRotation(tailrattle8, 0F, 0F, 0F);
      tailrattle9 = new ModelRenderer(this, 4, 52);
      tailrattle9.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle9.setRotationPoint(-0.8F, -1.2F, 2.7F);
      tailrattle9.setTextureSize(64, 64);
      tailrattle9.mirror = true;
      setRotation(tailrattle9, 0F, 0F, 0F);
      tailrattle10 = new ModelRenderer(this, 2, 50);
      tailrattle10.addBox(0F, 0F, 0F, 1, 1, 1);
      tailrattle10.setRotationPoint(-0.8F, -0.7F, 2.7F);
      tailrattle10.setTextureSize(64, 64);
      tailrattle10.mirror = true;
      setRotation(tailrattle10, 0F, 0F, 0F);
      tailrattle11 = new ModelRenderer(this, 0, 0);
      tailrattle11.addBox(0F, 0F, 0F, 1, 1, 2);
      tailrattle11.setRotationPoint(-0.5F, -0.8F, 3F);
      tailrattle11.setTextureSize(64, 64);
      tailrattle11.mirror = true;
      setRotation(tailrattle11, 0F, 0F, 0F);
     
      
      
      
      HEADPIECE.addChild(head1);
      HEADPIECE.addChild(head2);
      HEADPIECE.addChild(nose);
      HEADPIECE.addChild(ear1);
      HEADPIECE.addChild(ear2);
      HEADPIECE.addChild(noseslope);
      HEADPIECE.addChild(horn);
      HEADPIECE.addChild(TAIL1PIECE);
      
      TAIL1PIECE.addChild(tail1);
      TAIL1PIECE.addChild(neckrattle);
      TAIL1PIECE.addChild(TAIL2PIECE);
      
      TAIL2PIECE.addChild(tail2);
      TAIL2PIECE.addChild(TAIL3PIECE);
      
      TAIL3PIECE.addChild(tail3);
      TAIL3PIECE.addChild(TAIL4PIECE);
     
      TAIL4PIECE.addChild(tail4);
      TAIL4PIECE.addChild(TAIL5PIECE);
      
      TAIL5PIECE.addChild(tail5);
      TAIL5PIECE.addChild(TAIL6PIECE);
      
      TAIL6PIECE.addChild(tail6);
      TAIL6PIECE.addChild(tail6_2);
      TAIL6PIECE.addChild(tail6slope_2);
      TAIL6PIECE.addChild(TAIL7PIECE);
      
      TAIL7PIECE.addChild(tail7);
      TAIL7PIECE.addChild(tail7slope);
      TAIL7PIECE.addChild(TAIL8PIECE);
      
      TAIL8PIECE.addChild(tailrattle1);
      TAIL8PIECE.addChild(tailrattle2);
      TAIL8PIECE.addChild(tailrattle3);
      TAIL8PIECE.addChild(tailrattle4);
      TAIL8PIECE.addChild(tailrattle5);
      TAIL8PIECE.addChild(tailrattle6);
      TAIL8PIECE.addChild(tailrattle7);
      TAIL8PIECE.addChild(tailrattle8);
      TAIL8PIECE.addChild(tailrattle9);
      TAIL8PIECE.addChild(tailrattle10);
      TAIL8PIECE.addChild(tailrattle11);
      TAIL8PIECE.addChild(tail8);
      
    																  
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);

    HEADPIECE.render(f5);
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
