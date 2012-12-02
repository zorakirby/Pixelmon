package pixelmon.models.fossils;

import pixelmon.blocks.TileEntityFossilMachine;
import net.minecraft.src.*;

public class ModelRootFossil extends ModelBase
{
  //fields
    ModelRenderer top_1;
    ModelRenderer top_l1;
    ModelRenderer top_r2;
    ModelRenderer top_r1;
    ModelRenderer top_l_2;
    ModelRenderer midle_l;
    ModelRenderer midle_r;
    ModelRenderer tail;
    ModelRenderer base;
    ModelRenderer base1;
    ModelRenderer base2;
    ModelRenderer base3;
    ModelRenderer base4;
    ModelRenderer base5;
    ModelRenderer tail1;
    ModelRenderer base6;
  
  public ModelRootFossil()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      top_1 = new ModelRenderer(this, 8, 7);
      top_1.addBox(-1F, 0F, -1F, 2, 2, 3);
      top_1.setRotationPoint(0F, -0.1F, 0F);
      top_1.setTextureSize(32, 32);
      top_1.mirror = true;
      setRotation(top_1, 0.3346075F, 0F, 0F);
      top_l1 = new ModelRenderer(this, 0, 7);
      top_l1.addBox(-3F, 0F, 1F, 3, 1, 1);
      top_l1.setRotationPoint(-1F, 0F, 2F);
      top_l1.setTextureSize(32, 32);
      top_l1.mirror = true;
      setRotation(top_l1, 0F, -2.259633F, -0.1115358F);
      top_r2 = new ModelRenderer(this, 0, 9);
      top_r2.addBox(-1.2F, 0F, -1F, 3, 1, 1);
      top_r2.setRotationPoint(1F, 0F, 0F);
      top_r2.setTextureSize(32, 32);
      top_r2.mirror = true;
      setRotation(top_r2, 0F, -1.160076F, 0F);
      top_r1 = new ModelRenderer(this, 0, 7);
      top_r1.addBox(-3F, 0F, -1F, 3, 1, 1);
      top_r1.setRotationPoint(1F, -0.4F, 2F);
      top_r1.setTextureSize(32, 32);
      top_r1.mirror = true;
      setRotation(top_r1, -0.0371786F, -1.235156F, -0.1487144F);
      top_l_2 = new ModelRenderer(this, 0, 9);
      top_l_2.addBox(-1.8F, 0F, 0F, 3, 2, 1);
      top_l_2.setRotationPoint(-1F, 0F, 0F);
      top_l_2.setTextureSize(32, 32);
      top_l_2.mirror = true;
      setRotation(top_l_2, 0.1289891F, 1.04854F, 0.2230717F);
      midle_l = new ModelRenderer(this, 8, 10);
      midle_l.addBox(-1.5F, 0F, 0F, 2, 1, 2);
      midle_l.setRotationPoint(0F, 0.2F, -1F);
      midle_l.setTextureSize(32, 32);
      midle_l.mirror = true;
      setRotation(midle_l, 0F, -0.2617994F, 0F);
      midle_r = new ModelRenderer(this, 8, 10);
      midle_r.addBox(-1F, 0F, 0F, 2, 1, 2);
      midle_r.setRotationPoint(0F, 0.2F, -1F);
      midle_r.setTextureSize(32, 32);
      setRotation(midle_r, 0F, 0.6335855F, 0F);
      midle_r.mirror = false;
      tail = new ModelRenderer(this, 10, 13);
      tail.addBox(0.2F, 0F, -3.5F, 1, 1, 2);
      tail.setRotationPoint(0F, 0.3F, -1F);
      tail.setTextureSize(32, 32);
      tail.mirror = true;
      setRotation(tail, 0.0743572F, 0.4089647F, 0F);
      base = new ModelRenderer(this, 0, 0);
      base.addBox(-3F, 0F, -2F, 3, 1, 4);
      base.setRotationPoint(-0.3F, 0.6F, -2F);
      base.setTextureSize(32, 32);
      base.mirror = true;
      setRotation(base, 0F, -0.0812766F, 0F);
      base1 = new ModelRenderer(this, 0, 0);
      base1.addBox(-1.3F, 0F, 0.5F, 4, 1, 3);
      base1.setRotationPoint(0F, 0.6F, 0F);
      base1.setTextureSize(32, 32);
      base1.mirror = true;
      setRotation(base1, 0F, 0.2094395F, 0F);
      base2 = new ModelRenderer(this, 0, 0);
      base2.addBox(0.5F, 0F, -2.8F, 3, 1, 4);
      base2.setRotationPoint(0F, 0.6F, 0F);
      base2.setTextureSize(32, 32);
      base2.mirror = true;
      setRotation(base2, 0F, -0.2359809F, 0F);
      base3 = new ModelRenderer(this, 0, 0);
      base3.addBox(-3F, 0F, -3.2F, 6, 1, 3);
      base3.setRotationPoint(0F, 0.6F, -1F);
      base3.setTextureSize(32, 32);
      base3.mirror = true;
      setRotation(base3, 0F, -0.6859454F, 0F);
      base4 = new ModelRenderer(this, 0, 0);
      base4.addBox(-2.2F, 0.4F, -2.3F, 5, 1, 4);
      base4.setRotationPoint(0F, 0.6F, 0F);
      base4.setTextureSize(32, 32);
      base4.mirror = true;
      setRotation(base4, 0F, 0.0538057F, 0F);
      base5 = new ModelRenderer(this, 0, 0);
      base5.addBox(-1F, 0.4F, -1.3F, 3, 1, 2);
      base5.setRotationPoint(0F, 1F, 0F);
      base5.setTextureSize(32, 32);
      base5.mirror = true;
      setRotation(base5, 0F, 0.186616F, 0F);
      tail1 = new ModelRenderer(this, 10, 13);
      tail1.addBox(-0.5F, 0F, -2F, 1, 1, 2);
      tail1.setRotationPoint(0F, 0.3F, -1F);
      tail1.setTextureSize(32, 32);
      tail1.mirror = true;
      setRotation(tail1, 0F, 0F, 0F);
      base6 = new ModelRenderer(this, 0, 0);
      base6.addBox(-3F, 0F, -2F, 5, 1, 5);
      base6.setRotationPoint(0F, 0.6F, 0F);
      base6.setTextureSize(32, 32);
      base6.mirror = true;
      setRotation(base6, 0F, -0.7504916F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    top_1.render(f5);
    top_l1.render(f5);
    top_r2.render(f5);
    top_r1.render(f5);
    top_l_2.render(f5);
    midle_l.render(f5);
    midle_r.render(f5);
    tail.render(f5);
    base.render(f5);
    base1.render(f5);
    base2.render(f5);
    base3.render(f5);
    base4.render(f5);
    base5.render(f5);
    tail1.render(f5);
    base6.render(f5);
  }
  
  public void renderModel(TileEntityFossilMachine tile, float f5){
	    top_1.render(f5);
	    top_l1.render(f5);
	    top_r2.render(f5);
	    top_r1.render(f5);
	    top_l_2.render(f5);
	    midle_l.render(f5);
	    midle_r.render(f5);
	    tail.render(f5);
	    base.render(f5);
	    base1.render(f5);
	    base2.render(f5);
	    base3.render(f5);
	    base4.render(f5);
	    base5.render(f5);
	    tail1.render(f5);
	    base6.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
}
