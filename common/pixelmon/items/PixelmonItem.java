package pixelmon.items;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PixelmonItem extends Item {

	private boolean isUsableInBattle = false;
	private boolean isUsableOutsideBattle = false;
	protected boolean isEquippable = false;
	
	public PixelmonItem(int par1) {
		super(par1);
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}

	protected void SetUsableInBattle(boolean isUsable) {
		isUsableInBattle = isUsable;
	}

	public boolean isUsableInBattle() {
		return isUsableInBattle;
	}

	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {	}
	
	public boolean isUsableOutSideBattle(){
		return isUsableOutsideBattle;
	}
	
	public void useFromInventory(EntityPixelmon user){ }
	
	public boolean isEquippable(){
		return isEquippable;
	}
	
	public void removeFromInventory(ItemStack[] inv){
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != null && inv[i].getItem().shiftedIndex == shiftedIndex){
				inv[i].stackSize--;
				if (inv[i].stackSize < 1){
					inv[i] = null;
				}
				break;
			}
		}
	}
}
