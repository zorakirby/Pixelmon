package pixelmon.entities.pixelmon;

import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.helpers.DropItemHelper;
import pixelmon.entities.pixelmon.interactions.PixelmonInteraction;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity8HoldsItems extends Entity7HasAI {
	public ItemStack heldItem;
	private DropItemHelper dropItemHelper = new DropItemHelper(this);
	private PixelmonInteraction interaction;

	public Entity8HoldsItems(World par1World) {
		super(par1World);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		interaction = PixelmonInteraction.getInteraction(this);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (interaction != null)
			if (interaction.interact(par1EntityPlayer))
				return true;
		return super.interact(par1EntityPlayer);
	}

	public void setHeldItem(ItemStack var1) {
		heldItem = var1;
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		if (heldItem != null)
			nbt.setInteger("HeldItem", heldItem.itemID);
		else
			nbt.setInteger("HeldItem", -1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("HeldItem")) {
			int itemId = nbt.getInteger("HeldItem");
			if (itemId != -1) {
				heldItem = new ItemStack(PixelmonItems.getHeldItem(itemId));
			}
		}

	}

	@Override
	protected int getDropItemId() {
		return dropItemHelper.getDropItemID();
	}
}
