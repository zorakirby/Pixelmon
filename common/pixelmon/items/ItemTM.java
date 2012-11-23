package pixelmon.items;

import net.minecraft.src.ItemStack;
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
		else if (moveType == EnumType.Fighting)
			setIconIndex(56);
		else if (moveType == EnumType.Fire)
			setIconIndex(72);
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
