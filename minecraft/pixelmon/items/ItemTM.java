package pixelmon.items;

import net.minecraft.item.ItemStack;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.enums.EnumType;

public class ItemTM extends PixelmonItem {

	public String attackName;
	private int tmIndex;
	private boolean isHM;
	private EnumType type;

	public ItemTM(int id, String attackName, int tmIndex, EnumType moveType, boolean isHM) {
		super(id);
		this.tmIndex = tmIndex;
		this.isHM = isHM;
		this.attackName = attackName;
		this.type = moveType;
		setMaxStackSize(16);
		if (moveType == EnumType.Normal)
			setIconIndex(8);
		else if (moveType == EnumType.Water)
			setIconIndex(24);
		else if (moveType == EnumType.Ice)
			setIconIndex(40);
		else if (moveType == EnumType.Fire)
			setIconIndex(56);
		else if (moveType == EnumType.Fighting)
			setIconIndex(72);
		else if (moveType == EnumType.Poison)
			setIconIndex(88);
		else if (moveType == EnumType.Grass)
			setIconIndex(104);
		else if (moveType == EnumType.Psychic)
			setIconIndex(120);
		else if (moveType == EnumType.Rock)
			setIconIndex(136);
		else if (moveType == EnumType.Bug)
			setIconIndex(152);
		else if (moveType == EnumType.Dragon)
			setIconIndex(168);
		else if (moveType == EnumType.Ground)
			setIconIndex(184);
		else if (moveType == EnumType.Ghost)
			setIconIndex(200);
		else if (moveType == EnumType.Flying)
			setIconIndex(216);
		else if (moveType == EnumType.Electric)
			setIconIndex(232);
		else
			setIconIndex(8);

		setCreativeTab(PixelmonCreativeTabs.tms);
		setItemName(getItemName());
	}

	public String getItemName() {
		return "TM" + tmIndex + ": " + attackName;
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return getItemName();
	}

}
