package pixelmon.enums;

import net.minecraft.item.Item;
import pixelmon.config.PixelmonItemsPokeballs;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball", 0, 0, 3, 2, 3, 3, new EnumApricorns[]{EnumApricorns.Red, EnumApricorns.Red, EnumApricorns.Red}, 5, 15),
	GreatBall(1, 1.5, "greatball", 0, 1, 4, 2, 4, 3, new EnumApricorns[]{EnumApricorns.Blue, EnumApricorns.Red, EnumApricorns.Blue}, 2, 35),
	UltraBall(2, 2, "ultraball", 0, 2, 5, 2, 5, 3, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Yellow, EnumApricorns.Black}, 1, 55),
	MasterBall(3, 255, "masterball", 0, 3, 0, 0, 0, 0, null, 0, 0),
	LevelBall(4, 1, "levelball", 1, 8, 14, 2, 14, 3, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Red, EnumApricorns.Yellow}, 2, 35),
	MoonBall(5, 1, "moonball", 1, 15, 13, 2, 13, 3, new EnumApricorns[]{EnumApricorns.Blue, EnumApricorns.Yellow, EnumApricorns.Black}, 2, 35),
	FriendBall(6, 1, "friendball", 1, 3, 11, 2, 11, 3, new EnumApricorns[]{EnumApricorns.Green, EnumApricorns.Yellow, EnumApricorns.Red}, 3, 35),
	LoveBall(7, 1, "loveball", 0, 6, 10, 2, 10, 3, new EnumApricorns[]{EnumApricorns.Pink, EnumApricorns.Pink, EnumApricorns.Pink}, 3, 35),
	SafariBall(8, -1, "safariBall", 0, 4, 7, 2, 7, 3, new EnumApricorns[]{EnumApricorns.Green, EnumApricorns.Green, EnumApricorns.Yellow}, 3, 35),
	HeavyBall(9, 1, "heavyBall", 1, 6, 8, 2, 8, 3, new EnumApricorns[]{EnumApricorns.Blue, EnumApricorns.Blue, EnumApricorns.Blue}, 3, 35),
	FastBall(10, 1, "fastball", 1, 2, 9, 2, 9, 3, new EnumApricorns[]{EnumApricorns.Red, EnumApricorns.Yellow, EnumApricorns.Red}, 3, 35),
	RepeatBall(11, 1, "repeatball", 0, 5, 4, 0, 4, 1, new EnumApricorns[]{EnumApricorns.Red, EnumApricorns.Black, EnumApricorns.Red}, 3, 35),
	TimerBall(12, 1, "timerball", 1, 9, 10, 0, 10, 1, new EnumApricorns[]{EnumApricorns.Red, EnumApricorns.Black, EnumApricorns.White}, 3, 35),
	NestBall(13, 1, "nestball", 1, 0, 5, 0, 5, 1, new EnumApricorns[]{EnumApricorns.Green, EnumApricorns.Yellow, EnumApricorns.Green}, 3, 35),
	NetBall(14, 1, "netball", 1, 1, 6, 2, 6, 3, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Blue, EnumApricorns.Black}, 3, 35),
	DiveBall(15, 1, "diveball", 1, 11, 11, 0, 11, 1, new EnumApricorns[]{EnumApricorns.Blue, EnumApricorns.Pink, EnumApricorns.Blue}, 3, 35),
	LuxuryBall(16, 1, "luxuryball", 1, 12, 12, 0, 12, 1, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Red, EnumApricorns.White}, 3, 35),
	HealBall(17, 1, "healball", 1, 4, 7, 0, 7, 1, new EnumApricorns[]{EnumApricorns.White, EnumApricorns.Pink, EnumApricorns.White}, 3, 35),
	DuskBall(18, 1, "duskball", 1, 13, 13, 0, 13, 1, new EnumApricorns[]{EnumApricorns.Green, EnumApricorns.Black, EnumApricorns.Green}, 3, 35),
	PremierBall(19, 1, "premierball", 1, 5, 8, 0, 8, 1, new EnumApricorns[]{EnumApricorns.White, EnumApricorns.Red, EnumApricorns.White}, 3, 25);
	
	//TODO Add lure and cherish balls. 

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix, int iconIndexX, int iconIndexY, int lidIconIndexX, int lidIconIndexY,
			int discIconIndexX, int discIconIndexY, EnumApricorns[] recipe, int quantityMade, int chanceBreak) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndexX + iconIndexY * 16;
		this.lidIconIndex = lidIconIndexX + lidIconIndexY * 16;
		this.discIconIndex = discIconIndexX + discIconIndexY * 16;
		this.recipe = recipe;
		this.quantityMade = quantityMade;
		this.breakChance = chanceBreak;
	}

	private double ballBonus;
	private int index;
	private String filenamePrefix;
	private int iconIndex;
	public int lidIconIndex;
	public int discIconIndex;
	public EnumApricorns[] recipe;
	public int quantityMade;
	public int breakChance;

	public double getBallBonus() {
		return ballBonus;
	}

	public int getIndex() {
		return index;
	}

	public int getIconIndex() {
		return iconIndex;
	}

	public int getBreakChance(){
		return breakChance;
	}
	
	public Item getItem() {
		return PixelmonItemsPokeballs.getItemFromEnum(this);
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
		for (EnumPokeballs b : values())
			if (b.index == index)
				return b;

		return EnumPokeballs.PokeBall;
	}

	public Item getLid() {
		return PixelmonItemsPokeballs.getLidFromEnum(this);
	}

	public Item getDisc() {
		return PixelmonItemsPokeballs.getDiscFromEnum(this);
	}
}
