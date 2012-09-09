package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayerMP;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumStatusAilmentHealers;
import pixelmon.storage.PixelmonStorage;

public class ItemStatusAilmentHealer extends PixelmonItem {
	public EnumStatusAilmentHealers type;

	public ItemStatusAilmentHealer(int par1, EnumStatusAilmentHealers type) {
		super(par1);
		SetUsableInBattle(true);
		this.type = type;
		setMaxStackSize(16);
		setIconIndex(type.getIndex());
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		for(String s: this.type.statusesHealed()){
			userPokemon.removeStatus(s);
		}
		if (this.type.healsHP()){
			userPokemon.setEntityHealth(userPokemon.stats.HP);
		}
	}

}
