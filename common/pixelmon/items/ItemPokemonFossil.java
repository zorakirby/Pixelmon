package pixelmon.items;

import java.util.List;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;

public class ItemPokemonFossil extends PixelmonItem{

	public String pokemon = "";
	
	public ItemPokemonFossil(int Id, String pokemon) {
		super(Id);
		this.setCreativeTab(PixelmonCreativeTabs.natural);
		this.pokemon = pokemon;
	}
	
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	par3List.add(pokemon);
    }
	
	public String getPokemon(){
		return this.pokemon;
	}
	
}
