package pixelmon.sounds;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import pixelmon.enums.EnumPokemon;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Sounds {

	@SideOnly(Side.CLIENT)
	public static void installSounds() {
		for (EnumPokemon p : EnumPokemon.values()) {
			installSound("resources/newsound/pixelmon/", p.name.toLowerCase(), "pixelmon/");
		}

		installSound("resources/newsound/pixelmon/", "anvilHits", "pixelmon/");
	}

	@SideOnly(Side.CLIENT)
	public static void installMusic() {
		File musicDir = new File("./resources/music/pixelmon");
		File[] musicFiles = musicDir.listFiles();
		if (musicFiles == null || musicFiles.length == 0) {
			System.out.println("No Music Files found!");
		} else {
			for (File f : musicFiles) {
				Minecraft.getMinecraft().sndManager.addMusic(f.getName(), new File(Minecraft.getMinecraftDir() + "/resources/music/pixelmon/" + f.getName()));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private static void installSound(String path, String name, String newPath) {
		if (new File(path + name + ".ogg").exists())
			Minecraft.getMinecraft().sndManager.addSound(newPath + name + ".ogg", new File(path + name + ".ogg"));
	}
}
