package pixelmon.worldGeneration;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

public class MapGenDenialWrapper<T extends MapGenBase> extends MapGenBase{
	
	public T child;
	public EventType genType;
	public static HashMap<EventType, Collection<BiomeGenBase>> denialMap = new HashMap();
	public static final MapGenDenialWrapper SUBSCRIBER = new MapGenDenialWrapper();
	private MapGenDenialWrapper(){
		this.child = null;
		this.genType = null;
	}
	public MapGenDenialWrapper(T child, EventType genType){
		this.child = child;
		this.genType = genType;
	}
	
	public static void registerBiomeForDenial(BiomeGenBase biome, EventType... types){
		for(EventType type : types){
			if(!denialMap.containsKey(type))
				denialMap.put(type, new HashSet());
			denialMap.get(type).add(biome);
		}
	}
	
	
	public void generate(IChunkProvider provider, World world, int chunkX, int chunkZ, byte[] byteArray){
		BiomeGenBase biome = getBiomeAt(world, chunkX, chunkZ);
		if(denialMap.containsKey(genType) && denialMap.get(genType).contains(biome)){
			return;
		}
		else{
			child.generate(provider, world, chunkX, chunkZ, byteArray);
		}
	}
	
	public static BiomeGenBase getBiomeAt(World world, int chunkX, int chunkZ){
        return world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
	}
	
	@ForgeSubscribe
	public void onInitMapGenEvent(InitMapGenEvent event){
		event.newGen = new MapGenDenialWrapper(event.newGen, event.type);
	}

}
