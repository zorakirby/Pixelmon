package pixelmon.items;

import pixelmon.battles.attacks.Attack;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumEthers;

public class ItemEther extends PixelmonItem {
	public EnumEthers type;

	public ItemEther(int par1, EnumEthers type, String itemName) {
		super(par1, "healingitems/" + type.getTexture(), itemName);
		SetUsableInBattle(true);
		this.type = type;
		setMaxStackSize(16);
		setCreativeTab(PixelmonCreativeTabs.restoration);
	}

	private void restorePP(EntityPixelmon userPokemon, int moveIndex) {
		Attack m = userPokemon.getMoveset().get(moveIndex);
		if (this.type.restoresAllPP()) {
			m.pp = m.ppBase;
		} else {
			m.pp += 10;
			if ( m.pp > m.ppBase )
				m.pp = m.ppBase;
		}
	}

	public void restoreAllMoves(EntityPixelmon pxm) {
		for (int a = 0; a < pxm.getMoveset().size(); a++) {
			 restorePP(pxm, a);
		}
	}
	
	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon, int selectedMove) {
		if (this.type.restoresAllMoves()){
			restoreAllMoves(userPokemon);
		} else {
			restorePP(userPokemon, selectedMove);
		}
	}

}
