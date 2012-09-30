package pixelmon.enums;

import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.ItemApricornCooked;
import net.minecraft.src.Block;
import net.minecraft.src.Item;

public enum EnumPokeballs {
	PokeBall(0, 1, "pokeball", 0, 0, 3, 2, 3, 3, new EnumApricorns[]{EnumApricorns.Red, EnumApricorns.Red, EnumApricorns.Red}, 5),
	GreatBall(1, 1.5, "greatball", 0, 1, 4, 2, 4, 3, new EnumApricorns[]{EnumApricorns.Blue, EnumApricorns.Red, EnumApricorns.Blue}, 2),
	UltraBall(2, 2, "ultraball", 0, 2, 5, 2, 5, 3, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Yellow, EnumApricorns.Black}, 1),
	MasterBall(3, 255, "masterball", 0, 3, 0, 0, 0, 0, null, 0),
	LevelBall(4, 1, "levelball", 1, 8, 14, 2, 14, 3, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Red, EnumApricorns.Yellow}, 2),
	MoonBall(5, 1, "moonball", 1, 15, 13, 2, 13, 3, new EnumApricorns[]{EnumApricorns.Blue, EnumApricorns.Yellow, EnumApricorns.Black}, 2),
	FriendBall(6, 1, "friendball", 1, 3, 11, 2, 11, 3, new EnumApricorns[]{EnumApricorns.Green, EnumApricorns.Yellow, EnumApricorns.Red}, 3),
	LoveBall(7, 1, "loveball", 0, 6, 10, 2, 10, 3, new EnumApricorns[]{EnumApricorns.Pink, EnumApricorns.Pink, EnumApricorns.Pink}, 3),
	HeavyBall(8, 1, "heavyball", 1, 6, 8, 2, 8, 3, new EnumApricorns[]{EnumApricorns.Black, EnumApricorns.Blue, EnumApricorns.Black}, 2);

	private EnumPokeballs(int index, double ballBonus, String filenamePrefix, int iconIndexX, int iconIndexY, int lidIconIndexX, int lidIconIndexY,
			int discIconIndexX, int discIconIndexY, EnumApricorns[] recipe, int quantityMade) {
		this.ballBonus = ballBonus;
		this.index = index;
		this.filenamePrefix = filenamePrefix;
		this.iconIndex = iconIndexX + iconIndexY * 16;
		this.lidIconIndex = lidIconIndexX + lidIconIndexY * 16;
		this.discIconIndex = discIconIndexX + discIconIndexY * 16;
		this.recipe = recipe;
		this.quantityMade = quantityMade;
	}

	private double ballBonus;
	private int index;
	private String filenamePrefix;
	private int iconIndex;
	public int lidIconIndex;
	public int discIconIndex;
	public EnumApricorns[] recipe;
	public int quantityMade;

	public double getBallBonus() {
		return ballBonus;
	}

	public int getIndex() {
		return index;
	}

	public int getIconIndex() {
		return iconIndex;
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
