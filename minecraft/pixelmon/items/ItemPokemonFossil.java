package pixelmon.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import pixelmon.Pixelmon;
import pixelmon.client.models.fossils.ModelFossil;
import pixelmon.config.PixelmonCreativeTabs;

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
