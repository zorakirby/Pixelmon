package pixelmon.items;

import pixelmon.battles.status.StatusType;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumStatusAilmentHealers;

public class ItemStatusAilmentHealer extends PixelmonItem {
	public EnumStatusAilmentHealers type;

	public ItemStatusAilmentHealer(int par1, EnumStatusAilmentHealers type, String itemName) {
		super(par1, "healingitems/" + getTextureName(itemName), itemName);
		SetUsableInBattle(true);
		this.type = type;
		setMaxStackSize(16);
		setCreativeTab(PixelmonCreativeTabs.restoration);
	}

	private static String getTextureName(String itemName) {
		String texName = "";
		for (int i = 0; i < itemName.length(); i++)
			if (itemName.charAt(i) != ' ')
				texName += itemName.charAt(i);

		return texName.toLowerCase();
	}

	public boolean healPokemon(EntityPixelmon pxm) {
		boolean healedAilment = false;
		for (StatusType s : this.type.statusesHealed()) {
			if (pxm.removeStatus(s)) {
				healedAilment = true;
			}
		}
		return healedAilment;
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		for (StatusType s : this.type.statusesHealed()) {
			userPokemon.removeStatus(s);
		}
		if (this.type.healsHP()) {
			userPokemon.setEntityHealth(userPokemon.stats.HP);
		}
	}

}
