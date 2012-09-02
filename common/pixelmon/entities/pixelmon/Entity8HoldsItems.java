package pixelmon.entities.pixelmon;

import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity8HoldsItems extends Entity7HasAI {
	public ItemStack heldItem;

	public Entity8HoldsItems(World par1World) {
		super(par1World);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("HeldItem", heldItem.itemID);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		
	}
}
