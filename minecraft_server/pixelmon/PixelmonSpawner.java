package pixelmon;

import pixelmon.entities.*;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;
import net.minecraft.src.forge.EnumHelper;

public class PixelmonSpawner {

	public void init() {
		PixelmonEntityList.addSpawns();

		try {
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.monster, "e", 0);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.creature, "e", mod_Pixelmon.numGroundPokemon);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.waterCreature, "e", mod_Pixelmon.numWaterPokemon);
		} catch (Throwable e) {
			System.err.println(e);
			try{
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.monster, "maxNumberOfCreature", 0);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.creature, "maxNumberOfCreature", mod_Pixelmon.numGroundPokemon);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.waterCreature, "maxNumberOfCreature", mod_Pixelmon.numWaterPokemon);
			}catch(Throwable f){
				System.err.println(f);
			}
		}
	}
}
