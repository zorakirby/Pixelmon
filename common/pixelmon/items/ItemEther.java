package pixelmon.items;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayerMP;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumEthers;
import pixelmon.storage.PixelmonStorage;

public class ItemEther extends PixelmonItem {
	public EnumEthers type;

	public ItemEther(int par1, EnumEthers type) {
		super(par1);
		SetUsableInBattle(true);
		this.type = type;
		setMaxStackSize(16);
		setIconIndex(type.getIndex());
	}

	private void restorePP(EntityPixelmon userPokemon, int moveIndex) {
		Attack m = userPokemon.moveset.get(moveIndex);
		if (this.type.restoresAllPP()) {
			m.pp = m.ppBase;
		} else {
			m.pp += 10;
			if ( m.pp > m.ppBase )
				m.pp = m.ppBase;
		}
	}

	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon, int selectedMove) {
		if (this.type.restoresAllMoves()){
			for (int a = 0; a < userPokemon.moveset.size(); a++) {
				 restorePP(userPokemon, a);
			}
		} else {
			restorePP(userPokemon, selectedMove);
		}
	}

}
