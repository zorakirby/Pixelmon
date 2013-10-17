package pixelmon.util;

import java.lang.reflect.Field;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import pixelmon.worldGeneration.GenLayerAddRareBiome;

public class InfiltratorGenLayer {
	private static Field parentField;
	private static Field[] riverMixParents;
	public final static InfiltratorGenLayer INSTANCE = new InfiltratorGenLayer();

	@ForgeSubscribe
	public void onInitBiomeGensEvent(WorldTypeEvent.InitBiomeGens event){
		if(event.worldType != WorldType.DEFAULT)
			return;
		GenLayer[] biomeGens = event.newBiomeGens;
		insertBelow(GenLayerBiome.class, biomeGens[0], event.worldType);
		System.out.println("GenLayers Swapped");
	}
	
	/**
	 * Rips open the hierarchy of a <code>GenLayer</code> object by forcing a few protected fields into a public state, then crams in a new <code>GenLayerAddRareBiome</code> under the first instance of an <code>aboveClass</code> GenLayer.<br>
	 * If that's too evil-sounding, then try this:<br>
	 * Makes a carefully-placed incision under the <code>aboveClass</code> artery, installs a new <code>GenLayerRareBiome</code>, and patches everything up with happy-stitches. You then receive <code>Random.nextInt(100)</code> gifts and/or "Get Well Soon!" letters. (Unfortunately the letters only last a few months, and eventually you are transferred to a school for disabled students, but eventually, that turns out to be a good thing.) 
	 */
	public static void insertBelow(Class<? extends GenLayer> aboveClass, GenLayer object, WorldType type){
		try{
			while(!(object == null || getParent(object) == null || getParent(object).getClass().equals(aboveClass))){
				object = getParent(object);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("below = " + object.getClass().getSimpleName());
		GenLayerAddRareBiome rareLayer = null;
		GenLayer top = null;
		try{
			top = getParent(object);
			System.out.println(top);
			System.out.println("above = " + top.getClass().getSimpleName());
			rareLayer = new GenLayerAddRareBiome(200L, top, type);
			parentField.set(object, rareLayer);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void initParentFields(){
		for(Field f : GenLayer.class.getDeclaredFields()){
			if(f.getType().equals(GenLayer.class)){
				f.setAccessible(true);
				parentField = f;
				break;
			}
		}
		riverMixParents = new Field[2];
		int i = 0;
		for(Field f : GenLayerRiverMix.class.getDeclaredFields()){
			if(f.getType().equals(GenLayer.class)){
				f.setAccessible(true);
				riverMixParents[i] = f;
				i++;
			}
		}
	}
	
	public static GenLayer getParent(GenLayer genLayer){
		if(parentField == null){
			initParentFields();
		}
		GenLayer result = null;
		try{
			if(genLayer.getClass().equals(GenLayerRiverMix.class)){
				if(!(getParent((GenLayer) riverMixParents[0].get(genLayer)) instanceof GenLayerRiver)){
					return (GenLayer) riverMixParents[0].get(genLayer);
				}
				else
					return (GenLayer) riverMixParents[1].get(genLayer);
			}
			else
				return (GenLayer) parentField.get(genLayer);
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
		
}
