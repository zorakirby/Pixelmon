package pixelmon.sounds;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.enums.EnumPokemon;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.FMLFileResourcePack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Sounds {

	private static final String[] MusicList = { "A Simple Moment By the Sea", "Across the Desert", "Distant Shores", "Pixelmon Waltz", "Nightfall" };

	public static void installSounds() {
		AbstractResourcePack resourcePack = (AbstractResourcePack) FMLClientHandler.instance().getResourcePackFor("pixelmon");
		for (EnumPokemon p : EnumPokemon.values()) {
			installPokemonSounds(p, resourcePack);
		}

		installSound(Minecraft.getMinecraft().mcDataDir + "/resources/sound/pixelmon/", "anvilHits", "pixelmon/");
	}

	public static void installPokemonSounds(EnumPokemon p, AbstractResourcePack resourcePack) {
		boolean hasMaleFemale = false;
		BaseStats baseStats = Entity3HasStats.getBaseStats(p.name);
		if (resourcePack.resourceExists(new ResourceLocation("pixelmon:sound/pixelmon/" + p.name.toLowerCase() + "M" + ".ogg"))) {
			hasMaleFemale = true;
		}
		if (!hasMaleFemale) {
			addPokemonSound(p.name.toLowerCase(), resourcePack, baseStats);
			int i = 2;
			while (addPokemonSound(p.name.toLowerCase() + i++, resourcePack, baseStats)) {
			}
		} else {
			baseStats.hasMaleFemaleSound = true;
			baseStats.numMSounds = 1;
			baseStats.numFSounds = 1;
			addPokemonSound(p.name.toLowerCase() + "M", resourcePack, baseStats);
			addPokemonSound(p.name.toLowerCase() + "F", resourcePack, baseStats);
			int i = 2;
			while (addPokemonSound(p.name.toLowerCase() + "M" + i++, resourcePack, baseStats)) {
				baseStats.numMSounds++;
			}
			i = 2;
			while (addPokemonSound(p.name.toLowerCase() + "F" + i++, resourcePack, baseStats)) {
				baseStats.numFSounds++;
			}
		}
	}

	public static void detectServerSounds() {
		for (EnumPokemon p : EnumPokemon.values()) {
			detectPokemonSounds(p);
		}

	}

	private static void detectPokemonSounds(EnumPokemon p) {
		boolean hasMaleFemale = false;
		BaseStats baseStats = Entity3HasStats.getBaseStats(p.name);
		if (Sounds.class.getResourceAsStream("/assets/pixelmon/sound/pixelmon/" + p.name.toLowerCase() + "M" + ".ogg") != null)
			hasMaleFemale = true;
		if (!hasMaleFemale) {
			detectSound(p.name.toLowerCase(), baseStats);
			int i = 2;
			while (detectSound(p.name.toLowerCase() + i++, baseStats)) {
			}
		} else {
			baseStats.hasMaleFemaleSound = true;
			baseStats.numMSounds = 1;
			baseStats.numFSounds = 1;
			detectSound(p.name.toLowerCase() + "M", baseStats);
			detectSound(p.name.toLowerCase() + "F", baseStats);
			int i = 2;
			while (detectSound(p.name.toLowerCase() + "M" + i++, baseStats)) {
				baseStats.numMSounds++;
			}
			i = 2;
			while (detectSound(p.name.toLowerCase() + "F" + i++, baseStats)) {
				baseStats.numFSounds++;
			}
		}
	}

	private static boolean detectSound(String nameString, BaseStats baseStats){
		if (Sounds.class.getResourceAsStream("/assets/pixelmon/sound/pixelmon/" + nameString + ".ogg")!=null){
			baseStats.numSounds++;
			return true;
		}
		return false;
	}

	private static boolean addPokemonSound(String nameString, AbstractResourcePack resourcePack, BaseStats baseStats) {
		if (resourcePack.resourceExists(new ResourceLocation("pixelmon:sound/pixelmon/" + nameString + ".ogg"))) {
			Minecraft.getMinecraft().sndManager.addSound("pixelmon:pixelmon/" + nameString + ".ogg");
			baseStats.numSounds++;
			return true;
		}
		return false;
	}

	public static boolean installMusic() {
		AbstractResourcePack resourcePack = (AbstractResourcePack) FMLClientHandler.instance().getResourcePackFor("pixelmon");
		for (String songName : MusicList) {
			Minecraft.getMinecraft().sndManager.addMusic("pixelmon:pixelmon/" + songName + ".ogg");
		}
		return true;
	}

	private static void installSound(String path, String name, String newPath) {
		if (new File(path + name + ".ogg").exists())
			Minecraft.getMinecraft().sndManager.addSound("pixelmon:pixelmon/" + name + ".ogg");
	}
}
