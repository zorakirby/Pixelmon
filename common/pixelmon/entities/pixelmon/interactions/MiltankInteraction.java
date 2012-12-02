package pixelmon.entities.pixelmon.interactions;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import pixelmon.entities.pixelmon.Entity8HoldsItems;

public class MiltankInteraction extends PixelmonInteraction {

	public MiltankInteraction(Entity8HoldsItems pixelmon) {
		super(pixelmon);
		// TODO Auto-generated constructor stub
	}

	 public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.bucketEmpty.shiftedIndex)
        {
            if (--var2.stackSize <= 0)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk));
            }
            else if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketMilk)))
            {
                par1EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketMilk.shiftedIndex, 1, 0));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

}
