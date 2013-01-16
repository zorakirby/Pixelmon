package pixelmon.config;

import net.minecraft.creativetab.CreativeTabs;
import pixelmon.creativeTabs.CreativeTabBadges;
import pixelmon.creativeTabs.CreativeTabHeldItems;
import pixelmon.creativeTabs.CreativeTabNatural;
import pixelmon.creativeTabs.CreativeTabPokeball;
import pixelmon.creativeTabs.CreativeTabRestoration;
import pixelmon.creativeTabs.CreativeTabTM;

public class PixelmonCreativeTabs {

	public static final CreativeTabs pokeball = new CreativeTabPokeball(CreativeTabs.getNextID(), "Pokeballs");
	public static final CreativeTabs badges = new CreativeTabBadges(CreativeTabs.getNextID(), "Badges");
	public static final CreativeTabs restoration = new CreativeTabRestoration(CreativeTabs.getNextID(), "Restoration");
	public static final CreativeTabs natural = new CreativeTabNatural(CreativeTabs.getNextID(), "Natural");
	public static final CreativeTabs held = new CreativeTabHeldItems(CreativeTabs.getNextID(), "Held Items");
	public static final CreativeTabs tms = new CreativeTabTM(CreativeTabs.getNextID(), "TMs/HMs");

}
