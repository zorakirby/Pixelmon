package pixelmon.structure.generation;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldStructure {
	protected AxisAlignedBB size;
	
	private World worldObj;
	
	public int posX;
	public int posY;
	public int posZ;
	
	public WorldStructure(AxisAlignedBB var1, int var2, int var3, int var4, World var5)
	{
		size = var1;
		posX = var2;
		posY = var3;
		posZ = var4;
		worldObj = var5;
	//	WorldStructureHelper.addStructure(this);
	}
	
	public WorldStructure(int[] loc){
		posX = loc[0];
		posY = loc[1];
		posZ = loc[2];
	//	WorldStructureHelper.addStructure(this);
	}
	
	public WorldStructure(){
	//	WorldStructureHelper.addStructure(this);
	}
	
	public WorldStructure(World var1){
		worldObj = var1;
	//	WorldStructureHelper.addStructure(this);
	}
	

	public  boolean generate(World world, Random rand, int xBase, int yBase, int zBase){
		return false;
	}
	
	public void update()
	{
	}
	

	
	public void writeToNBT(NBTTagCompound var1)
	{
		NBTTagCompound var2 = new NBTTagCompound();
		var2.setString("WorldStructureClass", this.getClass().getName());
		if(size != null){
			var2.setInteger("minX", (int)size.minX);
			var2.setInteger("minY", (int)size.minY);
			var2.setInteger("minZ", (int)size.minZ);
			var2.setInteger("maxX", (int)size.maxX);
			var2.setInteger("maxY", (int)size.maxY);
			var2.setInteger("maxZ", (int)size.maxZ);
		}
		var1.setCompoundTag("size", var2);
		var1.setInteger("posX", posX);
		var1.setInteger("posY", posY);
		var1.setInteger("posZ", posZ);
	}
	
	public void readFromNBT(NBTTagCompound var1)
	{
		NBTTagCompound var2 = var1.getCompoundTag("size");
		size = AxisAlignedBB.getBoundingBox(var2.getInteger("minX"), var2.getInteger("minY"), var2.getInteger("minZ"), var2.getInteger("maxX"), var2.getInteger("maxY"), var2.getInteger("maxZ"));
		posX = var1.getInteger("posX");
		posY = var1.getInteger("posY");
		posZ = var1.getInteger("posZ");
	}
	
	public AxisAlignedBB getSize()
	{
		return size;
	}
	
	public AxisAlignedBB getBoundingBox()
	{
		AxisAlignedBB position = AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + size.maxX, posY + size.maxY, posZ + size.maxZ);
		return position;
	}
}
