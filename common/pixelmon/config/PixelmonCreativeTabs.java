package pixelmon.config;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.registry.GameRegistry;
import pixelmon.creativeTabs.*;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;

public class PixelmonCreativeTabs {

	public static final CreativeTabs pokeball = new CreativeTabPokeball(12, "Pokeballs");
	public static final CreativeTabs badges = new CreativeTabBadges(13, "Badges");
	public static final CreativeTabs restoration = new CreativeTabRestoration(14, "Restoration");
	public static final CreativeTabs natural = new CreativeTabNatural(15, "Natural");
	public static final CreativeTabs held = new CreativeTabHeldItems(16, "Held Items");
	public static final CreativeTabs tms = new CreativeTabTM(17, "TMs/HMs");

}
