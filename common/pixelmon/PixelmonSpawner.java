package pixelmon;

import java.util.ArrayList;
import java.util.List;

import pixelmon.entities.pixelmon.helpers.IHaveHelper;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Block;
import net.minecraft.src.Chunk;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.World;
import net.minecraftforge.common.Configuration;

public class PixelmonSpawner {
	public static int maxLandPerChunk = 0;
	public static int maxCavePerChunk = 0;
	public static int maxDeepSeaPerChunk = 0;
	
	public static Block[] LandSpawningBlocks = new Block[]{};
	
	private static int tick;
	
	public static void load(Configuration config)
	{
		maxLandPerChunk = config.getOrCreateIntProperty("Land", "spawning", 30).getInt();
		maxCavePerChunk = config.getOrCreateIntProperty("Cave", "spawning", 10).getInt();
		maxDeepSeaPerChunk = config.getOrCreateIntProperty("DeepSea", "spawning", 10).getInt();
	}
	
	public static void tick()
	{
		if(tick % 20 == 0)
		{
			spawnChunk();
		}
		tick++;
	}
	
	private static void spawnChunk()
	{
		for(String var1 : MinecraftServer.getServer().getAllUsernames())
		{
			EntityPlayerMP var2 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(var1);
			World var3 = var2.worldObj;
			Chunk var4 = var3.getChunkFromBlockCoords((int)var2.posX / 16, (int)var2.posZ / 16);
			if(var4.isChunkLoaded)
			{
			}
		}
	}
}
