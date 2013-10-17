package pixelmon.items;

import java.util.TreeMap;

import com.google.common.collect.HashMultimap;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;

public abstract class ArmorToolLibrary {
	
	public static HashMultimap<EnumToolMaterial, ItemTool> TOOLS = HashMultimap.create();
	public static HashMultimap<EnumArmorMaterial, ItemArmor> ARMOR = HashMultimap.create();
	
	/**
	 * do this after all other installed mods have registered their items.
	 */
	public static void init(){
		for(Item item : Item.itemsList){
			if(item == null)
				continue;
			else if(item instanceof ItemTool){
				ItemTool tool = (ItemTool) item;
				TOOLS.put(EnumToolMaterial.valueOf(tool.getToolMaterialName()), tool);
			}
			else if(item instanceof ItemArmor){
				ItemArmor armor = (ItemArmor) item;
				ARMOR.put(armor.getArmorMaterial(), armor);
			}
		}
	}
	
	public static boolean isStandard(EnumArmorMaterial mat){
		switch(mat){
		case CLOTH:
		case DIAMOND:
		case GOLD:
		case IRON: return true;
		case CHAIN:
		default: return false;
		}
	}
	
	public static boolean isStandard(EnumToolMaterial mat){
		switch(mat){
		case EMERALD:
		case GOLD:
		case IRON:
		case STONE:
		case WOOD: return true;
		default: return false;
		}
	}
	
}
