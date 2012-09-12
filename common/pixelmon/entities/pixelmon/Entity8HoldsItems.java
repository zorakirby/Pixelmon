package pixelmon.entities.pixelmon;

import pixelmon.entities.pixelmon.helpers.DropItemHelper;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity8HoldsItems extends Entity7HasAI {
	public ItemStack heldItem;
	public DropItemHelper dropItemHelper = new DropItemHelper(this);

	public Entity8HoldsItems(World par1World) {
		super(par1World);
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
		if (heldItem!=null)
			nbt.setInteger("HeldItem", heldItem.itemID);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);	
	}
	
	@Override
	protected int getDropItemId() {
		return dropItemHelper.getDropItemID();
	}
}
