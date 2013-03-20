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
		super(id, "tms/tm" + moveType.toString().toLowerCase(), getItemName(tmIndex, attackName));
		this.tmIndex = tmIndex;
		this.isHM = isHM;
		this.attackName = attackName;
		this.type = moveType;
		setMaxStackSize(16);

		setCreativeTab(PixelmonCreativeTabs.tms);
	}

	public static String getItemName(int tmIndex, String attackName) {
		return "TM" + tmIndex + ": " + attackName;
	}

	public String getItemName() {
		return getItemName(tmIndex, attackName);
	}
}
