package pixelmon.entities.pixelmon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.entities.pixelmon.helpers.DropItemHelper;
import pixelmon.entities.pixelmon.interactions.custom.PixelmonInteraction;

public abstract class Entity8HoldsItems extends Entity7HasAI {
	public ItemStack heldItem;
	private DropItemHelper dropItemHelper = new DropItemHelper(this);
	private PixelmonInteraction interaction;
	public int numInteractions;

	public Entity8HoldsItems(World par1World) {
		super(par1World);
		dataWatcher.addObject(24, (short) 0);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		if (!worldObj.isRemote) {
			if (interaction == null)
				interaction = PixelmonInteraction.getInteraction(this, true);
			else
				interaction = PixelmonInteraction.getInteraction(this, false);
		}
	}

	public int getNumInteractions() {
		return dataWatcher.getWatchableObjectShort(24);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		if (interaction != null && player instanceof EntityPlayerMP)
			if (interaction.interact(player))
				return true;
		return super.interact(player);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (interaction != null)
			interaction.tick();
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
		if (interaction != null)
			interaction.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("HeldItem")) {
			int itemId = nbt.getInteger("HeldItem");
			if (itemId != -1) {
				heldItem = new ItemStack(PixelmonItemsHeld.getHeldItem(itemId));
			}
		}
		if (interaction != null)
			interaction.readEntityFromNBT(nbt);

	}

	@Override
	protected int getDropItemId() {
		return dropItemHelper.getDropItemID();
	}
}
