package pixelmon.sounds;

import java.io.File;

import net.minecraft.src.ModLoader;

public class Sounds {

	public static void installSounds(){
		ModLoader.getMinecraftInstance().installResource("newsound/pixelmon/charmander.ogg", new File("resources/newsound/pixelmon/charmander.ogg"));
		ModLoader.getMinecraftInstance().installResource("newsound/pixelmon/magikarp.ogg", new File("resources/newsound/pixelmon/magikarp.ogg"));
	}

}
