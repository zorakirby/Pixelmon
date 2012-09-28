package pixelmon.items;

import java.util.Random;

import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumEvolutionStone;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemEvolutionStone extends Item {

	private EnumEvolutionStone stoneType;

	public ItemEvolutionStone(int id, EnumEvolutionStone stoneType, int i) {
		super(id);
		this.stoneType = stoneType;
		this.iconIndex = 8 + i * 16;
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
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
