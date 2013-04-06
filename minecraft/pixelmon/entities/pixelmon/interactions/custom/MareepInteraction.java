package pixelmon.entities.pixelmon.interactions.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.client.models.pokemon.ModelMareep;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class MareepInteraction extends PixelmonInteraction {

	public MareepInteraction(EntityPixelmon pixelmon, boolean isFirstInteraction) {
		super(pixelmon, 1, isFirstInteraction);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (getNumInteractions() == 0)
			return false;

		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

		if (var2 != null && var2.itemID == Item.shears.itemID) {
			par1EntityPlayer.dropPlayerItem(new ItemStack(Block.cloth, par1EntityPlayer.getRNG().nextInt(2) + 1));

			setNumInteractions(getNumInteractions() - 1);
			count=0;

			return true;
		} else {
			return false;
		}
	}

}
