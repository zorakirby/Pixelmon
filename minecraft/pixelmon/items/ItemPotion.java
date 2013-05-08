package pixelmon.items;

import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPotions;
import pixelmon.storage.PixelmonStorage;

public class ItemPotion extends PixelmonItem {
	public EnumPotions type;

	public ItemPotion(int par1, EnumPotions type, String itemName) {
		super(par1, "healingitems/" + type.getTexture(), itemName);
		SetUsableInBattle(true);
		this.type = type;
		setMaxStackSize(16);
		setCreativeTab(PixelmonCreativeTabs.restoration);
	}

	private int healAmount(EntityPixelmon userPokemon) {
		if (this.type.getHealAmount() != 0) {
			return this.type.getHealAmount();
		} else if (this.type.getHealPercent() != 0) {
			return (int) Math.ceil(((double)userPokemon.stats.HP) * this.type.getHealPercent() / 100);
		} else {
			return 0;
		}
	}

	public void healPokemon(EntityPixelmon pxm) {
		int newHP = pxm.getHealth() + healAmount(pxm);
		pxm.setEntityHealth(newHP);
		if (pxm.getOwner() != null)
			pxm.updateNBT();
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		healPokemon(userPokemon);
	}

}
