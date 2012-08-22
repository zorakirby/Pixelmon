package pixelmon.items;

import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class PixelmonItem extends Item {

	private boolean isUsableInBattle = false;

	public PixelmonItem(int par1) {
		super(par1);
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}

	protected void SetUsableInBattle(boolean isUsable) {
		isUsableInBattle = isUsable;
	}

	public boolean isUsableInBattle() {
		return false;
	}

	public void useFromBag(PixelmonEntityHelper userPokemon, PixelmonEntityHelper targetPokemon) {	}
}
