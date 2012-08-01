package pixelmon.items;

import java.util.HashMap;

import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumPokeballs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemPokeBall extends Item {
	private EnumPokeballs type;

	public static HashMap<EntityPlayer, EntityPokeBall> playerPokeballs;

	public ItemPokeBall(int i, EnumPokeballs type) {
		super(i);
		maxStackSize = 64;
		setMaxDamage(0xf4240);
		this.type = type;
		playerPokeballs = new HashMap<EntityPlayer, EntityPokeBall>();
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (playerPokeballs.get(entityplayer) != null && !playerPokeballs.get(entityplayer).isDead)
			return itemstack;

		itemstack.stackSize--;
		world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			EntityPokeBall pokeball = new EntityPokeBall(world, entityplayer, type);
			playerPokeballs.put(entityplayer, pokeball);
			world.spawnEntityInWorld(pokeball);
		}
		return itemstack;
	}
}
