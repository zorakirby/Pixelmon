package pixelmon.client.gui;

import net.minecraft.util.ResourceLocation;

public class GuiResources {
	public static ResourceLocation prefix = new ResourceLocation("pixelmon" + ":");
	
	
	public static ResourceLocation tradeGui = new ResourceLocation(prefix + "gui/tradeGui.png");
	public static ResourceLocation heldItem = new ResourceLocation(prefix + "helditem.png");
	public static ResourceLocation overlaySimple = new ResourceLocation(prefix + "gui/pixelmonOverlaySimple.png");
	public static ResourceLocation overlayExtended = new ResourceLocation(prefix + "gui/pixelmonOverlay.png");

	public static ResourceLocation pokemonInfoP1 = new ResourceLocation(prefix + "gui/pokemonInfoP1.png");
	public static ResourceLocation pokemonInfoP2 = new ResourceLocation(prefix + "gui/pokemonInforP2.png");
	public static ResourceLocation levelUpPopup = new ResourceLocation(prefix + "gui/levelUpPopUp.png");
	public static ResourceLocation battleGui1 = new ResourceLocation(prefix + "gui/battleGui1.png");
	public static ResourceLocation battleGui2 = new ResourceLocation(prefix + "gui/battleGui2.png");
	public static ResourceLocation battleGui3 = new ResourceLocation(prefix + "gui/battleGui3.png");
	public static ResourceLocation yesNo = new ResourceLocation(prefix + "gui/yesNo.png");
	public static ResourceLocation chooseMove = new ResourceLocation(prefix + "gui/chooseMove.png");
	public static ResourceLocation choosePokemon = new ResourceLocation(prefix + "gui/choosePokemon.png");
	public static ResourceLocation selectCurrentPokemon = new ResourceLocation(prefix + "gui/selectCurrentPokemon.png");
	public static ResourceLocation itemGui2 = new ResourceLocation(prefix + "gui/itemGui2.png");
	public static ResourceLocation itemGui1 = new ResourceLocation(prefix + "gui/itemGui1_Test.png");

	public static ResourceLocation pokecheckerPopup = new ResourceLocation(prefix + "gui/pokecheckerPopup.png");
	public static ResourceLocation pixelmonCreativeInventory = new ResourceLocation(prefix + "gui/PixelmonCreativeInventory.png");
	public static ResourceLocation pixelmonOverlay = new ResourceLocation(prefix + "gui/pixelmonOverlay.png");
	public static ResourceLocation pixelmonOverlayExtended2 = new ResourceLocation(prefix + "gui/pixelmonOverlayExtended2.png");
	public static ResourceLocation mcInventory = new ResourceLocation("minecraft:textures/gui/container/inventory.png");
	public static ResourceLocation mcItems = new ResourceLocation("gui/items.png");

	public static ResourceLocation pcPartyBox = new ResourceLocation(prefix + "gui/pcPartyBox.png");
	public static ResourceLocation pcBox = new ResourceLocation(prefix + "gui/pcBox.png");
	public static ResourceLocation pokechecker = new ResourceLocation(prefix + "pokechecker.png");

	public static ResourceLocation summarySummary = new ResourceLocation(prefix + "gui/summarySummary.png");
	public static ResourceLocation rename = new ResourceLocation(prefix + "gui/rename.png");
	public static ResourceLocation summaryMoves = new ResourceLocation(prefix + "gui/summaryMoves.png");
	public static ResourceLocation summaryStats = new ResourceLocation(prefix + "gui/summaryStats.png");

	public static ResourceLocation pokedex = new ResourceLocation(prefix + "gui/pokedex.png");

	public static ResourceLocation items = new ResourceLocation(prefix + "gui/items.png");
	public static ResourceLocation types = new ResourceLocation(prefix + "gui/types.png");
	public static ResourceLocation shiny = new ResourceLocation(prefix + "sprites/shinypokemon/star.png");

	public static ResourceLocation shinySprite(String numString) {
		return new ResourceLocation(prefix + "sprites/shinypokemon/" + numString + ".png");
	}

	public static ResourceLocation sprite(String numString) {
		return new ResourceLocation(prefix + "sprites/pokemon/" + numString + ".png");
	}

	public static ResourceLocation starterBackground = new ResourceLocation(prefix + "gui/starter/Background.jpg");
	public static ResourceLocation starterBorders = new ResourceLocation(prefix + "gui/starter/borders.png");
	public static ResourceLocation cwPanel = new ResourceLocation(prefix + "gui/starter/cwpanel.png");

	public static ResourceLocation evo = new ResourceLocation(prefix + "gui/evolution/Evolution.png");
	public static ResourceLocation button = new ResourceLocation(prefix + "gui/evolution/Button.png");
	public static ResourceLocation buttonOver = new ResourceLocation(prefix + "gui/evolution/ButtonOver.png");
	
	public static ResourceLocation background = new ResourceLocation(prefix + "gui/drops/Drops1.png");
	public static ResourceLocation itemSlot = new ResourceLocation(prefix + "gui/drops/Drops2.png");
	public static ResourceLocation itemSlotOver = new ResourceLocation(prefix + "gui/drops/Drops2Over.png");
	
	
}
