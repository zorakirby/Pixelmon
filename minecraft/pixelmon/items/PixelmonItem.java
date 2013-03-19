package pixelmon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonItem extends Item {

	private boolean isUsableInBattle = false;
	private boolean isUsableOutsideBattle = false;
	protected boolean isEquippable = false;
	String iconString;

	public PixelmonItem(int par1, String string, String name) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		this.iconString = string;
		setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister) {
		this.iconIndex = par1IconRegister.func_94245_a("pixelmon:" + iconString);
	}

	protected void SetUsableInBattle(boolean isUsable) {
		isUsableInBattle = isUsable;
	}

	public boolean isUsableInBattle() {
		return isUsableInBattle;
	}

	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
	}

	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon, int additionalInfo) {
		useFromBag(userPokemon, targetPokemon);
	}

	public boolean isUsableOutSideBattle() {
		return isUsableOutsideBattle;
	}

	public void useFromInventory(EntityPixelmon user) {
	}

	public boolean isEquippable() {
		return isEquippable;
	}

	public int removeFromInventory(ItemStack[] inv) {
		int newStackSize = -1;
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != null && inv[i].getItem().itemID == itemID) {
				inv[i].stackSize--;
				newStackSize = inv[i].stackSize;
				if (inv[i].stackSize < 1) {
					inv[i] = null;
				}
				break;
			}
		}
		return newStackSize;
	}

	public void removeFromInventory(ItemStack[] inv, ItemStack[] c_inv) {
		int newStackSize = removeFromInventory(inv);
		for (int i = 0; i < c_inv.length; i++) {
			if (c_inv[i] != null && c_inv[i].getItem().itemID == itemID) {
				if (newStackSize > 0) {
					c_inv[i].stackSize = newStackSize;
				} else {
					c_inv[i] = null;
				}
				break;
			}
		}
	}
}
