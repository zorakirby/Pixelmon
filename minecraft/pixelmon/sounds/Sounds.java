package pixelmon.sounds;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;
import pixelmon.enums.EnumPokemon;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.FMLFileResourcePack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Sounds {

	private static final String[] MusicList = { "A Simple Moment By the Sea", "Across the Desert", "Distant Shores", "Pixelmon Waltz" };

	@SideOnly(Side.CLIENT)
	public static void installSounds() {
		AbstractResourcePack resourcePack = (AbstractResourcePack) FMLClientHandler.instance().getResourcePackFor("pixelmon");
		for (EnumPokemon p : EnumPokemon.values()) {
			installPokemonSounds(p, resourcePack);
		}

		installSound(Minecraft.getMinecraft().mcDataDir + "/resources/sound/pixelmon/", "anvilHits", "pixelmon/");
	}

	public static void installPokemonSounds(EnumPokemon p, AbstractResourcePack resourcePack) {
		if (resourcePack.func_110589_b(new ResourceLocation("pixelmon:sound/pixelmon/" + p.name.toLowerCase() + ".ogg")))
			Minecraft.getMinecraft().sndManager.addSound("pixelmon:pixelmon/" + p.name.toLowerCase() + ".ogg");
		int i = 1;
		while (resourcePack.func_110589_b(new ResourceLocation("pixelmon:sound/pixelmon/" + p.name.toLowerCase() + i + ".ogg"))) {
			Minecraft.getMinecraft().sndManager.addSound("pixelmon:pixelmon/" + p.name.toLowerCase() + i + ".ogg");
			i++;
		}
	}

	@SideOnly(Side.CLIENT)
	public static boolean installMusic() {
		AbstractResourcePack resourcePack = (AbstractResourcePack) FMLClientHandler.instance().getResourcePackFor("pixelmon");
		for (String songName : MusicList) {
			Minecraft.getMinecraft().sndManager.addMusic("pixelmon:pixelmon/" + songName + ".ogg");
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	private static void installSound(String path, String name, String newPath) {
		if (new File(path + name + ".ogg").exists())
			Minecraft.getMinecraft().sndManager.addSound("pixelmon:pixelmon/" + name + ".ogg");
	}
}
