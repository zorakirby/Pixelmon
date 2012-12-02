package pixelmon.structure;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;



import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPainting;
import net.minecraft.src.EnumArt;
import net.minecraft.src.Item;
import net.minecraft.src.ItemDoor;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagByte;
import net.minecraft.src.NBTTagByteArray;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagInt;
import net.minecraft.src.NBTTagIntArray;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagShort;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * 
 * 
 * @author Gerald -xkyouchoux-
 *
 */

public class Structure 
{
	public int recMode;
	public int recsLeft;
	public World world;
	
	public ArrayList<StructurePiece> cachedRecs;
	public ArrayList<Entity> cachedEntitys;
	
	public int[] recXYZ = new int[7];
	
	public int startingX;
	public int startingY;
	public int startingZ;
	
	public boolean startingPositionSet;
	
	public Structure(World var1)
	{
		world = var1;
		cachedRecs = new ArrayList<StructurePiece>();
		cachedEntitys = new ArrayList<Entity>();
	}
	
	public String start(int var1)
	{
		if(recMode != 0)
		{
			return String.format("Rec currently set at %s, reset or finish the rec!", recMode);
		}
		if(var1 != 1 && var1 != 3)
		{
			return String.format("%s is a invalid rec mode! Only 1-3", var1);
		}
		recMode = var1;
		recsLeft = var1;
		return String.format("Setting Rec Mode to %s!", var1);
	}
	
	public String rec(int var1, int var2, int var3)
	{
		String var4 = String.format("Testing [%s %s %s]", var1, var2, var3);
		if(recMode == 0)
		{
			var4 = "You must start another rec!";
		}
		if(recMode == 1)
		{
			recXYZ[0] = var1;
			recXYZ[1] = var2;
			recXYZ[2] = var3;
			if(!startingPositionSet)
			{
				startingPositionSet = true;
				startingX = var1;
				startingY = var2;
				startingZ = var3;
			}
			sendRec();
		}
		if(recMode == 2)
		{
			if(recsLeft == 2)
			{
				recXYZ[0] = var1;
				recXYZ[1] = var2;
				recXYZ[2] = var3;
				if(!startingPositionSet)
				{
					startingPositionSet = true;
					startingX = var1;
					startingY = var2;
					startingZ = var3;
				}
				recsLeft = 1;
			}
			else if(recsLeft == 1)
			{
				recXYZ[3] = var1;
				recXYZ[4] = var2;
				recXYZ[5] = var3;
				sendRec();
			}
		}
		if(recMode == 3)
		{
			if(recsLeft == 3)
			{
				recXYZ[0] = var1;
				recXYZ[1] = var2;
				recXYZ[2] = var3;
				if(!startingPositionSet)
				{
					startingPositionSet = true;
					startingX = var1;
					startingY = var2;
					startingZ = var3;
				}
				recsLeft = 2;
			}
			else if(recsLeft == 2)
			{
				recXYZ[3] = var1;
				recXYZ[4] = var2;
				recXYZ[5] = var3;
				recsLeft = 1;
			}
			else if(recsLeft == 1)
			{
				recXYZ[6] = var2;
				sendRec();
			}
		}
		return var4;
	}
	
	public void sendRec()
	{
		if(recMode == 1)
		{
			int var1 = recXYZ[0];
			int var2 = recXYZ[1];
			int var3 = recXYZ[2];
			create(var1, var1, var2, var2, var3, var3);
		}
		if(recMode == 2)
		{
			int var1 = recXYZ[0];
			int var2 = recXYZ[1];
			int var3 = recXYZ[2];
			int var4 = recXYZ[3];
			int var5 = recXYZ[4];
			int var6 = recXYZ[5];
			int var7 = var1 > var4 ? var4 : var1;
			int var8 = var1 > var4 ? var1 : var4;
			int var9 = var2 > var5 ? var5 : var2;
			int var10 = var2 > var5 ? var2 : var5;
			int var11 = var3 > var6 ? var6 : var5;
			int var12 = var3 > var6 ? var3 : var6;
			create(var7, var8, var9, var10, var11, var12);
		}
		if(recMode == 3)
		{
			int var1 = recXYZ[0];
			int var2 = recXYZ[1];
			int var3 = recXYZ[2];
			int var4 = recXYZ[3];
			int var5 = recXYZ[4];
			int var6 = recXYZ[5];
			int var7 = recXYZ[6];
			int var8 = var1 > var4 ? var4 : var1;
			int var9 = var1 > var4 ? var1 : var4;
			int var10 = minY(var2, var5, var7);
			int var11 = maxY(var2, var5, var7);
			int var12 = var3 > var6 ? var6 : var3;
			int var13 = var3 > var6 ? var3 : var6;
			create(var8, var9, var10, var11, var12, var13);
		}
		reset();
	}
	
	public int minY(int var1, int var2, int var3)
	{
		int var4 = var1 > var2 ? var2 : var1;
		int var5 = var4 > var3 ? var3 : var4;
		return var5;
	}
	
	public int maxY(int var1, int var2, int var3)
	{
		int var4 = var1 > var2 ? var1 : var2;
		int var5 = var4 > var3 ? var4 : var3;
		return var5;
	}
	
	/**
	 * 
	 * @param var1 minX
	 * @param var2 maxX
	 * @param var3 minY
	 * @param var4 maxY
	 * @param var5 minZ
	 * @param var6 maxZ
	 */
	public void create(int var1, int var2, int var3, int var4, int var5, int var6)
	{
		int var7 = var2 + 1 - var1;
		int var8 = var4 + 1 - var3;
		int var9 = var6 + 1 - var5;
		for(int var10 = 0; var10 < var7; var10++)
		{
			for(int var11 = 0; var11 < var8; var11++)
			{
				for(int var12 = 0; var12 < var9; var12++)
				{
					int var13 = var1 + var10;
					int var14 = var3 + var11;
					int var15 = var5 + var12;
					int var16 = world.getBlockId(var13, var14, var15);
					int var17 = world.getBlockMetadata(var13, var14, var15);
					TileEntity var18 = world.getBlockTileEntity(var13, var14, var15);
					StructurePiece var19 = new StructurePiece(var13, var14, var15, var16, var17, startingX, startingY, startingZ);
					if(var18 != null)
					{
						NBTTagCompound var20 = new NBTTagCompound();
						var18.writeToNBT(var20);
						var19.setTile(var20);
					}
					this.cachedRecs.add(var19);
				}
			}
		}
	}
	
	public void reset()
	{
		recMode = 0;
		recsLeft = 0;
		recXYZ = new int[7];
	}
	
	public void addEntity(Entity var1)
	{
		var1.posX -= this.startingX;
		var1.posY -= this.startingY;
		var1.posZ -= this.startingZ;
		this.cachedEntitys.add(var1);
	}
	
	public void writeToNBT(NBTTagCompound var1)
	{
		int var2 = 0;
		NBTTagCompound blocks = new NBTTagCompound("");
		NBTTagCompound entitys = new NBTTagCompound("");
		for(StructurePiece var3 : cachedRecs)
		{
			NBTTagCompound var4 = new NBTTagCompound();
			var3.writeToNBT(var4);
			blocks.setCompoundTag("Rec" + var2++, var4);
		}
		for(Entity var3 : cachedEntitys)
		{
			NBTTagCompound var4 = new NBTTagCompound();
			if(var3 instanceof EntityPainting)
			{
				var4.setString("NAME", "Painting");
				var4.setInteger("X", ((EntityPainting) var3).xPosition - startingX);
				var4.setInteger("Y", ((EntityPainting) var3).yPosition - startingY);
				var4.setInteger("Z", ((EntityPainting) var3).zPosition - startingZ);
				//var4.setByte("DIRECTION", (byte)((EntityPainting) var3).direction);
				var4.setByte("IMAGE", (byte)((EntityPainting) var3).art.ordinal());
			}
			entitys.setCompoundTag("ENTITY" + var2++, var4);
		}
		var1.setCompoundTag("Blocks", blocks);
		var1.setCompoundTag("Entitys", entitys);
	}
	
	public void readFromNBT(World var1, NBTTagCompound var2, NBTTagCompound var3)
	{
		for(Object var4 : var2.getTags())
		{
			NBTTagCompound var5 = (NBTTagCompound)var4;
			StructurePiece var6 = StructurePiece.createFromNBT(var5);
			this.cachedRecs.add(var6);
		}
		for(Object var4 : var3.getTags())
		{
			NBTTagCompound var5 = (NBTTagCompound)var4;
			Entity var6 = null;
			if(var5.getString("NAME").equals("Painting"))
			{
				var6 = new EntityPainting(var1, var5.getInteger("X"), var5.getInteger("Y"), var5.getInteger("Z"), var5.getByte("DIRECTION"));
				((EntityPainting)var6).art = EnumArt.values()[var5.getByte("IMAGE")];
			}
			this.cachedEntitys.add(var6);
		}
		
	}
	
	public AxisAlignedBB generate(int var1, int var2, int var3)
	{
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		int minZ = 0;
		int maxZ = 0;
		for(StructurePiece var4 : cachedRecs)
		{
			if(Generation.blockToWaitOn.contains(String.format("'%s'", var4.blockID)))
			{
				continue;
			}
			var4.generate(world, var1, var2, var3);	
			if(minX > var4.blockX)
			{
				minX = var4.blockX;
			}
			if(minY > var4.blockY)
			{
				minY = var4.blockY;
			}
			if(minZ > var4.blockZ)
			{
				minZ = var4.blockZ;
			}
			if(maxX < var4.blockX)
			{
				maxX = var4.blockX;
			}
			if(maxY < var4.blockY)
			{
				maxY = var4.blockY;
			}
			if(maxZ < var4.blockZ)
			{
				maxZ = var4.blockZ;
			}
		}
		for(StructurePiece var4 : cachedRecs)
		{
			if(!Generation.blockToWaitOn.contains(String.format("'%s'", var4.blockID)) || Block.blocksList[var4.blockID] == Block.doorWood || Block.blocksList[var4.blockID] == Block.doorSteel)
			{
				continue;
			}
			var4.generate(world, var1, var2, var3);
			if(minX > var4.blockX)
			{
				minX = var4.blockX;
			}
			if(minY > var4.blockY)
			{
				minY = var4.blockY;
			}
			if(minZ > var4.blockZ)
			{
				minZ = var4.blockZ;
			}
			if(maxX < var4.blockX)
			{
				maxX = var4.blockX;
			}
			if(maxY < var4.blockY)
			{
				maxY = var4.blockY;
			}
			if(maxZ < var4.blockZ)
			{
				maxZ = var4.blockZ;
			}
		}
		for(StructurePiece var4 : cachedRecs)
		{
			if(Block.blocksList[var4.blockID] != Block.doorWood && Block.blocksList[var4.blockID] != Block.doorSteel)
			{
				continue;
			}
			if(var4.blockMeta >= 8)
			{
				continue;
			}
			if(minX > var4.blockX)
			{
				minX = var4.blockX;
			}
			if(minY > var4.blockY)
			{
				minY = var4.blockY;
			}
			if(minZ > var4.blockZ)
			{
				minZ = var4.blockZ;
			}
			if(maxX < var4.blockX)
			{
				maxX = var4.blockX;
			}
			if(maxY < var4.blockY)
			{
				maxY = var4.blockY;
			}
			if(maxZ < var4.blockZ)
			{
				maxZ = var4.blockZ;
			}
			ItemDoor.placeDoorBlock(world, var1 + var4.blockX, var2 + var4.blockY, var3 + var4.blockZ, var4.blockMeta, Block.blocksList[var4.blockID]);
		}
		for(Entity var4 : cachedEntitys)
		{
			world.spawnEntityInWorld(var4);
		}
		return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	public StructurePiece getPiece(int var1, int var2, int var3)
	{
		for(StructurePiece var4 : cachedRecs)
		{
			if(var4.blockX == var1 && var4.blockY == var2 && var4.blockZ == var3)
			{
				return var4;
			}
		}
		return null;
	}
}
