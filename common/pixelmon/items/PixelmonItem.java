package pixelmon.items;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

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
}
