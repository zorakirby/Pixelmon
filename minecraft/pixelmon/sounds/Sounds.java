package pixelmon.sounds;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;
import pixelmon.enums.EnumPokemon;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.FMLFileResourcePack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Sounds {

	@SideOnly(Side.CLIENT)
	public static void installSounds() {
		System.out.println("Checking for sounds in " + Minecraft.getMinecraft().mcDataDir + "/resources/newsound/pixelmon");
		for (EnumPokemon p : EnumPokemon.values()) {
			installSound(Minecraft.getMinecraft().mcDataDir + "/resources/sound/pixelmon/", p.name.toLowerCase(), "pixelmon/");
		}

		installSound(Minecraft.getMinecraft().mcDataDir + "/resources/sound/pixelmon/", "anvilHits", "pixelmon/");
	}

	@SideOnly(Side.CLIENT)
	public static boolean installMusic() {
		System.out.println("Checking for pixelmon resources");
		FMLFileResourcePack resourcePack = (FMLFileResourcePack)FMLClientHandler.instance().getResourcePackFor("pixelmon");
		ResourceManager r = Minecraft.getMinecraft().func_110442_L();
		File musicDir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/music/pixelmon");
		File[] musicFiles = musicDir.listFiles();
		if (musicFiles == null || musicFiles.length == 0) {
			System.out.println("No Music Files found!");
			return false;
		} else {
			for (File f : musicFiles) {
				Minecraft.getMinecraft().sndManager.addMusic("music.pixelmon." + f.getName());
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	private static void installSound(String path, String name, String newPath) {
		if (new File(path + name + ".ogg").exists())
			Minecraft.getMinecraft().sndManager.addSound("sound.pixelmon." + name + ".ogg");
	}
}
