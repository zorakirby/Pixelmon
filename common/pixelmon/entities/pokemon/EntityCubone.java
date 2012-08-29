package pixelmon.entities.pokemon;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;


public class EntityCubone extends EntityGroundPixelmon
{

	public EntityCubone(World par1World) 
	{
		super(par1World);
		init();
	}
	 protected int getDropItemId()
	    {
	        return Item.bone.shiftedIndex;
	    }
	public void init() 
	{
		name = "Cubone";
		isImmuneToFire = false;
		super.init();
	}
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID;
	}
	public void evolve() 
	{
	
	}
	
}