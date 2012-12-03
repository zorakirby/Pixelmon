package pixelmon.items;

import java.util.List;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelBase;
import pixelmon.Pixelmon;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.models.fossils.ModelFossil;

public class ItemPokemonFossil extends PixelmonItem {

	public String pokemon = "";
	public ModelFossil model;
	private String modelName;

	public ItemPokemonFossil(int Id, String pokemon, String modelName) {
		super(Id);
		this.setCreativeTab(PixelmonCreativeTabs.natural);
		this.pokemon = pokemon;
		this.modelName = modelName;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(pokemon);
	}

	public String getPokemon() {
		return this.pokemon;
	}
	
	public String getModelName(){
		return this.modelName;
	}

	public ModelFossil getModel() {
		if(model == null)
			return Pixelmon.proxy.loadFossilModel(modelName);
		return model;
	}
}
