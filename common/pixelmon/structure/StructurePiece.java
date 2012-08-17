package pixelmon.structure;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * 
 * @author Gerald -xkyouchoux-
 *
 */

@SideOnly(value = Side.SERVER)
public final class StructurePiece
{
	public int blockX;
	public int blockY;
	public int blockZ;
	public int blockID;
	public int blockMeta;
	public int removalX;
	public int removalY;
	public int removalZ;
	public NBTTagCompound tileData;
	
	public StructurePiece(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8)
	{
		blockX = var1;
		blockY = var2;
		blockZ = var3;
		blockID = var4;
		blockMeta = var5;
		removalX = var6;
		removalY = var7;
		removalZ = var8;
	}
	
	public StructurePiece()
	{
	}
	
	public void setTile(NBTTagCompound var1)
	{
		this.tileData = var1;
	}
	
	public static StructurePiece createFromNBT(NBTTagCompound var1)
	{
		StructurePiece var2 = new StructurePiece();
		var2.readFromNBT(var1);
		return var2;
	}
	
	public void readFromNBT(NBTTagCompound var1)
	{
		blockX = var1.getInteger("X");
		blockY = var1.getInteger("Y");
		blockZ = var1.getInteger("Z");
		blockID = var1.getInteger("ID");
		blockMeta = var1.getInteger("META");
		if(var1.hasKey("TILE"))
		{
			tileData = var1.getCompoundTag("TILE");
		}
	}
	
	public void writeToNBT(NBTTagCompound var1)
	{
		var1.setInteger("X", blockX - removalX);
		var1.setInteger("Y", blockY - removalY);
		var1.setInteger("Z", blockZ - removalZ);
		var1.setInteger("ID", blockID);
		var1.setInteger("META", blockMeta);
		if(tileData != null)
		{
			var1.setCompoundTag("TILE", tileData);
		}
	}
	
	public void generate(World var1, int var2, int var3, int var4)
	{
		var1.setBlockAndMetadataWithNotify(var2 + blockX, var3 + blockY, var4 + blockZ, blockID, blockMeta);
		if(tileData != null)
		{
			TileEntity var6 = TileEntity.createAndLoadEntity(tileData);
			var1.setBlockTileEntity(var2 + blockX, var3 + blockY, var4 + blockZ, var6);
		}
	}
}
