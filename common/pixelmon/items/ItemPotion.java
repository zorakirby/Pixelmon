package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayerMP;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPotions;
import pixelmon.storage.PixelmonStorage;

public class ItemPotion extends PixelmonItem {
	private EnumPotions type;

	public ItemPotion(int par1, EnumPotions type) {
		super(par1);
		SetUsableInBattle(true);
		this.type = type;
		setMaxStackSize(16);
		setIconIndex(type.getIndex());
	}
	
	private int healAmount(EntityPixelmon userPokemon) {
		if (this.type.getHealAmount() != 0) {
			return this.type.getHealAmount();
		} else if (this.type.getHealPercent() != 0) {
			return (int) Math.ceil(userPokemon.stats.HP * this.type.getHealPercent() / 100);
		} else {
			return 0;	
		}
	}
	
	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		int newHP = userPokemon.getHealth() + healAmount(userPokemon);
		if (newHP >= userPokemon.stats.HP) {
			userPokemon.setEntityHealth(userPokemon.stats.HP);
		} else {
			userPokemon.setEntityHealth(newHP);
		}
		if (userPokemon.getOwner()!=null)
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)userPokemon.getOwner()).updateNBT(userPokemon);
	}
	
}
