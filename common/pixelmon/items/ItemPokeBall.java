package pixelmon.items;

import java.util.HashMap;

import pixelmon.battles.BattleRegistry;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumPokeballs;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemPokeBall extends PixelmonItem {
	public EnumPokeballs type;
	
	public static HashMap<EntityPlayer, Integer> playerTimers;

	public ItemPokeBall(int i, EnumPokeballs type) {
		super(i);
		SetUsableInBattle(true);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		this.type = type;
		playerTimers = new HashMap<EntityPlayer, Integer>();
		setIconIndex(type.getIconIndex());
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if(!entityplayer.capabilities.isCreativeMode)
		{
			--itemstack.stackSize;
		}
		
		world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityPokeBall(world, entityplayer, type, !entityplayer.capabilities.isCreativeMode));
		}
		
		return itemstack;
	}
	
	@Override
	public void useFromBag(EntityPixelmon userPokemon, EntityPixelmon targetPokemon) {
		EntityPokeBall p = new EntityPokeBall(userPokemon.worldObj, userPokemon.getOwner(), targetPokemon, type, BattleRegistry.getBattle((EntityPlayer)userPokemon.getOwner()));
		userPokemon.getOwner().worldObj.spawnEntityInWorld(p);
		//EntityPokeBall p = new EntityPokeBall(userPokemon.getEntity().worldObj, userPokemon.getOwner(), type, !userPokemon.getOwner().capabilities.isCreativeMode);
		
	}
}
