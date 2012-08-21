package pixelmon.structure.exporter;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import pixelmon.ClientProxy;
import pixelmon.database.DatabaseHelper;
import pixelmon.structure.Generation;
import pixelmon.structure.Structure;
import pixelmon.structure.StructurePiece;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.ServerCommandManager;
import net.minecraft.src.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * 
 * DO NOT SHIP WITH MOD!
 * 
 * @author Gerald -xkyouchoux-
 *
 */

@Mod(modid = "GenExporter", name = "Generation Exporter", version = "1.3.1")
public class GenerationExporter 
{
	@Instance
	public static GenerationExporter insatnce;
	
	public Structure currentStructure;

	@ServerStarting
	public void onServerStart(FMLServerStartingEvent var1)
	{		
		MinecraftServer var2 = MinecraftServer.getServer();
		ServerCommandManager var3 = (ServerCommandManager)var2.getCommandManager();
		if(var3 != null && !ObfuscationReflectionHelper.obfuscation)
		{
			var3.registerCommand(new CommandGen());
		}
	}
	
	public String handleGenCommand(EntityPlayer var1, MovingObjectPosition var2, String[] var3) throws Exception
	{
		String var4 = null;
		if(var3.length == 2 && var3[0].equals("rec") && this.isStringNumber(var3[1]))
		{
			var4 = getStructure(var1).start(Integer.parseInt(var3[1]));
		}
		else if(var3.length == 2 && var3[0].equals("rec") && var3[1].equals("reset"))
		{
			currentStructure = new Structure(var1.worldObj);
			var4 = "Reset!";
		}
		else if(var3.length == 1 && var3[0].equals("rec"))
		{
			if(var2 == null)
			{
				var4 = "Not looking at a block!";
				stopCommand();
			}
			if(getStructure(var1).recMode == 0)
			{
				getStructure(var1).start(1);
			}
			var4 = getStructure(var1).rec(var2.blockX, var2.blockY, var2.blockZ);
		}
		else if(var3.length == 2 && var3[0].equals("export"))
		{
			saveStructure(var3[1]);
			currentStructure = null;
			var4 = "Exported " + var3[1] + " as pixelmon/structure/" + var3[1] + "/";
		}
		if(var3.length == 2 && var3[0].equals("spawn"))
		{
			Structure var5 = loadStructure(var3[1], var1.worldObj);
			var5.generate(var2.blockX, var2.blockY, var2.blockZ);
			var4 = "Spawned " + var3[1];
		}
		else if(var3.length == 2 && var3[0].equals("rec") && var3[1].equals("entity"))
		{
			if(var2.entityHit != null)
			{
				getStructure(var1).addEntity(var2.entityHit);
				var4 = "Added " + EntityList.getStringFromID(EntityList.getEntityID(var2.entityHit));
			}
			else
			{
				var4 = "No Entity to add!";
			}
		}
		return var4;
	}
	
	public Structure getStructure(EntityPlayer var1)
	{
		if(currentStructure == null)
		{
			currentStructure = new Structure(var1.worldObj);
		}
		return currentStructure;
	}
	
	public boolean isStringNumber(String var1)
	{
		try
		{
			int i = Integer.parseInt(var1);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public void stopCommand()
	{
		throw new IllegalStateException();
	}
	
	public String getCommandList()
	{
		return "rec <mode> - Sets the user rec mode\n rec - plots a rec position\n rec reset - resets the users rec list\n rec entity - adds the entity the user is looking at into the structure\n export <name> - creates a folder that the importer can read";
	}
	
	public Structure loadStructure(String var1, World var2)
	{
		Structure var3 = new Structure(var2);
		try
		{
			File var4 = new File(ClientProxy.getMinecraftDir(), "pixelmon");
			var4.mkdir();
			File var5 = new File(var4, "structure");
			var5.mkdir();
			File var6 = new File(var5, var1 + ".gen");
			var6.createNewFile();
			var3 = Generation.instance.importStructure(new FileInputStream(var6), var2);
		}
		catch(Exception e)
		{
			
		}
		return var3;
	}
	
	public void saveStructure(String var1)
	{
		try
		{
			File var4 = new File(ClientProxy.getMinecraftDir(), "pixelmon");
			var4.mkdir();
			File var5 = new File(var4, "structure");
			var5.mkdir();
			File var6 = new File(var5, var1 + ".gen");
			var6.createNewFile();
			exportStructure(new FileOutputStream(var6));
		}
		catch(Exception e)
		{
		}
	}
	
	public void exportStructure(OutputStream var1)
	{
		try
		{
			NBTTagCompound var2 = new NBTTagCompound("Structure");
			currentStructure.writeToNBT(var2);
			CompressedStreamTools.writeCompressed(var2, var1);
		}
		catch(Exception e)
		{
			
		}
	}
}
