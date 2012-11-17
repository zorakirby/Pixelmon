package pixelmon.items;

import pixelmon.Pixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PokeballManager;
import net.minecraft.src.*;

public class ItemPokedex extends Item
{

	public ItemPokedex(int par1) 
	{
		super(par1);
		setIconIndex(22);
		setTextureFile("/pixelmon/image/pitems.png");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	public ItemStack onItemRightClick(ItemStack i, World world, EntityPlayer player)
	{
		PokeballManager pm = PixelmonStorage.PokeballManager;
		EntityPlayerMP e = pm.getPlayerFromName(player.username);
		pm.getPlayerStorage(e).pokedex.sendToPlayer(e);
		player.openGui(Pixelmon.instance, EnumGui.Pokedex.getIndex(), world, 0, 0, 0);
		return i;
	}
	
	public EnumRarity getRarity(ItemStack i)
	{
		return EnumRarity.rare;
	}
}