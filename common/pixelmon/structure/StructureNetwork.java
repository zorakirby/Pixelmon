package pixelmon.structure;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import pixelmon.ClientProxy;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;

public class StructureNetwork {
	public ArrayList<WorldStructure> worldStructures;
	
	public StructureNetwork()
	{
		worldStructures = new ArrayList<WorldStructure>();
	}
	
	public void addStructure(WorldStructure var1)
	{
		worldStructures.add(var1);
	}
	
	@ForgeSubscribe
	public void loadNetwork(Load var1)
	{
		
	}
	
	@ForgeSubscribe
	public void saveNetwork(Save var1)
	{
		NBTTagCompound var2 = new NBTTagCompound("Structures");
		var2.setInteger("Size", worldStructures.size());
		for(int var3 = 0; var3 < worldStructures.size(); var3++)
		{
			NBTTagCompound var4 = new NBTTagCompound();
			worldStructures.get(var3).writeToNBT(var4);
			var2.setCompoundTag("Structure" + var3, var4);
		}
		try
		{
			CompressedStreamTools.writeCompressed(var2, new FileOutputStream(new File(var1.world.getSaveHandler().getSaveDirectoryName() + "data/structures.dat")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static File getWorkingDir()
	{
		if(MinecraftServer.getServer() != null && !MinecraftServer.getServer().isSinglePlayer())
		{
			return MinecraftServer.getServer().getFile("");
		}
		else
		{
			return ClientProxy.getMinecraftDir();
		}
	}
}
