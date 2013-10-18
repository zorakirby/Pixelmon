package pixelmon.client.render;

import pixelmon.client.shading.Cubemap.CubemapTexture;
import net.minecraft.util.ResourceLocation;

public class RenderResources {
	public static ResourceLocation anvil = new ResourceLocation("pixelmon:textures/blocks/anvil.png");
	public static ResourceLocation aluminiumIngot = new ResourceLocation("pixelmon:textures/blocks/aluminium/ingot.png");
	public static ResourceLocation ironDisc = new ResourceLocation("pixelmon:textures/pokeballs/irondisc.png");
	public static ResourceLocation healer = new ResourceLocation("pixelmon:textures/blocks/healer.png");
	public static ResourceLocation pc = new ResourceLocation("pixelmon:textures/blocks/pc.png");
	public static ResourceLocation tradingMachine = new ResourceLocation("pixelmon:textures/blocks/tradingmachine.png");
	public static ResourceLocation fossilCleaningMachine = new ResourceLocation("pixelmon:textures/blocks/fossilcleaningmachine.png");
	public static ResourceLocation fossilMachine = new ResourceLocation("pixelmon:textures/blocks/fossilmachine.png");
	public static ResourceLocation mossrocktex = new ResourceLocation("pixelmon:textures/blocks/mossrocktex.png");
	public static ResourceLocation icyrocktex = new ResourceLocation("pixelmon:textures/blocks/icyrocktex.png");
	
	public static ResourceLocation uno = new ResourceLocation("pixelmon:textures/blocks/shrines/articuno.png");
	public static ResourceLocation dos = new ResourceLocation("pixelmon:textures/blocks/shrines/zapdos.png");
	public static ResourceLocation tres = new ResourceLocation("pixelmon:textures/blocks/shrines/moltres.png");

	public static ResourceLocation pillar = new ResourceLocation("pixelmon:models/pillar/pillar.png");
	public static ResourceLocation pillarDamaged = new ResourceLocation("pixelmon:models/pillar/pillar_fractured.png");
	
	@CubemapTexture
	public static String glalieCubemap = "pixelmon/textures/pokemon/Glalie_Cubemap.png";
	@CubemapTexture
	public static String glalieCubemapShiny = "pixelmon/textures/pokemon/pokemon-shiny/shinyglalie_cubemap.png";
}
