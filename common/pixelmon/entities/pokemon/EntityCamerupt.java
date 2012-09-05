package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class EntityCamerupt extends EntityPixelmon {

	public EntityCamerupt(World world) {
		super(world);
		init();
	}

	public void init() {
		super.init("Camerupt");
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		if (var2 != null && var2.itemID == Item.bucketEmpty.shiftedIndex) {
			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketLava));
			return true;
		} else {
			return super.interact(par1EntityPlayer);
		}
	}
}
