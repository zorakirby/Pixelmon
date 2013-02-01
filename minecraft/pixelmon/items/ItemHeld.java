package pixelmon.items;

import net.minecraft.item.ItemStack;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumHeldItems;

/**
 * 
 * IF THE ITEM IS TO BE HELD BY A PIXELMON, USE THIS AND EDIT WHAT YOU NEED
 * 
 * @author Gerald -xkyouchoux-
 * 
 */

public abstract class ItemHeld extends PixelmonItem {
	private EnumHeldItems heldItemType;
	private boolean effectsBattles;

	public ItemHeld(int id, EnumHeldItems heldItemType) {
		super(id);
		isEquippable = true;
		this.heldItemType = heldItemType;
		effectsBattles = heldItemType.getBattleModifier();
		setCreativeTab(PixelmonCreativeTabs.held);
	}

	public EnumHeldItems getHeldItemType() {
		return heldItemType;
	}

	public boolean doesEffectBattles() {
		return effectsBattles;
	}

	public static void useItem(EntityPixelmon user, EntityPixelmon target, EnumHeldItems item) {
		if (user.getHeldItem() != null && user.getHeldItem().getItem() != null && user.getHeldItem().getItem() instanceof ItemHeld) {
			if (((ItemHeld) user.getHeldItem().getItem()).heldItemType == item && ((ItemHeld) user.getHeldItem().getItem()).effectEntity(user)) {
				user.setHeldItem(null);
			}
		}
	}

	public static boolean isItemOfType(ItemStack item, EnumHeldItems type) {
		if (item == null || (item != null && (item.getItem() == null || !(item.getItem() instanceof ItemHeld)))) {
			return false;
		} else
			return ((ItemHeld) item.getItem()).heldItemType == type;
	}

	public boolean effectEntity(EntityPixelmon helper1) {
		return false;
	}

	public static void useBattleItems(EntityPixelmon user, EntityPixelmon target) {
		for (EnumHeldItems item : EnumHeldItems.values()) {
			if (!item.getBattleModifier()) {
				continue;
			}
			useItem(user, target, item);
		}
	}
}
