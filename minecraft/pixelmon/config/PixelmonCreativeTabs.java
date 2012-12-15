package pixelmon.config;

import net.minecraft.creativetab.CreativeTabs;
import pixelmon.creativeTabs.CreativeTabBadges;
import pixelmon.creativeTabs.CreativeTabHeldItems;
import pixelmon.creativeTabs.CreativeTabNatural;
import pixelmon.creativeTabs.CreativeTabPokeball;
import pixelmon.creativeTabs.CreativeTabRestoration;
import pixelmon.creativeTabs.CreativeTabTM;

public class PixelmonCreativeTabs {

	public static final CreativeTabs pokeball = new CreativeTabPokeball(12, "Pokeballs");
	public static final CreativeTabs badges = new CreativeTabBadges(13, "Badges");
	public static final CreativeTabs restoration = new CreativeTabRestoration(14, "Restoration");
	public static final CreativeTabs natural = new CreativeTabNatural(15, "Natural");
	public static final CreativeTabs held = new CreativeTabHeldItems(16, "Held Items");
	public static final CreativeTabs tms = new CreativeTabTM(17, "TMs/HMs");

}
