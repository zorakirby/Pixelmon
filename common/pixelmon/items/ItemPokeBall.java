package pixelmon.items;

import java.util.HashMap;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumPokeballs;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemPokeBall extends PixelmonItem {
	private EnumPokeballs type;
	
	public static int ballTimer = 0;

	public static HashMap<EntityPlayer, Integer> playerTimers;

	public ItemPokeBall(int i, EnumPokeballs type) {
		super(i);
		SetUsableInBattle(false); // update this when the effect is properly added
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		this.type = type;
		playerTimers = new HashMap<EntityPlayer, Integer>();
		setIconIndex(type.getIconIndex());
		setTextureFile("/pixelmon/image/pitems.png");
		setTabToDisplayOn(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		
		if(ballTimer > 0)
		{
			return itemstack;
		}
		if(!world.isRemote)
		{
			ballTimer = 40;
		}
		
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
	public void useFromBag(PixelmonEntityHelper userPokemon, PixelmonEntityHelper targetPokemon) {
		ChatHandler.sendChat(userPokemon.getOwner(), "Using pokeballs in battle is not implemented yet");
		//EntityPokeBall p = new EntityPokeBall(userPokemon.getEntity().worldObj, userPokemon.getOwner(), type, !userPokemon.getOwner().capabilities.isCreativeMode);
		
	}
}
