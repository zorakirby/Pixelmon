package pixelmon.items;

import java.util.Random;

import pixelmon.enums.EnumEvolutionStone;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemEvolutionStone extends Item{
	
	private EnumEvolutionStone stoneType;
	
	public ItemEvolutionStone(int id, EnumEvolutionStone stoneType, int i){
		super(id);
		this.stoneType = stoneType;
		this.iconIndex = 8 + i*16;
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}
	
	public EnumEvolutionStone getType(){
		return stoneType;
	}


}
