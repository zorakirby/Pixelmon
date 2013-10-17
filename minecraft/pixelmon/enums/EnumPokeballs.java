package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.IEnumItem;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.ItemPokeballLid;
import pixelmon.util.FunctionHelper;
import static pixelmon.enums.EnumApricorns.*;

public enum EnumPokeballs implements IEnumItem{
	PokeBall(1, "pokeball", 0, 0, 3, 2, 3, 3, new EnumApricorns[]{Red, Red, Red}, 5, 15), //0
	GreatBall(1.5, "greatball", 0, 1, 4, 2, 4, 3, new EnumApricorns[]{Blue, Red, Blue}, 2, 35), //1
	UltraBall(2, "ultraball", 0, 2, 5, 2, 5, 3, new EnumApricorns[]{Black, Yellow, Black}, 1, 55), //2
	MasterBall(255, "masterball", 0, 3, 0, 0, 0, 0, null, 0, 0), //3
	LevelBall(1, "levelball", 1, 8, 14, 2, 14, 3, new EnumApricorns[]{Black, Red, Yellow}, 2, 35), //4
	MoonBall(1, "moonball", 1, 15, 13, 2, 13, 3, new EnumApricorns[]{Blue, Yellow, Black}, 2, 35), //5
	FriendBall(1, "friendball", 1, 3, 11, 2, 11, 3, new EnumApricorns[]{Green, Yellow, Red}, 3, 35), //6
	LoveBall(1, "loveball", 0, 6, 10, 2, 10, 3, new EnumApricorns[]{Pink, Pink, Pink}, 3, 35), //7
	SafariBall(-1, "safariball", 0, 4, 7, 2, 7, 3, new EnumApricorns[]{Green, Green, Yellow}, 3, 35), //8
	HeavyBall(1, "heavyball", 1, 6, 8, 2, 8, 3, new EnumApricorns[]{Blue, Blue, Blue}, 3, 35), //9
	FastBall(1, "fastball", 1, 2, 9, 2, 9, 3, new EnumApricorns[]{Red, Yellow, Red}, 3, 35), //10
	RepeatBall(1, "repeatball", 0, 5, 4, 0, 4, 1, new EnumApricorns[]{Red, Black, Red}, 3, 35), //11
	TimerBall(1, "timerball", 1, 9, 10, 0, 10, 1, new EnumApricorns[]{Red, Black, White}, 3, 35), //12
	NestBall(1, "nestball", 1, 0, 5, 0, 5, 1, new EnumApricorns[]{Green, Yellow, Green}, 3, 35), //13
	NetBall(1, "netball", 1, 1, 6, 2, 6, 3, new EnumApricorns[]{Black, Blue, Black}, 3, 35), //14
	DiveBall(1, "diveball", 1, 11, 11, 0, 11, 1, new EnumApricorns[]{Blue, Pink, Blue}, 3, 35), //15
	LuxuryBall(1, "luxuryball", 1, 12, 12, 0, 12, 1, new EnumApricorns[]{Black, Red, White}, 3, 35), //16
	HealBall(1, "healball", 1, 4, 7, 0, 7, 1, new EnumApricorns[]{White, Pink, White}, 3, 35), //17
	DuskBall(1, "duskball", 1, 13, 13, 0, 13, 1, new EnumApricorns[]{Green, Black, Green}, 3, 35), //18
	PremierBall(1, "premierball", 1, 5, 8, 0, 8, 1, new EnumApricorns[]{White, Red, White}, 3, 25); //19
	
	//TODO Add lure and cherish balls. 

	private EnumPokeballs( double ballBonus, String filenamePrefix, int iconIndexX, int iconIndexY, int lidIconIndexX, int lidIconIndexY,
			int discIconIndexX, int discIconIndexY, EnumApricorns[] recipe, int quantityMade, int chanceBreak) {
		this.ballBonus = ballBonus;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndexX + iconIndexY * 16;
		this.lidIconIndex = lidIconIndexX + lidIconIndexY * 16;
		this.discIconIndex = discIconIndexX + discIconIndexY * 16;
		this.recipe = recipe;
		this.quantityMade = quantityMade;
		this.breakChance = chanceBreak;
	}

	private double ballBonus;
	
	
	//Uh, you guys know about Enum.ordinal(), right?
	//private int index;
	
	String filenamePrefix;
	private int iconIndex;
	public int lidIconIndex;
	public int discIconIndex;
	public EnumApricorns[] recipe;
	public int quantityMade;
	public int breakChance;

	public double getBallBonus() {
		return ballBonus;
	}


	public int getIconIndex() {
		return iconIndex;
	}

	public int getBreakChance(){
		return breakChance;
	}

	public String getTexture() {
		return filenamePrefix + ".png";
	}

	public String getFlashRedTexture() {
		return filenamePrefix + "flashing.png";
	}

	public String getCaptureTexture() {
		return filenamePrefix + "captured.png";
	}

	public static EnumPokeballs getFromIndex(int index) {
		return values()[index];
	}
	
	public ItemPokeBall getItem(){
		return PixelmonItemsPokeballs.getItemFromEnum(this);
	}
	
	
	public ItemPokeballLid getLid(){
		return PixelmonItemsPokeballs.getLidFromEnum(this);
	}
	
	public ItemPokeballDisc getDisc(){
		return PixelmonItemsPokeballs.getDiscFromEnum(this);
	}
	
	/**
	 * {@inheritDoc}
	 * The Item categories for {@code EnumPokeballs} are:<br>
	 * {@link ItemPokeBall}, {@link ItemPokeballLid}, {@link ItemPokeballDisc}
	 */
	@Override
	public Item getItem(int type) {
		switch(type){
		case 0 : return getItem();
		case 1 : return getLid();
		case 2 : return getDisc();
		}
		return null;
	}
	
	@Override
	public int numTypes(){
		return 3;
	}
}
