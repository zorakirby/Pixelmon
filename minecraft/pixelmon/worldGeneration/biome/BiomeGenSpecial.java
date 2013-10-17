package pixelmon.worldGeneration.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixelmon.util.AbstractList2D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BiomeGenSpecial extends BiomeGenBase{

	public BiomeGenSpecial(int id) {
		super(id);
	}
	
	public abstract AbstractList2D<Integer> getShape();
	
	@SideOnly(Side.CLIENT)
	public float getTemperatureFactor(){
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		return player.worldObj.getWorldChunkManager().getTemperatures(null, (int)player.posX, (int)player.posZ, 1, 1)[0];
	}

}
