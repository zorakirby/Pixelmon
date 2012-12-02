package pixelmon.structure;

import java.io.IOException;
import java.io.InputStream;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

/**
 * 
 * @author Gerald -xkyouchoux-
 *
 */

public class Generation
{
	public static final String blockToWaitOn = "'12''13''26''34''36''37''38''39''40''50''51''55''59''63''64''65''66''68''69''70''71''72''75''76''77''78''81''83''92''93''94''96''104''105''106''111''115''117''127''132'";
	
	public static final Generation instance = new Generation();
	
	public static AxisAlignedBB generate(String var1, World var2, int var3, int var4, int var5)
	{
		Structure var6 = importStructure(var1, var2);
		return var6.generate(var3, var4, var5);
	}
	
	public static Structure importStructure(InputStream var1, World var2)
	{
		Structure var3 = new Structure(var2);
		try
		{
			if(var1 == null)
			{
				throw new IllegalArgumentException("File does not exist!");
			}
			NBTTagCompound var4 = CompressedStreamTools.readCompressed(var1);
			NBTTagCompound var5 = var4.getCompoundTag("Blocks");
			NBTTagCompound var6 = var4.getCompoundTag("Entitys");
			var3.readFromNBT(var2, var5, var6);
		}
		catch(Exception e)
		{
		}
		return var3;
	}
	
	public static Structure importStructure(String var1, World var2)
	{
		return importStructure(Generation.class.getResourceAsStream("/pixelmon/structure/" + var1 + ".gen"), var2);
	}
}
