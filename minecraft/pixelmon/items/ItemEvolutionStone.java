package pixelmon.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumEvolutionStone;

public class ItemEvolutionStone extends PixelmonItem {

	private EnumEvolutionStone stoneType;
	

	public ItemEvolutionStone(int id, EnumEvolutionStone stoneType, String itemName) {
		super(id, "evolutionstones/" + stoneType.toString().toLowerCase(), itemName);
		this.stoneType = stoneType;
		setCreativeTab(CreativeTabs.tabMisc);
	}

	public EnumEvolutionStone getType() {
		return stoneType;
	}

	public boolean useOnEntity(ItemStack itemstack, EntityPixelmon pixelmon, EntityPlayer player) {
		ItemEvolutionStone i = (ItemEvolutionStone) itemstack.getItem();
		for (EvolutionInfo e : DatabaseStats.getEvolveList(pixelmon.getName())) {
			if (e.mode == InfoMode.stone) {
				if (e.evolutionStone == i.getType()) {
					String evolveTo = e.pokemonName;
					if (evolveTo == null) {
						if (PixelmonConfig.printErrors)
							System.out.println(e.pokemonName + " isn't coded yet");
						return false;
					}
					pixelmon.evolve(evolveTo);
					itemstack.stackSize--;
					return true;
				}
			}
		}
		return false;
	}

}
