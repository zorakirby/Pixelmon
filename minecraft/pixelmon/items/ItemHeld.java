package pixelmon.items;

import net.minecraft.item.ItemStack;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.heldItems.EnumHeldItems;

/**
 * 
 * IF THE ITEM IS TO BE HELD BY A PIXELMON, USE THIS AND EDIT WHAT YOU NEED
 * 
 * @author Gerald -xkyouchoux-
 * 
 */

public abstract class ItemHeld extends PixelmonItem {
	private EnumHeldItems heldItemType;
	private boolean usableInBattle;
	private boolean affectsBattles;

	public ItemHeld(int id, EnumHeldItems heldItemType, String textureName) {
		super(id, "helditems/" + textureName);
		isEquippable = true;
		this.heldItemType = heldItemType;
		usableInBattle = heldItemType.getUsableInBattle();
		affectsBattles = heldItemType.getAffectsBattle();
		setCreativeTab(PixelmonCreativeTabs.held);
	}

	public EnumHeldItems getHeldItemType() {
		return heldItemType;
	}

	public boolean usableInBattle() {
		return usableInBattle;
	}

	public boolean getAffectsBattle() {
		return affectsBattles;
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
			if (!item.getUsableInBattle()) {
				continue;
			}
			useItem(user, target, item);
		}
	}
}
