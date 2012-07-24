package pixelmon.items;

import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumPokeballs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemEmptyPokeBall extends Item {
	private EnumPokeballs type;	

	public ItemEmptyPokeBall(int i, EnumPokeballs type) {
		super(i);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		this.type = type;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
			itemstack.stackSize--;
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote){
				world.spawnEntityInWorld(new EntityPokeBall(world, entityplayer, type));
		}
		return itemstack;
	}
}
