package pixelmon.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumEvolutionStone;

public class ItemEvolutionStone extends Item {

	private EnumEvolutionStone stoneType;

	public ItemEvolutionStone(int id, EnumEvolutionStone stoneType, int i) {
		super(id);
		this.stoneType = stoneType;
		this.iconIndex = 4 + i * 16;
		setTextureFile("/pixelmon/image/pitems.png");
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
